module code/calc

	entity CallCount {
		count :: Int
		
		function inc() {
			count := count + 1;
		}
	}
	var callCount := CallCount{count:=0}

	function doStuff(x : Int, y : Int) :Int {
		if (x >y) {
			var s := sum(x, y);
			return s;
		} else {
			return -1;
		}
	}
	
	function sum(x : Int, y : Int) : Int {
		return x + y;
	}
	
	function validateEmail(mail : String) : Bool {
    	return false;
	}