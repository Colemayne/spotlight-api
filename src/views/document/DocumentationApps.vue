<template>
  <div class="ad-container">
    <div class="ad-card" style="padding:10px 40px;">
      <h2 class="heading-text">{{ team.groupName }}</h2>
      <div class="grid-table">
        <button class="ad-button" v-for="application in applications" @click="chooseApplication(application.id)">{{ application.applicationName }}</button>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  data:() => ({
    team: {},
    applications: [],
  }),
  created() {
    this.getData();
  },
  methods: {
    getData: function() {
      let gid = this.$route.params.team;
      // Group Info
      fetch('/api/group/v1/select/'+gid,{
        method: 'GET'
      }).then(res => res.json())
      .then(data => {
        this.team = data[0];
        console.log(data);
      });
      // Application Info
      fetch('/api/application/v1/select/group/'+gid,{
        method: 'GET'
      }).then(res => res.json())
      .then(data => {
        console.log(data);
        this.applications = data;
      });
    },
    chooseApplication: function(id) {
      this.$router.push('/documentation/'+this.$route.params.team+'/'+id);
    }
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