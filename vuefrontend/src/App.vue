<template>
  <v-app>
    <v-main>
      확인용
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
