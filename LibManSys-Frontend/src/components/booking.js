
import Router from "../router/index";


import axios from 'axios'
var config = require('../../config')

var frontendUrl =   'http://' + config.dev.host + ':' + config.dev.port
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
            response: []
        }
    },
    created: function () {
        const b1 = new BookingDto('9:00:00', '12:00:00', '1234', '01-01-2022', '1', '1')
        const b2 = new BookingDto('6:00:00', '9:00:00', '1234', '01-01-2022', '1', '1')
        this.bookings = [b1, b2];
    },
    methods: {
        createBooking: function (bookingStartTime, bookingEndTime, bookingID, bookingDate, member, librarian) {
            var b = new BookingDto(bookingStartTime, bookingEndTime, bookingID, bookingDate, member, librarian)
            this.bookings.push(b)
            this.bookingStartTime = ''
            this.bookingEndTime = ''
            this.bookingDate = ''
            this.bookingID = ''
            this.member = ''
            this.librarian = ''
        },

        goToRegisterPage: function () {
            Router.push({
                path: "/Register",
                name: "Register",
            });
        },
        goToLoginPage: function () {
            Router.push({
                path: "/MemberLogin",
                name: "MemberLogin",
            });
        },
        goToSearchPage: function () {
            Router.push({
                path: "/SearchResults",
                name: "SearchResults",
            });
        },
        goToLibManagmentPage: function () {
            Router.push({
                path: "/LibraryManagementDashboard",
                name: "LibraryManagementDashboard",
            });
        },
        goToUserPage: function () {
            Router.push({
                path: "/OnlineMemberDashboard",
                name: "OnlineMemberDashboard",
            });
        },
        goToLibrarianPage: function () {
            Router.push({
                path: "/LibrarianDashboard",
                name: "LibrarianDashboard",
            });
        },
        goToItemPage: function () {
            Router.push({
                path: "/ItemPage",
                name: "ItemPage",
            });
        },
        goToContactUsPage: function () {
            Router.push({
                path: "/ContactUs",
                name: "ContactUs",
            });
        },
        goToBookingsPage: function () {
            Router.push({
                path: "/Bookings",
                name: "Bookings",
            });
        },
    }
}
