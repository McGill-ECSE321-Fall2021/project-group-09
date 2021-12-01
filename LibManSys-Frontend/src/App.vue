<template>
  <div id="app">
    <!-- <img src="./assets/libraryLogo.jpeg" width="100px" height="100px"> -->
    <topnavbar></topnavbar>
    <router-view id="body"></router-view> 
    <Footer id="footer"></Footer>
  </div>
</template>

<script>
import topnavbar from './components/topnavbar.vue'
import Footer from './components/Footer.vue'
export default {
  name: 'app',
  components: {
    topnavbar, // register component
    Footer
  }
}
</script>

<style>
#app {
  font-family: 'Avenir', Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
   /* margin-top: 60px; */

  display: flex;
  flex-direction: column;
  min-height: 100vh;
}
#body {
  flex: 1;
  margin: 0;
}
</style>
