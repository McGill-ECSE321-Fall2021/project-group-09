import Router from "../router/index";
import axios from 'axios'
var config = require('../../config')
var frontendUrl
var backendUrl
if (process.env.NODE_ENV === "production") {
  frontendUrl = 'https://' + config.build.host + ':' + config.build.port
  backendUrl = 'https://' + config.build.backendHost + ':' + config.build.backendPort

} else if (process.env.NODE_ENV === "development") {
  frontendUrl = 'http://' + config.dev.host + ':' + config.dev.port
  backendUrl = 'http://' + config.dev.backendHost + ':' + config.dev.backendPort
}
var AXIOS = axios.create({
    baseURL: backendUrl,
    headers: { 'Access-Control-Allow-Origin': frontendUrl }
})

function parseError(error) {
  var errorMsg = error
  if ( error.response ) {
      errorMsg = error.response.data
  }
  return errorMsg
}

// doesnt work
async function searchRequest (url) {
  return await new Promise((resolve, reject) => {
    AXIOS.get(url, {})
    .then( (response) => {
      console.log('serach request')
      console.log(response.data)
      resolve(response.data)
    })
    .catch( (error) => {
      var errorMsg = error
      if ( error.response ) {
          errorMsg = error.response.data
      }
      console.log(errorMsg)
      reject(errorMsg)
    })
    .finally(() => {
      console.log("returning request")
    })
  })
}

