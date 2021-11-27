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
                    <b class="nav-link" href="#" v-on:click="goToSearchPage()">Catalogue
              </b>
                </li>
            </ul>
            <ul class="nav-right ml-auto">
                <li class="nav-item">
                    <b class="nav-link" href="#" v-on:click="goToSubmitPage()">Login </b>
                </li>
                <li class="nav-item">
                    <b class="nav-link" href="#" v-on:click="goToRegisterPage()">Sign Up
              </b>
    
                </li>
    
            </ul>
        </nav>
    
        <section>
            <br />
            <br />
            <br />
    
       
            <b-container fluid>
         <h1> Librarian Profile </h1>
    
                <b-row class="my-1" color="#00000">
                    <b-col sm="2" color="#00000">
                        <label for="input-default" color="#00000">Name:</label>
                    </b-col>
    
                </b-row>
    
                <b-row class="my-1">
                    <b-col sm="2">
                        <label for="input-large">Email:</label>
                    </b-col>
                </b-row>
                <b-row class="my-1">
                    <b-col sm="2">
                        <label for="input-large">Address:</label>
                    </b-col>
                </b-row>
                <b-row class="my-1">
                    <b-col sm="2">
                        <label for="input-large">Something else:</label>
                    </b-col>
                </b-row>
    
            </b-container>
    
            <!--
    <b class="nav-link" href="#" v-on:click="goToLibraryManagmentPage()"
                >Library Managment -->
            </b>
            </b>
            </b>
            </b>
            <b-col class="myColumn">
    
                <td>
                    <b-button v-row justify="center" v-on:click="goToLibraryManagmentPage()">Library Managment</b-button>
                </td>
    
            </b-col>
    
        </section>
        <footer class="navbar navbar-dark bg-custom-1 center-collapsed">
            <b> blah blah blah some copyright bs </b>
    
        </footer>
    
    
    </div>
</template>


<script>
import MemberLogin from "../components/MemberLogin";
import SearchLibItems from "../components/SearchLibItems";
import LibraryManagementDashboard from "../components/LibraryManagementDashboard";
import Hello from "../components/Hello";
import Router from "../router/index";

export default {
    name: 'hello',
    data() {
        return {
            types: []
        }
    },
    methods: {
        goToSubmitPage: function() {
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
        },
        goToLibraryManagmentPage: function() {
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
header {
    color: #42b983;
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
    -ms-overflow-style: none;
    /* IE and Edge */
    scrollbar-width: none;
    /* Firefox */
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

h1,
h2 {
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

#container {
    width: 65%;
    margin: 0 auto;
}

button {
    font-family: "Segoe UI", Tahoma, Geneva, Verdana, sans-serif;
    padding: 16px 32px;
    background-color: #0000;
    color: #a85f32;
}

.myRow {
    display: flex;
    flex-direction: row;
    justify-content: space-between;
    height: calc(59%);
    padding-top: 10;
    padding-bottom: 10;
}

.myColumn {
    display: flex;
    flex-direction: column;
    height: calc(10%);
    justify-content: space-around;
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
