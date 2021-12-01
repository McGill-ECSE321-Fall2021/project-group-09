import axios from 'axios'
var AXIOS = axios.create({
  baseURL: this.backendUrl,
  headers: { 'Access-Control-Allow-Origin': this.frontendUrl }
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
			newLoan: '',
			errorLoan:'',
			response: []
		}
	},
	created: function () {
    // Initializing persons from backend
    AXIOS.get('/loan/get-by-member/3968') //change for logged in member
    .then(response => {
      // JSON responses are automatically parsed.
      this.loans = response.data;
      console.log(response.data);
    })
    .catch(e => {
      this.errorLoan = e
    })
    
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