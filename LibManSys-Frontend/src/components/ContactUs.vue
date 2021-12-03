<template>
  <div class="contact">
    <section>
      <overlay> 
          <map-overlay> </map-overlay>
          <text-overlay>
              <br>
      <h3>123 Test Street </h3>
      <h3>Montreal QC 1A2 C3D </h3>
      </text-overlay>
         </overlay>
      <overlayR>
        <h1>Contact Us</h1>
        <h2>Monday:</h2>
        <h2>Tuesday:</h2>
        <h2>Wednesday:</h2>
        <h2>Thursday:</h2>
        <h2>Friday:</h2>
        <h2>Saturday:</h2>
        <h2>Sunday:</h2>

        <br>
        <h2>(123) 456-7890 </h2>
        <h2>lib@library.ca </h2>
      </overlayR>
      <overlayH> 
        <h1blank>Contact Us</h1blank>
        <h2> 10:00 a.m. - 5:00 p.m. </h2> 
        <h2> 10:00 a.m. - 5:00 p.m. </h2> 
        <h2> 10:00 a.m. - 5:00 p.m. </h2>
        <h2> 10:00 a.m. - 5:00 p.m. </h2>
        <h2> 10:00 a.m. - 5:00 p.m. </h2>
        <h2> Closed </h2>
        <h2> Closed </h2>
      </overlayH>
    </section>
   
  </div>
</template>

<script>
import Register from "../components/Register";
import Router from "../router/index";

export default {
  name: "hello",
  data() {
    return {
      hover: false,
      msg: "Welcome to the Library",
    };
  },

  components: {
    Register,
  },

  methods: {
    goToRegisterPage: function () {
      Router.push({
        path: "/Register",
        name: "Register",
      });
    },
    goToLoginPage: function () {
      Router.push({
        path: "/MemberLogin",
        name: "MemberLogin",
      });
    },
    goToSearchPage: function () {
      Router.push({
        path: "/SearchLibItems",
        name: "SearchLibItems",
      });
    },
    goToLibManagmentPage: function () {
      Router.push({
        path: "/LibraryManagementDashboard",
        name: "LibraryManagementDashboard",
      });
    },
    goToUserPage: function () {
      Router.push({
        path: "/OnlineMemberDashboard",
        name: "OnlineMemberDashboard",
      });
    },
    goToLibrarianPage: function () {
      Router.push({
        path: "/LibrarianDashboard",
        name: "LibrarianDashboard",
      });
    },
    goToItemPage: function () {
      Router.push({
        path: "/ItemPage",
        name: "ItemPage",
      });
    },
    goToHomePage: function () {
      Router.push({
        path: "/",
        name: "Home",
      });
    }
  },
};
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

.bg-custom-2 {
  background: linear-gradient(
    90deg,
    rgba(0, 0, 0, 1),
    rgba(0, 0, 0, 0.8),
    rgba(0, 0, 0, 0)
  );
}

div {
  font-family: "Segoe UI", Tahoma, Geneva, Verdana, sans-serif;
  color: #000000;
  background-image: linear-gradient(
      115deg,
      rgba(0, 0, 0, 0.9),
      rgba(0, 0, 0, 0.9),
      rgba(43, 16, 7, 0.8)
    ),
    url(../assets/library-bkg.jpg);

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

overlay {
  position: absolute;
  top: 15%;
  bottom: 12%;
  right: 5%;
  left: 52%;
  background-color: #c2c2c2bd;
  color: rgb(0, 0, 0);
  padding-left: 40px;
  padding-right: 40px;
  opacity: 0.7;
  text-align: left;
  vertical-align: bottom;
}

map-overlay {
  position: absolute;
  top: 2%;
  bottom: 17%;
  right: 2%;
  left: 2%;
  background-image: url(../assets/map.png);
  background-size: 100%;
  color: rgb(0, 0, 0);
  padding-left: 40px;
  padding-right: 40px;
  opacity: 0.7;
  text-align: left;
}

text-overlay {
  position: absolute;
  top: 80%;
  bottom: 2%;
  right: 2%;
  left: 2%;
  background: none;
  color: rgb(0, 0, 0);
  padding-right: 40px;
  text-align: left;
}

overlayR {
  position: absolute;
  top: 15%;
  bottom: 12%;
  right: 50%;
  left: 5%;
  background: none;
  color: rgb(255, 255, 255);
  padding-left: 40px;
  padding-right: 40px;
  text-align: left;
}

overlayH {
  position: absolute;
  top: 15%;
  bottom: 12%;
  right: 40%;
  left: 20%;
  background: none;
  color: rgb(255, 255, 255);
  padding-left: 40px;
  padding-right: 40px;
  padding-top: 0px;
  text-align: left;
}

.normal {
  font-weight: 400;
}
h1 {
  font-family: "Lato", sans-serif;
  font-weight: bold;
  font-size: 70px;
}
h1blank {
  font-family: "Lato", sans-serif;
  font-weight: bold;
  padding-top: 0px;
  font-size: 66px;
  opacity: 0;
}
h2 {
  font-family: "Lato", sans-serif;
  font-size: 30px;
  font-weight: 600;
  opacity: 0.6;
}
h3 {
  font-family: "Lato", sans-serif;
  font-size: 25px;
  font-weight: 600;
}
.thick {
  font-weight: bold;
}
.light {
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
  color: #ffffff;
  font-size: 70px;
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
b-nav-item:hover {
  color: #8c5440;
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

.searchTerm {
  width: 100%;
  border: 2px solid #ffffff;
  border-right: none;
  padding: 15px;
  height: 20px;
  border-radius: 5px 0 0 5px;
  outline: none;
  color: #ffffff;
  background: none;
}

.searchTerm:focus {
  color: #ffffff;
}

.searchButton {
  width: 40px;
  height: 34px;
  border: 2px solid #ffffff;
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

/* Footer */
footer {
  height: 40px;
  font-weight: 200;
  text-align: center;
}
</style>
