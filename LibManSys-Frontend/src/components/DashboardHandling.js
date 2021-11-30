import axios from 'axios'
var config = require('../../config')

var frontendUrl = 'http://' + config.dev.host + ':' + config.dev.port
var backendUrl = 'http://' + config.dev.backendHost + ':' + config.dev.backendPort

var AXIOS = axios.create({
    baseURL: backendUrl,
    headers: { 'Access-Control-Allow-Origin': frontendUrl }
})

import OnlineMemberDashboard from "../components/OnlineMemberDashboard";
import SearchLibItems from "../components/SearchLibItems";
import Hello from "../components/Hello";
import Register from "../components/Register";
import MemberLogin from "../components/MemberLogin";
import Router from "../router/index";
import MemberLoans from "../components/MemberLoans";

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
  name: 'hello',
  data () {
   return {
        types: [
        ]
      }
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
