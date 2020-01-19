<template>
  <div class="nav-container">
    <div class="spotlight-logo"></div>
    <router-link v-for="button in buttons" class="ad-menu-button" :to="button.to" tag="button" :key="button.name">
      {{ button.name }}
    </router-link>
  </div>
</template>

<script>
export default {
  data: () => ({
    buttons: [
      {
        name: "Home",
        to: "/"
      },
      {
        name: "Edit",
        to: "/edit"
      },
      {
        name: "Documentation",
        to: "/documentation"
      }
    ],
  }),

}
</script>

<style>
.nav-container {
  position: absolute;
  box-sizing: border-box;
  padding: 10px;
  height: 60px;
  width: 100%;
  padding-left: 50px;
  top: 0;
  left: 0;
  background: #000000;
  background: linear-gradient(125deg, #ffffff, #ededed, #f5f5f5);
  box-shadow: 0 1px 4px 0 rgba(0,0,0,.25);
  color: black;
  display: grid;
  grid-gap: 10px;
  grid-template-columns: 75px 200px 200px 200px auto;
  grid-template-rows: 100%;
}
.th-icon {
  height: 100%;
  width: 100%;
}
.spotlight-logo {
  height: 100%;
  background: url('../../assets/spotlightLogo2.svg');
  background-repeat: no-repeat;

}
</style>
