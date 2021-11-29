import axios from 'axios'
var config = require('../../config')

var frontendUrl = 'http://' + config.dev.host + ':' + config.dev.port
var backendUrl = 'http://' + config.dev.backendHost + ':' + config.dev.backendPort

var AXIOS = axios.create({
    baseURL: backendUrl,
    headers: { 'Access-Control-Allow-Origin': frontendUrl }
})


export default {
    data() {
        return {
            MemberAccounts: [],
            newMemberAccount: {
				fullName: '',
				address: '',
				phoneNumber: '',
				emailAddress: '',
				password: '',
				username: '',
            },
			
			selectedMemberAccount: {
                username: '',
                password: '',
                name: ''
            },
            errorMemberAccount: '',
            response: [],

        }

	},
	
	methods: 
	{	createMemberAccount: function (fullName, address, phoneNumber, emailAddress, password, username) {
				AXIOS.post('/OnlineMembers/create/' + fullName + '?Address=' + address + '?Phone Number=' + phoneNumber +
				'?Email Address=' + emailAddress + '?Password=' + password + '?Username=' +username
				).then(response => {
                    // JSON responses are automatically parsed.
                    this.MemberAccounts.push(response.data)
                    this.newMemberAccount.fullName = ''
                    this.newMemberAccount.address = ''
					this.newMemberAccount.phoneNumber = ''
					this.newMemberAccount.emailAddress = ''
					this.newMemberAccount.password = ''
					this.newMemberAccount.username = ''
                    this.errorMemberAccount = ''
                    this.$currentUsername.value = username
                    this.$currentName.value = name
                    this.$router.push("/OnlineMemberDashboard")
                })
                    .catch(e => {
                        var errorMsg = e.response.data.message
                        console.log(errorMsg)
                        this.errorMemberAccount = errorMsg
					})
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
	
		goToSubmitPage: function (){
            Router.push({
                path: "/OnlineMemberDashboard",
                name: "OnlineMemberDashboard"
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

    	goToLoginPage: function (){
            Router.push({
                path: "/MemberLogin",
                name: "MemberLogin"

	})
		},
	}
}

