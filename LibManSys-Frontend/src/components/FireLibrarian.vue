
<template>
    <div class="reserve-body">
    
        <section>
            <br />
            <h2> Fire Librarian </h2>
            <br />
            <br />
    
            <div v-if="loggedInType === 'headLibrarian'">

                <b-container fluid>
                    
                    <br>
                    <b-row class="my-1" color="#00000">
                        <b-col sm="2" color="#00000">
                            <label for="input-default" color="#00000">Librarian Employee ID:</label>
                        </b-col>
                        <b-col sm="10">
                            <b-form-input v-model="employeeIDNumber" id="input-default" color="#FFFFF" placeholder="Enter the Librarian's Employee ID"></b-form-input>
                        </b-col>
                    </b-row>
                
        
        
                </b-container>
        
                <div v-if="firedLibrarian">
                    <br />
                    <p>
                        Fired librarian with employee ID number: {{firedLibrarian.employeeIDNumber}}
                    </p>
                    <br />
                </div>

                
                <div v-if="error">
                    <br />
                    <span  style="color: red">{{ error }} </span>
                    <br />
                </div>

                <br>
                <b-button v-row justify="center" v-on:click="fireLibrarian(employeeIDNumber)">Fire Librarian</b-button>
                <b-button v-row justify="center" v-on:click="goToPreviousPage()">Library Management</b-button>

            </div>
            <div v-else>
                <h3>Login as a head librarian to fire librarians.</h3>
            </div>

    
        </section>
    </div>
</template>


<script src="./FireLibrarian.js"></script>



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
