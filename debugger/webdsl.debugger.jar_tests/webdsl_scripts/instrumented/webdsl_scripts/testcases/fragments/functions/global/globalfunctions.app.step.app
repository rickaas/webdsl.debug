application globalfunctions

imports webdsldebuglib/debugevents

function resetSchedule ( )
{
  var hourCounter := 0 ;
  var intervalInHours := 12;
  var count := hourCounter + intervalInHours;
  WebDSLDebugEvents.statementStep("webdsl_scripts/testcases/fragments/functions/global/globalfunctions.app", "7,5,7,33");
  validateEmail("foo@bar.com");
}

function validateEmail ( mail : String ) : Bool
{
  WebDSLDebugEvents.statementStep("webdsl_scripts/testcases/fragments/functions/global/globalfunctions.app", "11,5,11,17");
  return false;
}

define page root(){}