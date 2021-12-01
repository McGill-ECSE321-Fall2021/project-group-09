import axios from 'axios'
var AXIOS = axios.create({
  baseURL: this.backendUrl,
  headers: { 'Access-Control-Allow-Origin': this.frontendUrl }
})

import Router from "../router/index";

function OnlineMemberDto(fullName, libCardNumber, address, phoneNumber, amountOwed, activeLoans, isVerifiedResident,   
			emailAddress, username) {
  this.fullName = fullName
  this.libCardNumber = libCardNumber
  this.address = address
  this.phoneNumber = phoneNumber
  this.amountOwed = amountOwed
  this.activeLoans = activeLoans
  this.isVerifiedResident = isVerifiedResident
  this.emailAddress = emailAddress
  this.username = username
}

export default {
  name: 'DashboardHandling',
  data () {
   return {
		fullName : '',
		libCardNumber: '',
		address : '',
		phoneNumber: '',
		amountOwed: '',
		activeLoans: '',
		isVerifiedResident: '',
		emailAddress: '',
		username: '',
		errorAccount: '',
		response: []
	}
  },

  created: function () {
    AXIOS.get('/login/currentMember')
	  
	.then(response => 
		{this.libCardNumber = response.data.libCardNumber})
	  
	.catch(e => { this.errorUser = e })
	  
	.finally(() => {
        AXIOS.get('/OnlineMember' + this.libCardNumber)
		
		.then(response => {
          this.profile = response.data
          this.profileId = this.profile.profileId
        })
        .catch(e => {
          this.errorProfile = e
        })
      })
    
  },
   methods: {

	 logout: function () {
      AXIOS.post('/logout', {}, {})
        .then(response => {
          this.errorProfile = ""
          this.profile = response.data
          swal("Success", "You have been logged out successfully", "success").then(okay => {
            this.$router.push('/')
          })
        })
        .catch(e => {
          var errorMsg = e
          console.log(errorMsg)
          this.errorProfile = errorMsg
          swal("ERROR", e.response.data, "error")
        })
    },
       goToSubmitPage: function (){
            Router.push({
                path: "/MemberLogin",
                name: "MemberLogin"
            })
        },
        goToRegisterPage: function (){
            Router.push({
                path: "/Register",
                name: "Register"
            })
        },
         goToSearchPage: function (){
            Router.push({
                path: "/SearchLibItems",
                name: "SearchLibItems"
            })
        },
        goToLoansPage: function (){
            Router.push({
                path: "/MemberLoans",
                name: "MemberLoans"
            })
        }
   }
}
