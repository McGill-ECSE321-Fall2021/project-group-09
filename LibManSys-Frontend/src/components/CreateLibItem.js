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
	name: 'result',
	data() {
		return {
			results: [],
			newResult: '',
			errorResult:'',
			genre:'',
			journalName: '',
			chiefEditor:'',
			edition:'',
			runtime:'',
			director:'',
			title:'',
			publishedYear:'',
			author: '',
			publisher:'',
			artist: '',
			numSongs: '',
			albumLengthInMinutes: '',
			ISBN: '',
			numPages: '',
			institution:'',
			response: [],
			form: {
                claimType: ""
            },
            claimTypeOptions: [
                { text: "Archive", value: "Archive" },
                { text: "Book", value: "Book" },
                { text: "NewsPaper", value: "NewsPaper" },
                { text: "Movie", value: "Movie" },
                { text: "MusicAlbum", value: "MusicAlbum" }
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
            return !this.director || !this.author  ;
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
		 createBook: function (title,publishedYear,author,publisher,ISBN,numPages) {
            //console.log("eid: " + employeeId)
            AXIOS.post('/book/create/', {}, { 
                 params: {
                     title: title,
                     publishedYear: publishedYear,
                    author: author,
                     publisher: publisher,
                    ISBN: ISBN,
                    numPages:numPages

                 }
            })
            .then(response => {
                this.results.push(response.data)
                this.errorResult = ''
                this.title = ''
                 this.publishedYear = ''
                this.author = ''
                this.publisher = ''
                this.ISBN = ''
                this.numPages = ''

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
        createArchive: function (title,publishedYear,institution) {
            //console.log("eid: " + employeeId)
            AXIOS.post('/archive/create/', {}, { 
                 params: {
                     title: title,
                     publishedYear: publishedYear,
                    institution: institution

                 }
            })
            .then(response => {
                this.results.push(response.data)
                this.errorResult = ''
                this.title = ''
                 this.publishedYear = ''
                this.institution = ''

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
        createNewspaper: function (title,publishedYear,journalName,edition,chiefEditor) {
            //console.log("eid: " + employeeId)
            AXIOS.post('/newspaper/create/', {}, { 
                 params: {
                     title: title,
                     publishedYear: publishedYear,
                    journalName: journalName,
                    edition: edition,
                   chiefEditor: chiefEditor 
                 }
            })
            .then(response => {
                this.results.push(response.data)
                this.errorResult = ''
                this.title = ''
                 this.publishedYear = ''
                this.journalName = ''
                this.edition = ''
                this.chiefEditor = ''

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
        createMusicAlbum: function (title,publishedYear,genre,artist,numSongs,albumLengthInMinutes) {
            //console.log("eid: " + employeeId)
            AXIOS.post('/musicAlbum/create/', {}, { 
                 params: {
                     title: title,
                     publishedYear: publishedYear,
                    genre: genre,
                     artist: artist,
                    numSongs: numSongs,
                     albumLengthInMinutes: albumLengthInMinutes
                    

                 }
            })
            .then(response => {
                this.results.push(response.data)
                this.errorResult = ''
                this.title = ''
                 this.genre = ''
                this.artist = ''
                this.numSongs = ''
                                this.publishedYear = ''

                this.albumLengthInMinutes = ''

            })
            .catch(error => {
                var errorMsg = error
                if ( error.response ) {
                    errorMsg = error.response.data
                }
                console.log(errorMsg)
                this.errorResult = errorMsg
            })
        }
        ,
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