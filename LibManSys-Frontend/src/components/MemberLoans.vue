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
                <myText> Member Loans </myText>
    
              <b-table dense :headers="headers" :items="myTable" item-key="name" class="elevation-1">

              </b-table>

    
    
    
            </b-container>
            <b-col class="myColumn">
    
                <td>
                    <b-button v-row justify="center" v-on:click="goToMemberPage()">Go Back to Member Profile</b-button>
                </td>

                
            </b-col>
             <b-col class="myColumn">
    <table>
      <tr>
          <td>John</td>
          <td>Event to attend</td>
      </tr>
      <tr v-for="loan in loans">
          <td>  {{loan.borrowedDate}} 
              <input type="text" placeholder="Person Name">
          </td>
          <td>
               <td>
                    <td>
                         <td>
              <button>Create</button>
          </td>
      </tr>
      
    </table>
     </b-col>
        </section>
        <footer class="navbar navbar-dark bg-custom-1 center-collapsed">
            <b> blah blah blah some copyright bs </b>
    
        </footer>
    
    
    </div>
</template>

<script src="./MemberLoans.js"></script>

<script >
import MemberLogin from "../components/MemberLogin";
import SearchLibItems from "../components/SearchLibItems";
import OnlineMemberDashboard from "../components/OnlineMemberDashboard";

import Router from "../router/index";
import Register from "../components/Register";

export default {
    name: 'hello',
    data() {


        return {
            types: [],
            headers: [{
                    text: 'Loans',
                    align: 'start',
                    sortable: false,
                    value: 'name',
                },
                { text: 'Loan ID', value: 'loanID' },
                { text: 'Title', value: 'title' },
                { text: 'Borrowed Date ', value: 'borrowedDate' },
                { text: 'Due Date', value: 'dueDate' },
                { text: 'Library Item ID', value: 'libraryItemId' },
                { text: 'Loan Status', value: 'loanStatus' },

            ],
            myTable: [{
                    loanID: '1',
                    title: 'Test1',
                    borrowedDate: '2/2/2021',
                    dueDate: '3/2/2021',
                    libraryItemId: 2,
                    loanStatus: 'Active'
                },
                {
                     loanID: '2',
                    title: 'Test2',
                    borrowedDate: '2/3/2021',
                    dueDate: '3/3/2021',
                    libraryItemId: 4,
                    loanStatus: 'Late'                    
                }
            ]
        }
    },
    methods: {
        goToSubmitPage: function() {
            Router.push({
                path: "/MemberLogin",
                name: "MemberLogin"
            })
        },
        goToRegisterPage: function() {
            Router.push({
                path: "/Register",
                name: "Register"
            })
        },
        goToSearchPage: function() {
            Router.push({
                path: "/SearchLibItems",
                name: "SearchLibItems"
            })
        },
        goToMemberPage: function() {
            Router.push({
                path: "/OnlineMemberDashboard",
                name: "OnlineMemberDashboard"
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
    background: linear-gradient( 200deg, rgba(0, 0, 0, 1), rgba(0, 0, 0, 0.8), rgba(0, 0, 0, 0));
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

myText {
    color: #a85f32;
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
