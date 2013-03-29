package webdsl.debugger.model;

import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.spoofax.debug.core.eclipse.LILineBreakpoint;

import webdsl.debugger.WebDSLConstants;

public class WebDSLLineBreakpoint extends LILineBreakpoint {
	/**
	 * Default constructor is required for the breakpoint manager
	 * to re-create persisted breakpoints. After instantiating a breakpoint,
	 * the <code>setMarker(...)</code> method is called to restore
	 * this breakpoint's attributes.
	 */
	public WebDSLLineBreakpoint() {
	}
	
	public WebDSLLineBreakpoint(final IResource resource, final int lineNumber) throws CoreException {
		super(resource, lineNumber);
	}
	@Override
	public String getModelIdentifier() {
		return WebDSLConstants.ID_WEBDSL_DEBUG_MODEL;
	}

	@Override
	public String getLineBreakpointMarkerID() {
		return WebDSLConstants.WEBDSL_LINEBREAKPOINT_MARKER;
	}
}
