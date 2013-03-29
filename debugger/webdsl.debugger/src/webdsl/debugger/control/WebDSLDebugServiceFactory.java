package webdsl.debugger.control;

import org.eclipse.debug.core.ILaunch;
import org.spoofax.debug.core.control.java.VMContainer;
import org.spoofax.debug.core.language.IDebugServiceFactory;
import org.spoofax.debug.core.language.LIConstants;

import webdsl.debugger.WebDSLConstants;
import webdsl.debugger.model.WebDSLDebugTarget;

import com.sun.jdi.VirtualMachine;

public class WebDSLDebugServiceFactory implements IDebugServiceFactory {
	
	@Override
	public VMContainer createVMContainer(VirtualMachine vm) {
		return new WebDSLVMContainer(vm);
	}
	
	@Override
	public WebDSLDebugTarget createDebugTarget(ILaunch launch, String port) {
		WebDSLDebugTarget target = new WebDSLDebugTarget(getLanguageID(), launch, port);
		return target;
	}
	
	@Override
	public LIConstants getLIConstants() {
		return new WebDSLConstants.WebDSLAttributes();
	}

	public static String getLanguageID() {
		//String languageID = webdsl.Activator.getInstance().getLanguageID();
		return WebDSLConstants.getLanguageID();
	}
}
