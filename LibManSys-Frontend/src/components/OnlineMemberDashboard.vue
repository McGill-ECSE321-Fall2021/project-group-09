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
  <div class="online-member-dashboard-body">

    <section>
      <br />
      <br />
           <br />

     
   <b-container fluid>
 <myText>  Member Profile </myText>



  <b-row class="my-1" >
    <b-col sm="2" > 
      <label for="input-large" >Email:</label>
    </b-col>
  </b-row>
    <b-row class="my-1" >
    <b-col sm="2" > 
      <label for="input-large" >Username:</label>
    </b-col>
  </b-row>
    <b-row class="my-1" color="#00000">
    <b-col sm="2" color="#00000">
      <label for="input-default" color="#00000">Full Name:</label>
    </b-col>
     </b-row>


  <b-row class="my-1" >
    <b-col sm="2" > 
      <label for="input-large" >Address:</label>
    </b-col>
  </b-row>
  <b-row class="my-1" >
    <b-col sm="2" > 
      <label for="input-large" >Phone Number:</label>
    </b-col>
  </b-row>
   <b-row class="my-1" >
    <b-col sm="2" > 
      <label for="input-large" >Fees: </label>
    </b-col>
      <b-col sm="2" > 
      <label for="input-large" >$ </label>
    </b-col>
  </b-row>
   <b-row class="my-1" >
    <b-col sm="2" > 
      <label for="input-large" >Verification Status: </label>
    </b-col>
    <b-col sm="2" > 
      <label for="input-large" >Verified or Not </label>
    </b-col>
  </b-row>
  
</b-container>
      <b-col class="myColumn">
    
                <td>
                    <b-button v-row justify="center" v-on:click="goToLoansPage()">Loans</b-button>
                </td>
    
            </b-col>
     
    </section>
  </div>
</template>


<script>


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
                path: "/MemberLogin",
                name: "MemberLogin"
            })
        },
        goToRegisterPage: function (){
            Router.push({
                path: "/Register",
                name: "Register"
            })
        },
         goToSearchPage: function (){
            Router.push({
                path: "/SearchLibItems",
                name: "SearchLibItems"
            })
        },
        goToLoansPage: function (){
            Router.push({
                path: "/MemberLoans",
                name: "MemberLoans"
            })
        }
   }
}
</script>


<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>

.bg-custom-1 {
  background: linear-gradient(
    200deg,
    rgba(0, 0, 0, 1),
    rgba(0, 0, 0, 0.8),
    rgba(0, 0, 0, 0)
  );
}

b.nav-link {
  color: #000000;
  font-size: 18px;
}
b-navbar {
  background-color: #4e1d04;
}
.myColumn {
    display: flex;
    flex-direction: column;
    height: calc(10%);
    justify-content: space-around;
}

div {
    /*  background: #f0dbce;*/
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
myText{
  color:#a85f32;
  font: "Times";
  font-size: 30px;
  font-weight: 100;

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
    padding: 16px 32px;
    background-color: #0000;
    color: #a85f32;
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
