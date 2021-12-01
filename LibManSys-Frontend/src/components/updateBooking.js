
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
            newBooking: '',
            errorBooking: '',
            response: [],
            startTime: '',
            endTime: '',
            id: '',
            bookingDate: '',
            

        }

    },
    created: function () {

    //     AXIOS.get('/bookings/view-all') //change for logged in member
    //         .then(response => {
    //             this.bookings = response.data;
    //             console.log(response.data);
    //         })
    //         .catch(e => {
    //             this.errorResult = e
    //         })
    },

    methods: {
        updateBooking: function (bookingID, startTime, endTime, bookingDate) {
            AXIOS.put('bookings/update/', {}, {
                params: {
                    id: bookingID,
                    startTime: startTime,
                    endTime: endTime,
                    bookingDate: bookingDate,

                }
            })
            .then (response => {
                this.bookings.push(response.data)
                this.errorBooking = ''
                this.startTime = ''
                this.endTime = ''
                this.id = ''
                this.bookingDate = ''
            })
            .catch(error => {
                var errorMsg = error
                if (error.response) {
                    errorMsg = error.response.data
                }
                console.log(errorMsg)
                this.errorBooking = errorMsg
            })

            

        },
        
        goToPreviousPage: function() {
            Router.push({
                path: "/LibraryManagementDashboard",
                name: "LibraryManagementDashboard"
            })
        },
    }



}

