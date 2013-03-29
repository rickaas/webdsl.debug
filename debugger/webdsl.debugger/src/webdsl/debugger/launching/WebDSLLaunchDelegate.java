package webdsl.debugger.launching;

import java.text.MessageFormat;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.Path;
import org.eclipse.debug.core.ILaunch;
import org.eclipse.debug.core.ILaunchConfiguration;
import org.eclipse.debug.core.ILaunchManager;
import org.eclipse.jdt.launching.IVMConnector;
import org.eclipse.jdt.launching.IVMInstall;
import org.eclipse.jdt.launching.IVMRunner;
import org.eclipse.jdt.launching.JavaRuntime;
import org.eclipse.jdt.launching.VMRunnerConfiguration;
import org.spoofax.debug.core.control.launching.IJavaProgramLaunchPreparation;
import org.spoofax.debug.core.control.launching.IJavaProgramPrepareResult;
import org.spoofax.debug.core.eclipse.JVMConnector;
import org.spoofax.debug.core.eclipse.JVMConnector.IJVMLaunched;
import org.spoofax.debug.core.language.LIConstants;
import org.spoofax.debug.core.launching.LILaunchDelegate;

import webdsl.debugger.WebDSLConstants;
import webdsl.debugger.control.WebDSLDebugServiceFactory;
import webdsl.debugger.model.WebDSLDebugTarget;

import com.sun.jdi.VirtualMachine;

public class WebDSLLaunchDelegate extends LILaunchDelegate implements IJVMLaunched {
	

	private ILaunch launch;
	private String mode;
	
	@Override
	public void launch(ILaunchConfiguration configuration, String mode,
			ILaunch launch, IProgressMonitor monitor) throws CoreException {
		this.launch = launch;
		this.mode = mode;
		
        if (monitor == null){
            monitor = new NullProgressMonitor();
        }
        monitor.beginTask("Starting debugger for "+this.getLanguageName()+" program", IProgressMonitor.UNKNOWN);
        
		// program name
		String program = configuration.getAttribute(this.getLIConstants().getProgram(), (String)null);
		if (program == null) {
			abort(this.getLanguageName() + " program unspecified.", null);
			return;
		}
		
		IFile file = ResourcesPlugin.getWorkspace().getRoot().getFile(new Path(program));
		if (!file.exists()) {
			abort(MessageFormat.format(this.getLanguageName() + " program {0} does not exist.", new Object[] {file.getFullPath().toString()}), null);
			return;
		}
		
		String port = "8000"; // TODO: make this configurable in the LaunchConfiguration
		JVMConnector connector = new JVMConnector(this, port, this.getLIConstants().getDebugModel());
		System.out.println("try connecting...");
		//connector.tryConnecting(); // socket listen
		connector.tryAttaching(); // socket attach
		monitor.done();
	}
	public void launchVMThenAddDebugTargets(IProgressMonitor monitor, 
			String classname, 
			String[] classpath, 
			ILaunch launch,
			String mode) throws CoreException {
		this.launch = launch;
		this.mode = mode;
		
		for(IVMConnector c : JavaRuntime.getVMConnectors()) {
			System.out.println(c.getIdentifier());
			System.out.println(c.getName());
		}
		
		// no program arguments supported
		String[] alngProgramArguments = new String[] {};
		
		// Initialize the VMRunner
		IVMInstall defaultInstall = JavaRuntime.getDefaultVMInstall();
		IVMRunner vmRunner = defaultInstall.getVMRunner(ILaunchManager.RUN_MODE); // always use RUN, so we can control the debug parameters of the VM
		// configure the vm arguments
		VMRunnerConfiguration vmRunnerConfiguration = new VMRunnerConfiguration(classname, classpath);
		vmRunnerConfiguration.setProgramArguments(alngProgramArguments);
		
		// the started wm will wait for a debugger to connect to this port
		//String port = ""+LaunchUtil.findFreePort();
		String port = "8080";
		
		// if we are in DEBUG_MODE also set the debugging parameters for the VM as we previously created an IVMRunner in RUN_MODE
		if (mode.equals(ILaunchManager.DEBUG_MODE)) {
			// socket listen, vm will wait until a debugger is attached
			//String[] realVMargs = new String[] { "-Xdebug", "-Xrunjdwp:transport=dt_socket,address="+port+",suspend=y", "-Xss8m" };
			//vmRunnerConfiguration.setVMArguments(realVMargs);
			
			// socket attach, attach debugger to a running vm
			String[] realVMargs = new String[] { "-Xdebug", "-Xrunjdwp:transport=dt_socket,address="+port+",suspend=y", "-Xss8m" };
			vmRunnerConfiguration.setVMArguments(realVMargs);
		}
		
		
		JVMConnector connector = new JVMConnector(this, port, this.getLIConstants().getDebugModel());
		System.out.println("try connecting...");
		connector.tryConnecting();

		System.out.println("The JVM will only launch if it can connect to the listener...");
		vmRunner.run(vmRunnerConfiguration, launch, monitor);
//		String connectorId = getVMConnectorId(configuration);
//		IVMConnector connector = null;
//		if (connectorId == null) {
//			connector = JavaRuntime.getDefaultVMConnector();
//		} else {
//			connector = JavaRuntime.getVMConnector(connectorId);
//		}
		

	}
	
