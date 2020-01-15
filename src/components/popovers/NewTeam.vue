<template>
  <div class="popover-container">
    <div class="popover-background" @click="close"></div>
    <div class="new-team-container">
      <h1>New Team</h1>
      <div style="height:30px;"></div>
      <h3>Team Name:</h3>
      <input class="ad-input"
             v-model="groupName">
      </input>
      <button class="ad-button" @click="addTeam(groupName)">Add Team</button>
    </div>
  </div>
</template>

<script>
export default {
  props: {
    close: Function,
    addTeam: Function
  },
  data: () => ({
    groupName: "",
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