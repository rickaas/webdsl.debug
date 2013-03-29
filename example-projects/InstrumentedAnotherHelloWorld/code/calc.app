module code/calc

	entity CallCount {
		count :: Int
		
		function inc() {
			WebDSLDebugEvents.functionEnter("code/calc.app", "", "inc");
			count := count + 1;
			WebDSLDebugEvents.functionExit("code/calc.app", "", "inc");
		}
	}
	var callCount := CallCount{count:=0}

	function doStuff(x : Int, y : Int) :Int {
		WebDSLDebugEvents.functionEnter("code/calc.app", "", "doStuff");
		if (x >y) {
			var s := sum(x, y);
			WebDSLDebugEvents.functionExit("code/calc.app", "", "doStuff");
			return s;
		} else {
			WebDSLDebugEvents.functionExit("code/calc.app", "", "doStuff");
			return -1;
		}
	}
	
	function sum(x : Int, y : Int) : Int {
		WebDSLDebugEvents.functionEnter("code/calc.app", "", "sum");
		WebDSLDebugEvents.functionExit("code/calc.app", "", "sum");
		return x + y;
	}
	
	function validateEmail(mail : String) : Bool {
		WebDSLDebugEvents.functionEnter("code/calc.app", "", "validateEmail");
		WebDSLDebugEvents.functionExit("code/calc.app", "", "validateEmail");
    	return false;
	}