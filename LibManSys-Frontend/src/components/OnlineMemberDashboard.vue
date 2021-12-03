<template>
  <div class="OnlineMemberDashboard">
    <section>
      <br />
      <br />
      <br />

     
      <b-container fluid>
        <h2>  ONLINE MEMBER PROFILE INFORMATION  </h2>
        <br />
        <br />

        <div v-if="onlineMember">
            <b-row class="my-1" color="#00000">
                <b-col sm="2" color="#00000">
                    <label for="input-default" color="#00000">Account ID:</label>
                </b-col>
                <b-col sm="10">
                    <p>{{onlineMember.accountID}}</p>
                </b-col>
            </b-row>

            <b-row class="my-1" color="#00000">
                <b-col sm="2" color="#00000">
                    <label for="input-default" color="#00000">Name:</label>
                </b-col>
                <b-col sm="10">
                    <p>{{onlineMember.fullName}}</p>
                </b-col>
            </b-row>

            <b-row class="my-1">
                <b-col sm="2">
                    <label for="input-large">Email:</label>
                </b-col>
                <b-col sm="10">
                    <p>{{onlineMember.memberEmail}}</p>
                </b-col>
            </b-row>
            <b-row class="my-1">
                <b-col sm="2">
                    <label for="input-large">Username:</label>
                </b-col>
                <b-col sm="10">
                    <p>{{onlineMember.memberUsername}}</p>
                </b-col>
            </b-row>
            <b-row class="my-1" color="#00000">
                <b-col sm="2" color="#00000">
                    <label for="input-default" color="#00000">Password:</label>
                </b-col>
                <b-col sm="10">
                    <p>{{onlineMember.memberPassword}}</p>
                </b-col>
            </b-row>
            <b-row class="my-1">
                <b-col sm="2">
                    <label for="input-large">Library Card Number:</label>
                </b-col>
                <b-col sm="10">
                    <p>{{onlineMember.libCardNumber}}</p>
                </b-col>
            </b-row>
            <b-row class="my-1">
                <b-col sm="2">
                    <label for="input-large">Address:</label>
                </b-col>
                <b-col sm="10">
                    <p>{{onlineMember.address}}</p>
                </b-col>
            </b-row>
            <b-row class="my-1">
                <b-col sm="2">
                    <label for="input-large">Phone Number:</label>
                </b-col>
                <b-col sm="10">
                    <p>{{onlineMember.phoneNumber}}</p>
                </b-col>
            </b-row>
            <b-row class="my-1">
                <b-col sm="2">
                    <label for="input-large">Fees owed:</label>
                </b-col>
                <b-col sm="10">
                    <p>{{onlineMember.amountOwed}}</p>
                </b-col>
            </b-row>
            <b-row class="my-1">
                <b-col sm="2">
                    <label for="input-large">Active Loans:</label>
                </b-col>
                <b-col sm="10">
                    <p>{{onlineMember.activeLoans}}</p>
                </b-col>
            </b-row>
            <b-row class="my-1">
                <b-col sm="2">
                    <label for="input-large">Verified Resident:</label>
                </b-col>
                <b-col sm="10">
                    <p>{{onlineMember.verifiedResident}}</p>
                </b-col>
            </b-row>
            <b-row class="my-1">
                <b-container>
                    <b-row class="my-2">
                        <b-col>
                            <label for="input-large">Bookings:</label>
                        </b-col>
                    </b-row>
                    
                    <b-row class="my-2">
                        <b-col>
                            <v-data-table  class="elevation-1" >
                                <tr>
                                      <td class="table-text">|  Booking ID  |</td>
                                      <td class="table-text">|  Start Time |</td>
                                      <td class="table-text">|  End Time |</td>
                                      <td class="table-text">|  Booking Date  |</td>
                                      <td class="table-text">|  Member Library Card Number  |</td>
                                      <td class="table-text">|  Librarian Employee ID  |</td>
                                </tr>
                                <tr v-for="booking in bookings" :key="booking.bookingID">
                                    <td class="table-text">{{booking.bookingID}}</td>
                                    <td class="table-text">{{booking.bookingStartTime}}}</td> 
                                    <td class="table-text">{{booking.bookingEndTime}}</td> 
                                    <td class="table-text">{{booking.bookingDate}} </td> 
                                    <td class="table-text">{{booking.memberLibCardNumber}} </td> 
                                    <td class="table-text">{{booking.librarianEmployeeID}} </td> 
                                </tr>
                                
                            </v-data-table> 
                        </b-col>
                    </b-row>
                </b-container>
            </b-row>
            <b-row class="my-1">
                <b-container>
                    <b-row class="my-2">
                        <b-col>
                            <label for="input-large">Reserved Library Items:</label>
                        </b-col>
                    </b-row>
                    
                    <b-row class="my-2">
                        <b-col>
                            <v-data-table  class="elevation-1" >
                                <tr>
                                      <td class="table-text">|  Library Item ID  |</td>
                                      <td class="table-text">|  Title |</td>
                                      <td class="table-text">|  Published Year |</td>
                                      <td class="table-text">|  Item Status  |</td>
                                      <td class="table-text">|  Library Item Type  |</td>
                                </tr>
                                <tr v-for="item in reservedLibraryItems" :key="item.libraryItemID">
                                    <td class="table-text">{{item.libraryItemID}}</td>
                                    <td class="table-text">{{item.title}}</td> 
                                    <td class="table-text">{{item.publishedYear}}</td> 
                                    <td class="table-text">{{item.itemStatus}} </td> 
                                    <td class="table-text">{{item.type}}</td> 
                                    <b-button type="submit" class="cancel-reserve-button" v-on:click="cancelReserveItem(item.libraryItemID)">
                                        Cancel Reservation
                                    </b-button>
                                </tr>
                                
                            </v-data-table> 
                        </b-col>
                    </b-row>
                </b-container>
            </b-row>
        </div>
        <!-- if librarian is logged in, display above, else display below content -->
        <div v-else>
            <b> No user is currently logged in, so no online member details to display.</b>
        </div>
      
      </b-container>

      <b-container>

              <b-button v-on:click="goToLoansPage()">Loans</b-button>
        
          
              <b-button v-on:click="goToEditPage()">Edit Profile</b-button>
          
      </b-container>

      <br />
      <br />

    </section>   

  </div>
</template>


<script src='./DashboardHandling.js'></script>


<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
OnlineMemberDashboard {
  color: firebrick;
}

.cancel-reserve-button {
  padding: 0.25em 0.5em;
  font-size: 0.8em;
  background-color: #ed3d37;
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