<!-- Same as Hello, might be better to use this-->

<template>
  <div class="search-lib-items-body">

    <section>
      <br />
      <h2> Search Page </h2>

      <br />
      <br />
    
      <b-container>
        <searchbar class="search" color="#ffffff">

          <select v-model="selected">
            <option disabled value=""> Select Item Type</option>
            <option value="all">All</option>
            <option value = 'archive'>Archive</option>
            <option value ='book'>Book</option>
            <option value ='movie'>Movie</option>
            <option value ='newspaper'>Newspaper</option>
            <option value ='musicalbum'>Music Album</option>
          </select>

          <!-- Archive filters -->
          <select v-model="selected2" v-show="selected === 'archive'">
            <option disabled value="">Select Filter</option>
            <option value = 'all'>All</option>
            <option value = 'institution'>Institution</option>
            <option  value = 'publishedYear'>Published Year</option>
            <option value = 'title'>Title</option>
          </select>

          <!-- book filters -->
          <select v-model="selected2" v-show="selected === 'book'">
            <option disabled value="">Select Filter</option>
            <option value = 'all'>All</option>
            <option value = 'author'>Author</option>
            <option value = 'isbn'>ISBN</option>
            <option value = 'publishedYear'>Published Year</option>
            <option>Title</option>
          </select>

          <!-- movie filters -->
          <select v-model="selected2" v-show="selected === 'movie'">
            <option disabled value="">Select Filter</option>
            <option value = 'all'>All</option>
            <option value = 'director'>Director</option>
            <option value = 'genre'>Genre</option>
            <option value = 'publishedYear'>Published Year</option>
            <option value = 'title'>Title</option>
          </select>

          <!-- newspaper filters -->
          <select v-model="selected2" v-show="selected === 'newspaper'">
            <option disabled value="">Select Filter</option>
            <option value = 'all'>All</option>
            <option value = 'chiefeditor'>Chief Editor</option>
            <option value = 'edition'>Edition </option>
            <option value = 'journalName'>Journal Name</option>
            <option value = 'publishedYear'>Published Year</option>
            <option  value = 'title'>Title</option>
          </select>

          <!-- music album filters -->
          <select v-model="selected2" v-show="selected === 'musicalbum'">
            <option disabled value="">Select Filter</option>
            <option value = 'all'>All</option>
            <option value = 'artist'>Artist </option>
            <option value = 'genre'>Genre </option>
            <option value = 'publishedyear'>Published Year</option>
            <option  value = 'title'>Title</option>
          </select>

          <!-- search bar input -->
          <input
            v-model ="message"
              type="text"
              class="searchTerm"
              placeholder="What are you looking for?"
          />

          <!-- display search term for debugging -->
          <!--   
          <p>Message is: {{ message }}</p>  
          -->

          <!-- General Search Button-->
          <div>
            <b-button type="submit" class="searchButton" v-on:click="search(message)"> 
              <img src="../assets/search.png" class="searchImage" />
            </b-button> 
          </div>

        </searchbar>
      </b-container>

      <br>
      <br>
      <br>
       <br>
      <br>
      <br>
    <!-- Display Results -->

 <!-- BOOK -->

    <div  v-if="bookResults.length"> 
      <myText3>All Books</myText3>
      <br>
      <v-data-table  class="elevation-1" >
        <tr><b-col class="myColumn">
          <td><myText2>|  ID  |</myText2> </td> </b-col>
          <td><myText2>|  Title  |</myText2> </td>
          <td><myText2>|  Author  |</myText2> </td>
          <td><myText2>|  Pages  |</myText2> </td>
          <td><myText2>|  Published Year  |</myText2> </td>
          <td><myText2>|  ISBN  |</myText2> </td>
          <td><myText2>|  Loanable Period  |</myText2></td>
          <td><myText2>|  Status  |</myText2></td>
        </tr>
        <tr v-for="result in bookResults" :key="result">
          <td><myText2> {{result.libraryItemID}}</myText2></td> 
          <td><myText2> {{result.title}}</myText2></td> 
          <td><myText2> {{result.author}}</myText2></td> 
          <td><myText2> {{result.numPages}} </myText2> </td>  
          <td><myText2> {{result.publishedYear}}</myText2></td> 
          <td><myText2> {{result.isbn}}</myText2></td> 
          <td><myText2>  {{result.loanablePeriod}}</myText2> </td>       
          <td><myText2> {{result.itemStatus}} </myText2> </td>
        </tr>
      
      </v-data-table> 
    </div>

    <!-- Movie -->
    <div v-if="movieResults.length">
      <myText3>All Movies</myText3>
      <br>
      <v-data-table  class="elevation-1" >
        <tr><b-col class="myColumn">
          <td><myText2>|  ID  |</myText2> </td> </b-col>
          <td><myText2>|  Title  |</myText2> </td>
          <td><myText2>|  Published Year |</myText2> </td>
          <td><myText2>|  Genre  |</myText2> </td>
          <td><myText2>|  Runtime  |</myText2> </td>
          <td><myText2>|  Director  |</myText2> </td>
          <td><myText2>|  Loanable Period  |</myText2></td>
          <td><myText2>|  Status  |</myText2></td>
        </tr>
        <tr v-for="result in movieResults" :key="result">
          <td><myText2> {{result.libraryItemID}}</myText2></td> 
          <td><myText2> {{result.title}}</myText2></td> 
          <td><myText2> {{result.publishedYear}}</myText2></td> 
          <td><myText2> {{result.genre}} </myText2> </td>  
          <td><myText2> {{result.runtime}} </myText2> </td>               
          <td><myText2> {{result.director}} </myText2> </td>               
          <td><myText2>  {{result.loanablePeriod}}</myText2> </td> 
          <td><myText2> {{result.itemStatus}} </myText2> </td> 
              
        </tr>
      </v-data-table> 
    </div>
    
    <!-- CD -->
    <div v-if="musicAlbumResults.length">
      <myText3>All Music Albums</myText3>
      <br>
      <v-data-table  class="elevation-1" >
        <tr><b-col class="myColumn">
          <td><myText2>|  ID  |</myText2> </td> </b-col>
          <td><myText2>|  Title  |</myText2> </td>
          <td><myText2>|  Published Year |</myText2> </td>
          <td><myText2>|  Genre   |</myText2> </td>
          <td><myText2>|  Artist  |</myText2> </td>
          <td><myText2>|  Number of Songs  |</myText2> </td>
          <td><myText2>| Length(min)   |</myText2></td>
        </tr>
        <tr v-for="result in musicAlbumResults" :key="result">
            <td><myText2> {{result.libraryItemID}}</myText2></td> 
            <td><myText2> {{result.title}}</myText2></td> 
            <td><myText2> {{result.publishedYear}}</myText2></td> 
            <td><myText2> {{result.genre}} </myText2> </td>  
            <td><myText2> {{result.artist}} </myText2> </td>               
            <td><myText2> {{result.numSongs}} </myText2> </td>               
            <td><myText2>  {{result.albumLengthInMinutes}}</myText2> </td>   
        </tr>
      </v-data-table> 
    </div>

    <!-- archive -->
    <div v-if="archiveResults.length">
      <myText3>All Archives </myText3>
      <br>

      <v-data-table  class="elevation-1" >
        <tr><b-col class="myColumn">
          <td><myText2>|  ID  |</myText2> </td>  </b-col>
          <td><myText2>|  Title  |</myText2> </td>
          <td><myText2>|  Published Year |</myText2> </td>
          <td><myText2>|  Institution   |</myText2> </td>
        </tr>
        <tr v-for="result in archiveResults" :key="result">
          <td ><myText2> {{result.libraryItemID}}</myText2></td> 
          <td>  <myText2> {{result.title}}</myText2></td> 
          <td> <myText2> {{result.publishedYear}}</myText2></td> 
          <td> <myText2> {{result.institution}} </myText2> </td>
        </tr>      
      </v-data-table> 
    </div>

    <!-- newspaper -->
    <div v-if="newspaperResults.length">
      <myText3>All NewsPaper</myText3>

      <v-data-table  class="elevation-1" >
        <tr><b-col class="myColumn">
          <td><myText2>|  ID  |</myText2> </td> </b-col>
          <td><myText2>|  Title  |</myText2> </td>
          <td><myText2>|  Published Year |</myText2> </td>
          <td><myText2>|  Journal Name  |</myText2> </td>
          <td><myText2>|  Edition  |</myText2> </td>
          <td><myText2>| Chief Editor  |</myText2></td>
        </tr>
        <tr v-for="result in newspaperResults" :key="result">
          <td><myText2> {{result.libraryItemID}}</myText2></td> 
          <td>  <myText2> {{result.title}}</myText2></td> 
          <td> <myText2> {{result.publishedYear}}</myText2></td> 
          <td>  <myText2> {{result.journalName}} </myText2> </td>  
          <td> <myText2> {{result.edition}} </myText2> </td>               
          <td>  <myText2> {{result.chiefEditior}} </myText2> </td>         
        </tr>
      
      </v-data-table> 
    </div>

    </section>

  </div>
