package webdsl.debugger;

import org.spoofax.debug.core.language.LIConstants;

public class WebDSLConstants {

	/**
	 * Unique identifier for the WebDSL debug model (value 
	 * <code>webdsl.debugger</code>).
	 */
	public static final String ID_WEBDSL_DEBUG_MODEL = "webdsl.debugger";
	
	/**
	 * Launch configuration key. Value is a path to a WebDSL
	 * program. The path is a string representing a full path
	 * to a WebDSL program in the workspace. 
	 */
	public static final String ATTR_WEBDSL_PROGRAM = ID_WEBDSL_DEBUG_MODEL + ".ATTR_WEBDSL_PROGRAM";
	
	// webdsl.debugger.launchConfigurationType
	public static final String WEBDSL_LAUNCH_CONFIG_TYPE = ID_WEBDSL_DEBUG_MODEL + ".launchConfigurationType";
	
	public static final String WEBDSL_LINEBREAKPOINT_MARKER = ID_WEBDSL_DEBUG_MODEL + ".lineBreakpoint.marker";
	
	public static String getLanguageID() {
		//String languageID = webdsl.Activator.getInstance().getLanguageID();
		return "WebDSL";
	}
	
	public static class WebDSLAttributes implements LIConstants {

		@Override
		public String getProgram() {
			return ATTR_WEBDSL_PROGRAM;
		}
		
		@Override
		public String getDebugModel() {
			return ID_WEBDSL_DEBUG_MODEL;
		}

		@Override
		public String getLineBreakpointMarker() {
			return WEBDSL_LINEBREAKPOINT_MARKER;
		}
	}
}
