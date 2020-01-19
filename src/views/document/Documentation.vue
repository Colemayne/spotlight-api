<template>
  <div class="ad-container">
    <div class="ad-card" style="padding:10px 40px;">
      <h2 class="heading-text">Documentation</h2>
      <p>Please select a team to view documentation for.</p>
      <div class="grid-table">
        <button class="ad-button" v-for="group in groups" @click="chooseTeam(group.id)">{{ group.groupName }}</button>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  data:() => ({
    groups: [],
  }),
  created() {
    this.getData();
  },
  methods: {
    getData: function() {
      fetch('/api/group/v1/select',{
        method: 'GET'
      }).then(res => res.json())
      .then(data => {
        this.groups = data;
        console.log(data);
      });
    },
    chooseTeam: function(id) {
      this.$router.push('/documentation/'+id);
    },
  },
}
</script>

<style>
.grid-table {
  position: relative;
  min-height: 100px;
  width: 100%;
  display: grid;
  grid-template-columns: auto;
  grid-template-rows: repeat(150px);
  grid-gap: 15px;
}
</style>