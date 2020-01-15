<template>
  <div class="ad-container">
    <div class="ad-card" style="padding:10px 40px;">
      <h2 class="heading-text">Select A Team.</h2>
      <div class="create-button">
        <button class="ad-button" @click="newGroup=true">Create Team</button>
      </div>
      <div style="height:30px"></div>
      <div class="team-grid">
        <button class="ad-button" v-for="group in groups" @click="chooseGroup(group.id)">
          <p>{{group.groupName}}</p>
        </button>
      </div>
    </div>
    <!-- POPOVERS -->
    <NewTeam v-if="newGroup" :close="closePopover" :addTeam="createTeam"/>
  </div>
</template>

<script>
import NewTeam from '@/components/popovers/NewTeam';
export default {
  data: () => ({
    groups: [],
    newGroup: false,
  }),
  components: {
    NewTeam,
  },
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
    closePopover: function() {
      this.newGroup = false;
    },
    createTeam: function(name) {
      console.log(name);
      fetch('/api/group/v1/add', {
        method: 'POST',
        body: name
      }).then(res => res.json())
      .then(data => {
        console.log(data);
      });
      this.newGroup = false;
    },
    chooseGroup: function(groupId) {
      this.$router.push('/edit/'+groupId);
    }
  },


}

</script>

<style>
.team-grid {
  position: relative;
  min-height: 250px;
  width: 100%;
  box-sizing: border-box;
  padding: 5px;
  display: grid;
  grid-template-columns: repeat(auto-fill, minmax(200px, 1fr));
  grid-template-rows: repeat(300px);
  grid-gap: 15px;
  text-align: center;
  font-weight: bold;
}
.create-button {
  position: absolute;
  height: 60px;
  width: 200px;
  top: 15px;
  right: 15px;
}
</style>