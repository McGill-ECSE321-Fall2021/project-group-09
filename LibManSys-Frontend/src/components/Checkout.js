// Checkout js

import Router from "../router/index"

import axios from 'axios'

var AXIOS = axios.create({
    baseURL: this.backendUrl,
    headers: { 'Access-Control-Allow-Origin': this.frontendUrl }
})

function LoanDto(borrowedDate, returnDate, lateFees, loanStatus, loanId,
	libraryItem, member, librarian) {
        
	this.borrowedDate = borrowedDate;
	this.returnDate = returnDate;
	this.lateFees = lateFees;
	this.loanStatus = loanStatus;
	this.loanId = loanId;
	this.libraryItem = libraryItem;
	this.member = member;
	this.librarian = librarian;
}

export default {
    data() {
        return {
            loans: [],
            
            newLoan: '',
            errorCheckout: '',
            employeeId: '',
            libCardNumber: '',
            libraryItemId: '',
            response: []
        }
    },
    created: function() {
        // test data
        const loan1 = new LoanDto("data")
        
        // sample initial content
        this.loans = [loan1]
    },
    computed: {
        // Return true if any inputs are missing
        isInputMissing() {
            return !this.employeeId || !this.libCardNumber || !this.libraryItemId;
        }
    },
    methods: {
        createLoan: function (loanName) {
            // create new loan and add it the list of people
            var l = new LoanDto(loanName)
            this.loans.push(l)
            // reset the name field for new loans
            this.newLoan = ''
        },
        checkoutItem: function (employeeId, libCardNumber, libraryItemId) {
            //console.log("eid: " + employeeId)
            AXIOS.post('/library-item/checkout', {}, { 
                 params: {
                     employeeId: employeeId,
                     libCardNumber: libCardNumber,
                     libraryItemId: libraryItemId
                 }
            })
            .then(response => {
                this.loans.push(response.data)
                this.errorCheckout = ''
                this.employeeId = ''
                this.libCardNumber = ''
                this.libraryItemId = ''
            })
            .catch(error => {
                var errorMsg = error
                if ( error.response ) {
                    errorMsg = error.response.data
                }
                console.log(errorMsg)
                this.errorCheckout = errorMsg
            })
        },
        
        
        // navigation functions
        goToSubmitPage: function() {
            Router.push({
                path: "/LibraryManagementDashboard",
                name: "LibraryManagementDashboard"
            })
        },
        goToSearchPage: function() {
            Router.push({
                path: "/SearchLibItems",
                name: "SearchLibItems"
            })
        },
        goToRegisterPage: function() {
            Router.push({
                path: "/Register",
                name: "Register"
            })
        },
        goToLoginPage: function() {
            Router.push({
                path: "/MemberLogin",
                name: "MemberLogin"
            })
        }
    }
}