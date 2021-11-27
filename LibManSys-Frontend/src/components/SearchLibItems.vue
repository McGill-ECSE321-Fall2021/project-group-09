<--! Same as Hello, might be better to use this-->

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
      <img src="../assets/logo-placeholder.png" height="15%" />
      <br />
      <a class="light"> Welcome to </a>
      <a class="thick"> Group 9's Public Library</a>
      <br />
      <br />
      <searchbar class="wrap">
        <searchbar class="search" color="#ffffff">
          <input
            type="text"
            class="searchTerm"
            placeholder="What are you looking for?"
          />
          <findbutton
            type="submit"
            class="searchButton"
            v-on:click="goToSearchPage()"
          >
            <img src="../assets/search.png" align="top" />
          </findbutton>
        </searchbar>
      </searchbar>
     
    </section>
  <td>   <button v-on:click="goToUserPage()">User Page</button>   </td> <!--! On user loged in = member -->
    <td>   <button v-on:click="goToLibrarianPage()">Librarian Page</button>   </td> <!--! On user loged in = librarian -->
     <td>   <button v-on:click="goToItemPage()">Test Item Page</button>   </td> 

 <footer class="navbar navbar-dark bg-custom-1 center-collapsed">
      <b> blah blah blah some copyright bs </b>
     
    </footer>

  </div>
</template>

<script>
import Register from "../components/Register";
import MemberLogin from "../components/MemberLogin";
import SearchLibItems from "../components/SearchLibItems";
import LibraryManagementDashboard from "../components/LibraryManagementDashboard";
import LibrarianDashboard from "../components/LibrarianDashboard";
import OnlineMemberDashboard from "../components/OnlineMemberDashboard";
import ItemPage from "../components/ItemPage";
import Router from "../router/index";

export default {
  name: "hello",
  data() {
    return {
      hover: false,
      msg: "Welcome to the Library",
    };
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
        },
        goToUserPage: function (){
            Router.push({
                path: "/OnlineMemberDashboard",
                name: "OnlineMemberDashboard"
            })
        },
        goToLibrarianPage: function (){
            Router.push({
                path: "/LibrarianDashboard",
                name: "LibrarianDashboard"
            })
        },
        goToItemPage: function (){
            Router.push({
                path: "/ItemPage",
                name: "ItemPage"
            })
        }
        }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
@import url("https://fonts.googleapis.com/css2?family=Lato:wght@100&display=swap");

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
  color: #ffffff;
  font-size: 18px;
}
b-navbar {
  background-color: #4e1d04;
}

div {
  font-family: "Segoe UI", Tahoma, Geneva, Verdana, sans-serif;
  color: #000000;
  background-image: linear-gradient(
      115deg,
      rgba(0, 0, 0, 0.9),
      rgba(0, 0, 0, 0.9),
      rgba(43, 16, 7, 0.8)
    ),
    url(../assets/library-bkg.jpg);

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
.hours {
  position: absolute;
  top: 15%;
  bottom: 12%;
  right: 1%;
  left: 52%;
  background-color: #1e3535;
  color: white;
  padding-left: 40px;
  padding-right: 40px;
  opacity: 0.7;
  text-align: left;
}
a.normal {
  font-weight: 400;
}
h2 {
  font-weight: normal;
}
a.thick {
  font-weight: bold;
}
a.light {
  font-weight: 100;
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
li.nav-item:hover {
  color: #8c5440;
}

a {
  color: #ffffff;
  font-size: 70px;
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
b-nav-item:hover {
  color: #8c5440;
}
#container {
  width: 65%;
  margin: 0 auto;
}
smallButton {
  font-family: "Segoe UI", Tahoma, Geneva, Verdana, sans-serif;
  padding: 16px 32px;
  background-color: #a85f32;
  border-radius: 10px;
  font-size: 12px;
  color: #000;
  background: #a85f32;
  border: 0;
  font-weight: 200;
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
/*Search Bar */
.search {
  width: 100%;
  position: relative;
  display: flex;
}

.searchTerm {
  width: 100%;
  border: 2px solid #ffffff;
  border-right: none;
  padding: 15px;
  height: 20px;
  border-radius: 5px 0 0 5px;
  outline: none;
  color: #ffffff;
  background: none;
}

.searchTerm:focus {
  color: #ffffff;
}

.searchButton {
  width: 40px;
  height: 34px;
  border: 2px solid #ffffff;
  border-left-style: hidden;
  background: none;
  text-align: center;
  border-radius: 0 5px 5px 0;
  cursor: pointer;
  font-size: 20px;
  vertical-align: text-top;
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

/* Footer */
footer {
  height: 40px;
  font-weight: 200;
  text-align: center;
}
</style>
