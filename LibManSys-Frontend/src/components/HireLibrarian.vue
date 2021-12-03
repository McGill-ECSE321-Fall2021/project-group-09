
<template>
  <div id="member-booking-body">
    <section>
      <br />
      <br />
      <br />

      <b-container fluid>
        <b class="booking-title"> Hire Librarian </b>

        <br />
        <br />
        <div v-if="loggedInType === 'headLibrarian'">

          <b-container fluid>
            <b-row class="my-1" color="#00000">
              <b-col sm="4" color="#00000">
                <label class="booking-input" for="input-default">Full Name:</label>
              </b-col>
              <b-col sm="5">
                <b-form-input
                  id="input-default"
                  v-model="fullName"
                  placeholder="First Last"
                ></b-form-input>
              </b-col>
              <b-col sm="3" color="#00000"> </b-col>
            </b-row>

            <b-row class="my-1">
              <b-col sm="4">
                <label class="booking-input" for="input-large">Email:</label>
              </b-col>
              <b-col sm="5">
                <b-form-input
                  id="input-default"
                  v-model="email"
                  placeholder="example@email.ca"
                ></b-form-input>
              </b-col>
              <b-col sm="3" color="#00000"> </b-col>
            </b-row>

            <b-row class="my-1" color="#00000">
              <b-col sm="4" color="#00000">
                <label class="booking-input" for="input-default">Password:</label>
              </b-col>
              <b-col sm="5">
                <b-form-input
                  id="input-default"
                  v-model="password"
                  placeholder="Must be at least 6 characters."
                ></b-form-input>
              </b-col>
              <b-col sm="3" color="#00000"> </b-col>
            </b-row>

            <b-row class="my-1">
              <b-col sm="4">
                <label class="booking-input" for="input-large"
                  >Username:</label
                >
              </b-col>
              <b-col sm="5">
                <b-form-input
                  id="input-default"
                  v-model="username"
                  placeholder="Please enter a username."
                ></b-form-input>
              </b-col>
              <b-col sm="3" color="#00000"> </b-col>
            </b-row>
          </b-container>
        
        <div v-if="newLibrarian">
          <br />
          
          <p>Created new librarian with employee ID: {{newLibrarian.employeeIDNumber}}
            and Username: {{newLibrarian.librarianUsername}}
            and Password: {{newLibrarian.librarianPassword}}
          </p>
        </div>

        <div v-if="errorLibrarian">
          <br />
          <span  style="color: red">{{ errorLibrarian }} </span>
          <br />
        </div>

        <b-container>
          <b-row class="my-1 text-center" align-h="center">
            <b
              class="button"
              href="#"
              v-on:click="
                createLibrarian(
                  fullName,
                  email,
                  password,
                  username
                )
              "
              >Create Account</b
            >
            <b
              class="button"
              href="#"
              v-on:click="goToPreviousPage()"
              >Back</b
            >
          </b-row>
        </b-container>

        </div>
        <div v-else>
          <h3>Login as a head librarian to hire librarians.</h3>
        </div>

      </b-container>
    </section>
    <!-- End of body div -->
  </div>
</template>


<script src="./hireLib.js"></script>


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
