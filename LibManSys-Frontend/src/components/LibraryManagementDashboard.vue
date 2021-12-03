<template>
  <div class="library-management-dashboard-body">
    <section>
      <br />
      <br />
      <br />

      <b-container>
        <h1>Library Management Dashboard</h1>
        <br />
        <br />
        <br />

        <b-row class="text-center myRow" align-v="center">
          <b-col class="myColumn">
            <b-button v-on:click="goToCreateLibItemPage()"
              >Create a Library Item</b-button
            >
          </b-col>
          <b-col class="myColumn">
            <td>
              <b-button v-on:click="goToReturnPage()"
                >Return a Library Item</b-button
              >
            </td>
          </b-col>
          <b-col class="myColumn">
            <td>
              <b-button  justify="center" v-on:click="goToCheckoutPage()"
                >Checkout a Library Item</b-button
              >
            </td>
          </b-col>
          <!-- Starting from here are functions that have not been added to the methods-->
          <b-col class="myColumn">
            <td>
              <b-button  justify="center" v-on:click="goToReservePage()"
                >Reserve a Library Item
              </b-button>
            </td>
          </b-col>
        </b-row>
        
        <br />

        <b-row class="text-center myRow" align-v="center">
          <b-col class="myColumn">
            <td>
              <b-button
                justify="center"
                v-on:click="goToRenewMemberLoanPage()"
                >Renew Member Loan</b-button
              >
            </td>
          </b-col>
          <b-col class="myColumn">
            <td>
              <b-button
                
                justify="center"
                v-on:click="goToCreateNewMemberPage()"
                >Create New Member</b-button
              >
            </td>
          </b-col>
        </b-row>

        <br />

        <b-row class="text-center myRow" align-v="center">
          <b-col class="myRow">
            <td>
              <b-button
                
                justify="center"
                v-on:click="goToCreateBookingsPage()"
                >Create New Booking</b-button
              >
            </td>
          </b-col>
          <b-col class="myColumn">
            <td>
              <b-button
                
                justify="center"
                v-on:click="goToUpdateBookingsPage()"
                >Update Booking</b-button
              >
            </td>
          </b-col>
          <b-col class="myColumn">
            <td>
              <b-button
                
                justify="center"
                v-on:click="goToViewBookingsPage()"
                >View Bookings</b-button
              >
            </td>
          </b-col>
          <b-col class="myColumn">
            <td>
              <b-button
                
                justify="center"
                v-on:click="goToDeleteBookingsPage()"
              >
                Delete Bookings</b-button
              >
            </td>
          </b-col>
        </b-row>

        <br />

        <!-- Head librarian options-->
        <div v-if="loggedInType === 'headLibrarian'" >

          <b-row class="text-center">
            <h3 style="margin: auto"> Head Librarian Management </h3>
          </b-row>

          <br />

          <b-row class="text-center">
            
            <b-col class="myColumn">
              <td>
                <b-button  justify="center" v-on:click="goToEditHoursPage()"
                  >Edit Library Hours</b-button
                >
              </td>
            </b-col>
            <b-col class="myColumn">
              <td>
                <b-button
                  
                  justify="center"
                  v-on:click="goToCreateLibrarianSchedulePage()"
                  >Create Librarian Schedule</b-button
                >
              </td>
            </b-col>
            <b-col class="myColumn">
              <td>
                <b-button  justify="center" v-on:click="goToFirePage()"
                  >Fire Librarian</b-button
                >
              </td>
            </b-col>
            <b-col class="myColumn">
              <td>
                <b-button  justify="center" v-on:click="goToHirePage()">
                  Hire Librarian</b-button
                >
              </td>
            </b-col>
          </b-row>
        </div>
        <br />
        <br />
        
      </b-container>
    </section>
  </div>
</template>

<script>
import Router from "../router/index";

export default {
  name: "hello",
  data() {
    return {
      loggedInUser: '',
      loggedInType: ''
    };
  },
  created: function() {
    // check if cookie is set for current user
    var userLoggedIn = $cookies.isKey("loggedInUser")
    if (userLoggedIn == true) {
        this.loggedInUser = $cookies.get("loggedInUser")
        this.loggedInType = $cookies.get("loggedInType")
    }
  },
  methods: {
    goToReturnPage: function () {
      Router.push({
        path: "/Return",
        name: "Return",
      });
    },
    goToCheckoutPage: function () {
      Router.push({
        path: "/Checkout",
        name: "Checkout",
      });
    },
    goToRegisterPage: function () {
      Router.push({
        path: "/Register",
        name: "Register",
      });
    },
    goToSubmitPage: function () {
      Router.push({
        path: "/MemberLogin",
        name: "MemberLogin",
      });
    },
    goToSearchPage: function () {
      Router.push({
        path: "/SearchLibItems",
        name: "SearchLibItems",
      });
    },
    goToLibraryManagmentPage: function () {
      Router.push({
        path: "/LibraryManagementDashboard",
        name: "LibraryManagementDashboard",
      });
    },
    goToReservePage: function () {
      Router.push({
        path: "/Reserve",
        name: "Reserve",
      });
    },
    goToCreateLibItemPage: function () {
      Router.push({
        path: "/CreateLibItem",
        name: "CreateLibItem",
      });
    },
    goToCreateNewMemberPage: function () {
      Router.push({
        path: "/CreateNewMember",
        name: "CreateNewMember",
      });
    },
    goToRenewMemberLoanPage: function () {
      Router.push({
        path: "/RenewMemberLoan",
        name: "RenewMemberLoan",
      });
    },
    goToHirePage: function () {
      Router.push({
        path: "/HireLibrarian",
        name: "HireLibrarian",
      });
    },
    goToFirePage: function () {
      Router.push({
        path: "/FireLibrarian",
        name: "FireLibrarian",
      });
    },goToEditHoursPage: function () {
      Router.push({
        path: "/EditLibraryHours",
        name: "EditLibraryHours",
      });
    },goToCreateLibrarianSchedulePage: function () {
      Router.push({
        path: "/CreateLibrarianSchedule",
        name: "CreateLibrarianSchedule",
      });
    },
    goToCreateBookingsPage: function () {
      Router.push({
        path: "/CreateBookings",
        name: "CreateBookings",
      });
    },
    goToUpdateBookingsPage: function () {
      Router.push({
        path: "/UpdateBookings",
        name: "UpdateBookings",
      });
    },
    goToViewBookingsPage: function () {
      Router.push({
        path: "/ViewBookings",
        name: "ViewBookings",
      });
    },
    goToDeleteBookingsPage: function () {
      Router.push({
        path: "/DeleteBookings",
        name: "DeleteBookings",
      });
    },
  },
};
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

.myColumn {
  display: flex;
  flex-direction: column;
  height: calc(10%);
  justify-content: space-around;
}

/*Resize the wrap to see the search bar change!*/

.wrap {
  width: 20%;
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
