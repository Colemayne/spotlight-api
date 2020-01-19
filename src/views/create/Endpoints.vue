<template>
  <div class="ad-container">
    <div class="ad-card" style="padding:10px 40px;">
      <h2 class="heading-text">{{ application.applicationName }}</h2>
      <p>{{ application.applicationDescription }}</p>
      <div class="create-button">
        <button class="ad-button" @click="newEndpoint=true">Create Endpoint</button>
      </div>
      <div style="height:30px"></div>
      <div class="app-grid">
        <button class="ad-button" style="height:100px;" v-for="endpoint in endpoints" @click="chooseEndpoint(endpoint.id)">
          <p>{{ endpoint.endpointName }}</p>
        </button>
      </div>
    </div>
    <!-- Popovers -->
    <NewEndpoint v-if="newEndpoint" :close="closePopover" :addEndpoint="createEndpoint"/>
  </div>
</template>

<script>
import NewEndpoint from '@/components/popovers/NewEndpoint';
export default {
  data: () => ({
    application: {},
    endpoints: [],
    newEndpoint: false,
  }),
  components: {
    NewEndpoint,
  },
  created() {
    this.getData();
  },
  methods: {
    getData: function() {
      let gid = this.$route.params.app;
      // Application Info
      fetch('/api/application/v1/select/'+gid,{
        method: 'GET'
      }).then(res => res.json())
      .then(data => {
        this.application = data[0];
        this.endpoints = data[0].endpoints;
      });
    },
    closePopover: function() {
      this.newEndpoint = false;
    },
    createEndpoint: function(endpoint) {
      endpoint.applicationId = this.application.id;
      fetch('/api/endpoint/v1/add', {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json'
        },
        body: JSON.stringify(endpoint)
      }).then(res => res.json())
      .then(data => {
        this.endpoints.push(data);
      });
      this.closePopover();
    },
    chooseEndpoint: function(id) {
      this.$router.push('/versions/'+id);
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