<template>
  <div class="ad-container">
    <div class="ad-card" style="padding:10px 40px;">
      <div style="height:30px"></div>
      <div class="grid-title">
        <h3 class="title-text">Endpoint:</h3>
        <button class="ad-button" @click="saveEndpoint"><v-icon>mdi-content-save</v-icon></button>
      </div>
      <div style="height: 50px;width:100%;margin-bottom:30px;">
        <input class="ad-input"
               v-model="endpoint.endpointName"
               placeholder="Name" />
      </div>

      <div style="min-height:50px;width:100%;margin-bottom:30px;">
        <textarea class="ad-input-textarea"
               v-model="endpoint.endpointDescription"
               type="text"
               placeholder="Purpose of the endpoint" />
      </div>
      <div class="grid-address">
        <v-select solo
                  :items="methods"
                  v-model="endpoint.endpointMethod"
                  label="Method">
        </v-select>
        <input class="ad-input"
               v-model="endpoint.endpointPort"
               placeholder="Port"
               type="number"/>
        <button class="ad-button" style="height:50px;" v-if="endpoint.endpointAuthRequired === 1" @click="endpoint.endpointAuthRequired = 0"><v-icon color="success" size="20">mdi-lock</v-icon> Auth</button>
        <button class="ad-button" style="height:50px;" v-else @click="endpoint.endpointAuthRequired = 1"><v-icon color="error" size="20">mdi-lock-open</v-icon> No Auth</button>
        <input class="ad-input"
               v-model="endpoint.endpointLocation"
               placeholder="Endpoint"/>
      </div>
      <div class="grid-title">
        <h3 class="title-text">Parameters:</h3>
        <button class="ad-button" style="height:50px;" @click="addParameter"><v-icon>mdi-plus</v-icon></button>
      </div>
      <div style="height:15px;width:100%;" />
      <div class="grid-kvd" v-if="endpoint.endpointParameters.length > 0" v-for="(parameter,index) in sortedParameters">
        <input class="ad-input" v-model="parameter.parameterKey" placeholder="Key" />
        <input class="ad-input" v-model="parameter.parameterType" placeholder="Type" />
        <button class="ad-button" style="height:50px;" v-if="parameter.parameterRequired === 1" @click="parameter.parameterRequired = 0"><v-icon color="success" size="30">mdi-check</v-icon> Required</button>
        <button class="ad-button" style="height:50px;" v-else @click="parameter.parameterRequired = 1"><v-icon color="error" size="30">mdi-crop-square</v-icon> Optional</button>
        <input class="ad-input" v-model="parameter.parameterDescription" placeholder="Description" />
        <button class="ad-button" style="height:50px;" @click="deleteParameter(parameter,index)"><v-icon>mdi-delete</v-icon></button>
      </div>
      <div class="grid-title">
        <h3 class="title-text">Example Body:</h3>
      </div>
      <div style="min-height:150px;width:100%;margin-bottom:30px;">
        <textarea class="ad-input-textarea"
               v-model="endpoint.endpointExampleBody"
               type="text"
               placeholder="Example of what might be sent with a http body." />
      </div>
      <div class="grid-title">
        <h3 class="title-text">Expected Response:</h3>
      </div>
      <div style="min-height:150px;width:100%;margin-bottom:30px;">
        <textarea class="ad-input-textarea"
               v-model="endpoint.endpointExpectation"
               type="text"
               placeholder="Expected response of the endpoint" />
      </div>
      <div class="grid-title">
        <h3 class="title-text">Notes:</h3>
      </div>
      <div style="min-height:150px;width:100%;margin-bottom:30px;">
        <textarea class="ad-input-textarea"
               v-model="endpoint.endpointNotes"
               type="text"
               placeholder="Any information to better assist the user for this endpoint." />
      </div>
    </div>
  </div>
</template>

