
import Router from "../router/index";


import axios from 'axios'
var config = require('../../config')

var frontendUrl = 'http://' + config.dev.host + ':' + config.dev.port
var backendUrl = 'http://' + config.dev.backendHost + ':' + config.dev.backendPort

var AXIOS = axios.create({
    baseURL: backendUrl,
    headers: { 'Access-Control-Allow-Origin': frontendUrl }
})

function BookingDto(bookingStartTime, bookingEndTime, bookingID, bookingDate, member, librarian) {
    this.bookingStartTime = bookingStartTime
    this.bookingEndTime = bookingEndTime
    this.bookingDate = bookingDate
    this.bookingID = bookingID
    this.member = member
    this.librarian = librarian
}

export default {
    name: 'booking',
    data() {
        return {
            bookings: [],
            newBooking: '',
            errorBooking: '',
            response: [],
            bookingStartTime: '',
            bookingEndTime: '',
            bookingID: '',
            bookingDate: '',
            member: '',
            librarian: ''

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
        createBooking: function (bookingStartTime, bookingEndTime, bookingID, bookingDate, member, librarian) {
            AXIOS.post('bookings/new/', {}, {
                params: {
                    bookingStartTime: bookingStartTime,
                    bookingEndTime: bookingEndTime,
                    bookingID: bookingID,
                    bookingDate: bookingDate,
                    member: member,
                    librarian: librarian //should be logged in librarian

                }
            })
                .then(response => {
                    this.bookings.push(response.data)
                    this.errorResult = ''
                    this.bookingStartTime = ''
                    this.bookingEndTime = ''
                    this.bookingID = ''
                    this.member = ''
                    this.librarian = ''
                })
                .catch(error => {
                    var errorMsg = error
                    if (error.response) {
                        errorMsg = error.response.data
                    }
                    console.log(errorMsg)
                    this.errorResult = errorMsg
                })
        },
    }

}

