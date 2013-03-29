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
	
	static start() : String
	static stop() : String
	static flush() : String
	static addFrameNameFilter() : String
	static resetFrameNameFilter() : String
}

define page start() {
  debug_start()
  "Start"
}
define page stop() {
  debug_stop()
  "Stop"
}
define page flush() {
  debug_flush()
  "Flush"
}
define page addFrameNameFilter() {
  debug_addFrameNameFilter()
  "Add Frame Name Filter"
}
define page resetFrameNameFilter() {
  debug_addFrameNameFilter()
  "Add Frame Name Filter"
}

define debug_start() {
  var s := WebDSLDebugEvents.start();
}
define debug_stop() {
  var s := WebDSLDebugEvents.stop();
}
define debug_flush() {
  var s := WebDSLDebugEvents.flush();
}
define debug_addFrameNameFilter() {
  var s := WebDSLDebugEvents.addFrameNameFilter();
}
define debug_resetFrameNameFilter() {
  var s := WebDSLDebugEvents.resetFrameNameFilter();
}

    // define page blog(s:String){          
    //   output(Request.getQueryString())
    // }

    native class nativejava.GetRequestParameter as Request{
      static getQueryString():String
    }