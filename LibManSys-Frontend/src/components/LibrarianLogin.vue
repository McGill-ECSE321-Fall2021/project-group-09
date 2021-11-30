<!---Commented--> 
<template>
  <div class="header">


    <nav class="navbar navbar-dark bg-custom-2 center-collapsed color : ##d9b086">
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
            <b class="login-title"> Librarian Log In </b>

            <br />
            <br />

            <b-row class="my-1" color="#00000">
                <b-col sm="4" color="#00000">
                    <label class="login-input" for="input-default">Username:</label>
                </b-col>
                <b-col sm="5" >
                    <b-form-input id="input-default" type="text" v-model="username" placeholder="Enter your username"></b-form-input>
                </b-col>
                <b-col sm="3" color="#00000">
                    
                </b-col>
            </b-row>

            <b-row class="my-1" >
                <b-col sm="4" > 
                    <label class="login-input" for="input-default" >Password:</label>
                </b-col>
                <b-col sm="5">
                    <b-form-input id="input-large"  type="text" v-model="password" placeholder="Enter your password"></b-form-input>
                </b-col>
                <b-col sm="3" color="#00000">
                    
                </b-col>
            </b-row>
          
        </b-container>
        
        <br />
        <span v-if="errorLogin" style="color:red">{{errorLogin}} </span>
        <br />

        <b-container>
          <b-row class="my-1 text-center" align-h="center">
            <b class="button" href="#" v-on:click="loginLibrarian(username, password)">Login</b>
            <b class="button" href="#" v-on:click="goToMemberLoginPage()">Go to Member Login</b>
          </b-row>
        </b-container>

    </section>
    
    
    <footer class="navbar navbar-dark bg-custom-1 center-collapsed">
      <b> blah blah blah some copyright bs </b> 
    </footer>

   
  </div>
</template>


<script src="./LibrarianLogin.js"></script>


<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>

header {
  color: #42b983;
}

.bg-custom-1 {
  background : #d9b086;
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
 /*   background: #f0dbce;*/
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
  color : #d9b086;
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
.button {
  display: inline-block;
  font-family: "Segoe UI", Tahoma, Geneva, Verdana, sans-serif;
  padding: 16px 32px;
  background-color: a85f32;
  border-radius: 10px;
  font-size: 15px;
  color: #000;
  background: a85f32;
  /* border: 0; */
  font-weight: 500;
}


.button:hover {
    cursor: pointer;
    background-color: #ffd24d;
}

.login-title {
  color:#a85f32;
  font: "Times";
  font-size: 30px;
  font-weight: 100;
}

.login-input {
    float: right;
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
}
</style>
