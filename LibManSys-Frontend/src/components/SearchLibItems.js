import Register from "../components/Register";
import Router from "../router/index";
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


export default {
  name: "hello",
  data() {
    return {
      hover: false,
      msg: "Welcome to the Library",
      selected: '',
    selected2: '',
    bookHidden: true,
    movieHidden: true,
    cdHidden: true,
    archiveHidden: true,
    newspaperHidden: true,
    results: [],
			books:[],
			movies:[],
		newspapers:[],
			archives:[],
			musicAlbums:[],
			newResult: '',
			errorResult:'',
			response: []
     
    };
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
    
  },

  components: {
    Register
  },

   methods: {
	goToBook: function(){
		bookHidden= false
		
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
        },
        goToSearchPage: function (){
            Router.push({
                path: "/SearchResults",
                name: "SearchResults"
            })
        },
        goToLibManagmentPage: function (){
            Router.push({
                path: "/LibraryManagementDashboard",
                name: "LibraryManagementDashboard"
            })
        },
        goToUserPage: function (){
            Router.push({
                path: "/OnlineMemberDashboard",
                name: "OnlineMemberDashboard"
            })
        },
        goToLibrarianPage: function (){
            Router.push({
                path: "/LibrarianDashboard",
                name: "LibrarianDashboard"
            })
        },
        goToItemPage: function (){
            Router.push({
                path: "/ItemPage",
                name: "ItemPage"
            })
        }
        }
}