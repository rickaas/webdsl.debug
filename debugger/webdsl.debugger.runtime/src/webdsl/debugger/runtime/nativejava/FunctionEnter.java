package webdsl.debugger.runtime.nativejava;

import utils.AbstractPageServlet;
import webdsl.debugger.runtime.DebugEvents;

public class FunctionEnter {

	public static String functionEnter(AbstractPageServlet page, String filename, String location, String functionName) {
		DebugEvents.enter(filename, location, functionName);
		return "";
	}
	
}
