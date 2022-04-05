<template>
  <v-container>
      <div v-html="test"></div>
      <v-btn @click="htmltest">click</v-btn>
  </v-container>
</template>

<script>
export default {
    data() {
        return {
            test:""
        }
    },
    methods:{
        htmltest(){
            this.test = "asdfasdfasdfasdf"
        }
    }
}
</script>

<style>

</style>