module make-users

// create a bunch of users

define page makeTestSet() {
  form { 
    "Make a test set"
    submit action {
      generateUsers();
    } { "Execute" }
  }
}

function generateUsers() {
  for(count:Int from 0 to 10) {
    for(id:Int from 0 to 100) {
      var u := User{username:= "MyName"+id, city:="Fake"+id, dob:="Unkown"+id };
      u.save();
    }
  }
}

define page automaticallyMakeTestSet() {
  templateGenerateTestSet()
}

define templateGenerateTestSet() {
  var s := generateTestSet()
}

function generateTestSet() : String {
  generateUsers();
  return "";
}
