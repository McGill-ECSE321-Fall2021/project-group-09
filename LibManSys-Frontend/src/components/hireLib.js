
import Router from "../router/index";


import axios from 'axios'
var config = require('../../config')

var frontendUrl = 'http://' + config.dev.host + ':' + config.dev.port
var backendUrl = 'http://' + config.dev.backendHost + ':' + config.dev.backendPort

var AXIOS = axios.create({
    baseURL: backendUrl,
    headers: { 'Access-Control-Allow-Origin': frontendUrl }
})



export default {
    name: 'librarian',
    data() {
        return {
            librarians: [],
            newLibrarian: '',
            errorLibrarian: '',
            response: [],
            fullName: '',
            email: '',
            password: '',
            username: '',

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
        createLibrarian: function (fullName, email, password, username) {
            AXIOS.post('librarian/create/', {}, {
                params: {
                    fullName: fullName,
                    email: email,
                    password: password,
                    username: username,

                }
            })
        
                .then(response => {
                    this.librarians.push(response.data)
                    this.errorLibrarian = ''
                    this.fullName = '',
                        this.email = '',
                        this.password = '',
                        this.username = ''
                })
                .catch(error => {
                    var errorMsg = error
                    if (error.response) {
                        errorMsg = error.response.data
                    }
                    console.log(errorMsg)
                    this.errorLibrarian = errorMsg
                })
        },

        goToPreviousPage: function () {
            Router.push({
                path: "/LibraryManagementDashboard",
                name: "LibraryManagementDashboard"
            })
        },


    },

}




