import axios from 'axios'
var config = require('../../config')
var frontendUrl
var backendUrl
if (process.env.NODE_ENV === "production") {
  console.log("prod env")
  frontendUrl = 'https://' + config.build.host + ':' + config.build.port
  backendUrl = 'https://' + config.build.backendHost + ':' + config.build.backendPort

} else if (process.env.NODE_ENV === "development") {
  console.log("dev env")
  frontendUrl = 'http://' + config.dev.host + ':' + config.dev.port
  backendUrl = 'http://' + config.dev.backendHost + ':' + config.dev.backendPort
}
var AXIOS = axios.create({
    baseURL: backendUrl,
    headers: { 'Access-Control-Allow-Origin': frontendUrl }
})

import Router from "../router/index";

function getLoans(loans) {
    var loanList = []
    for (const lid of loans) {
        AXIOS.get("/loan/" + lid)
        .then(response => {
            var loan = response.data
            console.log(loan)
            loanList.push(loan)
        })
        .catch(error => {
            var errorMsg = error
            if ( error.response ) {
                errorMsg = error.response.data
            }
            console.log(errorMsg)
        })
    }
    return loanList
}

function getBookings(bookings) {
  var bookingList = []
  for (const bid of bookings) {
      AXIOS.get("/bookings/getID/" + bid)
      .then(response => {
          var booking = response.data
          console.log(booking)
          bookingList.push(booking)
      })
      .catch(error => {
          var errorMsg = error
          if ( error.response ) {
              errorMsg = error.response.data
          }
          console.log(errorMsg)
      })
  }
  return bookingList
}

function getReservedLibraryItems(reservedLibraryItems) {
  var reservedLibraryItemsList = []
  for (const rid of reservedLibraryItems) {
      AXIOS.get("/library-item/" + rid)
      .then(response => {
          var item = response.data
          console.log(item)
          reservedLibraryItemsList.push(item)
      })
      .catch(error => {
          var errorMsg = error
          if ( error.response ) {
              errorMsg = error.response.data
          }
          console.log(errorMsg)
      })
  }
  return reservedLibraryItemsList
}

export default {
  name: 'DashboardHandling',
  data () {
  return {
      loggedInUser: '',
      onlineMember: '',
      loans: [],
      bookings: [],
      reservedLibraryItems: []
    }
  },
  created: function () {
    // check if user loggedin using cookie
    var userLoggedIn = $cookies.isKey("loggedInUser")

    if (userLoggedIn == true ) {
      var userType = $cookies.get("loggedInType")
      if (userType === "onlineMember") {
        // then we have a logged in online member
        this.loggedInUser = $cookies.get("loggedInUser")
        AXIOS.get("/OnlineMember/get-by-libcardnumber/" + this.loggedInUser)
        .then(response => {
            this.onlineMember = response.data
            // get associations from IDs
            this.loans = getLoans(response.data.loans)
            this.bookings = getBookings(response.data.bookings)
            this.reservedLibraryItems = getReservedLibraryItems(response.data.reservedLibraryItems)
        
            console.log(this.onlineMember)
            console.log(this.bookings)
        })
        .catch(error => {
            var errorMsg = error
            if ( error.response ) {
                errorMsg = error.response.data
            }
            console.log(errorMsg)
        })
      }
    }

  },
  methods: {

    cancelReserveItem(itemID) {
        AXIOS.post("/library-item/cancel-reservation", {}, {
            params: {
                "libCardNumber": this.onlineMember.libCardNumber,
                "libraryItemID": itemID
            }
        })
        .then(response => {
            this.error = ''
            window.location.reload()
        })
        .catch(error => {
            this.error = error
        })
    },
    // navigation
      goToSubmitPage: function (){
          Router.push({
              path: "/MemberLogin",
              name: "MemberLogin"
          })
      },
       goToEditPage: function (){
          Router.push({
              path: "/EditProfile",
              name: "EditProfile"
          })
      },
      goToRegisterPage: function (){
          Router.push({
              path: "/Register",
              name: "Register"
          })
      },
        goToSearchPage: function (){
          Router.push({
              path: "/SearchLibItems",
              name: "SearchLibItems"
          })
      },
      goToLoansPage: function (){
          Router.push({
              path: "/MemberLoans",
              name: "MemberLoans"
          })
      }
  }
}