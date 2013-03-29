package webdsl.debugger.runtime;

import java.io.IOException;

import org.spoofax.debug.interfaces.java.FireEvent;
import org.spoofax.debug.interfaces.java.GlobalVarEnvironmentScope;

import webdsl.debugger.profiler.EventHandlingDuration;
import webdsl.debugger.profiler.FrameDuration;
import webdsl.debugger.profiler.FrameNameFilter;

public class DebugEvents {

	public static final boolean TrackEventHandlingDuration = false;
	
	public static void enter(String filename, String location, String functionName) {
		if (TrackEventHandlingDuration) EventHandlingDuration.instance().start("enter"); // event handling duration
		String eventInfo = join(filename, location, functionName);
		if (FrameNameFilter.instance().exists(functionName)) {
			FrameDuration.instance().start(eventInfo); // stackframe duration
		}
		GlobalVarEnvironmentScope.get().enterFrame();
		FireEvent.enter(eventInfo);
		if (TrackEventHandlingDuration) EventHandlingDuration.instance().stop("enter"); // event handling duration
	}
	
	public static void exit(String filename, String location, String functionName) {
		if (TrackEventHandlingDuration) EventHandlingDuration.instance().start("exit"); // event handling duration
		String eventInfo = join(filename, location, functionName);
		if (FrameNameFilter.instance().exists(functionName)) {
			FrameDuration.instance().stop(eventInfo); // stackframe duration
		}
		GlobalVarEnvironmentScope.get().exitFrame(); // Or should this be after exit?
		FireEvent.exit(eventInfo);
		if (TrackEventHandlingDuration) EventHandlingDuration.instance().stop("exit"); // event handling duration
	}
	
	public static void var(String filename, String location, String varName, Object value) {
		if (TrackEventHandlingDuration) EventHandlingDuration.instance().start("var"); // event handling duration
		String eventInfo = join(filename, location, varName);
		GlobalVarEnvironmentScope.get().setVar(varName, value);
		FireEvent.var(eventInfo);
		if (TrackEventHandlingDuration) EventHandlingDuration.instance().stop("var"); // event handling duration
	}
	
	/**
	 * After each step the current term can be changed.
	 * Save the current term in the Variable Environment but do not include it in the debug event.
	 * The value is retrieved when the program is suspended. 
	 * @param filename
	 * @param location
	 */
	public static void step(String filename, String location) {
		if (TrackEventHandlingDuration) EventHandlingDuration.instance().start("step"); // event handling duration
		String eventInfo = join(filename, location);
		FireEvent.step(eventInfo);
		if (TrackEventHandlingDuration) EventHandlingDuration.instance().stop("step"); // event handling duration
	}
	
	private static String join(String s1, String s2, String s3) {
		String s = s1 + "\t" + s2 + "\t" + s3;
		return s;
	}
	
	private static String join(String s1, String s2) {
		String s = s1 + "\t" + s2;
		return s;
	}
	
	public static void start(String id) {
		//long t = System.currentTimeMillis();
		FrameDuration.instance(id);
		EventHandlingDuration.instance(id);
	}
	
	public static void stop() {
		try {
			FrameDuration.instance().close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		FrameDuration.reset();
		try {
			EventHandlingDuration.instance().close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		EventHandlingDuration.reset();
	}
	
	public static void flush() {
		try {
			FrameDuration.instance().flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			EventHandlingDuration.instance().flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void addFrameNameFilter(String filter) {
		FrameNameFilter.instance().addFilter(filter);
	}
	
	public static void resetFrameNameFilter() {
		FrameNameFilter.reset();
	}
}
