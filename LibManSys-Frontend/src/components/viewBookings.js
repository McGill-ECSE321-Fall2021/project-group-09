
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

function BookingDto(startTime, endTime, bookingID, bookingDate, memberID, librarianID) {
    this.startTime = startTime
    this.endTime = endTime
    this.bookingDate = bookingDate
    this.bookingID = bookingID
    this.memberID = memberID
    this.librarianID = librarianID
}

export default {
    name: 'booking',
    data() {
        return {
            bookings: [],

            loggedInType: '',
            loggedInUser: '',
            error: '',
        }

    },
    created: function () {
        // check if cookie is set for current user
        var userLoggedIn = $cookies.isKey("loggedInUser")
        if (userLoggedIn == true) {
            this.loggedInUser = $cookies.get("loggedInUser")
            this.loggedInType = $cookies.get("loggedInType")
            
        
            AXIOS.get('/bookings/view-all/')
            .then(response => {
                this.bookings = response.data;
                console.log(response.data);
            })
            .catch(error => {
                var errorMsg = error
                if ( error.response ) {
                    errorMsg = error.response.data
                }
                console.log(errorMsg)
                this.error = errorMsg
            })
        }
    },
    methods: {
        
        goToPreviousPage: function () {
            Router.push({
                path: "/LibraryManagementDashboard",
                name: "LibraryManagementDashboard"
            })
        },
    },
    goToPreviousPage: function() {
        Router.push({
            path: "/LibraryManagementDashboard",
            name: "LibraryManagementDashboard"
        })
    },
}