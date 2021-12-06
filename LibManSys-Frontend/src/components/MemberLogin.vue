
<template>
  <div id="member-login-body">
    <section>
        <br />
        <br />
        <br />

        <b-container fluid>
            <b class="login-title"> Online Member Log In </b>

            <br />
            <br />

            <b-row class="my-1" color="#00000">
                <b-col sm="4" color="#00000">
                    <label class="login-input" for="input-default">Username:</label>
                </b-col>
                <b-col sm="5" >
                    <b-form-input  id="input-default"  type="text" v-model="username" placeholder="Enter your username"></b-form-input>
                </b-col>
                <b-col sm="3" color="#00000">
                    
                </b-col>
            </b-row>

            <b-row class="my-1" >
                <b-col sm="4" > 
                    <label class="login-input" for="input-large" >Password:</label>
                </b-col>
                <b-col sm="5">
                    <b-form-input id="input-default" type="password" v-model="password" placeholder="Enter your password"></b-form-input>
                </b-col>
                <b-col sm="3" color="#00000">
                    
                </b-col>
            </b-row>
          
        </b-container>
        
        <br />
        <span v-if="errorLogin" class="error-msg">{{errorLogin}}</span>
        <br />

        <b-container>
          <b-row class="my-1 text-center" align-h="center">
            <b class="button" href="#" v-on:click="loginMember(username, password)">Login</b>
            <b class="button" href="#" v-on:click="goToLibrarianLoginPage()">Go to Librarian Login</b>
          </b-row>
        </b-container>

    </section>
  <!-- End of body div -->
    
  </div>

</template>


<script src="./MemberLogin.js"></script>


<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>

.error-msg {
  font-weight: 500;
  color: red;
}


.bg-custom-1 {
  background-color: #d9b086;
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
  color: #d9b086;
  font-family: "Segoe UI", Tahoma, Geneva, Verdana, sans-serif;
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
