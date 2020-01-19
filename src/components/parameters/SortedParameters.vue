<template>
  <div>
    <span v-for="parameter in sortedParameters">
    <p><span style="font-weight:bold">{{ parameter.parameterKey }}</span>
       <span class="font-italic" style="color:green" v-if="parameter.parameterRequired === 1"> Required</span>
       <span class="font-italic" style="color:red" v-else> Optional</span>
       <span class="font-italic font-weight-light">  {{ parameter.parameterType }} </span>
       <v-icon>mdi-chevron-right</v-icon>
       <span class="font-weight-light"> {{ parameter.parameterDescription }}</span></p>
    </span>
  </div>
</template>

<script>
export default {
  props: {
    parameters: Array,
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
      if(this.parameters) {
        return this.parameters.sort(compare);
      }
    },
  }
}
</script>

<style>
</style>