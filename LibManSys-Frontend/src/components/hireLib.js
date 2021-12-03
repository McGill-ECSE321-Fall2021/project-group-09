
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
            newLibrarian: '',
            errorLibrarian: '',

            fullName: '',
            email: '',
            password: '',
            username: '',

            loggedInType: '',
            loggedInUser: ''
        }

    },
    created: function () {
        // check if cookie is set for current user
        var userLoggedIn = $cookies.isKey("loggedInUser")
        if (userLoggedIn == true) {
            this.loggedInUser = $cookies.get("loggedInUser")
            this.loggedInType = $cookies.get("loggedInType")
        }
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
                this.errorLibrarian = ''
                this.fullName = ''
                this.email = ''
                this.password = ''
                this.username = ''
                this.newLibrarian = response.data
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




