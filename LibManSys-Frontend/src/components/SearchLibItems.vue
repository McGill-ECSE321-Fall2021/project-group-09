<!-- Same as Hello, might be better to use this-->

<template>
  <div class="search-lib-items-body">

    <section>
      <br />
      <h2 class="search-title"> Search Library Catalogue </h2>

      <b-container v-if="error" style="margin-bottom: 1em">
        <span class="error-msg">{{error}}</span>
      </b-container>
    
      <b-container>
        <div class="search" color="#ffffff">

          <select v-model="selected">
            <option disabled value=""> Select Item Type</option>
            <option value="all">All</option>
            <option value = 'archive'>Archive</option>
            <option value ='book'>Book</option>
            <option value ='movie'>Movie</option>
            <option value ='musicalbum'>Music Album</option>
            <option value ='newspaper'>Newspaper</option>
          </select>

          <!-- Archive filters -->
          <select v-model="selected2" v-show="selected === 'archive'">
            <option disabled value="">Select Filter</option>
            <option value = 'all'>All</option>
            <option value = 'title'>Title</option>
            <option value = 'published-year'>Published Year</option>
            <option value = 'institution'>Institution</option>
          </select>

          <!-- book filters -->
          <select v-model="selected2" v-show="selected === 'book'">
            <option disabled value="">Select Filter</option>
            <option value = 'all'>All</option>
            <option value = 'title'>Title</option>
            <option value = 'published-year'>Published Year</option>
            <option value = 'author'>Author</option>
            <option value = 'publisher'>Publisher</option>
            <option value = 'isbn'>ISBN</option>
            <option value = 'num-pages'>Number of Pages</option>
          </select>

          <!-- movie filters -->
          <select v-model="selected2" v-show="selected === 'movie'">
            <option disabled value="">Select Filter</option>
            <option value = 'all'>All</option>
            <option value = 'title'>Title</option>
            <option value = 'published-year'>Published Year</option>
            <option value = 'director'>Director</option>
            <option value = 'genre'>Genre</option>
          </select>

          <!-- music album filters -->
          <select v-model="selected2" v-show="selected === 'musicalbum'">
            <option disabled value="">Select Filter</option>
            <option value = 'all'>All</option>
            <option  value = 'title'>Title</option>
            <option value = 'published-year'>Published Year</option>
            <option value = 'artist'>Artist </option>
            <option value = 'genre'>Genre </option>
            <option  value = 'album-length'>Album Length in Minutes</option>
          </select>

          <!-- newspaper filters -->
          <select v-model="selected2" v-show="selected === 'newspaper'">
            <option disabled value="">Select Filter</option>
            <option value = 'all'>All</option>
            <option  value = 'title'>Title</option>
            <option value = 'published-year'>Published Year</option>
            <option value = 'journal-name'>Journal Name</option>
            <option value = 'edition'>Edition </option>
            <option value = 'chief-editor'>Chief Editor</option>
          </select>

          <!-- search bar input -->
          <input
            v-model ="message"
            type="text"
            id="search-box"
            placeholder="What are you looking for?"
          />

          <!-- Search Button-->
          <div>
            <b-button type="submit" class="searchButton" v-on:click="search(message)"> 
              <img src="../assets/search.png" class="searchImage" />
            </b-button> 
          </div>

        </div>
      </b-container>

    <!-- Display Results -->
    <br />

    <div v-if="noResults()">
      No Results to Display
    </div>

    <!-- archive -->
    <div v-if="archiveResults.length">
      <myText>Archive Results</myText>
      <table  class="center" >
        <tr>
          <td>|  ID  | </td>
          <td>|  Title  | </td>
          <td>|  Published Year | </td>
          <td>|  Institution   | </td>
        </tr>
        <tr v-for="result in archiveResults" :key="result.libraryItemID">
          <td > {{result.libraryItemID}}</td> 
          <td>   {{result.title}}</td> 
          <td>  {{result.publishedYear}}</td> 
          <td>  {{result.institution}}  </td>
        </tr>      
      </table> 
    </div>

    <!-- BOOK -->
    <b-container  v-if="bookResults.length"> 
      <myText>Book Results</myText>
      <table  class="center" >
        <tr>
          <td>|  ID  | </td> 
          <td>|  Title  | </td>
          <td>|  Author  | </td>
          <td>|  Pages  | </td>
          <td>|  Published Year  | </td>
          <td>|  ISBN  | </td>
          <td>|  Loanable Period  |</td>
          <td>|  Status  |</td>
        </tr>
        <tr v-for="result in bookResults" :key="result.libraryItemID">
          <td> {{result.libraryItemID}}</td> 
          <td> {{result.title}}</td> 
          <td> {{result.author}}</td> 
          <td> {{result.numPages}}  </td>  
          <td> {{result.publishedYear}}</td> 
          <td> {{result.isbn}}</td> 
          <td>  {{result.loanablePeriod}} </td>       
          <td> {{result.itemStatus}}  </td>
          <td v-if="loggedInUser && loggedInType === 'onlineMember'">
            <div v-if="result.itemStatus === 'Available'">
              <b-button type="submit" class="reserve-button" v-on:click="reserveItem(result.libraryItemID)">
                Reserve Item
              </b-button>
            </div>
            <div v-else>
              <b-button type="submit" class="reserve-button-na" v-on:click="doNothing()">
                Not Available
              </b-button>
            </div>
          </td>
        </tr>
      
      </table> 
    </b-container>

    <!-- Movie -->
    <div v-if="movieResults.length">
      <myText>Movie Results</myText>
      <table  class="center" >
        <tr>
          <td>|  ID  | </td> 
          <td>|  Title  | </td>
          <td>|  Published Year | </td>
          <td>|  Genre  | </td>
          <td>|  Runtime  | </td>
          <td>|  Director  | </td>
          <td>|  Loanable Period  |</td>
          <td>|  Status  |</td>
        </tr>
        <tr v-for="result in movieResults" :key="result.libraryItemID">
          <td> {{result.libraryItemID}}</td> 
          <td> {{result.title}}</td> 
          <td> {{result.publishedYear}}</td> 
          <td> {{result.genre}}  </td>  
          <td> {{result.runtime}}  </td>               
          <td> {{result.director}}  </td>               
          <td>  {{result.loanablePeriod}} </td> 
          <td> {{result.itemStatus}}  </td> 
          <td v-if="loggedInUser && loggedInType === 'onlineMember'">
            <div v-if="result.itemStatus === 'Available'">
              <b-button type="submit" class="reserve-button" v-on:click="reserveItem(result.libraryItemID)">
                Reserve Item
              </b-button>
            </div>
            <div v-else>
              <b-button type="submit" class="reserve-button-na" v-on:click="doNothing()">
                Not Available
              </b-button>
            </div>
          </td> 
        </tr>
      </table> 
    </div>
    
    <!-- CD -->
    <div v-if="musicAlbumResults.length">
      <myText>Music Album Results</myText>
      <table  class="center" >
        <tr>
          <td>|  ID  | </td> 
          <td>|  Title  | </td>
          <td>|  Published Year | </td>
          <td>|  Genre   | </td>
          <td>|  Artist  | </td>
          <td>|  Number of Songs  | </td>
          <td>| Length(min)   |</td>
        </tr>
        <tr v-for="result in musicAlbumResults" :key="result.libraryItemID">
            <td> {{result.libraryItemID}}</td> 
            <td> {{result.title}}</td> 
            <td> {{result.publishedYear}}</td> 
            <td> {{result.genre}}  </td>  
            <td> {{result.artist}}  </td>               
            <td> {{result.numSongs}}  </td>               
            <td>  {{result.albumLengthInMinutes}} </td>  
             <td v-if="loggedInUser && loggedInType === 'onlineMember'">
            <div v-if="result.itemStatus === 'Available'">
              <b-button type="submit" class="reserve-button" v-on:click="reserveItem(result.libraryItemID)">
                Reserve Item
              </b-button>
            </div>
            <div v-else>
              <b-button type="submit" class="reserve-button-na" v-on:click="doNothing()">
                Not Available
              </b-button>
            </div>
          </td> 
        </tr>
      </table> 
    </div>

    <!-- newspaper -->
    <div v-if="newspaperResults.length">
      <myText>Newspaper Results</myText>
      <table  class="center" >
        <tr>
          <td>|  ID  | </td>
          <td>|  Title  | </td>
          <td>|  Published Year | </td>
          <td>|  Journal Name  | </td>
          <td>|  Edition  | </td>
          <td>| Chief Editor  |</td>
        </tr>
        <tr v-for="result in newspaperResults" :key="result.libraryItemID">
          <td> {{result.libraryItemID}}</td> 
          <td>   {{result.title}}</td> 
          <td>  {{result.publishedYear}}</td> 
          <td>   {{result.journalName}}  </td>  
          <td>  {{result.edition}}  </td>               
          <td>   {{result.chiefEditior}}  </td>         
        </tr>
      
      </table> 
    </div>

    </section>

    <br />

  </div>
