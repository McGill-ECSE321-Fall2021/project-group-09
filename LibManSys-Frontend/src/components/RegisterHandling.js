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

import Router from "../router/index";


export default {
    data() {
        return {
            fullName: '',
            address: '',
            phoneNumber: '',
            emailAddress: '',
            password: '',
            username: '',
			
			error: '',
        }

	},
	
	methods:  {	
        createMemberAccount: function (fullName, address, phoneNumber, emailAddress, password, username) {
                if (fullName === '') {
                    this.error = "Please provide a name."
                    return
                }
                AXIOS.post('/OnlineMember/create/' + fullName, {}, {
                    params: {
                        address: address,
                        phoneNumber: phoneNumber,
                        emailAddress: emailAddress,
                        password: password,
                        username: username
                    }
                })
				.then(response => {
                    // if successful, login user and send to online member dashboard
                    this.error = ''
                    
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
                    this.error = errorMsg
                });
        },
	
		getAllMemberAccounts: function () {
            // Initializing persons from backend
            AXIOS.get('/onlineMembers')
                .then(response => {
                    // JSON responses are automatically parsed.
                    this.MemberAccounts = response.data
                })
                .catch(e => {
                    this.errorCustomerAccount = e
                })
        },
	
		/*goToSubmitPage: function (){
            Router.push({
                path: "/OnlineMemberDashboard",
                name: "OnlineMemberDashboard"
            })
        },*/
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

    	goToLoginPage: function (){
            Router.push({
                path: "/MemberLogin",
                name: "MemberLogin"

	})
		},
	}
}