<script>
import NewEndpoint from '@/components/popovers/NewEndpoint';
export default {
  data: () => ({
    endpoint: {},
    methods: [
      "GET",
      "POST",
      "PUT",
      "DELETE",
      "UPDATE",
      "HEAD"
    ],
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
      fetch('/api/endpoint/v1/select/'+gid,{
        method: 'GET'
      }).then(res => res.json())
      .then(data => {
        this.endpoint = data;
      });
    },
    addParameter: function() {
      let obj = {
        pKey: "",
        pType: "",
        parameterRequired: 1,
      };
      this.endpoint.endpointParameters.push(obj);
    },
    deleteParameter: function(parameter,index) {
      if(parameter.id) {
        fetch('/api/endpoint/v1/delete/parameter', {
          method: 'POST',
          headers: {
            'Content-Type': 'application/json'
          },
          body: JSON.stringify(parameter)
        });
      }
      this.endpoint.endpointParameters.splice(index,1);
    },
    addHeader: function() {
      let obj = {
        hKey: "",
        hVal: "",
      };
      this.endpoint.endpointHeaders.push(obj);
    },
    deleteHeader: function(header,index) {
      if(header.id) {
        fetch('/api/endpoint/v1/delete/headers', {
          method: 'POST',
          headers: {
            'Content-Type': 'application/json'
          },
          body: JSON.stringify(header)
        });
      }
      this.endpoint.endpointHeaders.splice(index,1);
    },
    saveEndpoint: function() {
        fetch('/api/endpoint/v1/save', {
          method: 'POST',
          headers: {
            'Content-Type': 'application/json'
          },
          body: JSON.stringify(this.endpoint)
        }).then(res => res.json())
        .then(data => {
          console.log(data);
          this.endpoint = data;
        });
    }
  },
  computed: {
    sortedParameters: function(){
      function compare(a, b) {
        if (a.id < b.id)
          return -1;
        if (a.id > b.id)
          return 1;
        return 0;
      }
      if(this.endpoint.endpointParameters) {
        return this.endpoint.endpointParameters.sort(compare);
      }
    },
  },

}
</script>

<style>
.grid-address {
  position:relative;
  height: 50px;
  width: 100%;
  display: grid;
  grid-template-columns: 150px 150px 150px auto;
  grid-template-rows: auto;
  grid-gap: 15px;
  margin-bottom:25px;
}
.grid-kv {
  position: relative;
  height: 50px;
  width: 100%;
  display: grid;
  grid-template-columns: auto auto 50px;
  grid-template-rows: auto;
  grid-gap: 15px;
  margin-bottom: 25px;
}
.grid-kvd {
  position: relative;
  height: 50px;
  width: 100%;
  display: grid;
  grid-template-columns: 200px 200px 200px auto 50px;
  grid-template-rows: auto;
  grid-gap: 15px;
  margin-bottom: 25px;
}
.grid-title {
  position: relative;
  height: 50px;
  width: 100%;
  display: grid;
  grid-template-columns: auto 50px;
  grid-template-rows: auto;
  grid-gap: 15px;
  margin-bottom: 25px;
}
.theme--light.v-text-field--solo > .v-input__control > .v-input__slot {
    background: #f2f2f2;
    font-weight: bold;
    color: black;
}

.v-text-field.v-text-field--solo:not(.v-text-field--solo-flat) > .v-input__control > .v-input__slot {
  box-shadow: 0px -6px 10px rgba(255, 255, 255, 1),
              0px 4px 15px rgba(0, 0, 0, 0.15),
              inset 0px -2px 5px rgba(255, 255, 255, 0),
              inset 0px 2px 5px rgba(0, 0, 0, 0);
}
.halfAndHalf {
  position: relative;
  width: 100%;
  display: grid;
  grid-template-columns: auto;
  grid-template-rows: auto;
  grid-gap: 30px;
  margin-bottom: 30px;
}
.title-text {
  font-size: 30px;
  text-decoration: underline;
}
</style>