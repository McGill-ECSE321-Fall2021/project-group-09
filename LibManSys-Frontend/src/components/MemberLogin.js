import Router from "../router/index";
//import { eventBus } from "../eventBus";

import axios from 'axios'
var config = require('../../config')

var frontendUrl =   'http://' + config.dev.host + ':' + config.dev.port
var backendUrl = 'http://' + config.dev.backendHost + ':' + config.dev.backendPort

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
       loginMember: function (username, password){
        console.log(username + ", " + password)
        // given username, password, get librarian employeeID
        AXIOS.post('/OnlineMember/login', {}, {
            params: {
                "username": username,
                "password": password
            }
        })
        .then(response => {
            this.errorLogin = ''

            var newLoggedInUser = response.data.libCardNumber
            // set cookie for logged in user
            $cookies.set("loggedInUser", newLoggedInUser)
            $cookies.set("loggedInType", "onlineMember")

            // published event
            EventBus.$emit('loggedInUserSet', newLoggedInUser)

            // send to dashboard
            Router.push({
                path: "/OnlineMemberDashboard",
                name: "OnlineMemberDashboard"
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
        
        // navigation methods
        goToLibrarianLoginPage: function () {
           Router.push({
                path: "/LibrarianLogin",
                name: "LibrarianLogin"
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