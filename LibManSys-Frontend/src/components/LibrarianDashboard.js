import Router from "../router/index";

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

function getSchedules(schedules) {
    var scheduleList = []
    for (const sid of schedules) {
        AXIOS.get("/schedule/get-by-id/" + sid)
        .then(response => {
            var schedule = response.data
            console.log(schedule)
            scheduleList.push(schedule)
        })
        .catch(error => {
            var errorMsg = error
            if ( error.response ) {
                errorMsg = error.response.data
            }
            console.log(errorMsg)
        })
    }
    return scheduleList
}

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

export default {
    data() {
        return {
            loggedInUser: '',
            librarian: '',
            schedules: [],
            loans: [],
            bookings: []
        }
    },
    created: function () {
        console.log("test: "+ this.frontendUrl)
        console.log("backend: " + this.backendUrl)

        // check if cookie is set for current user
        var userLoggedIn = $cookies.isKey("loggedInUser")
        if (userLoggedIn == true) {
            this.loggedInUser = $cookies.get("loggedInUser")
            AXIOS.get("/librarian/" + this.loggedInUser)
            .then(response => {
                this.librarian = response.data
                // get associations from IDs
                this.schedules = getSchedules(response.data.schedules)
                this.loans = getLoans(response.data.loans)
                this.bookings = getBookings(response.data.bookings)
            })
            .catch(error => {
                var errorMsg = error
                if ( error.response ) {
                    errorMsg = error.response.data
                }
                console.log(errorMsg)
            })
        }
    },
    methods: {
        // navigation
        goToRegisterPage: function() {
            Router.push({
                path: "/Register",
                name: "Register"
            })
        },
        goToLoginPage: function() {
            Router.push({
                path: "/MemberLogin",
                name: "MemberLogin"
            })
        },
        goToSearchPage: function() {
            Router.push({
                path: "/SearchLibItems",
                name: "SearchLibItems"
            })
        },
        goToLibraryManagmentPage: function() {
            Router.push({
                path: "/LibraryManagementDashboard",
                name: "LibraryManagementDashboard"
            })
        },
        goToViewBookingsPage: function() {
            Router.push({
                path: "/ViewBookings",
                name: "ViewBookings"
            })
        }
    }
}