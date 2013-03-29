module webdsldebuglib/debugevents

	// cannot call a function inside a template
	// we have to assign it to a variable.
	define debug_template_enter(filename : String, 
								location : String, 
								templateName : String)
	{
		var s := WebDSLDebugEvents.templateEnter(filename, location, templateName);
	}

	// cannot call a function inside a template
	// we have to assign it to a variable.
	define debug_template_exit(filename : String, 
								location : String, 
								templateName : String)
	{
		var s := WebDSLDebugEvents.templateExit(filename, location, templateName);
	}

	define debug_template_step(filename : String, location : String) {
	  var s := WebDSLDebugEvents.statementStep(filename, location)
	}
	
	define debug_template_var(filename : String, location : String, varname : String) {
	  var s := WebDSLDebugEvents.var(filename, location, varname)
	}
// ========= native methods implemented in Java ================

native class nativejava.WebDSLDebugEvents as WebDSLDebugEvents {
	
	// enter/exit for templates
	// filename, location, functionName
	static templateEnter(String, String, String) : String
	static templateExit(String, String, String) : String
	// enter/exit for functions
	static functionEnter(String, String, String) : String
	static functionExit(String, String, String) : String
	
	// stepping in functions/templates
	// filename, location
	static statementStep(String, String) : String
	
	// filename, location, varName
	static var(String, String, String) : String
}
/*
	// enter/exit for templates
	native function templateEnter(filename : String, location : String, functionName : String) : String;
	native function templateExit(filename : String, location : String, functionName : String) : String;
	// enter/exit for functions
	native function functionEnter(filename : String, location : String, functionName : String) : String;
	native function functionExit(filename : String, location : String, functionName : String) : String;
	
	// stepping in functions/templates
	native function statementStep(filename : String, location : String) : String;
*/
