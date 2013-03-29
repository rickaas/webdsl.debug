module make-users
imports webdsldebuglib/debugevents
define page makeTestSet () {
  debug_template_enter ( "make-users.app" , "5,0,12,0" , "makeTestSet" )
  debug_template_step ( "make-users.app" , "6,2,11,2" )
  form
    {
    debug_template_step ( "make-users.app" , "7,4,7,20" )
      "Make a test set"
      debug_template_step ( "make-users.app" , "8,4,10,21" )
      submit
      action
      {
        WebDSLDebugEvents.statementStep("make-users.app", "9,6,9,21");
        generateUsers();
      }
      [ ]
      {
        debug_template_step ( "make-users.app" , "10,11,10,19" )
        "Execute"
      }
    }
  debug_template_exit ( "make-users.app" , "5,0,12,0" , "makeTestSet" )
}
function generateUsers ( ) : Void
{
  WebDSLDebugEvents.functionEnter("make-users.app", "14,0,21,0", "generateUsers");
  try
  {
    WebDSLDebugEvents.statementStep("make-users.app", "15,2,20,2");
    for ( count : Int from 1 to 20 )
      {
        WebDSLDebugEvents.statementStep("make-users.app", "16,4,19,4");
        for ( id : Int from 0 to 100 )
          {
            var u := User{username := "MyName" + id
                          city := "Fake" + id
                          dob := "Unkown" + id} ;
            WebDSLDebugEvents.statementStep("make-users.app", "18,6,18,14");
            u.save();
          }
      }
  }
  finally
  {
    WebDSLDebugEvents.functionExit("make-users.app", "14,0,21,0", "generateUsers");
  }
}
define page automaticallyMakeTestSet () {
  debug_template_enter ( "make-users.app" , "23,0,25,0" , "automaticallyMakeTestSet" )
  debug_template_step ( "make-users.app" , "24,2,24,26" )
  templateGenerateTestSet (  )
  debug_template_exit ( "make-users.app" , "23,0,25,0" , "automaticallyMakeTestSet" )
}
define templateGenerateTestSet () {
  debug_template_enter ( "make-users.app" , "27,0,29,0" , "templateGenerateTestSet" )
  debug_template_step ( "make-users.app" , "28,2,28,27" )
  var s := generateTestSet() ;
  debug_template_exit ( "make-users.app" , "27,0,29,0" , "templateGenerateTestSet" )
}
function generateTestSet ( ) : String
{
  WebDSLDebugEvents.functionEnter("make-users.app", "31,0,34,0", "generateTestSet");
  try
  {
    WebDSLDebugEvents.statementStep("make-users.app", "32,2,32,17");
    generateUsers();
    WebDSLDebugEvents.statementStep("make-users.app", "33,2,33,11");
    return "";
  }
  finally
  {
    WebDSLDebugEvents.functionExit("make-users.app", "31,0,34,0", "generateTestSet");
  }
}