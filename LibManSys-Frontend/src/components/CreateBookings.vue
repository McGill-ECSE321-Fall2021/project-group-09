
<template>
  <div id="member-booking-body">
    <section>
      <br />
      <br />
      <br />

      <b-container fluid>
        <b class="booking-title"> New Booking </b>

        <br />
        <br />

        <b-row class="my-1" color="#00000">
          <b-col sm="4" color="#00000">
            <label class="booking-input" for="input-default">Date:</label>
          </b-col>
          <b-col sm="5">
            <b-form-input
              id="input-default"
              v-model="bookingDate"
              placeholder="yyyy-mm-dd"
            ></b-form-input>
          </b-col>
          <b-col sm="3" color="#00000"> </b-col>
        </b-row>

        <b-row class="my-1">
          <b-col sm="4">
            <label class="booking-input" for="input-large">Start Time</label>
          </b-col>
          <b-col sm="5">
            <b-form-input
              id="input-default"
              v-model="startTime"
              placeholder="hh:mm:ss"
            ></b-form-input>
          </b-col>
          <b-col sm="3" color="#00000"> </b-col>
        </b-row>

        <b-row class="my-1" color="#00000">
          <b-col sm="4" color="#00000">
            <label class="booking-input" for="input-default">End Time:</label>
          </b-col>
          <b-col sm="5">
            <b-form-input
              id="input-default"
              v-model="endTime"
              placeholder="hh:mm:ss"
            ></b-form-input>
          </b-col>
          <b-col sm="3" color="#00000"> </b-col>
        </b-row>

        <b-row class="my-1">
          <b-col sm="4">
            <label class="booking-input" for="input-large"
              >Library Card No:</label
            >
          </b-col>
          <b-col sm="5">
            <b-form-input
              id="input-default"
              v-model="memberID"
              placeholder="Please enter the member's library card number"
            ></b-form-input>
          </b-col>
          <b-col sm="3" color="#00000"> </b-col>
        </b-row>

        <b-row class="my-1">
          <b-col sm="4">
            <label class="booking-input" for="input-large">Employee ID:</label>
          </b-col>
          <b-col sm="5">
            <b-form-input
              id="input-default"
              v-model="librarianID"
              placeholder="Please enter your employee ID"
            ></b-form-input>
          </b-col>
          <b-col sm="3" color="#00000"> </b-col>
        </b-row>
      </b-container>

      <br />
      <span v-if="errorBooking" style="color: red">{{ errorBooking }} </span>
      <br />

      <b-container>
        <b-row class="my-1 text-center" align-h="center">
          <b
            class="button"
            href="#"
            v-on:click="
              createBooking(
                startTime,
                endTime,
                bookingDate,
                memberID,
                librarianID
              )
            "
            >Create Booking</b
          >
          <b
            class="button"
            href="#"
            v-on:click="goToPreviousPage()"
            >Back</b
          >
        </b-row>
      </b-container>
    </section>
    <!-- End of body div -->
  </div>
</template>


<script src="./booking.js"></script>


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
input,
::placeholder {
  font-family: "Lato", sans-serif;
  font-size: 18px;
  font-weight: 600;
}
</style>
