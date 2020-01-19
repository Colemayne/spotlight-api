<template>
  <div class="ad-container">
    <div class="ad-card" style="padding:10px 40px;">
      <h2 class="heading-text">{{ team.groupName }}</h2>
      <div class="create-button">
        <button class="ad-button" @click="newApplication=true">Create Application</button>
      </div>
      <div style="height:60px"></div>
      <div class="app-grid">
        <button class="ad-button" style="height:100px;" v-for="application in applications" @click="chooseApplication(application.id)">
          <p>{{application.applicationName}}</p>
        </button>
      </div>
    </div>
    <!-- Popovers -->
    <NewApplication v-if="newApplication" :close="closePopover" :addApplication="createApplication"/>
  </div>
</template>

<script>
import NewApplication from '@/components/popovers/NewApplication';
export default {
  data: () => ({
    team: {},
    applications: [],
    newApplication: false,
  }),
  components: {
    NewApplication,
  },
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
    closePopover: function() {
      this.newApplication = false;
    },
    createApplication: function(application) {
      application.groupId = this.team.id;
      fetch('/api/application/v1/add', {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json'
        },
        body: JSON.stringify(application)
      }).then(res => res.json())
      .then(data => {
        this.applications.push(data);
      });
      this.closePopover();
    },
    chooseApplication: function(id) {
      let gid = this.$route.params.team
      this.$router.push('/edit/'+gid+'/'+id);
    }
  },

}
</script>

<style>
.app-grid {
  position: relative;
  min-height: 250px;
  width: 100%;
  box-sizing: border-box;
  padding: 5px;
  display: grid;
  grid-template-columns: auto;
  grid-template-rows: repeat(100px);
  grid-gap: 15px;
  text-align: center;
  font-weight: bold;
}
</style>