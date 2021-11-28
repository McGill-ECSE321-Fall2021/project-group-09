import axios from 'axios'
import swal from 'sweetalert'
var config = require('../../config')

var frontendUrl = 'http://' + config.dev.host + ':' + config.dev.port
var backendUrl = 'http://' + config.dev.backendHost + ':' + config.dev.backendPort

var AXIOS = axios.create({
    baseURL: backendUrl,
    headers: { 'Access-Control-Allow-Origin': frontendUrl }
  })

  function OnlineMemberDto(fullName, address, phoneNumber,  emailAddress, username) {
  this.fullName = fullName
  this.address = address
  this.emailAddress = emailAddress
  this.phoneNumber = phoneNumber
  this.username = username
}

export default {
  name: 'profileHandling',
  
  data() {
    return {
      libCardNumber: '',
      errorMember:'',
      member: '',
      newProfile :'',
      customer: '',
      errorOnlineMember: '',
      response: []
    }
  },
  created: function () {
    // Initializing persons from backend
    AXIOS.get('/OnlineMember/{libCardNumber}')
      .then(response => { this.libCardNumber = response.data.libCardNumber })
      .catch(e => { this.errorMember = e })
      .finally(() => {
        AXIOS.get('/onlineMembers/' + this.libCardNumber)
        .then(response => {
          // JSON responses are automatically parsed.
          this.member = response.data
          this.profileId = this.member.libCardNumber
        })
        .catch(e => {
          this.errorMember = e
        })
      })
    
  },

   methods: {
    createOnlineMember: function (fullName, address, phoneNumber, emailAddress, password, username) {
      // Create a new person and add it to the list of people
      AXIOS.post('/OnlineMembers/create' + this.libCardNumber + '?fullName=' + fullName + '&Address=' + address
		+ '&Phone Number=' + phoneNumber + '&Email Address=' + emailAddress + '&Password=' + password + 
		'&Username=' + username, 
        {}, {}
        )
        .then(response => {
          // Update appropriate DTO collections
          this.errorMember=""
          this.member = response.data
          swal("Success", "Changes to your profile information are successfully saved", "success").then(okay => {
            if (okay) {
              this.$router.go('/OnlineMemberDashboard')
            }
          })
          
        })
        .catch(e => {
          var errorMsg = e
          console.log(errorMsg)
          this.errorMember = errorMsg
          swal("ERROR", e.response.data, "error");

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
        }
   }

}

