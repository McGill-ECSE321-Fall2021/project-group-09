<template>

  <div class="OnlineMemberDashboard">
  
  <section>
      <br />
      <br />
           <br />

     
<b-container fluid>
 
 <h2>  PROFILE INFORMATION  </h2>



<b-row>
    <b-col sm="2" > 
      <label for="fullName" >Full Name:</label>
    </b-col>
  </b-row>

 <b-row>
    <b-col sm="2" > 
      <label for="libCardNumber" >Library Card Number: </label>
    </b-col>
    <b-col sm="2"> 
      <label for="libCardNumber" >TBD</label>
    </b-col>
</b-row>

<b-row>
    <b-col sm="2" > 
      <label for="address">Address:</label>
    </b-col>
  </b-row>

<b-row>
    <b-col sm="2">
      <label for="phoneNumber">Phone Number:</label>
    </b-col>
     </b-row>

<b-row>
    <b-col sm="2" > 
      <label for="amountOwed" >Amount Owed: </label>
    </b-col>
    <b-col sm="2"> 
      <label for="amountOwed" >TBD</label>
    </b-col>
</b-row>

<b-row>
    <b-col sm="2" > 
      <label for="activeLoans" >Number of Active Loans: </label>
    </b-col>
    <b-col sm="2"> 
      <label for="activeLoans" >TBD</label>
    </b-col>
</b-row>
  
<b-row>
    <b-col sm="2" > 
      <label for="isVerifiedResident" >Residency Verification Status: </label>
    </b-col>
    <b-col sm="2" > 
      <label for="isVerifiedResident" >Verified? </label>
    </b-col>
  </b-row>

<b-row>
    <b-col sm="2" > 
      <label for="emailAddress" >Email Address:</label>
    </b-col>
</b-row>

<b-row>
    <b-col sm="2" > 
      <label for="username" >Username:</label>
    </b-col>
</b-row>
   
 
</b-container>
      <b-col class="myColumn">
    
                <td>
                    <b-button v-row justify="center" v-on:click="goToLoansPage()">Loans</b-button>
                </td>
    
            </b-col>
     
    </section>   

  </div>
</template>


<script src='./DashboardHandling.js'>

</script>


<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>


OnlineMemberDashboard {
  color: firebrick;
}

.bg-custom-1 {
  background: linear-gradient(
    200deg,
    rgba(0, 0, 0, 1),
    rgba(0, 0, 0, 0.8),
    rgba(0, 0, 0, 0)
  );
}

b.nav-link {
  color: firebrick;
  font-size: 18px;
}
b-navbar {
  background-color: firebrick;
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
  color: firebrick;
  background-color: #fffeee;
  background-size: contain;
  background-repeat: no-repeat;
  background-size: 100%;
  -ms-overflow-style: none; /* IE and Edge */
  scrollbar-width: none; /* Firefox */
  /*margin: auto;*/
  /*background-image: linear-gradient(
      115deg,
      rgba(0, 0, 0, 0.9),
      rgba(0, 0, 0, 0.9),
      rgba(43, 16, 7, 0.8)
    ),
    url(../assets/library-bkg.jpg);*/
    

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
h1, h2 {
  color: whitesmoke;
  font: 'American TypeWriter', serif;
  font-size: 40px;
  font-weight: normal;
  background-color: firebrick;
  width: 100%;
  margin: auto;
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
  color: firebrick;
}
myText{
  color: firebrick;
  font: "Times";
  font-size: 30px;
  font-weight: 100;

}
b {
  font: "Lato", sans-serif;
  font-weight: 100;
  color: firebrick;
  font-size: 18px;
}
b-nav-item {
  color: firebrick;
  font-size: 18px;
}
.nav-left {
  display: flex;
}
.nav-right {
  display: flex;
}

#container{
    width: 65%;
    margin: 0 auto;
   /* color: firebrick;
  background-color: bisque;
  background-size: contain;
  background-repeat: no-repeat;
  background-size: 100%;
  -ms-overflow-style: none; /* IE and Edge */
  /*scrollbar-width: none; /* Firefox */
   /*background-image: linear-gradient(
      115deg,
      rgba(0, 0, 0, 0.9),
      rgba(0, 0, 0, 0.9),
      rgba(43, 16, 7, 0.8)
    ),
    url(../assets/library-bkg.jpg);*/
}
button {
    font-family: "Segoe UI", Tahoma, Geneva, Verdana, sans-serif;
    padding: 16px 32px;
    background-color: firebrick;
    color: whitesmoke;
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
