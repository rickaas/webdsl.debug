package util;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import junit.framework.Assert;

import org.spoofax.interpreter.core.InterpreterErrorExit;
import org.spoofax.interpreter.core.InterpreterException;
import org.spoofax.interpreter.core.InterpreterExit;
import org.spoofax.interpreter.core.UndefinedStrategyException;
import org.strategoxt.HybridInterpreter;
import org.strategoxt.IncompatibleJarException;
import org.strategoxt.NoInteropRegistererJarException;

public class HybridInterpreterHelper {

	
	/**
	 * Create a new HybridInterpreter with webdsl.jar, acoda.jar, webdsl-editor,jar, webdsl-di.jar and libdsldi.jar loaded.
	 * @return
	 */
	public static HybridInterpreter createHybridInterpreter() {
		// init the Context
		//org.strategoxt.lang.Context context = trans.Main.init(); // not needed
		HybridInterpreter i = new HybridInterpreter();
		i.init();
		// Load the Jars.

		// language independent instrumentation
		safeLoadJar(i, WebdslTestConstants.LIBDSLDI_JAR);
		// also load dsldi-java because it contains strategies implemented in java
		// TODO: move the java strategies from dsldi-java.jar to lidsldi-java.jar
		safeLoadJar(i, WebdslTestConstants.DSLDI_JAVA_JAR);
		// language specific strategies
		safeLoadJar(i, WebdslTestConstants.ACODA_JAR);
		safeLoadJar(i, WebdslTestConstants.WEBDSL_JAR);
		safeLoadJar(i, WebdslTestConstants.WEBDSL_EDITOR_JAR);
		safeLoadJar(i, WebdslTestConstants.WEBDSL_DI_JAR);
		return i;
	}
	
	public static HybridInterpreter loadWebDSLCompiler() {
		// init the Context
		//org.strategoxt.lang.Context context = trans.Main.init(); // not needed
		HybridInterpreter i = new HybridInterpreter();
		i.init();
		// Load the Jars.

		safeLoadJar(i, WebdslTestConstants.WEBDSL_JAR);
		safeLoadJar(i, WebdslTestConstants.WEBDSL_EDITOR_JAR);
		return i;
	}
	
	public static void safeLoadJar(HybridInterpreter i, String location)
	{
		File jarLocation = new File(location);
		try {
			safeLoadJar(i, jarLocation.toURI().toURL());
		} catch (MalformedURLException e) {
			e.printStackTrace();
			Assert.fail();
		}
	}
	
	public static void safeLoadJar(HybridInterpreter i, URL jar)
	{
		try {
			i.loadJars(jar);
		} catch (SecurityException e) {
			e.printStackTrace();
			Assert.fail();
		} catch (NoInteropRegistererJarException e) {
			e.printStackTrace();
			Assert.fail();
		} catch (MalformedURLException e) {
			e.printStackTrace();
			Assert.fail();
		} catch (IncompatibleJarException e) {
			e.printStackTrace();
			Assert.fail();
		} catch (IOException e) {
			e.printStackTrace();
			Assert.fail();
		}
	}
	
	/**
	 * Invoke the strategy in the HybridInterpreter.
	 * The strategy name does not need escaping.
	 * 
	 * When invoking fails, do a Assert.fail().
	 * @param i
	 * @param strategy
	 * @return
	 */
	public static boolean safeInvoke(HybridInterpreter i, String strategy)
	{
		try {
			boolean b = i.invoke(strategy);
			return b;
		} catch (InterpreterErrorExit e) {
			e.printStackTrace();
			Assert.fail();
		} catch (InterpreterExit e) {
			e.printStackTrace();
			Assert.fail();
		} catch (UndefinedStrategyException e) {
			e.printStackTrace();
			Assert.fail();
		} catch (InterpreterException e) {
			e.printStackTrace();
			Assert.fail();
		}
		return false;
	}
	
//	public static boolean safeInvoke(HybridInterpreter i, String strategy)
//	{
//		i.getContext().getStrategoSignature()
//		i.lookupUncifiedSVar(strategy);
//	}
}
