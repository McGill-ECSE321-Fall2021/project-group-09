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




export default {
    name: 'librarian',
    data() {
        return {
            librarians: [],
           

        }

    },
    created: function () {

    },

    methods: {
        // deleteBooking: function (bookingID) {
        //     AXIOS.delete('bookings/delete/' + bookingID) 
            
        //         .then(response => {
        //             this.bookings.pop(response.data)
        //             this.bookingID = ''
                  
        //         })
        //         .catch(error => {
        //             var errorMsg = error
        //             if (error.response) {
        //                 errorMsg = error.response.data
        //             }
        //             console.log(errorMsg)
        //             this.errorBooking = errorMsg
        //         })
        // },
       
        // goToPreviousPage: function() {
        //     Router.push({
        //         path: "/LibraryManagementDashboard",
        //         name: "LibraryManagementDashboard"
        //     })
        // },
            

        // },
        goToPreviousPage: function() {
            Router.push({
                path: "/LibraryManagementDashboard",
                name: "LibraryManagementDashboard"
            })
        },

    }}
