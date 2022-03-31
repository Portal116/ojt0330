<template>
  <v-app>
    <v-main>
      <mainVue/>
    </v-main>
  </v-app>
</template>

<script>
import mainVue from './view/homepage.vue';

export default {
  name: 'App',

  components: {
    mainVue,
  },

  data: () => ({
    //
  }),
};
</script>
