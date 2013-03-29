application performance01

imports webdsldebuglib/debugevents

imports make-users

entity User {
  username :: String
  city :: String
  dob :: String
}

define output (u : User) {
  debug_template_enter ( "performance01.app" , "11,0,13,0" , "output" )
  debug_template_step ( "performance01.app" , "12,2,12,9" )
  "User: "
  debug_template_step ( "performance01.app" , "12,11,12,28" )
  output ( u.username )
  debug_template_exit ( "performance01.app" , "11,0,13,0" , "output" )
}

derive CRUD User

var u_1 := User{username := "test"
                city := "Fake"
                dob := "Unkown"}

define page root () {
  debug_template_enter ( "performance01.app" , "20,0,22,0" , "root" )
  debug_template_step ( "performance01.app" , "21,2,21,6" )
  top (  )
  debug_template_exit ( "performance01.app" , "20,0,22,0" , "root" )
}

define top () {
  debug_template_enter ( "performance01.app" , "24,0,34,0" , "top" )
  debug_template_step ( "performance01.app" , "25,2,33,2" )
  list
    {
    debug_template_step ( "performance01.app" , "26,4,26,43" )
      listitem
        {
        debug_template_step ( "performance01.app" , "26,15,26,41" )
          navigate root() [ ] { debug_template_step ( "performance01.app" , "26,34,26,39" ) "home" }
        }
      debug_template_step ( "performance01.app" , "27,4,27,58" )
      listitem
        {
        debug_template_step ( "performance01.app" , "27,15,27,56" )
          navigate listUsers() [ ] { debug_template_step ( "performance01.app" , "27,39,27,54" ) "Show all users" }
        }
      debug_template_step ( "performance01.app" , "28,4,28,67" )
      listitem
        {
        debug_template_step ( "performance01.app" , "28,15,28,65" )
          navigate filterWhereUsers() [ ] { debug_template_step ( "performance01.app" , "28,46,28,63" ) "filterWhereUsers" }
        }
      debug_template_step ( "performance01.app" , "29,4,29,59" )
      listitem
        {
        debug_template_step ( "performance01.app" , "29,15,29,57" )
          navigate orderByUsers() [ ] { debug_template_step ( "performance01.app" , "29,42,29,55" ) "orderByUsers" }
        }
      debug_template_step ( "performance01.app" , "30,4,30,55" )
      listitem
        {
        debug_template_step ( "performance01.app" , "30,15,30,53" )
          navigate createUser() [ ] { debug_template_step ( "performance01.app" , "30,39,30,51" ) "create user" }
        }
      debug_template_step ( "performance01.app" , "31,4,31,56" )
      listitem
        {
        debug_template_step ( "performance01.app" , "31,15,31,54" )
          navigate manageUser() [ ] { debug_template_step ( "performance01.app" , "31,39,31,52" ) "manage users" }
        }
      debug_template_step ( "performance01.app" , "32,4,32,59" )
      listitem
        {
        debug_template_step ( "performance01.app" , "32,15,32,57" )
          navigate makeTestSet() [ ] { debug_template_step ( "performance01.app" , "32,41,32,55" ) "Make test set" }
        }
    }
  debug_template_exit ( "performance01.app" , "24,0,34,0" , "top" )
}

define page listUsers () {
  debug_template_enter ( "performance01.app" , "36,0,45,0" , "listUsers" )
  debug_template_step ( "performance01.app" , "38,2,38,6" )
  top (  )
  debug_template_step ( "performance01.app" , "39,2,43,26" )
  for ( u : User ) {
    debug_template_step ( "performance01.app" , "40,4,40,12" )
    output ( u )
    debug_template_step ( "performance01.app" , "40,14,40,16" )
    " "
    debug_template_step ( "performance01.app" , "41,4,41,32" )
    navigate user(u) [ ] { debug_template_step ( "performance01.app" , "41,23,41,30" ) "[view]" }
    debug_template_step ( "performance01.app" , "41,34,41,36" )
    " "
    debug_template_step ( "performance01.app" , "42,4,42,36" )
    navigate editUser(u) [ ] { debug_template_step ( "performance01.app" , "42,27,42,34" ) "[edit]" }
  }
  separated-by {
  debug_template_step ( "performance01.app" , "43,18,43,23" )
  < hr />
  }
  debug_template_exit ( "performance01.app" , "36,0,45,0" , "listUsers" )
}

define page filterWhereUsers () {
  debug_template_enter ( "performance01.app" , "47,0,56,0" , "filterWhereUsers" )
  debug_template_step ( "performance01.app" , "48,2,48,6" )
  top (  )
  debug_template_step ( "performance01.app" , "51,2,55,26" )
  for ( u : User where u.username.contains("6") ) {
    debug_template_step ( "performance01.app" , "52,4,52,12" )
    output ( u )
    debug_template_step ( "performance01.app" , "52,14,52,16" )
    " "
    debug_template_step ( "performance01.app" , "53,4,53,32" )
    navigate user(u) [ ] { debug_template_step ( "performance01.app" , "53,23,53,30" ) "[view]" }
    debug_template_step ( "performance01.app" , "53,34,53,36" )
    " "
    debug_template_step ( "performance01.app" , "54,4,54,36" )
    navigate editUser(u) [ ] { debug_template_step ( "performance01.app" , "54,27,54,34" ) "[edit]" }
  }
  separated-by {
  debug_template_step ( "performance01.app" , "55,18,55,23" )
  < hr />
  }
  debug_template_exit ( "performance01.app" , "47,0,56,0" , "filterWhereUsers" )
}

define page orderByUsers () {
  debug_template_enter ( "performance01.app" , "58,0,66,0" , "orderByUsers" )
  debug_template_step ( "performance01.app" , "59,2,59,6" )
  top (  )
  debug_template_step ( "performance01.app" , "61,2,65,26" )
  for ( u : User order by u.username ) {
    debug_template_step ( "performance01.app" , "62,4,62,12" )
    output ( u )
    debug_template_step ( "performance01.app" , "62,14,62,16" )
    " "
    debug_template_step ( "performance01.app" , "63,4,63,32" )
    navigate user(u) [ ] { debug_template_step ( "performance01.app" , "63,23,63,30" ) "[view]" }
    debug_template_step ( "performance01.app" , "63,34,63,36" )
    " "
    debug_template_step ( "performance01.app" , "64,4,64,36" )
    navigate editUser(u) [ ] { debug_template_step ( "performance01.app" , "64,27,64,34" ) "[edit]" }
  }
  separated-by {
  debug_template_step ( "performance01.app" , "65,18,65,23" )
  < hr />
  }
  debug_template_exit ( "performance01.app" , "58,0,66,0" , "orderByUsers" )
}