package webdsl.debugger.runtime.nativejava;

import utils.AbstractPageServlet;
import webdsl.debugger.runtime.DebugEvents;

public class TemplateExit {

	public static String templateExit(AbstractPageServlet page, String filename, String location, String functionName) {
		DebugEvents.exit(filename, location, functionName);
		return "";
	}

}
