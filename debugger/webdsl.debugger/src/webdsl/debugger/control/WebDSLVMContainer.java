package webdsl.debugger.control;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.spoofax.debug.core.control.events.AbstractBreakPoint;
import org.spoofax.debug.core.control.java.VMContainer;
import org.spoofax.debug.core.control.java.events.BreakpointHandler;
import org.spoofax.debug.core.control.java.events.ClassPrepareHandler;
import org.spoofax.debug.core.control.java.events.IJavaEventHandler;
import org.spoofax.debug.core.control.java.events.ThreadDeathHandler;
import org.spoofax.debug.core.control.java.events.VMDeathHandler;
import org.spoofax.debug.core.control.java.events.VMDisconnectHandler;
import org.spoofax.debug.core.control.java.events.VMStartHandler;
import org.spoofax.debug.core.language.model.BasicDebugModel;
import org.spoofax.debug.core.language.model.BasicProgramState;
import org.spoofax.debug.core.language.model.StepController;
import org.spoofax.debug.core.model.IProgramState;
import org.spoofax.debug.interfaces.extractor.IDeserializeEventInfo;
import org.spoofax.debug.interfaces.info.IEventInfo;
import org.spoofax.debug.interfaces.java.EnterEvent;
import org.spoofax.debug.interfaces.java.ExitEvent;
import org.spoofax.debug.interfaces.java.ISuspendInClassEntry;
import org.spoofax.debug.interfaces.java.StepEvent;
import org.spoofax.debug.interfaces.java.VarEvent;

import webdsl.debugger.model.DeserializeEventInfo;

import com.sun.jdi.VirtualMachine;
import com.sun.jdi.event.BreakpointEvent;
import com.sun.jdi.event.ClassPrepareEvent;
import com.sun.jdi.event.ThreadDeathEvent;
import com.sun.jdi.event.VMDeathEvent;
import com.sun.jdi.event.VMDisconnectEvent;
import com.sun.jdi.event.VMStartEvent;

public class WebDSLVMContainer extends VMContainer {

	// The DSL program state
	protected IProgramState programState = null;
	
	// helper to process events
	protected BasicDebugModel basicDebugModel = null;
	
	protected StepController stepController;
	
	public WebDSLVMContainer(VirtualMachine vm) {
		super(vm);
		// init DSL specific data structures
		this.programState = new BasicProgramState();
		this.basicDebugModel = new BasicDebugModel(programState);
		this.stepController = new StepController(programState);
		// init DSL specific handlers
		initHandlers();
	}
	
	private List<ISuspendInClassEntry> getSuspendTargets() {
		List<ISuspendInClassEntry> list = new ArrayList<ISuspendInClassEntry>();
		// create a list of fake events ISuspendInClassEntry
		// these classes are also used in the VM running the DSL program
		list.add(new EnterEvent("stub"));
		list.add(new ExitEvent("stub"));
		list.add(new StepEvent("stub"));
		list.add(new VarEvent("stub"));
		return list;
	}
	
	@Override
	public IProgramState getProgramState() {
		return this.programState;
	}
	
	@Override
	public StepController getStepController() {
		return this.stepController;
	}

	@Override
	public boolean processDebugEvent(IEventInfo eventInfo) {
		// update the program state
		basicDebugModel.processDebugEvent(eventInfo);
//		if (basicDebugModel.shouldSuspend(eventInfo)) {
//			isSuspended = true;
//		}
		if (basicDebugModel.hasPendingSuspend()) {
			isSuspended = true;
			this.stepController.cancelStep(eventInfo.getThreadName());
			this.getProgramState().getThread(eventInfo.getThreadName()).setSuspend(true);
			this.suspended(eventInfo);
		} else if (basicDebugModel.breakpointHit(eventInfo)) {
			isSuspended = true;
			this.stepController.cancelStep(eventInfo.getThreadName());
			this.getProgramState().getThread(eventInfo.getThreadName()).setSuspend(true);
			this.breakpointHit(eventInfo);
		} else if (this.stepController.isStepping(eventInfo.getThreadName())){
			if (this.stepController.shouldSuspend(eventInfo)) {
				isSuspended = true;
				this.stepController.cancelStep(eventInfo.getThreadName());
				this.getProgramState().getThread(eventInfo.getThreadName()).setSuspend(true);
				this.stepped(eventInfo);
			}
		}
		return isSuspended;
	}

	private IDeserializeEventInfo deserializer;
	
	// When a class is loaded attach a breakpoint handler
	private ClassPrepareHandler classPrepareHandler;
	
	// A breakpoint is set in java classes.
	// The BreakpointHandler uses the deserializer to extract language-specific events. 
	private BreakpointHandler breakpointHandler;
	
	@SuppressWarnings("rawtypes")
	protected Map<Class, IJavaEventHandler> handlers = new HashMap<Class, IJavaEventHandler>();
	
	private void initHandlers() {
		deserializer = new DeserializeEventInfo();
		
		List<ISuspendInClassEntry> suspendTargets = this.getSuspendTargets();
		classPrepareHandler = new ClassPrepareHandler(suspendTargets);
		classPrepareHandler.installLoadedClasses(this.vm);
		
		breakpointHandler = new BreakpointHandler(this, deserializer);
		
		handlers.put(VMDeathEvent.class, new VMDeathHandler(this));
		handlers.put(VMStartEvent.class, new VMStartHandler(this));
		handlers.put(ThreadDeathEvent.class, new ThreadDeathHandler(this));
		handlers.put(VMDisconnectEvent.class, new VMDisconnectHandler(this));
		

	}
	
	@SuppressWarnings("rawtypes")
	@Override
	public IJavaEventHandler getHandler(Class clazz) {
		if (clazz.equals(ClassPrepareEvent.class)) {
			return classPrepareHandler;
		} else if (clazz.equals(BreakpointEvent.class)) {
			return breakpointHandler;
		} else {
			if (this.handlers.containsKey(clazz)) {
				return this.handlers.get(clazz);
			}
		}
		return null;
	}
	
	@Override
	public void scheduleSuspend() {
		// do not suspend the underlying java vm but wait for the first event.
		this.basicDebugModel.scheduleSuspend();
	}

	@Override
	public void addBreakpoint(AbstractBreakPoint breakpoint) {
		this.basicDebugModel.add(breakpoint);
	}

	@Override
	public void removeBreakpoint(AbstractBreakPoint breakpoint) {
		this.basicDebugModel.remove(breakpoint);
	}
	
	@Override
	public void removeAllBreakpoints() {
		this.basicDebugModel.clear();
	}

	@Override
	public boolean canTerminate() {
		return false;
	}
	
	/**
	 * We always connect to a running server, so we need to be able to disconnect.
	 */
	@Override
	public boolean supportsDisconnect() {
		return true;
	}

}
