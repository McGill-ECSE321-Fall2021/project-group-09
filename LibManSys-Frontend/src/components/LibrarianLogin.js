import Router from "../router/index";

import axios from 'axios'
var config = require('../../config')

var frontendUrl =   'https://' + config.dev.host + ':' + config.dev.port
var backendUrl = 'https://' + config.dev.backendHost + ':' + config.dev.backendPort

var AXIOS = axios.create({
  baseURL: backendUrl,
  headers: { 'Access-Control-Allow-Origin': frontendUrl }
})

export default {
  data () {
   return {
        // form input
        username: '',
        password: '',
        errorLogin: '',
      }
  },
   methods: {
       loginLibrarian: function (username, password){
            console.log(username + ", " + password)
            // given username, password, get librarian employeeID
            AXIOS.post('/librarian/login', {}, {
                params: {
                    "username": username,
                    "password": password
                }
            })
            .then(response => {
                this.errorLogin = ''

                // set cookie for logged in user
                $cookies.set("loggedInUser", response.data.employeeIDNumber)

                // send to dashboard
                Router.push({
                    path: "/LibrarianDashboard",
                    name: "LibrarianDashboard"
                })
            })
            .catch(error => {
                var errorMsg = error
                if ( error.response ) {
                    errorMsg = error.response.data
                }
                console.log(errorMsg)
                this.errorLogin = errorMsg
            })            
        },
        
        
        goToMemberLoginPage: function() {
            Router.push({
                path: "/MemberLogin",
                name: "MemberLogin"
            })
        },
        goToSearchPage: function (){
            Router.push({
                path: "/SearchLibItems",
                name: "SearchLibItems"
            })
        },
        goToRegisterPage: function (){
            Router.push({
                path: "/Register",
                name: "Register"
            })
        },
   }
}