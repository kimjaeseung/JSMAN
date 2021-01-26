<template>
  <v-app>
    <router-view></router-view>
  </v-app>
</template>

<script>
// import HelloWorld from "./components/HelloWorld";

export default {
  name: "App",

  components: {
    // HelloWorld
  },

  data: () => ({
    //
  })
};
</script>
<style>
@import url('https://fonts.googleapis.com/css2?family=Noto+Sans+KR:wght@500&family=Noto+Serif+KR:wght@700&display=swap');

html, body {
  font-family: Spectral, "Noto Serif KR", serif;
  font-weight: 900;
}

#app {
  font-family: Spectral, "Noto Serif KR", serif;
}

</style>