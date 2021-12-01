import Router from "../router/index";

export default {
  name: "topvarbar",
  data() {
    return {
      loggedInUser: '',
      loggedInType: '',
      IDtype: ''
    };
  },
  mounted: function () {
    EventBus.$on('loggedInUserSet', (loggedInUser) => {
        console.log('event logged in suser set');
        this.loggedInUser = loggedInUser;
        if (this.loggedInUser) {
            this.loggedInType = $cookies.get('loggedInType')
            this.IDtype = this.loggedInType === 'librarian' ? 'employee ID number' : 'library card number';
        } else {
            this.loggedInType = ''
            this.IDtype = ''
        }
    })
  },
  beforeDestroy: function () {
    EventBus.$off('loggedInUserSet')
  },
  methods: {
    signOut: function () {
        console.log('sign out ')

        // remove loggedIn cookies
        $cookies.remove("loggedInUser")
        $cookies.remove("loggedInType")

        // published event
        EventBus.$emit('loggedInUserSet', '')

        // go to home page
        // basically just force reload, doesn't really matter what page we go to
        Router.push({
            path: "/SearchLibItems",
            name: "SearchLibItems"
        })
    },
    goToUserDashboard: function () {
        if (this.loggedInType === 'librarian') {
            if (this.$route.name !== "LibrarianDashboard") {
                Router.push({
                    path: "/LibrarianDashboard",
                    name: "LibrarianDashboard"
                })
            }
        } else {
            if (this.$route.name !== "OnlineMemberDashboard") {
                Router.push({
                    path: "/OnlineMemberDashboard",
                    name: "OnlineMemberDashboard"
                })
            }
        }
    },
    goToRegisterPage: function (){
        if (this.$route.name !== "Register") {
            Router.push({
                path: "/Register",
                name: "Register"
            })
        }
    },
    goToLoginPage: function (){
        if (this.$route.name !== "MemberLogin") {
            Router.push({
                path: "/MemberLogin",
                name: "MemberLogin"
            })
        }
    },
    goToSearchPage: function() {
        if (this.$route.name !== "SearchLibItems") {    
            Router.push({
                path: "/SearchLibItems",
                name: "SearchLibItems"
            })
        }
    },
        goToHomePage: function () {
            Router.push({
              path: "/",
              name: "Home",
            });
    }
  },
  goToSearchPage: function () {
    Router.push({
      path: "/SearchResults",
      name: "SearchResults",
    });
  },
}