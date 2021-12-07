
<template>
  <div class="search-results-body">

    <section>
      <br />
      <br />
           <br />

   <b-container fluid>
      <myText>  Search Results </myText>
<br>
 
  
    <myText3>All Archives </myText3>
    <div>
    <v-data-table  class="elevation-1" >
      <tr><b-col class="myColumn">
          <td><myText2>|  ID  |</myText2> </td>  </b-col>
                    <td><myText2>|  Title  |</myText2> </td>

          <td><myText2>|  Published Year |</myText2> </td>
                    <td><myText2>|  Institution   |</myText2> </td>
      </tr>
      <tr v-for="result in archives" :key="result">
          <td >   <myText2> {{result.libraryItemID}}</myText2></td> 
                  <td>  <myText2> {{result.title}}</myText2></td> 

                     <td> <myText2> {{result.publishedYear}}</myText2></td> 
                   <td> <myText2> {{result.institution}} </myText2> </td>
              
             
             
      </tr>
      
    </v-data-table> 
   
    </div>

     <myText3>All Books</myText3>
    <div > 
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


 <myText3>All Movies</myText3>
    <div>
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

    <myText3>All Music Albums</myText3>
    <div>
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
      <tr v-for="result in musicAlbums" :key="result">
          <td >  
          <myText2> {{result.libraryItemID}}</myText2></td> 
                  <td>  <myText2> {{result.title}}</myText2></td> 

                     <td> <myText2> {{result.publishedYear}}</myText2></td> 
                   <td>
             <myText2> {{result.genre}} </myText2> </td>  
              <td>  <myText2> {{result.artist}} </myText2> </td>               
            <td> <myText2> {{result.numSongs}} </myText2> </td>               

                <td>
            <myText2>  {{result.albumLengthInMinutes}}</myText2> </td> 
              
             
             
      </tr>
      
    </v-data-table> 
   
    </div>

    <myText3>All NewsPaper</myText3>
    <div>
    <v-data-table  class="elevation-1" >
      <tr><b-col class="myColumn">
          <td><myText2>|  ID  |</myText2> </td> </b-col>
                    <td><myText2>|  Title  |</myText2> </td>

                  <td><myText2>|  Published Year |</myText2> </td>
                    <td><myText2>|  Journal Name  |</myText2> </td>
                    <td><myText2>|  Edition  |</myText2> </td>
                    <td><myText2>| Chief Editor  |</myText2></td>
      </tr>
      <tr v-for="result in newspapers" :key="result">
          <td >  
          <myText2> {{result.libraryItemID}}</myText2></td> 
              <td>  <myText2> {{result.title}}</myText2></td> 
              <td> <myText2> {{result.publishedYear}}</myText2></td> 
              <td>  <myText2> {{result.journalName}} </myText2> </td>  
              <td> <myText2> {{result.edition}} </myText2> </td>               
              <td>  <myText2> {{result.chiefEditior}} </myText2> </td>         
              
             
             
      </tr>
      
    </v-data-table> 
   
    </div>

</b-container>

    </section>
    

   
  </div>


</template>
<script src="./SearchResults.js"></script> 

<!--
<script>

import MemberLogin from "../components/MemberLogin";
import Router from "../router/index";
import SearchLibItems from "../components/SearchLibItems";
import Register from "../components/Register";

export default {
  name: 'hello',
  data () {
   return {
        types: [
        ],
         headers: [{
                    text: 'Loans',
                    align: 'start',
                    sortable: false,
                    value: 'name',
                },
                { text: 'Item Type', value: 'itemType' },
                { text: 'Title', value: 'title' },
                { text: 'ISBN ', value: 'isbn' },
                { text: 'Author', value: 'author' },
                { text: 'Published Year ', value: 'publishedYear' },
                { text: 'Item ID', value: 'libraryItemId' },

            ],
            myTable: [{
                    itemType: 'Book',
                    title: 'Test1',
                    isbn: '2/2/2021',
                    author: '3/2/2021',
                    publishedYear: 2,
                    libraryItemId: '1'
                },
                {
                     itemType: 'Book',
                    title: 'Test2',
                    isbn: '2/3/2021',
                    author: '3/3/2021',
                    publishedYear: 4,
                    libraryItemId: '2'                    
                }
            ]
      }
  },
   methods: {
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
</script> -->


<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
@import url("https://fonts.googleapis.com/css2?family=Lato:wght@100&display=swap");

.bg-custom-1 {
  background: linear-gradient(
    200deg,
    rgba(0, 0, 0, 1),
    rgba(0, 0, 0, 0.8),
    rgba(0, 0, 0, 0)
  );
}

b.nav-link {
  color: #000000;
  font-size: 18px;
}
b-navbar {
  background-color: #4e1d04;
}

div {
 /*font-family: "Segoe UI", Tahoma, Geneva, Verdana, sans-serif;*/
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
  background-size: contain;
  background-repeat: no-repeat;
  background-size: 100%;
}
myText2 {
    color: #5E3515;
    font: "Times";
    font-size: 18px;
    font-weight: 100;
}

a.normal {
  font-weight: 400;
}
h1, h2 {
    font: "Lato";
  font-weight: 100;
  color: black;

}
ul {
  list-style-type: none;
  padding: 0;
}

li {
  display: inline-block;
  margin: 0 10px;
}

a {
  color: #42b983;
}
b {
  font: "Lato", sans-serif;
  font-weight: 100;
  color: #ffffff;
  font-size: 18px;
}
b-nav-item {
  color: #efecf2;
  font-size: 18px;
}
myText{
  color:black;
  font: "Times";
  font-size: 35px;
  font-weight: 100;

}

myText3{
  color:#a85f32;
  font: "Times";
  font-size: 25px;
  font-weight: 100;

}
.nav-left {
  display: flex;
}
.nav-right {
  display: flex;
}

#container{
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


/*Resize the wrap to see the search bar change!*/
.wrap {
  width: 40%;
  position: absolute;
  top: 60%;
  left: 50%;
  transform: translate(-50%, -50%);
}
input,
::placeholder {
  font-family: "Lato", sans-serif;
  font-size: 18px;
  font-weight: 600;
  color: #ffffff;
}
</style>
