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
      tryNewWeek();
      var execute := false ;
      hourCounter := hourCounter + 1;
      if ( hourCounter >= intervalInHours )
      {
        hourCounter := 0;
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
      hourCounter := hourCounter - hours;
      nextInvocation := nextInvocation.addHours(hours);
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