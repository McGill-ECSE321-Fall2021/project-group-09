import Router from "../router/index";
//import { eventBus } from '../eventBus';

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
            var headLibrarianEmployeeID = ''
            // get head librarian employee ID
            AXIOS.get("/head-librarian", {})
            .then(response => {
                headLibrarianEmployeeID = response.data.employeeIDNumber
                console.log("hl id: " + headLibrarianEmployeeID)
            })
            .catch(error => {
                // no head librarian
            })

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

                var newLoggedInUser = response.data.employeeIDNumber
                // set cookie for logged in user
                $cookies.set("loggedInUser", newLoggedInUser)
                // check if head librarian
                var librarianType = ''
                
                console.log("hl id 2: " + headLibrarianEmployeeID)
                console.log("l id 3: " + newLoggedInUser)
                if (headLibrarianEmployeeID != '' &&
                newLoggedInUser == headLibrarianEmployeeID) {
                    librarianType = "headLibrarian"
                } else {
                    librarianType = "librarian"
                }
                $cookies.set("loggedInType", librarianType)

                // publish event
                EventBus.$emit('loggedInUserSet', newLoggedInUser)

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