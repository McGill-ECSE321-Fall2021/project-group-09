
<template>
    <div class="create-lib-item-body">    
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
                            <b-form-input id="name" v-model="title" placeholder="Enter the Title"></b-form-input>
                        </b-col>
                    </b-row>
    
                    <b-row class="my-1">
    
                        <b-col sm="2">
                            <label for="input-large"> Published Year:</label>
                        </b-col>
                        <b-col sm="10">
                            <b-form-input id="input-large" v-model="publishedYear" placeholder="Enter the Published Year"></b-form-input>
                        </b-col>
    
                    </b-row>

                 <b-row class="my-1">
    
                        <b-col sm="2">
                            <label for="input-large"> Institution :</label>
                        </b-col>
                        <b-col sm="10">
                            <b-form-input id="input-large" v-model="institution"  placeholder="Enter the Institution"></b-form-input>
                        </b-col>
    
                    </b-row>
                                <b-button b-row justify="center"  v-on:click="createArchive(title,publishedYear,institution)">Create Archive</b-button>

                </div>

                 <!-- Book FIELDS -->
                <div v-show="form.claimType === 'Book'">
    
    
                    <b-row class="my-1">
                        <b-col sm="2">
                            <label for="name">Title:</label>
                        </b-col>
                        <b-col sm="10">
                            <b-form-input id="name" v-model="title" placeholder="Enter the Title"></b-form-input>
                        </b-col>
                    </b-row>
                    <b-row class="my-1">
    
                        <b-col sm="2">
                            <label for="input-large"> Author :</label>
                        </b-col>
                        <b-col sm="10">
                            <b-form-input id="input-large" v-model="author" placeholder="Enter the Author"></b-form-input>
                        </b-col>
    
                    </b-row>
    
                    <b-row class="my-1">
    
                        <b-col sm="2">
                            <label for="input-large"> Published Year:</label>
                        </b-col>
                        <b-col sm="10">
                            <b-form-input id="input-large" v-model="publishedYear" placeholder="Enter the Published Year"></b-form-input>
                        </b-col>
    
                    </b-row>

                     <b-row class="my-1">
    
                        <b-col sm="2">
                            <label for="input-large"> Length :</label>
                        </b-col>
                        <b-col sm="10">
                            <b-form-input id="input-large" v-model="numPages" placeholder="Enter the Number of Pages"></b-form-input>
                        </b-col>
    
                    </b-row>

                     <b-row class="my-1">
    
                        <b-col sm="2">
                            <label for="input-large"> ISBN :</label>
                        </b-col>
                        <b-col sm="10">
                            <b-form-input id="input-large" v-model="ISBN" placeholder="Enter the ISBN"></b-form-input>
                        </b-col>
    
                    </b-row>

                     <b-row class="my-1">
    
                        <b-col sm="2">
                            <label for="input-large"> Publisher :</label>
                        </b-col>
                        <b-col sm="10">
                            <b-form-input id="input-large" v-model="publisher" placeholder="Enter the Publisher"></b-form-input>
                        </b-col>
    
                    </b-row>
                        <b-button b-row justify="center"  v-on:click="createBook(title,publishedYear,author,publisher,ISBN,numPages)">Create Book</b-button>

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
                            <b-form-input id="input-large" type="text" v-model="runtime" placeholder="Enter the Runtime"></b-form-input>
                        </b-col>
    
                    </b-row>

                     <b-row class="my-1">
    
                        <b-col sm="2">
                            <label for="input-large"> Genre :</label>
                        </b-col>
                        <b-col sm="10">
                            <b-form-input id="input-large" type="text"  v-model="genre" placeholder="Enter the Genre"></b-form-input>
                        </b-col>
    
                    </b-row>

                <b-button b-row justify="center"  v-on:click="createMovie(title,publishedYear,director,runtime,genre)">Create Movie</b-button>


                     
                </div>

                <!-- MusicAlbum FIELDS -->
                <div v-show="form.claimType === 'MusicAlbum'">
    
    
                    <b-row class="my-1">
                        <b-col sm="2">
                            <label for="name">Title:</label>
                        </b-col>
                        <b-col sm="10">
                            <b-form-input id="name" v-model="title" placeholder="Enter the Title"></b-form-input>
                        </b-col>
                    </b-row>
    
                    <b-row class="my-1">
    
                        <b-col sm="2">
                            <label for="input-large"> Published Year:</label>
                        </b-col>
                        <b-col sm="10">
                            <b-form-input id="input-large" v-model="publishedYear" placeholder="Enter the Published Year"></b-form-input>
                        </b-col>
    
                    </b-row>

                 <b-row class="my-1">
    
                        <b-col sm="2">
                            <label for="input-large"> Genre :</label>
                        </b-col>
                        <b-col sm="10">
                            <b-form-input id="input-large" v-model="genre"  placeholder="Enter the Genre"></b-form-input>
                        </b-col>
    
                    </b-row>
                    <b-row class="my-1">
    
                        <b-col sm="2">
                            <label for="input-large"> Artist :</label>
                        </b-col>
                        <b-col sm="10">
                            <b-form-input id="input-large" v-model="artist"  placeholder="Enter the Artist"></b-form-input>
                        </b-col>
    
                    </b-row>
                     <b-row class="my-1">
    
                        <b-col sm="2">
                            <label for="input-large"> Number of Songs :</label>
                        </b-col>
                        <b-col sm="10">
                            <b-form-input id="input-large" v-model="numSongs"  placeholder="Enter the Number of Songs"></b-form-input>
                        </b-col>
    
                    </b-row>

                      <b-row class="my-1">
    
                        <b-col sm="2">
                            <label for="input-large"> Length :</label>
                        </b-col>
                        <b-col sm="10">
                            <b-form-input id="input-large" v-model="albumLengthInMinutes"  placeholder="Enter the Length of the Album in Minutes"></b-form-input>
                        </b-col>
    
                    </b-row>

                                <b-button b-row justify="center"  v-on:click="createMusicAlbum(title,publishedYear,genre,artist,numSongs,albumLengthInMinutes)">Create Music Album</b-button>

                </div>

                 <!-- NEWSPAPER FIELDS -->
                <div v-show="form.claimType === 'NewsPaper'">
    
    
                    <b-row class="my-1">
                        <b-col sm="2">
                            <label for="name">Title:</label>
                        </b-col>
                        <b-col sm="10">
                            <b-form-input id="name" v-model="title" placeholder="Enter the Title"></b-form-input>
                        </b-col>
                    </b-row>
    
                    <b-row class="my-1">
    
                        <b-col sm="2">
                            <label for="input-large"> Published Year:</label>
                        </b-col>
                        <b-col sm="10">
                            <b-form-input id="input-large" v-model="publishedYear" placeholder="Enter the Published Year"></b-form-input>
                        </b-col>
    
                    </b-row>

                     <b-row class="my-1">
    
                        <b-col sm="2">
                            <label for="input-large"> Journal Name:</label>
                        </b-col>
                        <b-col sm="10">
                            <b-form-input id="input-large" v-model="journalName" placeholder="Enter the Journal Name"></b-form-input>
                        </b-col>
    
                    </b-row>
                     <b-row class="my-1">
    
                        <b-col sm="2">
                            <label for="input-large"> Edition:</label>
                        </b-col>
                        <b-col sm="10">
                            <b-form-input id="input-large" v-model="edition" placeholder="Enter the Edition "></b-form-input>
                        </b-col>
    
                    </b-row>
                     <b-row class="my-1">
    
                        <b-col sm="2">
                            <label for="input-large"> Chief Editor:</label>
                        </b-col>
                        <b-col sm="10">
                            <b-form-input id="input-large" v-model="chiefEditior" placeholder="Enter the Chief Editor "></b-form-input>
                        </b-col>
    
                    </b-row>
                                <b-button b-row justify="center"  v-on:click="createNewspaper(title,publishedYear,journalName,edition,chiefEditior)">Create NewsPaper</b-button>

                </div>

                
    
            </b-container>
             <span v-if="errorResult" style="color:red">{{errorResult}} </span>

            <br>


    <br> <br>            <b-button v-row justify="center" v-on:click="goToSubmitPage()">Go Back to Managment </b-button> 
    
    
        </section>   
    
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