export default {
  name: "SearchLibItems",
  data () {
    return {
      msg: "Welcome to the Library",
      selected: '',
      selected2: '',
      message: '',
			error: '',
      archiveResults: new Array(),
      bookResults: new Array(),
      movieResults: new Array(),
      musicAlbumResults: new Array(),
      newspaperResults: new Array(),

      loggedInUser: '',
      loggedInType: ''
    };
  },
  created: function () {
    // check if logged in cookie is set
    var isLoggedIn = $cookies.isKey("loggedInUser")
    if (isLoggedIn) {
      this.loggedInUser = $cookies.get("loggedInUser")
      this.loggedInType = $cookies.get("loggedInType")
    }
  },
  methods: {
    doNothing() {

    },
    reserveItem(itemID) {
      AXIOS.post("/library-item/reserve", {}, {
        params: {
          "libCardNumber": this.loggedInUser,
          "libraryItemID": itemID
        }
      })
      .then(response => {
        this.error = ''
        window.location.reload()
      })
      .catch(error => {
        this.error = parseError(error)
      })
    },
    noResults () {
      var noResults = this.archiveResults.length === 0 && this.bookResults.length === 0 && this.movieResults.length === 0 && this.musicAlbumResults.length === 0 && this.newspaperResults.length === 0
      console.log("no results?: " + noResults)
      return noResults
    },
    search(query) {
      console.log("sel1: '" + this.selected + "' sel2: '" + this.selected2 + "'")

      // clear previous search error
      this.error = ''

      // clear previous search results
      this.archiveResults = new Array()
      this.bookResults = new Array()
      this.movieResults = []
      this.musicAlbumResults = []
      this.newspaperResults = []

      // if selected not all, and selected2 is set, then we need a query
      if (this.selected && this.selected !== "all" && this.selected2 && this.selected2 !== "all" && !query) {
        this.error = "Please enter a search term"
        return
      }

      var itemFilter = this.selected
      var attrFilter = this.selected2

      if (!itemFilter || itemFilter === "all") {
        console.log("serach all items")
        this.searchAll()
      } else if (itemFilter === "archive") {
        // Search archives
        if (!attrFilter) {
          attrFilter = "all"
        }
        console.log("serach book by " + attrFilter)
        this.searchArchives(query, attrFilter)

      } else if (itemFilter === "book") {
        // Search Books
        if (!attrFilter) {
          attrFilter = "all"
        }
        console.log("serach book by " + attrFilter)
        this.searchBooks(query, attrFilter)

      } else if (itemFilter === "movie") {
        // Search movies
        if (!attrFilter) {
          attrFilter = "all"
        }
        console.log("serach book by " + attrFilter)
        this.searchMovies(query, attrFilter)

      } else if (itemFilter === "musicalbum") {
        // Search music albums
        if (!attrFilter) {
          attrFilter = "all"
        }
        console.log("serach book by " + attrFilter)
        this.searchMusicAlbums(query, attrFilter)

      } else if (itemFilter === "newspaper") {
        // search newspapers
        if (!attrFilter) {
          attrFilter = "all"
        }
        console.log("serach book by " + attrFilter)
        this.searchNewspapers(query, attrFilter)
      }
    },
    searchAll () {
      console.log("search all function")
      AXIOS.get("/library-item/", {})
      .then(response => {
        this.error = ''
        var libraryItems = response.data
        libraryItems.forEach((item) => {
          switch (item.type) {
            case "BOOK":
              this.bookResults.push(item)
              break
            case "ARCHIVE":
              this.archiveResults.push(item)
              break
            case "MOVIE":
              this.movieResults.push(item)
              break
            case "MUSIC_ALBUM":
              this.musicAlbumResults.push(item)
              break
            case "NEWSPAPER":
              this.newspaperResults.push(item)
              break
            default:
              console.log("error: item type not recognized: '" + item.type + "'")
          }
        })
      })
      .catch(error => {
        this.error = parseError(error)
      })
      .finally(onfinally => {
        console.log("found "+ this.bookResults.length + " library items")
      })
    },
    searchArchives (query, attr) {
      switch (attr) {
        case "all":
          console.log("search all archives: " + this.archiveResults.length)
          AXIOS.get("/archive/", {})
          .then( (response) => {
            console.log('serach request')
            console.log(response.data)
            response.data.forEach((item) => {
              console.log("libitemarchive test: " + item.libraryItemID)
              this.archiveResults.push(item)
            })
          })
          .catch( (error) => {
            this.error = parseError(error)
          })
          .finally(() => {
            console.log("found " + this.archiveResults.length + " archives")
            console.log(this.archiveResults)
          })
          break
        case "title":
          console.log("search archive by title: ")
          AXIOS.get("/archive/by-title/" + query, {})
          .then( (response) => {
            console.log(response.data)
            response.data.forEach((item) => {
              console.log("libitemarchive test: " + item.libraryItemID)
              this.archiveResults.push(item)
            })
          })
          .catch( (error) => {
            this.error = parseError(error)
          })
          break
        case "published-year":
          AXIOS.get("/archive/by-published-year/" + query, {})
          .then( (response) => {
            console.log(response.data)
            response.data.forEach((item) => {
              console.log("libitemarchive test: " + item.libraryItemID)
              this.archiveResults.push(item)
            })
          })
          .catch( (error) => {
            this.error = parseError(error)
          })
          break
        case "institution":
          AXIOS.get("/archive/by-institution/" + query, {})
          .then( (response) => {
            console.log(response.data)
            response.data.forEach((item) => {
              console.log("libitemarchive test: " + item.libraryItemID)
              this.archiveResults.push(item)
            })
          })
          .catch( (error) => {
            this.error = parseError(error)
          })
          break
        default:
          console.log("invalid attr: '" + attr + "'")
      }
    },
    searchBooks (query, attr) {
      switch (attr) {
        case "all":
          console.log("search all books: " + this.bookResults.length)
          AXIOS.get("/book/", {})
          .then( (response) => {
            console.log('serach request')
            console.log(response.data)
            response.data.forEach((item) => {
              console.log("libitembook test: " + item.libraryItemID)
              this.bookResults.push(item)
            })
          })
          .catch( (error) => {
            this.error = parseError(error)
          })
          .finally(() => {
            console.log("found " + this.bookResults.length + " books")
            console.log(this.bookResults)
          })
          break
        case "title":
          console.log("search book by title: ")
          AXIOS.get("/book/by-title/" + query, {})
          .then( (response) => {
            console.log(response.data)
            response.data.forEach((item) => {
              console.log("libitembook test: " + item.libraryItemID)
              this.bookResults.push(item)
            })
          })
          .catch( (error) => {
            this.error = parseError(error)
          })
          break
        case "published-year":
          console.log("pub year")
          AXIOS.get("/book/by-published-year/" + query, {})
          .then( (response) => {
            console.log(response.data)
            response.data.forEach((item) => {
              console.log("libitembook test: " + item.libraryItemID)
              this.bookResults.push(item)
            })
          })
          .catch( (error) => {
            this.error = parseError(error)
          })
          break
        case "author":
          AXIOS.get("/book/by-author/" + query, {})
          .then( (response) => {
            console.log(response.data)
            response.data.forEach((item) => {
              console.log("libitembook test: " + item.libraryItemID)
              this.bookResults.push(item)
            })
          })
          .catch( (error) => {
            this.error = parseError(error)
          })
          break
        case "publisher":
          AXIOS.get("/book/by-publisher/" + query, {})
          .then( (response) => {
            console.log(response.data)
            response.data.forEach((item) => {
              console.log("libitembook test: " + item.libraryItemID)
              this.bookResults.push(item)
            })
          })
          .catch( (error) => {
            this.error = parseError(error)
          })
          break
        case "isbn":
          AXIOS.get("/book/by-isbn/" + query, {})
          .then( (response) => {
            console.log(response.data)
            response.data.forEach((item) => {
              console.log("libitembook test: " + item.libraryItemID)
              this.bookResults.push(item)
            })
          })
          .catch( (error) => {
            this.error = parseError(error)
          })
          break
        case "num-pages":
          AXIOS.get("/book/by-num-pages/" + query, {})
          .then( (response) => {
            console.log(response.data)
            response.data.forEach((item) => {
              console.log("libitembook test: " + item.libraryItemID)
              this.bookResults.push(item)
            })
          })
          .catch( (error) => {
            this.error = parseError(error)
          })
          break
        default:
          console.log("invalid attr: '" + attr + "'")
      }
    },
    searchMovies (query, attr) {
      switch (attr) {
        case "all":
          console.log("search all movies: " + this.movieResults.length)
          AXIOS.get("/movie/", {})
          .then( (response) => {
            console.log('serach request')
            console.log(response.data)
            response.data.forEach((item) => {
              console.log("libitemmovie test: " + item.libraryItemID)
              this.movieResults.push(item)
            })
          })
          .catch( (error) => {
            this.error = parseError(error)
          })
          .finally(() => {
            console.log("found " + this.movieResults.length + " movies")
            console.log(this.movieResults)
          })
          break
        case "title":
          console.log("search movie by title: ")
          AXIOS.get("/movie/by-title/" + query, {})
          .then( (response) => {
            console.log(response.data)
            response.data.forEach((item) => {
              console.log("libitemmovie test: " + item.libraryItemID)
              this.movieResults.push(item)
            })
          })
          .catch( (error) => {
            this.error = parseError(error)
          })
          break
        case "published-year":
          AXIOS.get("/movie/by-published-year/" + query, {})
          .then( (response) => {
            console.log(response.data)
            response.data.forEach((item) => {
              console.log("libitemmovie test: " + item.libraryItemID)
              this.movieResults.push(item)
            })
          })
          .catch( (error) => {
            this.error = parseError(error)
          })
          break
        case "director":
          AXIOS.get("/movie/by-director/" + query, {})
          .then( (response) => {
            console.log(response.data)
            response.data.forEach((item) => {
              console.log("libitemmovie test: " + item.libraryItemID)
              this.movieResults.push(item)
            })
          })
          .catch( (error) => {
            this.error = parseError(error)
          })
          break
        case "genre":
          AXIOS.get("/movie/by-genre/" + query, {})
          .then( (response) => {
            console.log(response.data)
            response.data.forEach((item) => {
              console.log("libitemmovie test: " + item.libraryItemID)
              this.movieResults.push(item)
            })
          })
          .catch( (error) => {
            this.error = parseError(error)
          })
          break
        case "runtime":
          AXIOS.get("/movie/by-runtime/" + query, {})
          .then( (response) => {
            console.log(response.data)
            response.data.forEach((item) => {
              console.log("libitemmovie test: " + item.libraryItemID)
              this.movieResults.push(item)
            })
          })
          .catch( (error) => {
            this.error = parseError(error)
          })
          break
        default:
          console.log("invalid attr: '" + attr + "'")
      }
    },
    searchMusicAlbums (query, attr) {
      switch (attr) {
        case "all":
          console.log("search all musicAlbums: " + this.musicAlbumResults.length)
          AXIOS.get("/musicAlbum/", {})
          .then( (response) => {
            console.log('serach request')
            console.log(response.data)
            response.data.forEach((item) => {
              console.log("libitemmusicAlbum test: " + item.libraryItemID)
              this.musicAlbumResults.push(item)
            })
          })
          .catch( (error) => {
            this.error = parseError(error)
          })
          .finally(() => {
            console.log("found " + this.musicAlbumResults.length + " musicAlbums")
            console.log(this.musicAlbumResults)
          })
          break
        case "title":
          console.log("search musicAlbum by title: ")
          AXIOS.get("/musicAlbum/by-title/" + query, {})
          .then( (response) => {
            console.log(response.data)
            response.data.forEach((item) => {
              console.log("libitemmusicAlbum test: " + item.libraryItemID)
              this.musicAlbumResults.push(item)
            })
          })
          .catch( (error) => {
            this.error = parseError(error)
          })
          break
        case "published-year":
          AXIOS.get("/musicAlbum/by-published-year/" + query, {})
          .then( (response) => {
            console.log(response.data)
            response.data.forEach((item) => {
              console.log("libitemmusicAlbum test: " + item.libraryItemID)
              this.musicAlbumResults.push(item)
            })
          })
          .catch( (error) => {
            this.error = parseError(error)
          })
          break
        case "artist":
          AXIOS.get("/musicAlbum/by-artist/" + query, {})
          .then( (response) => {
            console.log(response.data)
            response.data.forEach((item) => {
              console.log("libitemmusicAlbum test: " + item.libraryItemID)
              this.musicAlbumResults.push(item)
            })
          })
          .catch( (error) => {
            this.error = parseError(error)
          })
          break
        case "genre":
          AXIOS.get("/musicAlbum/by-genre/" + query, {})
          .then( (response) => {
            console.log(response.data)
            response.data.forEach((item) => {
              console.log("libitemmusicAlbum test: " + item.libraryItemID)
              this.musicAlbumResults.push(item)
            })
          })
          .catch( (error) => {
            this.error = parseError(error)
          })
          break
        case "num-songs":
          AXIOS.get("/musicAlbum/by-num-songs/" + query, {})
          .then( (response) => {
            console.log(response.data)
            response.data.forEach((item) => {
              console.log("libitemmusicAlbum test: " + item.libraryItemID)
              this.musicAlbumResults.push(item)
            })
          })
          .catch( (error) => {
            this.error = parseError(error)
          })
          break
        case "album-length":
          AXIOS.get("/musicAlbum/by-album-length/" + query, {})
          .then( (response) => {
            console.log(response.data)
            response.data.forEach((item) => {
              console.log("libitemmusicAlbum test: " + item.libraryItemID)
              this.musicAlbumResults.push(item)
            })
          })
          .catch( (error) => {
            this.error = parseError(error)
          })
          break
        default:
          console.log("invalid attr: '" + attr + "'")
      }
    },
    searchNewspapers (query, attr) {
      switch (attr) {
        case "all":
          console.log("search all newspapers: " + this.newspaperResults.length)
          AXIOS.get("/newspaper/", {})
          .then( (response) => {
            console.log('serach request')
            console.log(response.data)
            response.data.forEach((item) => {
              console.log("libitemnewspaper test: " + item.libraryItemID)
              this.newspaperResults.push(item)
            })
          })
          .catch( (error) => {
            this.error = parseError(error)
          })
          .finally(() => {
            console.log("found " + this.newspaperResults.length + " newspapers")
            console.log(this.newspaperResults)
          })
          break
        case "title":
          console.log("search newspaper by title: ")
          AXIOS.get("/newspaper/by-title/" + query, {})
          .then( (response) => {
            console.log(response.data)
            response.data.forEach((item) => {
              console.log("libitemnewspaper test: " + item.libraryItemID)
              this.newspaperResults.push(item)
            })
          })
          .catch( (error) => {
            this.error = parseError(error)
          })
          break
        case "published-year":
          AXIOS.get("/newspaper/by-published-year/" + query, {})
          .then( (response) => {
            console.log(response.data)
            response.data.forEach((item) => {
              console.log("libitemnewspaper test: " + item.libraryItemID)
              this.newspaperResults.push(item)
            })
          })
          .catch( (error) => {
            this.error = parseError(error)
          })
          break
        case "journal-name":
          AXIOS.get("/newspaper/by-journal-name/" + query, {})
          .then( (response) => {
            console.log(response.data)
            response.data.forEach((item) => {
              console.log("libitemnewspaper test: " + item.libraryItemID)
              this.newspaperResults.push(item)
            })
          })
          .catch( (error) => {
            this.error = parseError(error)
          })
          break
        case "edition":
          AXIOS.get("/newspaper/by-edition/" + query, {})
          .then( (response) => {
            console.log(response.data)
            response.data.forEach((item) => {
              console.log("libitemnewspaper test: " + item.libraryItemID)
              this.newspaperResults.push(item)
            })
          })
          .catch( (error) => {
            this.error = parseError(error)
          })
          break
        case "chief-editor":
          AXIOS.get("/newspaper/by-chief-editor/" + query, {})
          .then( (response) => {
            console.log(response.data)
            response.data.forEach((item) => {
              console.log("libitemnewspaper test: " + item.libraryItemID)
              this.newspaperResults.push(item)
            })
          })
          .catch( (error) => {
            this.error = parseError(error)
          })
          break
        default:
          console.log("invalid attr: '" + attr + "'")
      }
    },
    goToSearchPage (){
        Router.push({
            path: "/SearchResults",
            name: "SearchResults"
        })
    }
  }
}