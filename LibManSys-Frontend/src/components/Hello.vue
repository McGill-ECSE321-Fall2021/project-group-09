<template>
  <div class="hello">
        <td>   <button v-on:click="goToRegisterPage()">Register</button>   </td>
   <span></span>
   <td>   <button v-on:click="goToLoginPage()">Login</button>   </td>
    <span></span>
    <td>   <button v-on:click="goToSearchPage()">Catalogue</button>   </td>

    <h1>{{ msg }}</h1>
    
    <h2>A project by Group 09</h2>
    
    <h2>Tada it works
<span></span><span></span>
    <td>   <button v-on:click="goToLibManagmentPage()">Library Managment</button>   </td>
    <td>   <button v-on:click="goToSearchPage()">NavBar2</button>   </td>
    <td>   <button v-on:click="goToSearchPage()">NavBar3</button>   </td>

    </h2>
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
import Register from "../components/Register";
import MemberLogin from "../components/MemberLogin";
import SearchLibItems from "../components/SearchLibItems";
import LibraryManagementDashboard from "../components/LibraryManagementDashboard";
import Router from "../router/index";


export default {
  name: 'hello',
  data () {
    return {
      msg: 'Welcome to the Library'
    }
  },
  components: {
    Register,
  },
   methods: {
        goToRegisterPage: function (){
            Router.push({
                path: "/Register",
                name: "Register"
            })
        },
        goToLoginPage: function (){
            Router.push({
                path: "/MemberLogin",
                name: "MemberLogin"
            })
        },
        goToSearchPage: function (){
            Router.push({
                path: "/SearchLibItems",
                name: "SearchLibItems"
            })
        },
        goToLibManagmentPage: function (){
            Router.push({
                path: "/LibraryManagementDashboard",
                name: "LibraryManagementDashboard"
            })
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
smallButton{
  font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
  padding:16px 32px;
  background-color: #a85f32;
  border-radius: 10px;
  font-size:12px;
  color:#000;
  background: #a85f32;
  border:0;
  font-weight:200;
}
button{
  font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
  padding:16px 32px;
  background-color: a85f32;
  border-radius: 10px;
  font-size:15px;
  color:#000;
  background: a85f32;
  border:0;
  font-weight:200;
}
</style>
