<template>
    
    <nav class="navbar navbar-dark bg-custom-3 center-collapsed">
        <!--  <div class="navbar-collapse collapse w-100 order-1 order-md-0 dual-collapse2"> -->
        <ul class="nav-left mr-auto">
        <li class="nav-item">
            <b class="nav-link" href="#a">LibManSys </b>
        </li>
        <li class="nav-item">
            <b class="nav-link" href="#" v-on:click="goToSearchPage()"
            >Catalogue
            </b>
        </li>
        </ul>
        <ul class="nav-right ml-auto">
        <li class="nav-item">
            <b class="nav-link" href="#" v-on:click="goToLoginPage()">Login </b>
        </li>
        <li class="nav-item">
            <b class="nav-link" href="#" v-on:click="goToRegisterPage()"
            >Sign Up
            </b>
                
        </li>
        
        </ul>
    </nav>

</template>

<script>
import Router from "../router/index";

export default {
  name: "topvarbar",
  data() {
    return {
      msg: "Welcome to the Library"
    };
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
    goToSearchPage: function() {
            Router.push({
                path: "/SearchLibItems",
                name: "SearchLibItems"
            })
        }
  }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
@import url("https://fonts.googleapis.com/css2?family=Lato:wght@100&display=swap");

.bg-custom-1 {
    background: linear-gradient(
        65deg,
        rgba(200, 200, 200, 1),
        rgba(112, 103, 103, 0.8),
        rgba(0, 0, 0, 0)
    );
}

.bg-custom-2 {
    background: linear-gradient(
        45deg,
        rgba(0, 0, 0, 0.8),
        rgba(0, 0, 0, 0.4),
        rgba(0, 0, 0, 0.8)    
    );
}

.bg-custom-3 {
  background: rgba(255, 255, 255, 1);
}

nav.navbar {
  height: 60px;
}
b.nav-link {
  color: #000000;
  font-size: 18px;
  font-family: "Segoe UI", Tahoma, Geneva, Verdana, sans-serif;
  font-weight: 450;
}

.nav-item {
    background-color: rgba(255, 255, 255, 1);
    border-radius: 10px;
}
.nav-item:hover {
  /*background-color: #8c5440;*/
}
::-webkit-scrollbar {
  display: none;
}
ul {
  list-style-type: none;
  padding: 0;
}
.nav-left {
  display: flex;
}
.nav-right {
  display: flex;
}

li {
  display: inline-block;
  cursor: pointer;
  margin: 0px;
}
</style>