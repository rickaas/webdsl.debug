package webdsl.debugger.model.events;

import org.spoofax.debug.core.language.events.AbstractEventInfo;

public class WebDSLStepEvent extends AbstractEventInfo {

	public WebDSLStepEvent(String threadName, String filename, String location) {
		super(threadName, filename, location);
	}

	@Override
	public String getEventType() {
		return "step";
	}
	
}
