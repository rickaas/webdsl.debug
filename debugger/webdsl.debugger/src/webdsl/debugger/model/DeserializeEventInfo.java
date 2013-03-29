package webdsl.debugger.model;

import java.util.regex.Pattern;

import org.spoofax.debug.interfaces.extractor.IDeserializeEventInfo;
import org.spoofax.debug.interfaces.info.IEventInfo;
import org.spoofax.debug.interfaces.java.DebugEventBase;

import webdsl.debugger.model.events.WebDSLEnterEvent;
import webdsl.debugger.model.events.WebDSLExitEvent;
import webdsl.debugger.model.events.WebDSLStepEvent;
import webdsl.debugger.model.events.WebDSLVarEvent;

public class DeserializeEventInfo implements IDeserializeEventInfo {

	public static final String TAB = "\t";
	
	public final static String TAB_IN_REGEX = "\t";
	
	public final static Pattern tabSplit = Pattern.compile(TAB_IN_REGEX);
	
	// in ActionLanguage we only we a single thread. At least for now...
	public final static String THREAD_MAIN = "main";
	
	public IEventInfo deserialize(String eventType, String eventInfoData) {
		if (DebugEventBase.ENTER.equals(eventType)) {
			return enter(eventInfoData);
		} else if (DebugEventBase.EXIT.equals(eventType)) {
			return exit(eventInfoData);
		} else if (DebugEventBase.STEP.equals(eventType)) {
			return step(eventInfoData);
		} else if (DebugEventBase.VAR.equals(eventType)) {
			return var(eventInfoData);
		} else {
			throw new RuntimeException("Not supported event type " + eventType + " with info " + eventInfoData);
		}
	}
	
	private IEventInfo enter(String eventInfoData) {
		String[] tokens = tabSplit.split(eventInfoData);
		if (tokens.length != 3) throw new RuntimeException("Could not deserialize event info: " + eventInfoData);
		String filename = tokens[0];
		String location = tokens[1];
		String functionName = tokens[2];
		return new WebDSLEnterEvent(THREAD_MAIN, filename, location, functionName);
	}
	
	private IEventInfo exit(String eventInfoData) {
		String[] tokens = tabSplit.split(eventInfoData);
		if (tokens.length != 3) throw new RuntimeException("Could not deserialize event info: " + eventInfoData);
		String filename = tokens[0];
		String location = tokens[1];
		String functionName = tokens[2];
		return new WebDSLExitEvent(THREAD_MAIN, filename, location, functionName);
	}
	
	private IEventInfo step(String eventInfoData) {
		String[] tokens = tabSplit.split(eventInfoData);
		if (tokens.length != 2) throw new RuntimeException("Could not deserialize event info: " + eventInfoData);
		String filename = tokens[0];
		String location = tokens[1];
		return new WebDSLStepEvent(THREAD_MAIN, filename, location);

	}
	
	private IEventInfo var(String eventInfoData) {
		String[] tokens = tabSplit.split(eventInfoData);
		if (tokens.length != 3) throw new RuntimeException("Could not deserialize event info: " + eventInfoData);
		String filename = tokens[0];
		String location = tokens[1];
		String varName = tokens[2];
		return new WebDSLVarEvent(THREAD_MAIN, filename, location, varName);
	}
	
}
