import axios from 'axios'
var config = require('../../config')

var frontendUrl = 'http://' + config.dev.host + ':' + config.dev.port
var backendUrl = 'http://' + config.dev.backendHost + ':' + config.dev.backendPort

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
import OnlineMemberDashboard from "../components/OnlineMemberDashboard";
import SearchLibItems from "../components/SearchLibItems";
import Hello from "../components/Hello";
import Register from "../components/Register";
import LibraryManagementDashboard from "../components/LibraryManagementDashboard";
import MemberLogin from "../components/MemberLogin";
import Router from "../router/index";

export default {
	name: 'result',
	data() {
		return {
			results: [],
			newResult: '',
			errorResult:'',
			title:'',
			publishedYear:'',
			director: '',
			runtime:'',
			genre: '',
			response: [],
			form: {
                claimType: ""
            },
            claimTypeOptions: [
                { text: "Archive", value: "Archive" },
                { text: "Book", value: "Book" },
                { text: "NewsPaper", value: "NewsPaper" },
                { text: "Movie", value: "Movie" },
                { text: "CD", value: "CD" }
            ]
        
			
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
            return !this.director ;
        }
    },
	methods:{
		 createLoan: function (loanName) {
            // create new loan and add it the list of people
            var l = new LoanDto(loanName)
            this.loans.push(l)
            // reset the name field for new loans
            this.newLoan = ''
        },
		 createMovie: function (title, publishedYear, director,runtime,genre) {
            //console.log("eid: " + employeeId)
            AXIOS.post('/movie/create/', {}, { 
                 params: {
                     title: title,
                     publishedYear: publishedYear,
                    director: director,
                     runtime: runtime,
                    genre: genre

                 }
            })
            .then(response => {
                this.results.push(response.data)
                this.errorResult = ''
                this.title = ''
                 this.publishedYear = ''
                this.director = ''
                this.runtime = ''
                this.genre = ''

            })
            .catch(error => {
                var errorMsg = error
                if ( error.response ) {
                    errorMsg = error.response.data
                }
                console.log(errorMsg)
                this.errorResult = errorMsg
            })
        },
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
,
	methods: {
	createLibItems: function(borrowedDate,returnedDate,lateFees, loanStatus,loanId,libraryItem, member, librarian){
		var p = new LibItems(borrowedDate,returnedDate,lateFees, loanStatus,loanId,libraryItem, member, librarian);
		this.results.push(p);
		this.newResult = '';
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
        }, goToSearchPage: function (){
            Router.push({
                path: "/SearchLibItems",
                name: "SearchLibItems"
            })
        }
	}
	
	
}