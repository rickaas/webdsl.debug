application performance01

imports make-users

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
  //log("START listUsers")
  top()
  for(u:User) {
    output(u) " "
    navigate(user(u)){ "[view]" } " "
    navigate(editUser(u)){ "[edit]" }
  } separated-by{ <hr />  }
  //log("END listUsers")
}

define page filterWhereUsers() {
  top()

  // loop all users and find each one that has a username that contains the number '6'
  for(u:User where u.username.contains("6")) {
    output(u) " "
    navigate(user(u)){ "[view]" } " "
    navigate(editUser(u)){ "[edit]" }
  } separated-by{ <hr />  }
}

define page orderByUsers() {
  top()
  
  for(u:User order by u.username) {
    output(u) " "
    navigate(user(u)){ "[view]" } " "
    navigate(editUser(u)){ "[edit]" }
  } separated-by{ <hr />  }
}
