<template>
  <v-app>
    <v-content>
      <div class="ad-background" />
      <NavBar/>
      <router-view/>
    </v-content>
  </v-app>
</template>

<script>
import NavBar from '@/components/NavBar/NavBar.vue'
export default {
  name: 'App',

  components: {
    NavBar
  },

  data: () => ({
    //
  }),
};
</script>
