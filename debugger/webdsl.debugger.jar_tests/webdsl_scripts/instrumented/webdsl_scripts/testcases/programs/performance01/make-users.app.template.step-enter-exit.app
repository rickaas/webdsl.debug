module make-users
imports webdsldebuglib/debugevents
define page makeTestSet () {
  debug_template_enter ( "webdsl_scripts/testcases/programs/performance01/make-users.app" , "5,0,12,0" , "makeTestSet" )
  debug_template_step ( "webdsl_scripts/testcases/programs/performance01/make-users.app" , "6,2,11,2" )
  form
    {
    debug_template_step ( "webdsl_scripts/testcases/programs/performance01/make-users.app" , "7,4,7,20" )
      "Make a test set"
      debug_template_step ( "webdsl_scripts/testcases/programs/performance01/make-users.app" , "8,4,10,21" )
      submit
      action
      {
        generateUsers();
      }
      [ ]
      {
        debug_template_step ( "webdsl_scripts/testcases/programs/performance01/make-users.app" , "10,11,10,19" )
        "Execute"
      }
    }
  debug_template_exit ( "webdsl_scripts/testcases/programs/performance01/make-users.app" , "5,0,12,0" , "makeTestSet" )
}
function generateUsers ( ) : Void
{
  for ( count : Int from 1 to 20 )
    {
      for ( id : Int from 0 to 100 )
        {
          var u := User{username := "MyName" + id
                        city := "Fake" + id
                        dob := "Unkown" + id} ;
          u.save();
        }
    }
}
define page automaticallyMakeTestSet () {
  debug_template_enter ( "webdsl_scripts/testcases/programs/performance01/make-users.app" , "23,0,25,0" , "automaticallyMakeTestSet" )
  debug_template_step ( "webdsl_scripts/testcases/programs/performance01/make-users.app" , "24,2,24,26" )
  templateGenerateTestSet (  )
  debug_template_exit ( "webdsl_scripts/testcases/programs/performance01/make-users.app" , "23,0,25,0" , "automaticallyMakeTestSet" )
}
define templateGenerateTestSet () {
  debug_template_enter ( "webdsl_scripts/testcases/programs/performance01/make-users.app" , "27,0,29,0" , "templateGenerateTestSet" )
  debug_template_step ( "webdsl_scripts/testcases/programs/performance01/make-users.app" , "28,2,28,27" )
  var s := generateTestSet() ;
  debug_template_exit ( "webdsl_scripts/testcases/programs/performance01/make-users.app" , "27,0,29,0" , "templateGenerateTestSet" )
}
function generateTestSet ( ) : String
{
  generateUsers();
  return "";
}