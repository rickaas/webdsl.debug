package webdsl.debugger.profiler;

import java.io.IOException;

public class FrameDuration {
	
	public static DurationStorage instance() {
		if (instance == null) {
			String t = Long.toString(System.currentTimeMillis());
			instance = instance(t);
		}
		return instance;
	}
	
	public static DurationStorage instance(String id) {
		if (instance == null) {
			try {
				if (id == null) {
					id = Long.toString(System.currentTimeMillis());
				}
				instance = new DurationStorage("FrameDuration."+id+".trace");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return instance;
	}

	private static DurationStorage instance = null;
	
	public static void reset() {
		instance = null;
	}
}
