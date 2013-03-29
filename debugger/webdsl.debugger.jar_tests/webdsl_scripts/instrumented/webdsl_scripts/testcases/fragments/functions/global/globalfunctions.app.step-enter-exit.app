application globalfunctions

imports webdsldebuglib/debugevents

function resetSchedule ( ) : Void
{
  WebDSLDebugEvents.functionEnter("webdsl_scripts/testcases/fragments/functions/global/globalfunctions.app", "3,1,8,1", "resetSchedule");
  try
  {
    var hourCounter := 0 ;
    var intervalInHours := 12 ;
    var count := hourCounter + intervalInHours ;
    WebDSLDebugEvents.statementStep("webdsl_scripts/testcases/fragments/functions/global/globalfunctions.app", "7,5,7,33");
    validateEmail("foo@bar.com");
  }
  finally
  {
    WebDSLDebugEvents.functionExit("webdsl_scripts/testcases/fragments/functions/global/globalfunctions.app", "3,1,8,1", "resetSchedule");
  }
}

function validateEmail ( mail : String ) : Bool
{
  WebDSLDebugEvents.functionEnter("webdsl_scripts/testcases/fragments/functions/global/globalfunctions.app", "10,4,12,1", "validateEmail");
  try
  {
    WebDSLDebugEvents.statementStep("webdsl_scripts/testcases/fragments/functions/global/globalfunctions.app", "11,5,11,17");
    return false;
  }
  finally
  {
    WebDSLDebugEvents.functionExit("webdsl_scripts/testcases/fragments/functions/global/globalfunctions.app", "10,4,12,1", "validateEmail");
  }
}

define page root () {
}