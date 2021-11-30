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
    <div id="librarian-dashboard-body">
        <section>
            <br />
            <br />
            <br />
    
    
            <b-container fluid>
                <b class="librarian-dashboard-title"> Librarian Profile </b>
                <br />
                <br />

                <div v-if="librarian">
                    <b-row class="my-1" color="#00000">
                        <b-col sm="2" color="#00000">
                            <label for="input-default" color="#00000">Account ID:</label>
                        </b-col>
                        <b-col sm="10">
                            <p>{{librarian.accountID}}</p>
                        </b-col>
                    </b-row>

                    <b-row class="my-1" color="#00000">
                        <b-col sm="2" color="#00000">
                            <label for="input-default" color="#00000">Name:</label>
                        </b-col>
                        <b-col sm="10">
                            <p>namme</p>
                        </b-col>
                    </b-row>
        
                    <b-row class="my-1">
                        <b-col sm="2">
                            <label for="input-large">Email:</label>
                        </b-col>
                        <b-col sm="10">
                            <p>namme</p>
                        </b-col>
                    </b-row>
                    <b-row class="my-1">
                        <b-col sm="2">
                            <label for="input-large">Username:</label>
                        </b-col>
                        <b-col sm="10">
                            <p>namme</p>
                        </b-col>
                    </b-row>
                    <b-row class="my-1" color="#00000">
                        <b-col sm="2" color="#00000">
                            <label for="input-default" color="#00000">Password:</label>
                        </b-col>
                        <b-col sm="10">
                            <p>namme</p>
                        </b-col>
                    </b-row>
                    <b-row class="my-1">
                        <b-col sm="2">
                            <label for="input-large">Employee ID Number:</label>
                        </b-col>
                        <b-col sm="10">
                            <p>namme</p>
                        </b-col>
                    </b-row>
                    <!-- Associations -->
                    <b-row class="my-1">
                        <b-col sm="2">
                            <label for="input-large">Schedules:</label>
                        </b-col>
                        <b-col sm="10">
                            <p>namme</p>
                        </b-col>
                    </b-row>
                    <b-row class="my-1">
                        <b-col sm="2">
                            <label for="input-large">Loans:</label>
                        </b-col>
                        <b-col sm="10">
                            <p>namme</p>
                        </b-col>
                    </b-row>
                    <b-row class="my-1">
                        <b-col sm="2">
                            <label for="input-large">Bookings:</label>
                        </b-col>
                        <b-col sm="10">
                            <p>namme</p>
                        </b-col>
                    </b-row>
                </div>
                <!-- if librarian is logged in, display above, else display below content -->
                <div v-else>
                    <b> No user is currently logged in, so no librarian details to display.</b>
                </div>
            </b-container>
    
            <b-col class="myColumn">
                <td>
                    <b-button justify="center" v-on:click="goToLibraryManagmentPage()">Library Managment</b-button>
                </td>
            </b-col>
    
        </section>
        
    </div>
        
</template>


<script src="./LibrarianDashboard.js"></script>


<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>

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
     /* background: #f0dbce;*/
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

.librarian-dashboard-title {
  color:#a85f32;
  font: "Times";
  font-size: 30px;
  font-weight: 100;

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
