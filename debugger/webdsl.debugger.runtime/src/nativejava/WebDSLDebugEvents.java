package nativejava;

import webdsl.debugger.runtime.DebugEvents;

public class WebDSLDebugEvents {

	// enter/exit for templates
	// filename, location, functionName
	public static String templateEnter(String filename, String location, String functionName) {
		DebugEvents.enter(filename, location, functionName);
		return "";
	}
	public static String templateExit(String filename, String location, String functionName) {
		DebugEvents.exit(filename, location, functionName);
		return "";
	}
	// enter/exit for functions
	public static String functionEnter(String filename, String location, String functionName) {
		DebugEvents.enter(filename, location, functionName);
		return "";
	}
	public static String functionExit(String filename, String location, String functionName) {
		DebugEvents.exit(filename, location, functionName);
		return "";
	}
	
	// stepping in functions/templates
	// filename, location
	public static String statementStep(String filename, String location) {
		DebugEvents.step(filename, location);
		return "";
	}
	
	public static String var(String filename, String location, String varName) {
		DebugEvents.var(filename, location, varName, null);
		return "";
	}
}
