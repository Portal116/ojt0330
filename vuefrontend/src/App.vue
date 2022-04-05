<template>
  <v-app>
    <v-main>
      <homepage/>
    </v-main>
  </v-app>
</template>

<script>
import homepage from './view/homepage.vue';

export default {
  name: 'App',

  components: {
    homepage,
  },

  data: () => ({
    //
  }),
};
</script>
