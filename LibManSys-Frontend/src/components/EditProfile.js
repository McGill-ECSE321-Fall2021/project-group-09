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
import MemberLoans from "../components/MemberLoans";


export default {
    data() {
        return {
            
            memberAccounts: [],
            
            newMemberAccount: {
				fullName: '',
				address: '',
				phoneNumber: '',
				emailAddress: '',
                username: '',
                activeLoans: '',
                amountOwed: ''
            },

            selectedMemberAccount: {
				fullName: '',
				address: '',
				phoneNumber: '',
				emailAddress: '',
				password: '',
				username: '',
            },
			
			errorMemberAccount: '',
            response: [],

        }

	},
	
	methods: 
	{	/*createMemberAccount: function (fullName, address, phoneNumber, emailAddress, password, username) {
                
                AXIOS.post('/OnlineMember/create/' + fullName + '?address=' + address + '&phoneNumber=' + phoneNumber +
				'&emailAddress=' + emailAddress + '&password=' + password + '&username=' +username
				)
				
				.then(response => {
                   //this.MemberAccounts.push(response.data)
                    /*fullName = this.fullName,
                    address = this.address,
                    phoneNumber = this.phoneNumber,
                    emailAddress = this.emailAddress,
                    password = this.password, 
                    username = this.username;*/
         /*           this.newMemberAccount.fullName = fullName
                    this.newMemberAccount.address = address
					this.newMemberAccount.phoneNumber = phoneNumber
                    this.newMemberAccount.emailAddress = emailAddress
                    this.newMemberAccount.amountOwed = 0
                    this.newMemberAccount.activeLoans = 0
					this.newMemberAccount.username = username
                    this.errorMemberAccount = ''
                    /*swal("Success", "Registration Successful", "success")*/
         /*       })
                .then(okay =>{
                    this.$router.push("/OnlineMemberDashboard")
                })
                    .catch(e => {
                        var errorMsg = e
                        console.log(errorMsg)
                        this.errorProfile = errorMsg
                        //swal("Error", e.response.data, "error")
					});
        }, */
	
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

