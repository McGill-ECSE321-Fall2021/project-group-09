<template>
  <div class="hello">
    
    <h1></h1>
    <h2>Online member can login using the following form</h2>
    
   
    <ul>
     <!-- Commented out Commented
     <li><a href="http://router.vuejs.org/" target="_blank">vue-router</a></li>
      <li><a href="http://vuex.vuejs.org/" target="_blank">vuex</a></li>
      <li><a href="http://vue-loader.vuejs.org/" target="_blank">vue-loader</a></li>
      <li><a href="https://github.com/vuejs/awesome-vue" target="_blank">awesome-vue</a></li>
    --> 
    </ul>
  </div>
</template>

<script>

import Router from "../router/index";


export default {
  name: 'hello',
  data () {
    return {
      msg: 'Welcome to the Library'
    }
  }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
h1, h2 {
  font-weight: normal;
}

ul {
  list-style-type: none;
  padding: 0;
}

li {
  display: inline-block;
  margin: 0 10px;
}

a {
  color: #42b983;
}
#container{
    width: 65%;
    margin: 0 auto;
}
button{
  font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
  padding:16px 32px;
  background-color: lightskyblue;
  border-radius: 10px;
  font-size:23px;
  color:#000;
  background: lightskyblue;
  border:0;
  font-weight:200;
}
</style>