	public void connected(VirtualMachine jvm) {
		if (jvm == null) {
			return; // This method could be called from another thread, we could throw an Exception but we don't know who will catch it...
		}
		//called when connected to vm
		System.out.println("connected: "+ jvm.name());

		if (mode.equals(ILaunchManager.DEBUG_MODE)) {
			
			WebDSLDebugTarget webdslTarget = new WebDSLDebugTarget(null, WebDSLDebugServiceFactory.getLanguageID(), this.launch, jvm);
			this.launch.addDebugTarget(webdslTarget);
			// WebDSL debugger can attach to an existing VM that has already been started
			// We need to install the breakpoints.
			// TODO: is this the correct place to call it?
			//webdslTarget.installDeferredBreakpoints();
			webdslTarget.started(); // this will also call installDeferredBreakpoints()
			
//			// RL: experimental, also add a java debug target??
//			monitor.subTask("Attaching to the ActionLanguage VM");
//			LIDebugTarget target = Activator.getDefault().getDebugServiceFactory().createDebugTarget(launch, port);
//			//AlngDebugTarget target = new AlngDebugTarget(launch, port);
//			//(launch,p,requestPort,eventPort );
//			launch.addDebugTarget(target);
//			monitor.worked(1);
//			
//			// JDI debug target requires a vm and process...
//			boolean allowTerminate = false;
//			boolean allowDisconnect = false;
//			boolean resume = false;
			// resume is optional, defaults to true
			//IDebugTarget target = JDIDebugModel.newDebugTarget(launch, jvm, "Java backend for ActionLanguage", null, allowTerminate, allowDisconnect, resume);
//			// newDebugTarget(ILaunch launch, com.sun.jdi.VirtualMachine vm, String name, IProcess process, boolean allowTerminate, boolean allowDisconnect) 
//			// newDebugTarget(ILaunch launch, com.sun.jdi.VirtualMachine vm, String name, IProcess process, boolean allowTerminate, boolean allowDisconnect, boolean resume)
			
		}
	}
	
	public void launchVM(IProgressMonitor monitor, 
			String classname, 
			String[] classpath, 
			ILaunch launch,
			String mode) throws CoreException {
		this.launchVMThenAddDebugTargets(monitor, classname, classpath, launch, mode);
//		boolean s = 1==(2-1);
//		if (s) return;
//		
//		String[] alngProgramArguments = new String[] {};
//		
//		// Initialize the VMRunner
//		IVMInstall defaultInstall = JavaRuntime.getDefaultVMInstall();
//		IVMRunner vmRunner = defaultInstall.getVMRunner(ILaunchManager.RUN_MODE); // always use RUN, so we can control the debug parameters of the VM
//		
//		VMRunnerConfiguration vmRunnerConfiguration = new VMRunnerConfiguration(classname, classpath);
//		vmRunnerConfiguration.setProgramArguments(alngProgramArguments);
//		// TODO: working directory should be the project directory
//		//vmRunnerConfiguration.setWorkingDirectory(path)
//		
//		// the started wm will wait for a debugger to connect to this port
//		String port = ""+LaunchUtil.findFreePort();
//		
//		// if we are in DEBUG_MODE also set the debugging parameters for the VM as we previously created an IVMRunner in RUN_MODE
//		if (mode.equals(ILaunchManager.DEBUG_MODE)) {
//			// socket attach
//			//String[] realVMargs = new String[] { "-Xdebug", "-Xrunjdwp:transport=dt_socket,address="+port+",server=y,suspend=y" };
//			// socket listen, vm will wait untill a debugger is attached
//			String[] realVMargs = new String[] { "-Xdebug", "-Xrunjdwp:transport=dt_socket,address="+port+",suspend=y", "-Xss8m" };
//		//String[] realVMargs = new String[] { "-Xrunjdwp:transport=dt_socket,address=9000,server=y,suspend=y" };
//		//String[] realVMargs = new String[] { "-Xdebug" };
//			vmRunnerConfiguration.setVMArguments(realVMargs);
//		}
//		
//		if (mode.equals(ILaunchManager.DEBUG_MODE)) {
//			monitor.subTask("Attaching to the ActionLanguage VM");
//			LIDebugTarget target = Activator.getDefault().getDebugServiceFactory().createDebugTarget(launch, port);
//			//AlngDebugTarget target = new AlngDebugTarget(launch, port);
//			//(launch,p,requestPort,eventPort );
//			launch.addDebugTarget(target);
//			monitor.worked(1);
//			
//			// RL: experimental, also add a java debug target??
//			// JDI debug target requires a vm and process...
//			// Lets move this to LIDebugTarget
//			//JDIDebugModel.newDebugTarget(launch, vm, name, process, allowTerminate, allowDisconnect)
//			// newDebugTarget(ILaunch launch, com.sun.jdi.VirtualMachine vm, String name, IProcess process, boolean allowTerminate, boolean allowDisconnect) 
//			// newDebugTarget(ILaunch launch, com.sun.jdi.VirtualMachine vm, String name, IProcess process, boolean allowTerminate, boolean allowDisconnect, boolean resume) 
//		}
//		
//		// start the VM with the alng program
//		// using attach, run before the StrategoDebugTarget is initialized
//		// using listen, run after the StrategoDebugTarget is initialized
//		System.out.println("RUN");
//		vmRunner.run(vmRunnerConfiguration, launch, monitor);
//		
//		monitor.worked(1);
	}

	@Override
	public String getLanguageName() {
		return WebDSLConstants.getLanguageID();
	}

	@Override
	public LIConstants getLIConstants() {
		return webdsl.debugger.Activator.getDefault().getDebugServiceFactory().getLIConstants();
	}

	@Override
	public IJavaProgramLaunchPreparation getLaunchPreparation() {
		return new WebDSLLaunchPreparation();
	}

	// RL: We shouldn't start a WebDSL app, we can only connect to an instance
	public String[] getClasspaths(IJavaProgramPrepareResult result) {
		String[] classpath = new String[0];
		
		return classpath;
	}
}