</template>

<script src="./SearchLibItems.js"></script> 

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
@import url("https://fonts.googleapis.com/css2?family=Lato:wght@100&display=swap");



b.nav-link {
  color: #ffffff;
  font-size: 18px;
}
b-navbar {
  background-color: #4e1d04;
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
  height: 100vh;
  background-size: contain;
  background-repeat: no-repeat;
  background-size: 100%;
}
.hours {
  position: absolute;
  top: 15%;
  bottom: 12%;
  right: 1%;
  left: 52%;
  background-color: #1e3535;
  color: white;
  padding-left: 40px;
  padding-right: 40px;
  opacity: 0.7;
  text-align: left;
}
a.normal {
  font-weight: 400;
}
h2 {
  font-weight: normal;
}
a.thick {
  font-weight: bold;
}
a.light {
  font-weight: 100;
}
ul {
  list-style-type: none;
  padding: 0;
}
.nav-left {
  display: flex;
}
.nav-right {
  display: flex;
}

li {
  display: inline-block;
  cursor: pointer;
  margin: 0px;
}
li.nav-item:hover {
  color: #8c5440;
}

a {
  color: #000000;
  font-size: 70px;
}
b {
  font: "Lato", sans-serif;
  font-weight: 100;
  color: #000000;
  font-size: 18px;
}
b-nav-item {
  color: #efecf2;
  font-size: 18px;
}
b-nav-item:hover {
  color: #8c5440;
}
#container {
  width: 65%;
  margin: 0 auto;
}
smallButton {
  font-family: "Segoe UI", Tahoma, Geneva, Verdana, sans-serif;
  padding: 16px 32px;
  background-color: #a85f32;
  border-radius: 10px;
  font-size: 12px;
  color: #000;
  background: #a85f32;
  border: 0;
  font-weight: 200;
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

.searchTerm {
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

.searchTerm:focus {
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

/* Footer */
footer {
  height: 40px;
  font-weight: 200;
  text-align: center;
}
</style>
