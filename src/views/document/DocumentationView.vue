<template>
  <div class="ad-container">
    <div class="ad-card" style="padding:10px 40px;">
      <h2 class="heading-text">{{ application.applicationName }}</h2>
      <p>{{ application.applicationDescription }}</p>
      <h3>Endpoints:</h3>
      <div class="view-card" v-for="endpoint in sortedEndpoints">
        <div class="view-card-color" />
        <p v-if="endpoint.endpointName">{{ endpoint.endpointName }}</p>
        <p class="endpointLocation">{{ endpoint.endpointLocation }}</p>
        <p v-if="endpoint.endpointVersion">Version {{ endpoint.endpointVersion }}</p>
        <div style="height:10px;" />
        <div class="view-kv-split" v-if="endpoint.endpointDescription">
          <div class="left-split">
            <p>Description</p>
          </div>
          <div class="right-split">
            <pre v-if="endpoint.endpointDescription">{{ endpoint.endpointDescription }}</pre>
          </div>
        </div>
        <div class="view-kv-split" v-if="endpoint.endpointAuthRequired">
          <div class="left-split">
            <p>Authentication</p>
          </div>
          <div class="right-split">
            <p v-if="endpoint.endpointAuthRequired === 1" style="color:green">Required</p>
            <p v-else style="color:red">Not Required</p>
          </div>
        </div>
        <div class="view-kv-split">
          <div class="left-split">
            <p>URL</p>
          </div>
          <div class="right-split">
            <div class="codeBlock">
              <p style="transform:translateY(10px);">localhost:{{ endpoint.endpointPort }}{{ endpoint.endpointLocation }}</p>
            </div>
          </div>
        </div>
        <div class="view-kv-split" v-if="endpoint.endpointMethod">
          <div class="left-split">
            <p>Method</p>
          </div>
          <div class="right-split">
            <p class="font-weight-light">{{ endpoint.endpointMethod }}</p>
          </div>
        </div>
        <div class="view-kv-split" v-if="endpoint.endpointParameters.length > 0">
          <div class="left-split">
            <p>Parameters</p>
          </div>
          <div class="right-split">
            <SortedParameters :parameters="endpoint.endpointParameters" />
          </div>
        </div>
        <div class="view-kv-split" v-if="endpoint.endpointExampleBody">
          <div class="left-split">
            <p>Example Body</p>
          </div>
          <div class="right-split">
            <div class="codeBlock">
              <pre>{{ endpoint.endpointExampleBody }}</pre>
            </div>
          </div>
        </div>
        <div class="view-kv-split" v-if="endpoint.endpointExpectation">
          <div class="left-split">
            <p>Expected Reponse</p>
          </div>
          <div class="right-split">
            <div class="codeBlock">
              <pre>{{ endpoint.endpointExpectation }}</pre>
            </div>
          </div>
        </div>
        <div class="view-kv-split">
          <div class="left-split">
            <p>Notes</p>
          </div>
          <div class="right-split">
              <pre>{{ endpoint.endpointNotes}}</pre>
          </div>
        </div>
        <div style="height:30px;" />
      </div>
    </div>
  </div>
</template>

<script>
import SortedParameters from '@/components/parameters/SortedParameters';
export default {
  data:() => ({
    application: {},
    endpoints: [],
  }),
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
  },
  computed: {
    sortedEndpoints: function(){
      function compare(a, b) {
        if (a.id < b.id)
          return -1;
        if (a.id > b.id)
          return 1;
        return 0;
      }
      return this.endpoints.sort(compare);
    },
  },
  components: {
    SortedParameters,
  }
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
.view-card {
  position: relative;
  background: rgba(255,255,255,1);
  box-sizing: border-box;
  padding: 8px;
  padding-left: 40px;
  padding-right: 40px;
  margin-bottom: 30px;
  border-radius: 0px 50px 50px 0px;
  box-shadow: inset 0px 0px 10px rgba(0,0,0,0.15);
}
.view-card-color {
  position: absolute;
  height: 100%;
  width: 10px;
  top: 0;
  left: 0;
  background: black;
}
.view-card p {
  font-size:24px;
}
.view-kv-split {
  position: relative;
  min-height: 50px;
  width: 100%;
  margin-bottom: 15px;
  display: grid;
  grid-template-columns: 150px auto;
  grid-template-rows: auto;
  grid-gap: 25px;
}
.endpointLocation {
  font-weight: bold;
}
.left-split p{
  text-align: right;
  font-size: 18px;
  color: rgba(0,0,0,0.50);
}
.right-split p {
  font-size: 18px;
  font-weight: auto;
}
</style>