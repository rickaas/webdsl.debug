application globalfunctions

imports webdsldebuglib/debugevents

	function resetSchedule(){
		WebDSLDebugEvents.functionEnter("webdsl_scripts/testcases/fragments/functions/global/globalfunctions.app"
			, "3,1,8,1"
			, "resetSchedule");
		var hourCounter := 0;
	    var intervalInHours := 12;
	    var count := hourCounter + intervalInHours;
	    validateEmail("foo@bar.com");
	}
	  
    function validateEmail(mail : String) : Bool {
		WebDSLDebugEvents.functionEnter("webdsl_scripts/testcases/fragments/functions/global/globalfunctions.app"
			, "10,4,12,1"
			, "validateEmail");
    	return false;
	}
	
	define page root(){}