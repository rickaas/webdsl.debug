module finallyblock

	function resetSchedule(){
		try {
			var hourCounter := 0;
		    var intervalInHours := 12;
		    var count := hourCounter + intervalInHours;
	    } finally {
		    validateEmail("foo@bar.com");
	    }
	}
	  
    function validateEmail(mail : String) : Bool {
    	return false;
	}