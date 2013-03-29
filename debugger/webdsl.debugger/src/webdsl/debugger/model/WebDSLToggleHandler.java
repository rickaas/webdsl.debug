package webdsl.debugger.model;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.debug.core.DebugPlugin;
import org.eclipse.debug.core.model.IBreakpoint;
import org.eclipse.debug.core.model.ILineBreakpoint;
import org.spoofax.debug.core.eclipse.LIToggleHandler;

import webdsl.debugger.WebDSLConstants;

public class WebDSLToggleHandler extends LIToggleHandler {

	public WebDSLToggleHandler() {

	}
	
//	@Override
//	public void clearLineBreakpoint(IFile resource, int lineNumber) throws CoreException {
//		for(ILineBreakpoint breakpoint : this.getLineBreakpointsAt(resource, lineNumber)) {
//			breakpoint.delete();
//		}
//	}
//
//	@Override
//	public void disableLineBreakpoint(IFile resource, int lineNumber)
//			throws CoreException {
//		for(ILineBreakpoint breakpoint : this.getLineBreakpointsAt(resource, lineNumber)) {
//			breakpoint.setEnabled(false);
//		}
//	}
//
//	@Override
//	public void enableLineBreakpoint(IFile resource, int lineNumber) throws CoreException {
//		for(ILineBreakpoint breakpoint : this.getLineBreakpointsAt(resource, lineNumber)) {
//			breakpoint.setEnabled(true);
//		}
//	}
//
//	/**
//	 * linenumber is 1-based
//	 */
//	@Override
//	public void setLineBreakpoint(IFile resource, int lineNumber) throws CoreException {
//		AlngLineBreakpoint lineBreakpoint = new AlngLineBreakpoint(resource, lineNumber);
//		if (!this.getLineBreakpointsAt(resource, lineNumber).isEmpty()) {
//			// breakpoint already exists...
//			System.out.println("ILineBreakpoint already exists at " + resource + " " + lineNumber);
//		}
//		DebugPlugin.getDefault().getBreakpointManager().addBreakpoint(lineBreakpoint);
//	}

	@Override
	protected IBreakpoint create(IFile resource, int lineNumber)
	throws CoreException {
		WebDSLLineBreakpoint lineBreakpoint = new WebDSLLineBreakpoint(resource, lineNumber);
		return lineBreakpoint;
	}

	@Override
	protected List<ILineBreakpoint> getLineBreakpointsAt(IFile resource, int lineNumber) throws CoreException {
		List<ILineBreakpoint> matching = new ArrayList<ILineBreakpoint>();
		IBreakpoint[] breakpoints = DebugPlugin.getDefault().getBreakpointManager().getBreakpoints(
		WebDSLConstants.ID_WEBDSL_DEBUG_MODEL);
		// if a breakpoint exists on that line, enable it
		for (int i = 0; i < breakpoints.length; i++) {
			IBreakpoint breakpoint = breakpoints[i];
			if (resource.equals(breakpoint.getMarker().getResource())) {
				if ((breakpoint instanceof ILineBreakpoint) && ((ILineBreakpoint) breakpoint).getLineNumber() == lineNumber) {
					matching.add((ILineBreakpoint) breakpoint);

				}
			}
		}
		return matching;
	}
	
}
