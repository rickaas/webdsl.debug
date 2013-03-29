package webdsl.debugger.launching;

import org.eclipse.core.runtime.IPath;
import org.spoofax.debug.core.control.launching.IJavaProgramLaunchPreparation;
import org.spoofax.debug.core.control.launching.IJavaProgramPrepareResult;
import org.spoofax.debug.core.control.launching.LaunchPreparationException;

public class WebDSLLaunchPreparation implements IJavaProgramLaunchPreparation {
	
	public IJavaProgramPrepareResult prepare(String projectName, IPath alngProgramPath, String mode) throws LaunchPreparationException {
		IJavaProgramPrepareResult result = null;
//		LaunchProgram l = new LaunchProgram(new WebDSLHybridInterpreterProvider());
//		
//		final CompilationResultWrapper compilationResult;
//		
//		if (mode.equals(ILaunchManager.DEBUG_MODE)) {
//			try {
//				compilationResult = l.compileForDebug(alngProgramPath.toOSString());
//			} catch (InterpreterErrorExit e) {
//				throw new LaunchPreparationException("Compiling for debug failed", e);
//			} catch (InterpreterExit e) {
//				throw new LaunchPreparationException("Compiling for debug failed", e);
//			} catch (UndefinedStrategyException e) {
//				throw new LaunchPreparationException("Compiling for debug failed", e);
//			} catch (InterpreterException e) {
//				throw new LaunchPreparationException("Compiling for debug failed", e);
//			}
//		} else if (mode.equals(ILaunchManager.RUN_MODE)) {
//			try {
//				compilationResult = l.compileForRun(alngProgramPath.toOSString());
//
//			} catch (InterpreterErrorExit e) {
//				throw new LaunchPreparationException("Compiling for run failed", e);
//			} catch (InterpreterExit e) {
//				throw new LaunchPreparationException("Compiling for run failed", e);
//			} catch (UndefinedStrategyException e) {
//				throw new LaunchPreparationException("Compiling for run failed", e);
//			} catch (InterpreterException e) {
//				throw new LaunchPreparationException("Compiling for run failed", e);
//			}
//		} else {
//			compilationResult = null;
//		}
//		
//		if (compilationResult == null) throw new LaunchPreparationException("No compilation result returned while preparing program for launch.");
//		
//		result = new IJavaProgramPrepareResult() {
//			
//			@Override
//			public String getClassname() {
//				return compilationResult.getClassname();
//			}
//			
//			@Override
//			public String getBinDirectory() {
//				return compilationResult.getBinDirectory();
//			}
//		};
		
		return result;
	}
}
