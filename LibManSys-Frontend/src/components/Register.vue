
<template>
  <div class="Register">

      <section>
            <br />
            <br />
                <br />

      <b-container fluid>
            <h2>  ONLINE MEMBER REGISTRATION </h2>

      <b-form>

        <b-form-text>
          <b-col sm="2" > 
            <label for="fullName" >Full Name:</label>
          </b-col>
          <b-col sm="5">
            <b-form-input id="fullName" v-model="fullName" placeholder="Enter your Full Name"></b-form-input>
          </b-col>
        </b-form-text> 

        <b-form-text>
          <b-col sm="1" > 
            <label for="address" >Address:</label>
          </b-col>
          <b-col sm="5">
            <b-form-input id="address" v-model="address" placeholder="Enter your Address"></b-form-input>
          </b-col>
        </b-form-text>

        <b-form-text>
          <b-col sm="2" > 
            <label for="phoneNumber">Phone Number:</label>
          </b-col>
          <b-col sm="5">
            <b-form-input id="phoneNumber" v-model="phoneNumber" placeholder="Enter your phone number"></b-form-input>
          </b-col>
        </b-form-text>

        <b-form-text>
          <b-col sm="1">
            <label for="emailAddress">Email:</label>
          </b-col>
          <b-col sm="5" >
            <b-form-input id="emailAddress" v-model="emailAddress" placeholder="Enter your Email"></b-form-input>
          </b-col>
        </b-form-text>

        <b-form-text>
          <b-col sm="1" > 
            <label for="password">Password:</label>
          </b-col>
          <b-col sm="5">
            <b-form-input id="password" type="password" v-model="password" placeholder="Enter your Password"></b-form-input>
          </b-col>
        </b-form-text>

        <b-form-text>
          <b-col sm="1" > 
            <label for="username" >Username:</label>
          </b-col>
          <b-col sm="5">
            <b-form-input id="username" v-model="username" placeholder="Enter your username"></b-form-input>
          </b-col>
        </b-form-text>

        <br />
        <span v-if="error" style="color:red">{{error}} </span>
        <br v-if="error" />
        <br v-if="error" />

        <button
            @click="createMemberAccount(fullName, address, phoneNumber, emailAddress, password, username)"
            type="Registerbutton"
            style="background-color: firebrick; color: coral"
            class="btn btn-primary"
          >
            <font size="3"><b>Create Account</b></font>

        </button>

        <br />
        <br />

         </b-form>
        
        </b-container>

      </section>

  </div>

</template>


<script src='./RegisterHandling.js'>

</script>


<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>

Register {
  color: #42b983;
}
myText{
  color:#a85f32;
  font: "Times";
  font-size: 36px;
  font-weight: 100;

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
  color: coral;
  font-size: 18px;
}
b-navbar {
  background-color: #4e1d04;
}

div {
  color: coral;
  background-color: bisque;
  background-size: contain;
  background-repeat: no-repeat;
  background-size: 100%;
  -ms-overflow-style: none; /* IE and Edge */
  scrollbar-width: none; /* Firefox */
  margin: auto;
   background-image: linear-gradient(
      115deg,
      rgba(0, 0, 0, 0.9),
      rgba(0, 0, 0, 0.9),
      rgba(43, 16, 7, 0.8)
    ),
    url(../assets/library-bkg.jpg);
}

::-webkit-scrollbar {
  display: none;
}
section {
  background-size: contain;
  background-repeat: no-repeat;
  background-size: 100%;
  margin-left: 10%;
  margin-right: 10%;
  align-items: baseline;
}

a.normal {
  font-weight: 400;
}
h1, h2 {
 
  color: coral;
  font-size: 40px;
  font-weight: normal;
  background-color: firebrick;
  width: 100%;
  margin-left: 0%;
  

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
    width: 50%;
    margin: auto;
}
button {
  font-family: "Segoe UI", Tahoma, Geneva, Verdana, sans-serif;
  margin-left: 0cm;
  /* padding: 16px 32px; */
  background-color: firebrick;
  /* border-radius: 10px; */
  /* font-size: 15px; */
  color: coral;
  /* background: a85f32 */
  /* border: 0; */
  /* font-weight: 200; */
}


</style>

   
