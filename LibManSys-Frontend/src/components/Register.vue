
<template>
  <div class="header">


    <nav class="navbar navbar-dark bg-custom-2 center-collapsed">
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

    <section>
      <br />
      <br />
           <br />

   <b-container fluid>
      <h2>  Registration </h2>

  <b-row class="my-1" color="#00000">
    <b-col sm="2" color="#00000">
      <label for="input-default" color="#00000">Email:</label>
    </b-col>
    <b-col sm="10" >
      <b-form-input id="input-default" color="#FFFFF" placeholder="Enter your Email"></b-form-input>
    </b-col>
  </b-row>

  <b-row class="my-1" >
    <b-col sm="2" > 
      <label for="input-large" >Password:</label>
    </b-col>
    <b-col sm="10">
      <b-form-input id="input-large" placeholder="Enter your Password"></b-form-input>
    </b-col>
  </b-row>

  <b-row class="my-1" >
    <b-col sm="2" > 
      <label for="name" >Full Name:</label>
    </b-col>
    <b-col sm="10">
      <b-form-input id="name" placeholder="Enter your Full Name"></b-form-input>
    </b-col>
  </b-row>

  <b-row class="my-1" >
    <b-col sm="2" > 
      <label for="address" >Address:</label>
    </b-col>
    <b-col sm="10">
      <b-form-input id="address" placeholder="Enter your Address"></b-form-input>
    </b-col>
  </b-row>

  <b-row class="my-1" >
    <b-col sm="2" > 
      <label for="Blabla" >Blabla:</label>
    </b-col>
    <b-col sm="10">
      <b-form-input id="Blabla"  placeholder="Enter your Blablabla"></b-form-input>
    </b-col>
  </b-row>
  
</b-container>

<b class="nav-link" href="#" v-on:click="goToSubmitPage()"
            >Submit
          </b>
     
    </section>
     <footer class="navbar navbar-dark bg-custom-1 center-collapsed">
      <b> blah blah blah some copyright bs </b>
      
    </footer>

   
  </div>
</template>


<script>

import OnlineMemberDashboard from "../components/OnlineMemberDashboard";
import SearchLibItems from "../components/SearchLibItems";
import Hello from "../components/Hello";
import Register from "../components/Register";
import MemberLogin from "../components/MemberLogin";
import Router from "../router/index";

export default {
  data () {
   return {
        types: [
        ]
      }
  },
   methods: {
       goToSubmitPage: function (){
            Router.push({
                path: "/OnlineMemberDashboard",
                name: "OnlineMemberDashboard"
            })
        },
        goToSearchPage: function (){
            Router.push({
                path: "/SearchLibItems",
                name: "SearchLibItems"
            })
        },
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
        }
   }
}
</script>


<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>

header {
  color: #42b983;
}
/*
.bg-custom-1 {
  background: linear-gradient(
    200deg,
    rgba(0, 0, 0, 1),
    rgba(0, 0, 0, 0.8),
    rgba(0, 0, 0, 0)
  );
}
-->
.bg-custom-2 {
  background: linear-gradient(
    90deg,
    rgba(0, 0, 0, 1),
    rgba(0, 0, 0, 0.8),
    rgba(0, 0, 0, 0)
  );
} */


nav.navbar {
  height: 60px;
}
b.nav-link {
  color: #000000;
  font-size: 18px;
}
b-navbar {
  background-color: #4e1d04;
}

div {
  color: #000000;
 

  background-size: contain;
  background-repeat: no-repeat;
  background-size: 100%;
  -ms-overflow-style: none; /* IE and Edge */
  scrollbar-width: none; /* Firefox */
  
}

::-webkit-scrollbar {
  display: none;
}
section {
  height: 100vh;
  background-size: contain;
  background-repeat: no-repeat;
  background-size: 100%;
}

a.normal {
  font-weight: 400;
}
h1, h2 {
 
  color: black;
  font-size: 40px;
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
b {
  font: "Lato", sans-serif;
  font-weight: 100;
  color: #ffffff;
  font-size: 18px;
}

#container{
    width: 65%;
    margin: 0 auto;
}
button {
  font-family: "Segoe UI", Tahoma, Geneva, Verdana, sans-serif;
  /* padding: 16px 32px; */
  /* background-color: a85f32; */
  /* border-radius: 10px; */
  /* font-size: 15px; */
  color: #000;
  /* background: a85f32; */
  /* border: 0; */
  /* font-weight: 200; */
}


</style>
