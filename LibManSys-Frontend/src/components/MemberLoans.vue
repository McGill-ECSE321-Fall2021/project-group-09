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
    <div class="member-loan-body">
    
        <section>
            <br />
            <br />
            <br />
    

            <b-container class="bv-example-row">
                <myText> Member Loans </myText>
              
            
  <div  > 
    <v-data-table  class="elevation-1" >
      <tr><b-col class="myColumn">
          <td><myText2>|  LoanId  |</myText2> </td> </b-col>
          <td><myText2>|  Borrowed Date |</myText2> </td>
                    <td><myText2>|  Libcard # |</myText2> </td>

                    <td><myText2>|  Library Item  |</myText2></td>
                    <td><myText2>|  Loan Status  |</myText2></td>
                    <td><myText2>|  Late Fees  |</myText2></td>
      </tr>

      <tr v-for="loan in loans" :key="loan">
          <td >  
          <myText2> {{loan.loanId}}</myText2></td>

                     <td> <myText2> {{loan.borrowedDate}}</myText2></td> 
                                   <td> <myText2> {{loan.memberLibCardNumber
}}</myText2></td> 

                <td>
            <myText2>  {{loan.libraryItemID}}</myText2> </td> 
              <td>
             <myText2> {{loan.loanStatus}}</myText2> </td> 
              <td>
             <myText2> {{loan.lateFees}} </myText2> </td> 
      </tr>
      
    </v-data-table> 
   
    </div>
    
           

    
    
    
            </b-container>
           
            
      <b-col class="myColumn">
    
                <td>
                    <b-button v-row justify="center" v-on:click="goToMemberPage()">Go Back to Member Profile</b-button>
                </td>

                
            </b-col>
        </section>
    
    </div>
</template>

<script src="./MemberLoans.js"></script> 


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
    color: #622A0F;
    font: "Times";
    font-size: 30px;
    font-weight: 100;
}
myText2 {
    color: #7E481C;
    font: "Times";
    font-size: 18px;
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
    color: #7E481C;
}
/*Resize the wrap to see the search bar change!*/
.wrap {
    width: 40%;
    position: absolute;
    top: 60%;
    left: 50%;
    transform: translate(-50%, -50%);
}
.v-data-table > .v-data-table__wrapper > table {
    border-spacing: 0 0.5rem;
}
input,
::placeholder {
    font-family: "Lato", sans-serif;
    font-size: 18px;
    font-weight: 600;
    color: #ffffff;
}
</style>