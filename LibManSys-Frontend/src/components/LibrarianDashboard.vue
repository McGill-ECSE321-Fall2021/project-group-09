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
                            <p>{{librarian.fullName}}</p>
                        </b-col>
                    </b-row>
        
                    <b-row class="my-1">
                        <b-col sm="2">
                            <label for="input-large">Email:</label>
                        </b-col>
                        <b-col sm="10">
                            <p>{{librarian.librarianEmail}}</p>
                        </b-col>
                    </b-row>
                    <b-row class="my-1">
                        <b-col sm="2">
                            <label for="input-large">Username:</label>
                        </b-col>
                        <b-col sm="10">
                            <p>{{librarian.librarianUsername}}</p>
                        </b-col>
                    </b-row>
                    <b-row class="my-1" color="#00000">
                        <b-col sm="2" color="#00000">
                            <label for="input-default" color="#00000">Password:</label>
                        </b-col>
                        <b-col sm="10">
                            <p>{{librarian.librarianPassword}}</p>
                        </b-col>
                    </b-row>
                    <b-row class="my-1">
                        <b-col sm="2">
                            <label for="input-large">Employee ID Number:</label>
                        </b-col>
                        <b-col sm="10">
                            <p>{{librarian.employeeIDNumber}}</p>
                        </b-col>
                    </b-row>
                    <!-- Associations -->
                    <b-row class="my-1">
                        <b-container>
                            <b-row class="my-2">
                                <b-col>
                                    <label for="input-large">Schedules:</label>
                                </b-col>
                            </b-row>
                            
                            <b-row class="my-2">
                                <b-col>
                                    <v-data-table  class="elevation-1" >
                                        <tr>
                                             <td class="table-text">|  Schedule ID  |</td>
                                             <td class="table-text">|  Opening Time |</td>
                                             <td class="table-text">|  Closing Time |</td>
                                             <td class="table-text">|  Day of Week  |</td>
                                             <td class="table-text">|  Librarian Employee ID  |</td>
                                        </tr>
                                        <tr v-for="schedule in schedules" :key="schedule.scheduleID">
                                            <td class="table-text">{{schedule.scheduleID}}</td>
                                            <td class="table-text">{{schedule.openingTime}}</td> 
                                            <td class="table-text">{{schedule.closingTime}}</td> 
                                            <td class="table-text">{{schedule.dayofWeek}} </td> 
                                            <td class="table-text">{{schedule.librarianEmployeeID}} </td> 
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
                                    <label for="input-large">Loans:</label>
                                </b-col>
                            </b-row>
                            
                            <b-row class="my-2">
                                <b-col>
                                    <v-data-table  class="elevation-1" >
                                        <tr>
                                             <td class="table-text">|  Loan ID  |</td>
                                             <td class="table-text">|  Borrowed Date |</td>
                                             <td class="table-text">|  Returned Date |</td>
                                             <td class="table-text">|  Late Fees  |</td>
                                             <td class="table-text">|  Loan Status  |</td>
                                             <td class="table-text">|  Library Item ID  |</td>
                                             <td class="table-text">|  Member Library Card Number  |</td>
                                             <td class="table-text">|  Librarian Employee ID  |</td>
                                        </tr>
                                        <tr v-for="loan in loans" :key="loan.loanId">
                                            <td class="table-text">{{loan.loanId}}</td>
                                            <td class="table-text">{{loan.borrowedDate}}</td> 
                                            <td class="table-text">{{loan.returnDate}}</td> 
                                            <td class="table-text">{{loan.lateFees}} </td> 
                                            <td class="table-text">{{loan.loanStatus}} </td> 
                                            <td class="table-text">{{loan.libraryItemID}} </td>  
                                            <td class="table-text">{{loan.memberLibCardNumber}} </td>
                                            <td class="table-text">{{loan.librarianEmployeeID}} </td> 
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
                        <b-col class="myColumn">
                            <b-button justify="center" v-on:click="goToLibraryManagmentPage()">Library Managment</b-button>
                        </b-col>
                    </b-row>
                </div>
                
                <!-- if librarian is logged in, display above, else display below content -->
                <div v-else>
                    <b> No user is currently logged in, so no librarian details to display.</b>
                </div>
            </b-container>
    
            <b-container>
                    
            </b-container>

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
    /*height: 100vh;*/
    padding-bottom: 2em;
    background-size: contain;
    background-repeat: no-repeat;
    background-size: 100%;
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
