<!-- Only accessible by Librarians and Head Librarians

Librarians: 
- Create new Library Item 

Head Librarian:
- View all librarians (and create/fire librarians)
- Set library schedule 
- Set library hours 
- Update library phone number and email address

-->
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

      <h1>  Log In </h1>
   <b-container fluid>


  <b-row class="my-1" color="#00000">
    <b-col sm="2" color="#00000">
      <label for="input-default" color="#00000">Email:</label>
    </b-col>
    <b-col sm="10" >
      <b-form-input id="input-default" color="#00000"placeholder="Enter your name"></b-form-input>
    </b-col>
  </b-row>

  <b-row class="my-1" >
    <b-col sm="2" > 
      <label for="input-large" >Password:</label>
    </b-col>
    <b-col sm="10">
      <b-form-input id="input-large" size="lg" placeholder="Enter your name"></b-form-input>
    </b-col>
  </b-row>
  
</b-container>

<b class="nav-link" href="#" v-on:click="goToSubmitPage()"
            >Catalogue
          </b>
     
    </section>
     <footer class="navbar navbar-dark bg-custom-1 center-collapsed">
      <b> blah blah blah some copyright bs </b>
      
    </footer>

   
  </div>
</template>


<script>

import Register from "../components/Register";
import Router from "../router/index";

export default {
  name: 'hello',
  data () {
   return {
        types: [
        ]
      }
  },
   methods: {
       goToSubmitPage: function (){
            Router.push({
                path: "/Register",
                name: "Register"
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

.bg-custom-1 {
  background: linear-gradient(
    200deg,
    rgba(0, 0, 0, 1),
    rgba(0, 0, 0, 0.8),
    rgba(0, 0, 0, 0)
  );
}

.bg-custom-2 {
  background: linear-gradient(
    90deg,
    rgba(0, 0, 0, 1),
    rgba(0, 0, 0, 0.8),
    rgba(0, 0, 0, 0)
  );
}

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
  font-family: "Segoe UI", Tahoma, Geneva, Verdana, sans-serif;
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
  font-family: "Lato", sans-serif;
  height: 100vh;
  background-size: contain;
  background-repeat: no-repeat;
  background-size: 100%;
}

a.normal {
  font-weight: 400;
}
h1, h2 {
  font-weight: normal;
  color: black;

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
b-nav-item {
  color: #efecf2;
  font-size: 18px;
}
.nav-left {
  display: flex;
}
.nav-right {
  display: flex;
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


/*Resize the wrap to see the search bar change!*/
.wrap {
  width: 40%;
  position: absolute;
  top: 60%;
  left: 50%;
  transform: translate(-50%, -50%);
}
input,
::placeholder {
  font-family: "Lato", sans-serif;
  font-size: 18px;
  font-weight: 600;
  color: #ffffff;
}
</style>
