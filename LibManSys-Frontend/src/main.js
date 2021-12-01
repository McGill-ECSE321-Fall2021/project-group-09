// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue'
//import VueSweetalert2 from 'vue-sweetalert2';
import BootstrapVue from "bootstrap-vue"
import App from './App'
import router from './router'
import 'bootstrap/dist/css/bootstrap.min.css'
import 'bootstrap-vue/dist/bootstrap-vue.css'

// get config
var config = require('../config')

// Setup Vue cookies
Vue.use(require('vue-cookies'))
// set default config
Vue.$cookies.config('7d')

// set global cookie for urls depending of NODE_ENV
if (process.env.NODE_ENV === "production") {
  console.log("prod env")
  Vue.frontendUrl = Vue.prototype.frontendUrl = 'https://' + config.build.host + ':' + config.build.port
  Vue.backendUrl = Vue.prototype.backendUrl = 'https://' + config.build.backendHost + ':' + config.build.backendPort

} else if (process.env.NODE_ENV === "development") {
  console.log("dev env")
  Vue.frontendUrl = Vue.prototype.frontendUrl = 'http://' + config.dev.host + ':' + config.dev.port
  Vue.backendUrl = Vue.prototype.backendUrl = 'http://' + config.dev.backendHost + ':' + config.dev.backendPort
}

Vue.use(BootstrapVue)
Vue.config.productionTip = false

// event bus
window.EventBus = new Vue();

//use Sweetalert
//Vue.use(VueSweetalert2);

/* eslint-disable no-new */
new Vue({
  el: '#app',
  router,
  template: '<App/>',
  components: { App }
})
