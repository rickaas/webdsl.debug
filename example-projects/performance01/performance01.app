application performance01

// The application is instrumented with debug statements, that are used to log function entry and function exit times to calculate the duration.
// The application should not be connected to a debugger as this will influence the duration of each function because of the debugger communication overhead.
// Although this will not be an accurate measurement of time-performance of a WebDSL program it can act as a baseline measurement.
imports make-users
imports webdsldebuglib/debugevents

entity User {
  username :: String
  city :: String
  dob :: String
}

define output(u:User){
  "User: " output(u.username)
}

derive CRUD User

//application global var
var u_1 := User{username:= "test", city:="Fake", dob:="Unkown" }

define page root(){
  top()
}

define top() {
  list {
    listitem { navigate(root()) { "home" } }
    listitem { navigate(listUsers()) { "Show all users" } }
    listitem { navigate(filterWhereUsers()) { "filterWhereUsers" } }
    listitem { navigate(orderByUsers()) { "orderByUsers" } }
    listitem { navigate(createUser()){ "create user" } }
    listitem { navigate(manageUser()){ "manage users" } }
    listitem { navigate(makeTestSet()) { "Make test set" } }
  }
}

define page listUsers() {
  debug_template_enter("performance01.app","36,1,45,1","listUsers")
  top()
  for(u:User) {
    output(u) " "
    navigate(user(u)){ "[view]" } " "
    navigate(editUser(u)){ "[edit]" }
  } separated-by{ <hr />  }
  debug_template_exit("performance01.app","36,1,45,1","listUsers")
}

define page filterWhereUsers() {
  debug_template_enter("performance01.app","47,1,57,1","filterWhereUsers")
  top()
  // loop all users and find each one that has a username that contains the number '6'
  for(u:User where u.username.contains("6")) {
    output(u) " "
    navigate(user(u)){ "[view]" } " "
    navigate(editUser(u)){ "[edit]" }
  } separated-by{ <hr />  }
  debug_template_exit("performance01.app","47,1,57,1","filterWhereUsers")
}

define page orderByUsers() {
  debug_template_enter("performance01.app","59,1,68,1","orderByUsers")
  top()
  for(u:User order by u.username) {
    output(u) " "
    navigate(user(u)){ "[view]" } " "
    navigate(editUser(u)){ "[edit]" }
  } separated-by{ <hr />  }
  debug_template_exit("performance01.app","59,1,68,1","orderByUsers")
}
