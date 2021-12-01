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
  props: ['currentMember'],
  data () {
   return {
	   profile: {
		fullName : '',
		libCardNumber: '',
		address : '',
		phoneNumber: '',
		amountOwed: '',
		activeLoans: '',
		isVerifiedResident: '',
		emailAddress: '',
		username: ''
	   },
		errorProfile: '',
		response: []
	}
  },

  created: function () {
    AXIOS.get('/login/currentMember')
	  
	.then(response => 
		{this.libCardNumber = response.data.libCardNumber})
	  
	.catch(e => { this.errorUser = e })
	  
	.finally(() => {
        AXIOS.get('/OnlineMember/' + this.libCardNumer)
        .then(response => {
          // JSON responses are automatically parsed.
          this.profile = response.data
          this.profileId = this.profile.profileId
        })
        .catch(e => {
          this.errorProfile = e
        })
      })
    
  },
   
  methods: {

	 Logout: function () {
      AXIOS.post('/logout', {}, {})
        .then(response => {
          this.errorProfile = ""
          this.profile = response.data
		}) 
		  .then(okay => {
            this.$router.push('/MemberLogin')
          })
        .catch(e => {
          var errorMsg = e
          console.log(errorMsg)
          this.errorProfile = errorMsg
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
