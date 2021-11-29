import OnlineMemberDashboard from "../components/OnlineMemberDashboard";
import SearchLibItems from "../components/SearchLibItems";
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
       loginLibrarian: function (){
            Router.push({
                path: "/LibrarianDashboard",
                name: "LibrarianDashboard"
            })
        },
        
        
        goToMemberLogin: function() {
            
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