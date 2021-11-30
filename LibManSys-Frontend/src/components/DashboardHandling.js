import axios from 'axios'
var config = require('../../config')

var frontendUrl = 'http://' + config.dev.host + ':' + config.dev.port
var backendUrl = 'http://' + config.dev.backendHost + ':' + config.dev.backendPort

var AXIOS = axios.create({
    baseURL: backendUrl,
    headers: { 'Access-Control-Allow-Origin': frontendUrl }
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
