
<template>
  <div id="view-booking-body">
    <section>
      
      <div v-if="loggedInType === 'librarian' || loggedInType === 'headLibrarian'">
        <b-container fluid>
          ​
          <h2>All Bookings</h2>
          <table>
            ​
            <tr>
              <td>Booking ID</td>
              <td>Booking Date</td>
              <td>Start Time</td>
              <td>End Time</td>
            </tr>
            ​
            <tr v-for="booking in bookings" v-bind:key="booking.bookingID">
              ​
              <td>{{ booking.bookingID }}</td>
              <td>{{ booking.bookingDate }}</td>
              <td>{{ booking.bookingStartTime }}</td>
              <td>{{ booking.bookingEndTime }}</td>
            </tr>
            ​
          </table>
        </b-container>

        <div v-if="error">
            <br />
            <span  style="color: red">{{ error }} </span>
            <br />
        </div>

        <b class="button" href="#" v-on:click="goToPreviousPage()">Back</b>

      </div>
      <div v-else>
        <h4>Login to view librarian things</h4>
      </div>
    </section>
  </div>
</template>


<script src = './viewBookings.js'></script>


<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
.bg-custom-1 {
  background-color: #d9b086;
}

b.nav-link {
  color: #000000;
  font-size: 18px;
}

b-navbar {
  background-color: #4e1d04;
}

div {
  font-family: "Segoe UI", Tahoma, Geneva, Verdana, sans-serif;
  /*   background: #f0dbce;*/
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
  color: #d9b086;
  font-family: "Lato", sans-serif;
  background-size: contain;
  background-repeat: no-repeat;
  background-size: 100%;
}

a.normal {
  font-weight: 400;
}
h1,
h2 {
  color: black;
  font-size: 40px;
  font-weight: normal;
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
.nav-left {
  display: flex;
}
.nav-right {
  display: flex;
}

#container {
  width: 65%;
  margin: 0 auto;
}
.button {
  display: inline-block;
  font-family: "Segoe UI", Tahoma, Geneva, Verdana, sans-serif;
  padding: 16px 32px;
  background-color: a85f32;
  border-radius: 10px;
  font-size: 15px;
  color: #000;
  background: a85f32;
  /* border: 0; */
  font-weight: 500;
}

.button:hover {
  cursor: pointer;
  background-color: #ffd24d;
}

.booking-title {
  color: #a85f32;
  font: "Times";
  font-size: 30px;
  font-weight: 100;
}

.booking-input {
  float: right;
}

/*Resize the wrap to see the search bar change!*/
.wrap {
  width: 40%;
  position: absolute;
  top: 60%;
  left: 50%;
  transform: translate(-50%, -50%);
}
myText {
  color: black;
  font: "Times";
  font-size: 35px;
  font-weight: 100;
}

myText3 {
  color: #a85f32;
  font: "Times";
  font-size: 25px;
  font-weight: 100;
}

table {
  table-layout: auto;
  margin-left: auto;
  margin-right: auto;
  width: 100%;
}
input,
::placeholder {
  font-family: "Lato", sans-serif;
  font-size: 18px;
  font-weight: 600;
}
</style>
