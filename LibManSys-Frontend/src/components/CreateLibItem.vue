
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
                    <b class="nav-link" href="#" v-on:click="goToLoginPage()">Login </b>
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
                <h2> Create Library Item </h2>
                <br>
                <b-row class="my-1" color="#00000">
                    <b-col sm="2" color="#00000">
                        <label for="input-default" color="#00000">Category:</label>
                    </b-col>
                    <div id="v-model-select" class="demo">
    
                        <b-form-group>
                            <b-form-radio-group id="claimType" required v-model="form.claimType" :options="claimTypeOptions" name="claimType" stacked>
                            </b-form-radio-group>
                        </b-form-group>
                    </div>
                </b-row>
                <!-- ARCHIVE FIELDS -->
                <div v-show="form.claimType === 'Archive'">
    
    
                    <b-row class="my-1">
                        <b-col sm="2">
                            <label for="name">Title:</label>
                        </b-col>
                        <b-col sm="10">
                            <b-form-input id="name" placeholder="Enter the Title"></b-form-input>
                        </b-col>
                    </b-row>
    
                    <b-row class="my-1">
    
                        <b-col sm="2">
                            <label for="input-large"> Published Year:</label>
                        </b-col>
                        <b-col sm="10">
                            <b-form-input id="input-large" placeholder="Enter the Published Year"></b-form-input>
                        </b-col>
    
                    </b-row>

                 <b-row class="my-1">
    
                        <b-col sm="2">
                            <label for="input-large"> Other Archive:</label>
                        </b-col>
                        <b-col sm="10">
                            <b-form-input id="input-large" placeholder="Enter the Other"></b-form-input>
                        </b-col>
    
                    </b-row>
                </div>


                <!-- BOOK FIELDS -->
                <div v-show="form.claimType === 'Book'">
    
                    <b-row class="my-1">
                        <b-col sm="2">
                            <label for="name">Title:</label>
                        </b-col>
                        <b-col sm="10">
                            <b-form-input id="name" placeholder="Enter the Title"></b-form-input>
                        </b-col>
                    </b-row>
                    <b-row class="my-1">
    
                        <b-col sm="2">
                            <label for="input-large"> ISBN:</label>
                        </b-col>
                        <b-col sm="10">
                            <b-form-input id="input-large" placeholder="Enter the ISBN"></b-form-input>
                        </b-col>
    
                    </b-row>

                     <b-row class="my-1">
    
                        <b-col sm="2">
                            <label for="input-large"> Author:</label>
                        </b-col>
                        <b-col sm="10">
                            <b-form-input id="input-large" placeholder="Enter the Author"></b-form-input>
                        </b-col>
    
                    </b-row>
    
    
                </div>

                 <!-- CD FIELDS -->
                <div v-show="form.claimType === 'CD'">
    
    
                    <b-row class="my-1">
                        <b-col sm="2">
                            <label for="name">Title:</label>
                        </b-col>
                        <b-col sm="10">
                            <b-form-input id="name" placeholder="Enter the Title"></b-form-input>
                        </b-col>
                    </b-row>
    
                    <b-row class="my-1">
    
                        <b-col sm="2">
                            <label for="input-large"> Published Year:</label>
                        </b-col>
                        <b-col sm="10">
                            <b-form-input id="input-large" placeholder="Enter the Published Year"></b-form-input>
                        </b-col>
    
                    </b-row>

                     <b-row class="my-1">
    
                        <b-col sm="2">
                            <label for="input-large"> Length :</label>
                        </b-col>
                        <b-col sm="10">
                            <b-form-input id="input-large" placeholder="Enter the Length"></b-form-input>
                        </b-col>
    
                    </b-row>
                </div>
                 <!-- MOVIE FIELDS -->
                <div v-show="form.claimType === 'Movie'">
    
    
                    <b-row class="my-1">
                        <b-col sm="2">
                            <label for="name">Title:</label>
                        </b-col>
                        <b-col sm="10">
                            <b-form-input id="name" type="text" v-model="title" placeholder="Enter the Title"></b-form-input>
                        </b-col>
                    </b-row>
    
                    <b-row class="my-1">
    
                        <b-col sm="2">
                            <label for="input-large"> Published Year:</label>
                        </b-col>
                        <b-col sm="10">
                            <b-form-input id="input-large" type="text" v-model="publishedYear" placeholder="Enter the Published Year"></b-form-input>
                        </b-col>
    
                    </b-row>

                     <b-row class="my-1">
    
                        <b-col sm="2">
                            <label for="input-large"> Director :</label>
                        </b-col>
                        <b-col sm="10">
                            <b-form-input id="input-large" type="text" v-model="director" placeholder="Enter the Director"></b-form-input>
                        </b-col>
    
                    </b-row>

                     <b-row class="my-1">
    
                        <b-col sm="2">
                            <label for="input-large"> Runtime :</label>
                        </b-col>
                        <b-col sm="10">
                            <b-form-input id="input-large"type="text" v-model="runtime" placeholder="Enter the Runtime"></b-form-input>
                        </b-col>
    
                    </b-row>

                    </b-row>

                     <b-row class="my-1">
    
                        <b-col sm="2">
                            <label for="input-large"> Genre :</label>
                        </b-col>
                        <b-col sm="10">
                            <b-form-input id="input-large" type="text"  v-model="genre" placeholder="Enter the Genre"></b-form-input>
                        </b-col>
    
                    </b-row>

                     </b-row>

                     
                </div>

                 <!-- NEWSPAPER FIELDS -->
                <div v-show="form.claimType === 'NewsPaper'">
    
    
                    <b-row class="my-1">
                        <b-col sm="2">
                            <label for="name">Title:</label>
                        </b-col>
                        <b-col sm="10">
                            <b-form-input id="name" placeholder="Enter the Title"></b-form-input>
                        </b-col>
                    </b-row>
    
                    <b-row class="my-1">
    
                        <b-col sm="2">
                            <label for="input-large"> Published Year:</label>
                        </b-col>
                        <b-col sm="10">
                            <b-form-input id="input-large" placeholder="Enter the Published Year"></b-form-input>
                        </b-col>
    
                    </b-row>

                     <b-row class="my-1">
    
                        <b-col sm="2">
                            <label for="input-large"> Other Newspaper:</label>
                        </b-col>
                        <b-col sm="10">
                            <b-form-input id="input-large" placeholder="Enter the Other"></b-form-input>
                        </b-col>
    
                    </b-row>
                </div>

                
    
            </b-container>
             <span v-if="errorResult" style="color:red">{{errorResult}} </span>

            <br>
            <b-button b-row justify="center" v-bind:disabled="isInputMissing" v-on:click="createMovie(title,publishedYear,director,runtime,genre)">Create Item</b-button>

            <b-button v-row justify="center" v-on:click="goToSubmitPage()">Create Item</b-button>
    
    
        </section>
        <footer class="navbar navbar-dark bg-custom-1 center-collapsed">
            <b> blah blah blah some copyright bs </b>
    
        </footer>
    
    
    </div>
