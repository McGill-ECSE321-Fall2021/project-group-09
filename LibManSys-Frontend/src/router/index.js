import Vue from 'vue'
import Router from 'vue-router'
import Home from '@/components/Home'
import Bookings from '@/components/CreateBookings.vue'
import Checkout from '@/components/Checkout.vue'
import LibrarianDashboard from '@/components/LibrarianDashboard.vue'
import LibrarianLogin from '@/components/LibrarianLogin.vue'
import LibraryInfo from '@/components/LibraryInfo'
import LibraryManagementDashboard from '@/components/LibraryManagementDashboard'
import MemberLogin from '@/components/MemberLogin.vue'
import MemberLoans from '@/components/MemberLoans.vue'
import OnlineMemberDashboard from '@/components/OnlineMemberDashboard'
import Return from '@/components/Return.vue'
import Register from '@/components/Register'
import SearchLibItems from '@/components/SearchLibItems.vue'
import ItemPage from '@/components/ItemPage'
import Reserve from '@/components/Reserve.vue'
import CreateLibItem from '@/components/CreateLibItem.vue'
import SearchResults from '@/components/SearchResults.vue'
import HireLibrarian from '@/components/HireLibrarian.vue'
import FireLibrarian from '@/components/FireLibrarian.vue'
import UpdateBookings from '@/components/UpdateBookings.vue'
import ViewBookings from '@/components/ViewBookings.vue'
import DeleteBookings from '@/components/DeleteBookings.vue'
import EditLibraryHours from '@/components/EditLibraryHours.vue'
import CreateLibrarianSchedule from '@/components/CreateLibrarianSchedule.vue'
import RenewMemberLoan from '@/components/RenewMemberLoan.vue'
import CreateNewMember from '@/components/CreateNewMember.vue'

import EditProfile from '@/components/EditProfile.vue'



import ContactUs from '@/components/ContactUs'
// import * as VueGoogleMaps from "vue2-google-maps" // Import package

// Vue.config.productionTip = false

// Vue.use(VueGoogleMaps, {
//   load: {
//     key: "GOOGLE MAP API KEY GOES HERE",
//     libraries: "places"
//   }
// });

// new Vue({
//   render: h => h(App),
// }).$mount('#app')

Vue.use(Router)

export default new Router({
  routes: [
    {
      path: '/',
      name: 'Home',
      component: Home
    },
    {
      path: '/CreateBookings',
      name: 'CreateBookings',
      component: Bookings
    },
    {
      path: '/Checkout',
      name: 'Checkout',
      component: Checkout
    }
    ,
    {
      path: '/LibrarianDashboard',
      name: 'LibrarianDashboard',
      component: LibrarianDashboard
    }
    ,
    {
      path: '/LibrarianLogin',
      name: 'LibrarianLogin',
      component: LibrarianLogin
    }
     ,
     {
      path: '/LibraryInfo',
      name: 'LibraryInfo',
      component: LibraryInfo
    }
    ,
    {
      path: '/CreateLibrarianSchedule',
      name: 'CreateLibrarianSchedule',
      component: CreateLibrarianSchedule
    }
    ,
    {
      path: '/EditLibraryHours',
      name: 'EditLibraryHours',
      component: EditLibraryHours
    }
    ,
    {
      path: '/LibraryManagementDashboard',
      name: 'LibraryManagementDashboard',
      component: LibraryManagementDashboard
    }
    ,
    {
      path: '/MemberLoans',
      name: 'MemberLoans',
      component: MemberLoans
    }
    ,
    {
      path: '/MemberLogin',
      name: 'MemberLogin',
      component: MemberLogin
    }
    ,
    {
      path: '/OnlineMemberDashboard',
      name: 'OnlineMemberDashboard',
      component: OnlineMemberDashboard
    }
    ,
    {
      path: '/Register',
      name: 'Register',
      component: Register
    }
    ,
    {
      path: '/Return',
      name: 'Return',
      component: Return
    }
    ,
    {
      path: '/SearchLibItems',
      name: 'SearchLibItems',
      component: SearchLibItems
    },
    {
      path: '/SearchResults',
      name: 'SearchResults',
      component: SearchResults
    },{
      path: '/ItemPage',
      name: 'ItemPage',
      component: ItemPage
    }
    ,{
      path: '/Reserve',
      name: 'Reserve',
      component: Reserve
    }
    ,{
      path: '/CreateLibItem',
      name: 'CreateLibItem',
      component: CreateLibItem
    }
    ,{
      path: '/ContactUs',
      name: 'ContactUs',
      component: ContactUs
    },{
      path: '/HireLibrarian',
      name: 'HireLibrarian',
      component: HireLibrarian
    },{
      path: '/FireLibrarian',
      name: 'FireLibrarian',
      component: FireLibrarian
    },{
      path: '/UpdateBookings',
      name: 'UpdateBookings',
      component: UpdateBookings
    },{
      path: '/ViewBookings',
      name: 'ViewBookings',
      component: ViewBookings
    },{
      path: '/DeleteBookings',
      name: 'DeleteBookings',
      component: DeleteBookings
    },{
      path: '/EditProfile',
      name: 'EditProfile',
      component: EditProfile
    },{
      path: '/CreateNewMember',
      name: 'CreateNewMember',
      component: CreateNewMember
    },{
      path: '/RenewMemberLoan',
      name: 'RenewMemberLoan',
      component: RenewMemberLoan
    },
  ]
})
