
<template>
    <div class="reserve-body">
    
        <section>
            <br />
            <br />
            <br />
    
            <b-container fluid>
                <h2> Reserve Library Item </h2>
    <br>
                <b-row class="my-1" color="#00000">
                    <b-col sm="2" color="#00000">
                        <label for="input-default" color="#00000">Library Item ID:</label>
                    </b-col>
                    <b-col sm="10">
                        <b-form-input id="input-default" color="#FFFFF" placeholder="Enter the Library Item ID"></b-form-input>
                    </b-col>
                </b-row>
    
                <b-row class="my-1">
                    <b-col sm="2">
                        <label for="input-large">Borrowing Date:</label>
                    </b-col>
                    <b-col sm="10">
                        <b-form-input id="input-large" placeholder="Enter the Borrowing Date"></b-form-input>
                    </b-col>
                </b-row>
    
                <b-row class="my-1">
                    <b-col sm="2">
                        <label for="name">Library Card ID:</label>
                    </b-col>
                    <b-col sm="10">
                        <b-form-input id="name" placeholder="Enter the Library Card ID"></b-form-input>
                    </b-col>
                </b-row>
    
    
            </b-container>
    
         <br>
         <b-button v-row justify="center" v-on:click="goToSubmitPage()">Reserve Item</b-button>

    
        </section>
    </div>
</template>


<script>
import Router from "../router/index";

export default {
    data() {
        return {
            types: []
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
</script>


<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>

b.nav-link {
    color: #000000;
    font-size: 18px;
}

b-navbar {
    background-color: #4e1d04;
}

div {
        background: #f0dbce;
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
