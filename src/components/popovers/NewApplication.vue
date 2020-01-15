<template>
  <div class="popover-container">
    <div class="popover-background" @click="close"></div>
    <div class="new-team-container">
      <h1>New Application</h1>
      <div style="height:30px;"></div>
      <h3>Application Name:</h3>
      <input class="ad-input"
             v-model="application.applicationName">
      </input>
      <h3>Application Description:</h3>
      <textarea class="ad-input-textarea"
             v-model="application.applicationDescription">
      </textarea>
      <button class="ad-button" @click="addApplication(application)">Add Application</button>
    </div>
  </div>
</template>

<script>
export default {
  props: {
    close: Function,
    addApplication: Function
  },
  data: () => ({
    application: {
      applicationName: "",
      applicationDescription: "",
    },
  }),
  methods: {

  }
}
</script>

<style>
.new-team-container {
  position: absolute;
  box-sizing: border-box;
  padding: 20px 40px;
  height: auto;
  width: 800px;
  top: 50%;
  left: 50%;
  transform: translate(-50%,-50%);
  background: #f2f2f2;
  border-radius: 3px;
  z-index: 101;
}
</style>