
<template>
    <div class="reserve-body">
    
        <h2> Reserve Library Item </h2>
        <br>
        <section>
             <div v-if="loggedInType === 'librarian' || loggedInType === 'headLibrarian'">
                <br />
                <br />
                <br />

                <b-container fluid>
                    <b-row class="my-1" color="#00000">
                        <b-col sm="2" color="#00000">
                            <label for="input-default" color="#00000">Librarian Employee ID Number:</label>
                        </b-col>
                        <b-col sm="10">
                            <p>{{loggedInUser}}</p>
                        </b-col>
                    </b-row>

                    <b-row class="my-1">
                        <b-col sm="2">
                            <label for="input-large">Member Library Card Number:</label>
                        </b-col>
                        <b-col sm="10">
                            <b-form-input id="input-large" v-model="libCardNumber" placeholder="Member Lib Card Number"></b-form-input>
                        </b-col>
                    </b-row>

                    <b-row class="my-1">
                        <b-col sm="2">
                            <label for="name">Library Card ID:</label>
                        </b-col>
                        <b-col sm="10">
                            <b-form-input id="name" v-model="libraryItemID" placeholder="Enter the Library Card ID"></b-form-input>
                        </b-col>
                    </b-row>


                </b-container>

                <br />

                <div v-if="reservedItem">
                    <p>Reserved library item {{reservedItem.title}} with ID {{reservedItem.libraryItemID}} for member with library card number {{reservedItem.reservingMemberLibCardNumber}}</p>
                </div>

                        
                <br>
                <span v-if="error" style="color:red">{{error}} </span>
                <br>

                <br>
                <b-button justify="center" v-on:click="reserveItem(libCardNumber, libraryItemID)">Reserve Item</b-button>
                <b-button justify="center" v-on:click="goToSubmitPage()">Library Management</b-button>


        </div>
        <div v-else>
            <h3>Login as a librarian to use library management tools.</h3>
        </div>

    
        </section>
    </div>
</template>


<script src="./Reserve.js"></script>



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
        background: #ffffff;
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
