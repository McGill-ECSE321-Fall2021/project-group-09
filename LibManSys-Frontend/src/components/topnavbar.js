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
    console.log("mounted nav bar " + $cookies.get("loggedInUser"))

    EventBus.$on('loggedInUserSet', (loggedInUser) => {
        console.log('event logged in suser set');
        this.loggedInUser = loggedInUser;
        if (this.loggedInUser) {
            this.loggedInType = $cookies.get('loggedInType')
            switch (this.loggedInType) {
                case 'onlineMember':
                    this.IDtype = 'library card number'
                    break
                case 'librarian':
                    this.IDtype = 'employee ID number'
                    break
                case 'headLibrarian':
                    this.IDtype = 'employee ID number'
                    break
                default:
            }
        } else {
            this.loggedInType = ''
            this.IDtype = ''
        }
    })


    if ($cookies.isKey("loggedInUser") == true) {
        this.loggedInUser = $cookies.get("loggedInUser")
        this.loggedInType = $cookies.get("loggedInType")
        switch (this.loggedInType) {
            case 'onlineMember':
                this.IDtype = 'library card number'
                break
            case 'librarian':
                this.IDtype = 'employee ID number'
                break
            case 'headLibrarian':
                this.IDtype = 'employee ID number'
                break
            default:
                this.IDtype = ''
        }
    }
    console.log("nmvar bar: " + this.loggedInUser)
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
        if (this.loggedInType === 'librarian'
        || this.loggedInType === "headLibrarian") {
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