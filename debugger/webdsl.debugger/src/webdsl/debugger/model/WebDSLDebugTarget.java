package webdsl.debugger.model;

import org.eclipse.debug.core.ILaunch;
import org.eclipse.debug.core.model.IDebugTarget;
import org.spoofax.debug.core.eclipse.LIDebugTarget;

import com.sun.jdi.VirtualMachine;

public class WebDSLDebugTarget extends LIDebugTarget {

	public WebDSLDebugTarget(String languageID, ILaunch launch, String port) {
		super(languageID, launch, port);
		// TODO Auto-generated constructor stub
	}

	public WebDSLDebugTarget(IDebugTarget javaTarget, String languageID, ILaunch launch, VirtualMachine vm) {
		super(javaTarget, languageID, launch, vm);
	}

}
