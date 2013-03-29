application entityfunctions

imports webdsldebuglib/debugevents

entity RepoSearchManager {
  newWeekMoment :: Date ( default= now() )
  hourCounter :: Int ( default= 0 )
  intervalInHours :: Int ( default= 11 )
  nextInvocation :: DateTime
  function newHour ( ) : Void
  {
    WebDSLDebugEvents.functionEnter("webdsl_scripts/testcases/fragments/functions/entity/entityfunctions.app", "9,2,16,5", "newHour");
    try
    {
      WebDSLDebugEvents.statementStep("webdsl_scripts/testcases/fragments/functions/entity/entityfunctions.app", "10,3,10,15");
      tryNewWeek();
      var execute := false ;
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
    finally
    {
      WebDSLDebugEvents.functionExit("webdsl_scripts/testcases/fragments/functions/entity/entityfunctions.app", "9,2,16,5", "newHour");
    }
  }
  function shiftByHours ( hours : Int ) : Int
  {
    WebDSLDebugEvents.functionEnter("webdsl_scripts/testcases/fragments/functions/entity/entityfunctions.app", "17,5,21,2", "shiftByHours");
    try
    {
      WebDSLDebugEvents.statementStep("webdsl_scripts/testcases/fragments/functions/entity/entityfunctions.app", "18,9,18,43");
      hourCounter := hourCounter - hours;
      WebDSLDebugEvents.statementStep("webdsl_scripts/testcases/fragments/functions/entity/entityfunctions.app", "19,9,19,59");
      nextInvocation := nextInvocation.addHours(hours);
      WebDSLDebugEvents.statementStep("webdsl_scripts/testcases/fragments/functions/entity/entityfunctions.app", "20,9,20,17");
      return 5;
    }
    finally
    {
      WebDSLDebugEvents.functionExit("webdsl_scripts/testcases/fragments/functions/entity/entityfunctions.app", "17,5,21,2", "shiftByHours");
    }
  }
  function tryNewWeek ( ) : Void
  {
    WebDSLDebugEvents.functionEnter("webdsl_scripts/testcases/fragments/functions/entity/entityfunctions.app", "22,5,26,5", "tryNewWeek");
    try
    {
      WebDSLDebugEvents.statementStep("webdsl_scripts/testcases/fragments/functions/entity/entityfunctions.app", "23,9,25,9");
      if ( newWeekMoment.addDays(7).before(now()) )
      {
      }
    }
    finally
    {
      WebDSLDebugEvents.functionExit("webdsl_scripts/testcases/fragments/functions/entity/entityfunctions.app", "22,5,26,5", "tryNewWeek");
    }
  }
  function foo ( ) : Void
  {
    WebDSLDebugEvents.functionEnter("webdsl_scripts/testcases/fragments/functions/entity/entityfunctions.app", "28,5,30,5", "foo");
    try
    {
    }
    finally
    {
      WebDSLDebugEvents.functionExit("webdsl_scripts/testcases/fragments/functions/entity/entityfunctions.app", "28,5,30,5", "foo");
    }
  }
  function bar ( ) : Bool
  {
    WebDSLDebugEvents.functionEnter("webdsl_scripts/testcases/fragments/functions/entity/entityfunctions.app", "32,5,34,5", "bar");
    try
    {
      WebDSLDebugEvents.statementStep("webdsl_scripts/testcases/fragments/functions/entity/entityfunctions.app", "33,6,33,18");
      return false;
    }
    finally
    {
      WebDSLDebugEvents.functionExit("webdsl_scripts/testcases/fragments/functions/entity/entityfunctions.app", "32,5,34,5", "bar");
    }
  }
}

define page root () {
}