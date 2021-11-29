
<template>
  <div class="Register">


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
      <h2>  ONLINE MEMBER REGISTRATION </h2>

  <b-form-text class="my-1" >
    <b-col sm="1" > 
      <label for="name" >FullName:</label>
    </b-col>
    <b-col sm="5">
      <b-form-input id="name" placeholder="Enter your Full Name"></b-form-input>
    </b-col>
  </b-form-text> 

  <b-form-text class="my-1" >
    <b-col sm="1" > 
      <label for="address" >Address:</label>
    </b-col>
    <b-col sm="5">
      <b-form-input id="address" placeholder="Enter your Address"></b-form-input>
    </b-col>
  </b-form-text>

  <b-form-text class="my-1" >
    <b-col sm="1" > 
      <label for="Blabla" >PhoneNumber:</label>
    </b-col>
    <b-col sm="5">
      <b-form-input id="Blabla"  placeholder="Enter your phone number"></b-form-input>
    </b-col>
  </b-form-text>

  <b-form-text class="my-1" color="#00000">
    <b-col sm="1" color="#00000">
      <label for="input-default" color="#00000">MemberEmail:</label>
    </b-col>
    <b-col sm="5" >
      <b-form-input id="input-default" color="#FFFFF" placeholder="Enter your Email"></b-form-input>
    </b-col>
  </b-form-text>

  <b-form-text class="my-1" >
    <b-col sm="1" > 
      <label for="input-large" >Password:</label>
    </b-col>
    <b-col sm="5">
      <b-form-input id="input-large" placeholder="Enter your Password"></b-form-input>
    </b-col>
  </b-form-text>

  <b-form-text class="my-1" >
    <b-col sm="1" > 
      <label for="name" >Username:</label>
    </b-col>
    <b-col sm="5">
      <b-form-input id="name" placeholder="Enter your username"></b-form-input>
    </b-col>
  </b-form-text>

  
</b-container>


<button
      @click="createCustomerAccount(username, password, confirmPassword, name)"
      type="button"
       style="background-color: firebrick; color: coral"
      class="btn"
    >
      <font size="3"><b>Create Account</b></font>

</button>
     
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
   methods: 
	{	createMemberAccount: function (fullName, address, phoneNumber, emailAddress, password, username) {
				AXIOS.post('/OnlineMembers/create/' + fullName + '?Address=' + address + '?Phone Number=' + phoneNumber +
				'?Email Address=' + emailAddress + '?Password=' + password + '?Username=' +username
				).then(response => {
                    // JSON responses are automatically parsed.
                    this.MemberAccounts.push(response.data)
                    this.newMemberAccount.fullName = ''
                    this.newMemberAccount.address = ''
					this.newMemberAccount.phoneNumber = ''
					this.newMemberAccount.emailAddress = ''
					this.newMemberAccount.password = ''
					this.newMemberAccount.username = ''
                    this.errorMemberAccount = ''
                    this.$currentUsername.value = username
                    this.$currentName.value = name
                    this.$router.push("/OnlineMemberDashboard")
                })
                    .catch(e => {
                        var errorMsg = e.response.data.message
                        console.log(errorMsg)
                        this.errorMemberAccount = errorMsg
					})
        },
	
		getAllMemberAccounts: function () {
            // Initializing persons from backend
            AXIOS.get('/onlineMembers')
                .then(response => {
                    // JSON responses are automatically parsed.
                    this.MemberAccounts = response.data
                })
                .catch(e => {
                    this.errorCustomerAccount = e
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
   },
}
   }



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
  height: 100vh;
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

   
