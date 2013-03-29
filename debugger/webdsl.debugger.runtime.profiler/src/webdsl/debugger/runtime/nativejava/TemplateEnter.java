package webdsl.debugger.runtime.nativejava;

import utils.AbstractPageServlet;
import webdsl.debugger.runtime.DebugEvents;

public class TemplateEnter {

	public static String templateEnter(AbstractPageServlet page, String filename, String location, String functionName) {
		DebugEvents.enter(filename, location, functionName);
		return "";
	}
}
