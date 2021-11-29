import OnlineMemberDashboard from "../components/OnlineMemberDashboard";
import SearchLibItems from "../components/SearchLibItems";
import Hello from "../components/Hello";
import Register from "../components/Register";
import Router from "../router/index";

export default {
  data () {
   return {
        types: [
        ]
      }
  },
   methods: {
       loginMember: function (){
            Router.push({
                path: "/OnlineMemberDashboard",
                name: "OnlineMemberDashboard"
            })
        },
        goToSearchPage: function (){
            Router.push({
                path: "/SearchLibItems",
                name: "SearchLibItems"
            })
        },
        goToRegisterPage: function (){
            Router.push({
                path: "/Register",
                name: "Register"
            })
        },
   }
}