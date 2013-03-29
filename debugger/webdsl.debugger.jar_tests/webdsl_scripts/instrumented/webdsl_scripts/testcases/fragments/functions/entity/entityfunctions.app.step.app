application entityfunctions

imports webdsldebuglib/debugevents

entity RepoSearchManager {
  newWeekMoment :: Date ( default= now() )
  hourCounter :: Int ( default= 0 )
  intervalInHours :: Int ( default= 11 )
  nextInvocation :: DateTime
  function newHour ( )
  {
    WebDSLDebugEvents.statementStep("webdsl_scripts/testcases/fragments/functions/entity/entityfunctions.app", "10,3,10,15");
    tryNewWeek();
    var execute := false;
    WebDSLDebugEvents.statementStep("webdsl_scripts/testcases/fragments/functions/entity/entityfunctions.app", "12,8,12,38");
    hourCounter := hourCounter + 1;
    WebDSLDebugEvents.statementStep("webdsl_scripts/testcases/fragments/functions/entity/entityfunctions.app", "13,8,15,8");
    if ( hourCounter >= intervalInHours )
    {
      WebDSLDebugEvents.statementStep("webdsl_scripts/testcases/fragments/functions/entity/entityfunctions.app", "14,9,14,25");
      hourCounter := 0;
      WebDSLDebugEvents.statementStep("webdsl_scripts/testcases/fragments/functions/entity/entityfunctions.app", "14,27,14,42");
      execute := true;
    }
  }
  function shiftByHours ( hours : Int ) : Int
  {
    WebDSLDebugEvents.statementStep("webdsl_scripts/testcases/fragments/functions/entity/entityfunctions.app", "18,9,18,43");
    hourCounter := hourCounter - hours;
    WebDSLDebugEvents.statementStep("webdsl_scripts/testcases/fragments/functions/entity/entityfunctions.app", "19,9,19,59");
    nextInvocation := nextInvocation.addHours(hours);
    WebDSLDebugEvents.statementStep("webdsl_scripts/testcases/fragments/functions/entity/entityfunctions.app", "20,9,20,17");
    return 5;
  }
  function tryNewWeek ( )
  {
    WebDSLDebugEvents.statementStep("webdsl_scripts/testcases/fragments/functions/entity/entityfunctions.app", "23,9,25,9");
    if ( newWeekMoment.addDays(7).before(now()) )
    {
    }
  }
  function foo ( )
  {
  }
  function bar ( ) : Bool
  {
    WebDSLDebugEvents.statementStep("webdsl_scripts/testcases/fragments/functions/entity/entityfunctions.app", "33,6,33,18");
    return false;
  }
}

define page root(){}