</template>

<script src="./SearchLibItems.js"></script> 

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
@import url("https://fonts.googleapis.com/css2?family=Lato:wght@100&display=swap");

td {
  padding: 0.25em 0.5em;
}

.reserve-button {
  padding: 0.25em 0.5em;
  font-size: 0.8em;
  background-color: #41cc4b;
}

.reserve-button-na {
  padding: 0.25em 0.5em;
  font-size: 0.8em;
  background-color: #ed3d37;
}

.center {
  margin-left: auto;
  margin-right: auto;
}

.search-title {
  font-weight: 1000;
}

.error-msg {
  font-weight: 300;
  color: red;
}

.search-lib-items-body {
  height: 100%;
}

div {
  font-family: "Segoe UI", Tahoma, Geneva, Verdana, sans-serif;
  color: #000000;
  

  background-size: contain;
  background-repeat: no-repeat;
  background-size: 100%;
  -ms-overflow-style: none; /* IE and Edge */
  scrollbar-width: none; /* Firefox */
  
}

::-webkit-scrollbar {
  display: none;
}
section {
  font-family: "Lato", sans-serif;
  background-size: contain;
  background-repeat: no-repeat;
  background-size: 100%;
}

myText {
    color: #622A0F;
    font-size: 28px;
    font-weight: 100;
}

