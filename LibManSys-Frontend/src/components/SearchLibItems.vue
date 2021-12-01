<--! Same as Hello, might be better to use this-->

<template>
  <div class="search-lib-items-body">

    <section>
      <br />
      <h2> Search Page </h2>
     
      <searchbar class="wrap">
        
        <searchbar class="search" color="#ffffff">
         <!--  <select v-model="selected">
  <option v-for="option in options" v-bind:value="option.value">
    {{ option.text }}
  </option>
</select> -->

<select v-model="selected">
  <option disabled value=""> Select Item Type</option>
  <option value="all">All</option>
  <option value = 'archive'>Archive</option>
  <option value ='book'>Book</option>
  <option value ='movie'>Movie</option>
  <option value ='newspaper'>NewsPaper</option>
  <option value ='musicalbum'>MusicAlbum</option>
</select>
<div v-show="selected === 'archive'">
<select v-model="selected2">
  <option disabled value="">Select Filter</option>
  <option>Institution</option>
    <option>Published Year</option>
  <option>Title</option>
</select>
</div>
<div v-show="selected === 'book'">
<select v-model="selected2">
  <option disabled value="">Select Filter</option>
    <option value = 'all'>All</option>
  <option value = 'author'>Author</option>
  <option value = 'isbn'>ISBN</option>
    <option value = 'publishedYear'>Published Year</option>
  <option>Title</option>
</select>
</div>
<div v-show="selected === 'movie'">
<select v-model="selected2">
  <option disabled value="">Select Filter</option>
      <option value = 'all'>All</option>
  <option value = 'director'>Director</option>
      <option value = 'genre'>Genre</option>
      <option value = 'publishedYear'>Published Year</option>
  <option value = 'title'>Title</option>
</select>
</div>
<div v-show="selected === 'newspaper'">
<select v-model="selected2">
  <option disabled value="">Select Filter</option>
  <option>Chief Editor</option>
    <option>Edition </option>
    <option>Journal Name</option>
    <option>Published Year</option>
  <option>Title</option>
</select>
</div>
<div v-show="selected === 'musicalbum'">
<select v-model="selected2">
  <option disabled value="">Select Filter</option>
  <option>Artist </option>
    <option>Genre </option>
    <option>Published Year</option>
  <option>Title</option>
</select>
</div>
          <input
            type="text"
            class="searchTerm"
            placeholder="What are you looking for?"
          />

           <!-- General Search Button-->
      <div v-show="selected !== 'all' && selected !== 'book' && selected !== 'movie' && selected !== 'archive' && selected !== 'musicalbum' && selected !== 'newspaper'    " >
          <findbutton
            type="submit"
            class="searchButton"
            v-on:click="goToSearchPage()"
          > 
            <img src="../assets/search.png" align="top" />
          </findbutton> </div>


           <!-- All Button-->
          <div v-show="selected === 'all'" >
          <findbutton
            type="submit"
            class="searchButton"
            v-on:click="goToSearchPage()"
          > 
            <img src="../assets/search.png" align="top" />
          </findbutton> </div>
 <!-- Movie Buttons-->
            <div v-show="selected  === 'movie' && selected2!=='director'&& selected2!=='title'&& selected2!=='publishedYear'&& selected2!=='genre'" >
          <findbutton
            type="submit"
            class="searchButton"
            v-on:click="movieHidden = false, bookHidden = true"
          > 
            <img src="../assets/search.png" align="top" />
          </findbutton> </div>
            <div v-show="selected  === 'movie' && (selected2==='director'|| selected2==='title'|| selected2==='publishedYear'|| selected2==='genre')" >
          <findbutton
            type="submit"
            class="searchButton"
            v-on:click=""
          > 
            <img src="../assets/search.png" align="top" />
          </findbutton> </div>

 <!-- Book Buttons-->
            <div v-show="selected === 'book' && selected2!=='author'&& selected2!=='title'&& selected2!=='publishedYear'&& selected2!=='isbn'" >
          <findbutton
            type="submit"
            class="searchButton"
            v-on:click="bookHidden = false, movieHidden = true"
          > 
            <img src="../assets/search.png" align="top" />
          </findbutton> </div>

            <div v-show="selected === 'book' && (selected2==='author'|| selected2==='title'|| selected2==='publishedYear'|| selected2==='isbn')" >
          <findbutton
            type="submit"
            class="searchButton"
            v-on:click=""
          > 
            <img src="../assets/search.png" align="top" />
          </findbutton> </div>
        </searchbar>
      </searchbar>
 <!-- BOOK -->
 <br>
 <br>
 <br>
 <br>
     <div  v-if="!bookHidden && movieHidden"> 
     <myText3>All Books</myText3>
    
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
      <tr v-for="result in results" :key="result">
          <td >  
          <myText2> {{result.libraryItemID}}</myText2></td> 
                  <td>  <myText2> {{result.title}}</myText2></td> 

                     <td> <myText2> {{result.author}}</myText2></td> 
                   <td>
             <myText2> {{result.numPages}} </myText2> </td>  
              <td> <myText2> {{result.publishedYear}}</myText2></td> 
                      <td> <myText2> {{result.isbn}}</myText2></td> 
                                 

                <td>
            <myText2>  {{result.loanablePeriod}}</myText2> </td> 
              
              <td>
             <myText2> {{result.itemStatus}} </myText2> </td> 
             
      </tr>
      
    </v-data-table> 
   
    </div>
 <!-- -->
     <div v-if="!movieHidden && bookHidden">

     <myText3>All Movies</myText3>
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
      <tr v-for="result in movies" :key="result">
          <td >  
          <myText2> {{result.libraryItemID}}</myText2></td> 
                 <td>   <myText2> {{result.title}}</myText2></td> 

                     <td> <myText2> {{result.publishedYear}}</myText2></td> 
                   <td>
             <myText2> {{result.genre}} </myText2> </td>  
              <td>
             <myText2> {{result.runtime}} </myText2> </td>               
           <td>  <myText2> {{result.director}} </myText2> </td>               

                <td>
            <myText2>  {{result.loanablePeriod}}</myText2> </td> 
              
              <td>
             <myText2> {{result.itemStatus}} </myText2> </td> 
             
      </tr>
      
    </v-data-table> 
   
    </div>
    
 <!-- -->



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
  background: none;
  text-align: center;
  border-radius: 0 5px 5px 0;
  cursor: pointer;
  font-size: 20px;
  vertical-align: text-top;
}

/*Resize the wrap to see the search bar change!*/
.wrap {
  width: 60%;
  position: absolute;
  top: 20%;
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
