application globalfunctions

	function resetSchedule(){
		var hourCounter := 0;
	    var intervalInHours := 12;
	    var count := hourCounter + intervalInHours;
	    validateEmail("foo@bar.com");
	}
	  
    function validateEmail(mail : String) : Bool {
    	return false;
	}
	
	define page root(){}