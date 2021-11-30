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
import MemberLogin from "../components/MemberLogin";
import SearchLibItems from "../components/SearchLibItems";
import OnlineMemberDashboard from "../components/OnlineMemberDashboard";

import Router from "../router/index";
import Register from "../components/Register";
export default {
	name: 'result',
	data() {
		return {
			results: [],
			books:[],
			movies:[],
		newspapers:[],
			archives:[],
			musicAlbums:[],
			newResult: '',
			errorResult:'',
			response: []
		}
	},
	created: function () {
    // Initializing persons from backend
    AXIOS.get('/book/') //change for logged in member
    .then(response => {
      // JSON responses are automatically parsed.
      this.results = response.data;
      console.log(response.data);
    })
    .catch(e => {
      this.errorResult = e
    }),
    AXIOS.get('/movie') //change for logged in member
    .then(response => {
      // JSON responses are automatically parsed.
      this.movies = response.data;
      console.log(response.data);
    })
    .catch(e => {
      this.errorResult = e
    }),
      AXIOS.get('/newspaper/') //change for logged in member
    .then(response => {
      // JSON responses are automatically parsed.
      this.newspapers = response.data;
      console.log(response.data);
    })
    .catch(e => {
      this.errorResult = e
    }),
     AXIOS.get('/archive/') //change for logged in member
    .then(response => {
      // JSON responses are automatically parsed.
      this.archives = response.data;
      console.log(response.data);
    })
    .catch(e => {
      this.errorResult = e
    }),
     AXIOS.get('/musicAlbum/') //change for logged in member
    .then(response => {
      // JSON responses are automatically parsed.
      this.musicAlbums = response.data;
      console.log(response.data);
    })
    .catch(e => {
      this.errorResult = e
    })
    
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