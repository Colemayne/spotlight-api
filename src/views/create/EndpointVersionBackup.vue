<template>
  <div class="ad-container">
    <div class="ad-card" style="padding:10px 40px;">
      <h2 class="heading-text">{{ endpoint.endpointName }}</h2>
      <p>{{ endpoint.endpointDescription }}</p>
      <div class="create-button">
        <button class="ad-button" @click="newVersion=true">Create Version</button>
      </div>
      <div style="height:30px"></div>
      <span v-for="(version, index) in versions">
        <button class="radio-large" :class="currentVersion.endpointVersion == version ? 'radio-large-selected':''" @click="chooseVersion(version)">{{ version }}</button>
        <div class="spacer1" v-if="index != versions.length - 1"></div>
      </span>
      <span v-if="currentVersion">
        <p><span style="font-weight:bold">Version:</span> {{ currentVersion.endpointVersion }}</p>
        <div class="edit-grid">
        <span style="font-weight:bold">Description:</span>
        <input class="ad-input" style="height:45px;" v-model="currentVersion.endpointDescription"/>
        <span style="font-weight:bold">Address:</span>
        <input class="ad-input" style="height:45px;" v-model="currentVersion.endpointLocation"/>
        <span style="font-weight:bold">Port:</span>
        <input class="ad-input" style="height:45px;" v-model="currentVersion.endpointPort"/>
        <span style="font-weight:bold">Method:</span>
        <input class="ad-input" style="height:45px;" v-model="currentVersion.endpointMethod"/>
        </div>
        <pre>{{ currentVersion }}</pre>
      </span>
    </div>
    <!-- Popovers -->
    <NewEndpoint v-if="newVersion" :close="closePopover" :addVersion="createVersion"/>
  </div>
</template>

<script>
import NewEndpoint from '@/components/popovers/NewEndpoint';
export default {
  data: () => ({
    endpoint: {},
    newVersion: false,
    versions: [],
    currentVersion: {},
  }),
  components: {
    NewEndpoint,
  },
  created() {
    this.getData();
  },
  methods: {
    getData: function() {
      let gid = this.$route.params.endpoint;
      // Application Info
      fetch('/api/endpoint/v1/select/'+gid,{
        method: 'GET'
      }).then(res => res.json())
      .then(data => {
        this.endpoint = data;
        for(let i = 0; i < data.endpointVersions.length; i++){
          this.versions.push(data.endpointVersions[i].endpointVersion);
        }
        this.currentVersion = data.endpointVersions[0];

      });
    },
    closePopover: function() {
      this.newEndpoint = false;
    },
    createVersion: function(endpoint) {
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
    chooseVersion: function(version) {
      for(let i = 0; i < this.endpoint.endpointVersions.length; i++) {
        if(this.endpoint.endpointVersions[i].endpointVersion === version) {
          this.currentVersion = this.endpoint.endpointVersions[i];
        }
      }
    }
  },

}
</script>

<style>
.edit-grid {
  position: relative;
  min-height: 250px;
  width: 100%;
  box-sizing: border-box;
  padding: 5px;
  display: grid;
  grid-template-columns: 125px auto;
  grid-template-rows: repeat(55px);
  grid-gap: 15px;
  text-align: left;
  font-weight: bold;
  background: rgba(0,255,0,0.02);
}
.edit-grid span {
  padding-top: 10px;
}
</style>