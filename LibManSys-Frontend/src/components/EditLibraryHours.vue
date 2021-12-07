
<template>
    <div class="hours-body">
    
        <section>
            <br />
            <br />
            <br />
    
            <b-container fluid>
                <h2> Edit Library Hours </h2>
    <br>
                <b-row class="my-1" color="#00000">
                    <b-col sm="2" color="#00000">
                        <label for="input-default" color="#00000">Monday Start:</label>
                    </b-col>
                    <b-col sm="10">
                        <b-form-input id="input-default" color="#FFFFF" ></b-form-input>
                    </b-col>
                </b-row>
    
                <b-row class="my-1">
                    <b-col sm="2">
                        <label for="input-large">Monday End:</label>
                    </b-col>
                    <b-col sm="10">
                        <b-form-input id="input-default" ></b-form-input>
                    </b-col>
                </b-row>

                <b-row class="my-1" color="#00000">
                    <b-col sm="2" color="#00000">
                        <label for="input-default" color="#00000">Tuesday Start:</label>
                    </b-col>
                    <b-col sm="10">
                        <b-form-input id="input-default" color="#FFFFF" ></b-form-input>
                    </b-col>
                </b-row>
    
                <b-row class="my-1">
                    <b-col sm="2">
                        <label for="input-large">Tuesday End:</label>
                    </b-col>
                    <b-col sm="10">
                        <b-form-input id="input-default" ></b-form-input>
                    </b-col>
                </b-row>

                <b-row class="my-1" color="#00000">
                    <b-col sm="2" color="#00000">
                        <label for="input-default" color="#00000">Wednesday Start:</label>
                    </b-col>
                    <b-col sm="10">
                        <b-form-input id="input-default" color="#FFFFF" ></b-form-input>
                    </b-col>
                </b-row>
    
                <b-row class="my-1">
                    <b-col sm="2">
                        <label for="input-large">Wednesday End:</label>
                    </b-col>
                    <b-col sm="10">
                        <b-form-input id="input-default" ></b-form-input>
                    </b-col>
                </b-row>

                <b-row class="my-1" color="#00000">
                    <b-col sm="2" color="#00000">
                        <label for="input-default" color="#00000">Thursday Start:</label>
                    </b-col>
                    <b-col sm="10">
                        <b-form-input id="input-default" color="#FFFFF" ></b-form-input>
                    </b-col>
                </b-row>
    
                <b-row class="my-1">
                    <b-col sm="2">
                        <label for="input-large">Thursday End:</label>
                    </b-col>
                    <b-col sm="10">
                        <b-form-input id="input-default" ></b-form-input>
                    </b-col>
                </b-row>

                <b-row class="my-1" color="#00000">
                    <b-col sm="2" color="#00000">
                        <label for="input-default" color="#00000">Friday Start:</label>
                    </b-col>
                    <b-col sm="10">
                        <b-form-input id="input-default" color="#FFFFF" ></b-form-input>
                    </b-col>
                </b-row>
    
                <b-row class="my-1">
                    <b-col sm="2">
                        <label for="input-large">Friday End:</label>
                    </b-col>
                    <b-col sm="10">
                        <b-form-input id="input-default" ></b-form-input>
                    </b-col>
                </b-row>

                <b-row class="my-1" color="#00000">
                    <b-col sm="2" color="#00000">
                        <label for="input-default" color="#00000">Saturday Start:</label>
                    </b-col>
                    <b-col sm="10">
                        <b-form-input id="input-default" color="#FFFFF" ></b-form-input>
                    </b-col>
                </b-row>
    
                <b-row class="my-1">
                    <b-col sm="2">
                        <label for="input-large">Saturday End:</label>
                    </b-col>
                    <b-col sm="10">
                        <b-form-input id="input-default" ></b-form-input>
                    </b-col>
                </b-row>

                <b-row class="my-1" color="#00000">
                    <b-col sm="2" color="#00000">
                        <label for="input-default" color="#00000">Sunday Start:</label>
                    </b-col>
                    <b-col sm="10">
                        <b-form-input id="input-default" color="#FFFFF" ></b-form-input>
                    </b-col>
                </b-row>
    
                <b-row class="my-1">
                    <b-col sm="2">
                        <label for="input-large">Sunday End:</label>
                    </b-col>
                    <b-col sm="10">
                        <b-form-input id="input-default" ></b-form-input>
                    </b-col>
                </b-row>

            </b-container>
    
         <br>
            <b-button v-row justify="center">Set Schedule</b-button>


    
        </section>
    </div>
</template>


<script ></script>



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
