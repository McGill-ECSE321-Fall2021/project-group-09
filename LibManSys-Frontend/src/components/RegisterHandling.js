import axios from 'axios'
var AXIOS = axios.create({
    baseURL: this.backendUrl,
    headers: { 'Access-Control-Allow-Origin': this.frontendUrl }
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
				password: '',
				username: '',
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
	{	createMemberAccount: function (fullName, address, phoneNumber, emailAddress, password, username) {
                
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
                    this.newMemberAccount.fullName = ''
                    this.newMemberAccount.address = ''
					this.newMemberAccount.phoneNumber = ''
					this.newMemberAccount.emailAddress = ''
					this.newMemberAccount.password = ''
					this.newMemberAccount.username = ''
                    this.errorMemberAccount = ''
                    /*swal("Success", "Registration Successful", "success")*/
                })
                .then(okay =>{
                    this.$router.push("/OnlineMemberDashboard")
                })
                    .catch(e => {
                        var errorMsg = e
                        console.log(errorMsg)
                        this.errorProfile = errorMsg
                        //swal("Error", e.response.data, "error")
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

