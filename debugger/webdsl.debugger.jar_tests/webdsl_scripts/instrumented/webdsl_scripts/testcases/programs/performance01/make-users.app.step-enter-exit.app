module make-users

imports webdsldebuglib/debugevents

define page makeTestSet () {
  form
    {
    "Make a test set"
      submit
      action
      {
        WebDSLDebugEvents.statementStep("webdsl_scripts/testcases/programs/performance01/make-users.app", "9,6,9,21");
        generateUsers();
      }
      [ ]
      {
        "Execute"
      }
    }
}
function generateUsers ( ) : Void
{
  WebDSLDebugEvents.functionEnter("webdsl_scripts/testcases/programs/performance01/make-users.app", "14,0,21,0", "generateUsers");
  try
  {
    WebDSLDebugEvents.statementStep("webdsl_scripts/testcases/programs/performance01/make-users.app", "15,2,20,2");
    for ( count : Int from 1 to 20 )
      {
        WebDSLDebugEvents.statementStep("webdsl_scripts/testcases/programs/performance01/make-users.app", "16,4,19,4");
        for ( id : Int from 0 to 100 )
          {
            var u := User{username := "MyName" + id
                          city := "Fake" + id
                          dob := "Unkown" + id} ;
            WebDSLDebugEvents.statementStep("webdsl_scripts/testcases/programs/performance01/make-users.app", "18,6,18,14");
            u.save();
          }
      }
  }
  finally
  {
    WebDSLDebugEvents.functionExit("webdsl_scripts/testcases/programs/performance01/make-users.app", "14,0,21,0", "generateUsers");
  }
}
define page automaticallyMakeTestSet () {
  templateGenerateTestSet (  )
}
define templateGenerateTestSet () {
  var s := generateTestSet() ;
}
function generateTestSet ( ) : String
{
  WebDSLDebugEvents.functionEnter("webdsl_scripts/testcases/programs/performance01/make-users.app", "31,0,34,0", "generateTestSet");
  try
  {
    WebDSLDebugEvents.statementStep("webdsl_scripts/testcases/programs/performance01/make-users.app", "32,2,32,17");
    generateUsers();
    WebDSLDebugEvents.statementStep("webdsl_scripts/testcases/programs/performance01/make-users.app", "33,2,33,11");
    return "";
  }
  finally
  {
    WebDSLDebugEvents.functionExit("webdsl_scripts/testcases/programs/performance01/make-users.app", "31,0,34,0", "generateTestSet");
  }
}