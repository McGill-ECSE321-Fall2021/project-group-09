
import Router from "../router/index";


import axios from 'axios'

var AXIOS = axios.create({
    baseURL: this.backendUrl,
    headers: { 'Access-Control-Allow-Origin': this.frontendUrl }
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
            bookingID: '',
            bookingDate: '',
            memberID: '',
            librarianID: ''

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
        createBooking: function (startTime, endTime, bookingID, bookingDate, memberID, librarianID) {
            AXIOS.post('bookings/new/', {}, {
                params: {
                    startTime: startTime,
                    endTime: endTime,
                    bookingID: bookingID,
                    bookingDate: bookingDate,
                    memberID: memberID,
                    librarianID: librarianID //should be logged in librarian

                }
            })
                .then(response => {
                    this.bookings.push(response.data)
                    this.errorResult = ''
                    this.startTime = ''
                    this.endTime = ''
                    this.bookingID = ''
                    this.memberID = ''
                    this.librarianID = ''
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
            

        },

    }




