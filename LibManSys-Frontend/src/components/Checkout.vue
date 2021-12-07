
<template>
    <div class="checkout-body">
    
        <section>
            <br />
            <br />
                <h2> Checkout Library Item </h2>
            <br />
            <br />
        <div v-if="loggedInType === 'librarian' || loggedInType === 'headLibrarian'">
            <b-container fluid>
                <b-row class="my-1" color="#00000">
                    <b-col sm="2" color="#00000">
                        <label for="input-default" color="#00000">Librarian ID:</label>
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
                        <b-form-input id="input-large" type="text" v-model="libCardNumber" placeholder="Enter Member Library Card Number"></b-form-input>
                    </b-col>
                </b-row>
    
                <b-row class="my-1">
                    <b-col sm="2">
                        <label for="name">Library Item ID:</label>
                    </b-col>
                    <b-col sm="10">
                        <b-form-input id="name" type="text" v-model="libraryItemId" placeholder="Enter Library Item ID"></b-form-input>
                    </b-col>
                </b-row>
    
    
            </b-container>

        </div>
        <div v-else>
            <h3>Please log in as a librarian to perform checkouts.</h3>
        </div>
    
         <br>

        <div v-if="loan"> 
            <v-data-table  class="elevation-1" >
                <tr><b-col class="myColumn">
                    <td><myText2>|  LoanId  |</myText2> </td> </b-col>
                    <td><myText2>|  Borrowed Date |</myText2> </td>
                    <td><myText2>|  Libcard # |</myText2> </td>

                    <td><myText2>|  Library Item  |</myText2></td>
                    <td><myText2>|  Loan Status  |</myText2></td>
                    <td><myText2>|  Late Fees  |</myText2></td>
                </tr>
                <tr>
                    <td >  <myText2> {{loan.loanId}}</myText2></td>
                    <td> <myText2> {{loan.borrowedDate}}</myText2></td> 
                    <td> <myText2> {{loan.memberLibCardNumber }}</myText2></td> 
                    <td><myText2>  {{loan.libraryItemID}}</myText2> </td> 
                    <td><myText2> {{loan.loanStatus}}</myText2> </td> 
                    <td> <myText2> {{loan.lateFees}} </myText2> </td> 
                </tr>
                
            </v-data-table> 
        </div>
         
        
         
         <br>
          <span v-if="errorCheckout" style="color:red">{{errorCheckout}} </span>
         <br>
         
         <b-button b-row justify="center" v-bind:disabled="isInputMissing" v-on:click="checkoutItem(libCardNumber, libraryItemId)">Checkout Item</b-button>
         <b-button b-row justify="center" v-on:click="goToSubmitPage()">Library Managment</b-button>

    
        </section>
    
    </div>
</template>

<script src="./Checkout.js"></script>

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

b.nav-link {
    color: #000000;
    font-size: 18px;
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