#container {
  width: 65%;
  margin: 0 auto;
}
button {
  font-family: "Segoe UI", Tahoma, Geneva, Verdana, sans-serif;
  /* padding: 16px 32px; */
  /* background-color: a85f32; */
  /* border-radius: 10px; */
  /* font-size: 15px; */
  color: #000;
  /* background: a85f32; */
  /* border: 0; */
  /* font-weight: 200; */
}
/*Search Bar */
.search {
  width: 100%;
  position: relative;
  display: flex;
}

#search-box {
  width: 100%;
  border: 2px solid #000000;
  border-right: none;
  padding: 15px;
  height: 30px;
  border-radius: 5px 0 0 5px;
  outline: none;
  color: #000000;
  background: none;
}

#search-box:focus {
  color: #000000;
}

.searchButton {
  width: 40px;
  height: 34px;
  border: 2px solid #000000;
  border-left-style: hidden;
  background-color: #505050;
  text-align: center;
  border-radius: 0 5px 5px 0;
  cursor: pointer;
  font-size: 20px;
}
.searchImage {
  vertical-align: top;
}

/*Resize the wrap to see the search bar change!*/
.wrap {
  width: 60%;
  position: absolute;
  top: 30%;
  left: 50%;
  transform: translate(-50%, -50%);
}
input,
::placeholder {
  font-family: "Lato", sans-serif;
  font-size: 18px;
  font-weight: 600;
  color: #000000;
}

</style>
