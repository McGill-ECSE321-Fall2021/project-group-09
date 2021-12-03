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
    data() {
        return {
            loan: undefined,
            loggedInType: '',
            loggedInUser: '',
            libCardNumber: '',
            libraryItemID: '',
            error: ''
        }
    },
    created: function() {
        // check if cookie is set for current user
        var userLoggedIn = $cookies.isKey("loggedInUser")
        if (userLoggedIn == true) {
            this.loggedInUser = $cookies.get("loggedInUser")
            this.loggedInType = $cookies.get("loggedInType")
        }
    },
    methods: {
        returnItem(libCardNumber, libraryItemID) {
            AXIOS.post("/library-item/return", {}, {
                params: {
                    'libCardNumber': libCardNumber,
                    'libraryItemID': libraryItemID
                }
            })
            .then(response => {
                this.error = ''
                this.libCardNumber = ''
                this.libraryItemID = ''
                this.loan = response.data
            })
            .catch(error => {
                var errorMsg = error
                if ( error.response ) {
                    errorMsg = error.response.data
                }
                console.log(errorMsg)
                this.error = errorMsg
            })
        },
        
        // navigation functions
        goToSubmitPage: function() {
            Router.push({
                path: "/LibraryManagementDashboard",
                name: "LibraryManagementDashboard"
            })
        },
        goToSearchPage: function() {
            Router.push({
                path: "/SearchLibItems",
                name: "SearchLibItems"
            })
        },
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
        }
    }
}