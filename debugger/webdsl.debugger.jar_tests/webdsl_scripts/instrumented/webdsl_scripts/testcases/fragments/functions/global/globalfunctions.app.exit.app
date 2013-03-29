application globalfunctions

imports webdsldebuglib/debugevents

	function resetSchedule(){
		try {
		var hourCounter := 0;
	    var intervalInHours := 12;
	    var count := hourCounter + intervalInHours;
	    validateEmail("foo@bar.com");
	    } finally {
			WebDSLDebugEvents.functionExit("webdsl_scripts/testcases/fragments/functions/global/globalfunctions.app"
				, "3,1,8,1"
				, "resetSchedule");
		}
	}
	  
    function validateEmail(mail : String) : Bool {
    	try {
    	return false;
    	} finally {
			WebDSLDebugEvents.functionExit("webdsl_scripts/testcases/fragments/functions/global/globalfunctions.app"
				, "10,4,12,1"
				, "validateEmail");
		}
	}
	
	define page root(){}