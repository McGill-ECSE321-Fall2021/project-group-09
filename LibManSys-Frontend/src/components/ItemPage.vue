
<template>
  <div class="item-page-body">


    <section>
      <br />
      <br />
           <br />

   <b-container fluid>
      <myText>  Test for a library Item </myText>

  <b-row class="my-1" color="#00000">
    <b-col sm="2" color="#00000">
      <label for="input-default" color="#00000">Name:</label>
    </b-col>
   
  </b-row>

  <b-row class="my-1" >
    <b-col sm="2" > 
      <label for="input-large" >Item Name:</label>
    </b-col>
  </b-row>
  <b-row class="my-1" >
    <b-col sm="2" > 
      <label for="input-large" >ISBN:</label>
    </b-col>
  </b-row>
  <b-row class="my-1" >
    <b-col sm="2" > 
      <label for="input-large" >Published Year:</label>
    </b-col>
  </b-row>
  
</b-container>

    </section>
   
  </div>
</template>


<script>
import Router from "../router/index";
export default {
  name: 'hello',
  data () {
   return {
        types: [
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
</script>


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
  /*font-family: "Lato", sans-serif;*/
  background-size: contain;
  background-repeat: no-repeat;
  background-size: 100%;
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
  color:#a85f32;
  font: "Times";
  font-size: 30px;
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
