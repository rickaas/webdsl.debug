application entityfunctions

	entity RepoSearchManager{
		newWeekMoment   :: Date(default=now())
		hourCounter     :: Int (default=0)
		intervalInHours :: Int (default=11)
		nextInvocation  :: DateTime
		
		function newHour(){
			tryNewWeek();
      		var execute := false;
      		hourCounter := hourCounter + 1;
      		if (hourCounter >= intervalInHours) { 
      			hourCounter := 0; execute := true; 
      		}
    	}
    	function shiftByHours(hours : Int) : Int{
        	hourCounter := hourCounter - hours;
        	nextInvocation := nextInvocation.addHours( hours );
        	return 5;
		}
    	function tryNewWeek(){
        	if(newWeekMoment.addDays(7).before(now())){
            	//newWeekMoment := newWeekMoment.addDays(7);
        	}
    	}
    	
    	function foo() {
    		
    	}
    	
    	function bar() : Bool {
    		return false;
    	}
	}
	
	define page root(){}