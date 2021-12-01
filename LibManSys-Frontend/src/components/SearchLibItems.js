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
  name: "SearchLibItems",
  data() {
    return {
      msg: "Welcome to the Library",
      selected: '',
      selected2: '',
      bookHidden: true,
      movieHidden: true,
      cdHidden: true,
      archiveHidden: true,
      newspaperHidden: true,
      message: '',
      results: [],
			books:[],
			movies:[],
		  newspapers:[],
			archives:[],
			musicAlbums:[],
			newResult: '',
			errorResult:'',
			response: [],

      archiveResults: [],
      bookResults: [],
      movieResults: [],
      musicAlbumResults: [],
      newspaperResults: []
     
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
  methods: {
    search: function(query) {
      console.log("sel1: '" + this.selected + "' sel2: '" + this.selected2 + "'")

      var itemFilter = this.selected
      var attrFilter = this.selected2

      if (!itemFilter || itemFilter === "all") {
        console.log("serach all items")
      } else if (itemFilter === "archive") {
        if (!attrFilter || attrFilter === "all" ) {
          console.log("s archive")
        } else if (attrFilter === "title") {
          console.log("s archive title")
        } else if (attrFilter === "publishedYear") {
          console.log("s archive published year")
        } else if (attrFilter === "institution") {
          console.log("s archives insitiution")
        }
      } else if (itemFilter === "book") {
        console.log(" s book")
        if (!attrFilter || attrFilter === "all" ) {
          
        } else if (attrFilter === "title") {
          console.log("s book title")
        } else if (attrFilter === "publishedYear") {
          console.log("s book published year")
        } else if (attrFilter === "author") {
          console.log("book author")
        } else if (attrFilter === "isbn") {
          console.log("book isbn")
        }
      } else if (itemFilter === "movie") {
        if (!attrFilter || attrFilter === "all" ) {
          console.log("s movie")
        } else if (attrFilter === "title") {
          console.log("s movie title")
        } else if (attrFilter === "publishedYear") {
          console.log("s movie published year")
        } else if (attrFilter === "genre") {
          console.log("genre")
        } else if (attrFilter === "director") {
          console.log("director")
        }
      } else if (itemFilter === "musicalbum") {
        console.log("s ma")
        if (!attrFilter || attrFilter === "all" ) {
          // TODO TODO TODO TODO TODO 
        } else if (attrFilter === "title") {
          console.log("s msuci albim title")
        } else if (attrFilter === "publishedYear") {
          console.log("s msuic album published year")
        }
      } else if (itemFilter === "newspaper") {
        console.log("s new")
        if (!attrFilter || attrFilter === "all" ) {
          
        } else if (attrFilter === "title") {
          console.log("s newspaper title")
        } else if (attrFilter === "publishedYear") {
          console.log("s newspaper published year")
        }
      }
    },
    goToSearchPage: function (){
        Router.push({
            path: "/SearchResults",
            name: "SearchResults"
        })
    }
  }
}