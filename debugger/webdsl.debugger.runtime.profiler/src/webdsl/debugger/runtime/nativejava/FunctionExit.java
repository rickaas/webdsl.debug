package webdsl.debugger.runtime.nativejava;

import utils.AbstractPageServlet;
import webdsl.debugger.runtime.DebugEvents;

public class FunctionExit {

	public static String functionExit(AbstractPageServlet page, String filename, String location, String functionName) {
		DebugEvents.exit(filename, location, functionName);
		return "";
	}
}
