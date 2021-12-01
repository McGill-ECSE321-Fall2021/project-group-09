<template>

  <div class="OnlineMemberDashboard">
  
  <section>
      <br />
      <br />
           <br />

     
<b-container fluid>
 
 <h2>  PROFILE INFORMATION  </h2>

<table class="paddingBetweenCols">

 <tr>
    <td>Full Name: </td>
        <td>{{profile.fullName}}</td>
</tr>

 <tr>
    <td>Library Card Number: </label>
      <td>{{profile.libCardNum}}</td>
</tr>

 <tr>
    <td>Address: </label>
      <td>{{profile.address}}</td>
</tr>

 <tr>
    <td>Phone Number: </label>
      <td>{{profile.phoneNumber}}</td>
</tr>

 <tr>
    <td>Fees Owed: </label>
      <td>{{profile.amountOwed}}</td>
</tr>

 <tr>
    <td>Active Loans: </label>
      <td>{{profile.activeLoans}}</td>
</tr>
  
 <tr>
    <td>Residency Verification status: </label>
      <td>{{profile.isVerifiedResident}}</td>
</tr>

 <tr>
    <td>Email Address: </label>
      <td>{{profile.emailAddress}}</td>
</tr>

 <tr>
    <td>Username: </label>
      <td>{{0}}</td>
</tr>
   
 
 </table>

</b-container>
  
<b-container>
    <b-button v-on:click="goToLoansPage()">Loans</b-button>
    
    <button 
        
        @click()="EditMemberInformation()"
        type="EditMemberbutton"
        class="btn btn-primary"
        >
          <font size="3"><b>Edit Profile</b></font>

        </button>


    <button
            @click="Logout()"
            type="Logoutbutton"
            class="btn btn-primary"
          >
            
            <font size="3"><b>Logout</b></font>

      </button>

    
     
</b-container>
    

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
