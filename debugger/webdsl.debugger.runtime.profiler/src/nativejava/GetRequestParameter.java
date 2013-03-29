package nativejava;

import utils.ThreadLocalServlet;

public class GetRequestParameter {
	public static String getQueryString(){
		String q = ThreadLocalServlet.get().getRequest().getQueryString();
		return q;
	}
}
