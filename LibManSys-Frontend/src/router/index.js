import Vue from 'vue'
import Router from 'vue-router'
import Hello from '@/components/Hello'
import Bookings from '@/components/Bookings'
import Checkout from '@/components/Checkout'
import LibrarianDashboard from '@/components/LibrarianDashboard'
import LibrarianLogin from '@/components/LibrarianLogin'
import LibraryInfo from '@/components/LibraryInfo'
import LibraryManagementDashboard from '@/components/LibraryManagementDashboard'
import MemberLogin from '@/components/MemberLogin'
import MemberLoans from '@/components/MemberLoans'
import OnlineMemberDashboard from '@/components/OnlineMemberDashboard'
import Return from '@/components/Return'
import Register from '@/components/Register'
import SearchLibItems from '@/components/SearchLibItems'


Vue.use(Router)

export default new Router({
  routes: [
    {
      path: '/',
      name: 'Home',
      component: Hello
    },
    {
      path: '/Bookings',
      name: 'Bookings',
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
    }
  ]
})