</template>
<script src="./CreateLibItem.js"></script> 


<!--<script>
import OnlineMemberDashboard from "../components/OnlineMemberDashboard";
import SearchLibItems from "../components/SearchLibItems";
import Hello from "../components/Hello";
import Register from "../components/Register";
import LibraryManagementDashboard from "../components/LibraryManagementDashboard";
import MemberLogin from "../components/MemberLogin";
import Router from "../router/index";

export default {
    data() {
        return {
            form: {
                claimType: ""
            },
            claimTypeOptions: [
                { text: "Archive", value: "Archive" },
                { text: "Book", value: "Book" },
                { text: "NewsPaper", value: "NewsPaper" },
                { text: "Movie", value: "Movie" },
                { text: "CD", value: "CD" }
            ]
        }
    },
    methods: {
        goToSubmitPage: function() {
            Router.push({
                path: "/LibraryManagementDashboard",
                name: "LibraryManagementDashboard"
            })
        },
        goToSearchPage: function() {
            Router.push({
                path: "/SearchLibItems",
                name: "SearchLibItems"
            })
        },
        goToRegisterPage: function() {
            Router.push({
                path: "/Register",
                name: "Register"
            })
        },

        goToLoginPage: function() {
            Router.push({
                path: "/MemberLogin",
                name: "MemberLogin"
            })
        }
    }
} 
</script> -->


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
      /*  background: #f0dbce;*/
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
</style>
