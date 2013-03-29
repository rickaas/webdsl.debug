package webdsl.debugger.runtime.nativejava;

import utils.AbstractPageServlet;
import webdsl.debugger.runtime.DebugEvents;

public class StatementStep {

	public static String statementStep(AbstractPageServlet page, String filename, String location) {
		DebugEvents.step(filename, location);
		return "";
	}
}
