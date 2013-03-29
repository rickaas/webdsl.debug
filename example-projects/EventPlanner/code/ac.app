module code/ac

  principal is User with credentials name,password

  access control rules
    rule page *(*){ true }
    rule page admin(e:ALink){ e.event.organizer == principal }
    rule template *(*){ true }
    
section ac templates

  extend entity Event {
    organizer -> User
  }
  
  var user_admin := User{ name := "admin" password := "admin" }
  var user_test := User{ name := "test" password := "test" }

  entity User{
    name :: String
    password :: Secret
    email :: Email
  }

section templates for authentication

  define auth() {
    if(loggedIn()) {
      signout()
    } else {
      signin()
    }
  }

  // call when user is loggedIn, show Logout button
  define signout(){
    "Logged in as: " output(securityContext.principal.name)
    " "
    form{
      submitlink action{logout();} {"Logout"}
    }
  }

  // call when user is not loggedIn, show Login button
  define signin(){
    var name := ""
    var pass : Secret := ""
    form{
      table {
        row {
          column {
            label("name: "){ input(name) }
          }
          column {
            label("password: "){input(pass)}
          }
          column {
            submit action{
              validate(authenticate(name,pass), "The login credentials are not valid.");
              message("You are now logged in.");
            } {"login"}
          }
        } // end of row
      } // end of table
    }
  }


