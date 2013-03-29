package webdsl.debugger.launching;

import webdsl.debugger.WebDSLConstants;

public class WebDSLLaunchShortcut {
	
	public void searchAndLaunch(Object[] search, String mode) {
		// TODO: implement!
        /*
    	IType[] types = null;
        if (search != null) {
            try {
             types = AppletLaunchConfigurationUtils.findApplets(
                     new ProgressMonitorDialog(getShell()), search);
            } catch (Exception e) {
               
            }
            IType type = null;
         if (types.length == 0) {
                MessageDialog.openInformation(
                    getShell(), "Applet Launch", "No applets found."};
         } else if (types.length > 1) {
                type = chooseType(types, mode);
         } else {
                type = types[0];
            }
            if (type != null) {
             launch(type, mode);
            }
        }
        */
    }
	
	/* (non-Javadoc)
	 * @see org.eclipse.jdt.debug.ui.launchConfigurations.JavaLaunchShortcut#getTypeSelectionTitle()
	 */
	protected String getTypeSelectionTitle() {
		return "Select "+WebDSLConstants.getLanguageID()+" Application"; //LauncherMessages.JavaApplicationLaunchShortcut_0;
	}

	public String getLaunchConfigurationTypeID() {
		return WebDSLConstants.WEBDSL_LAUNCH_CONFIG_TYPE;
	}
	public String getProgramAttribute() {
		return WebDSLConstants.ATTR_WEBDSL_PROGRAM;
	}
}
