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

function LoanDto(borrowedDate,returnedDate,lateFees, loanStatus,loanId,libraryItem, member, librarian) {
	this.name = loanId;
	this.borrowedDate = borrowedDate;
	this.returnedDate = returnedDate;
	this.lateFees = lateFees;
	this.loanStatus = loanStatus;
	this.libraryItem = libraryItem;
	this.member = member;
	this.librarian = librarian;
}

import Router from "../router/index";
export default {
	name: 'loan',
	data() {
		return {
			loans: [],
			            loggedInUser: '',

			newLoan: '',
			errorLoan:'',
			response: []
		}
	},
	created: function () {
        var userLoggedIn = $cookies.isKey("loggedInUser")
        if (userLoggedIn == true) {
            this.loggedInUser = $cookies.get("loggedInUser")
    // Initializing persons from backend
    AXIOS.get('/loan/get-by-member/'+ this.loggedInUser) //change for logged in member
    .then(response => {
      // JSON responses are automatically parsed.
      this.loans = response.data;
      console.log(response.data);
    })
    .catch(e => {
      this.errorLoan = e
    })}
    
  }
,
	methods: {
	createLoan: function(borrowedDate,returnedDate,lateFees, loanStatus,loanId,libraryItem, member, librarian){
		var p = new LoanDto(borrowedDate,returnedDate,lateFees, loanStatus,loanId,libraryItem, member, librarian);
		this.loans.push(p);
		this.newLoan = '';
	},
	goToSubmitPage: function() {
            Router.push({
                path: "/MemberLogin",
                name: "MemberLogin"
            })
        },
        goToRegisterPage: function() {
            Router.push({
                path: "/Register",
                name: "Register"
            })
        },
        goToSearchPage: function() {
            Router.push({
                path: "/SearchLibItems",
                name: "SearchLibItems"
            })
        },
        goToMemberPage: function() {
            Router.push({
                path: "/OnlineMemberDashboard",
                name: "OnlineMemberDashboard"
            })
        }
	}
	
	
}