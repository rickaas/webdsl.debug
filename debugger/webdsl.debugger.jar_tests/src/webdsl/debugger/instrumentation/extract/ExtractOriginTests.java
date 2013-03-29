package webdsl.debugger.instrumentation.extract;

import java.io.File;

import junit.framework.Assert;

import org.junit.Test;
import org.spoofax.interpreter.terms.IStrategoTerm;
import org.strategoxt.HybridInterpreter;

import util.HybridInterpreterHelper;
import util.WebdslTestConstants;
import util.webdsl_scripts.GlobalFunctionsScript;

public class ExtractOriginTests {

	@Test
	public void extractOriginFromLocalvar() {
		HybridInterpreter i = HybridInterpreterHelper.createHybridInterpreter();
		
		String program_location = WebdslTestConstants.WEBDSL_SCRIPTS_TESTCASES_DIR + "/fragments/functions/global/globalfunctions.app";
		File file = new File(program_location);
		Assert.assertTrue(file.exists());
		
		i.setCurrent(i.getFactory().makeString(program_location));
		boolean b = HybridInterpreterHelper.safeInvoke(i, WebdslTestConstants.PARSE_WEBDSL_FILE);
		Assert.assertTrue(b);
		IStrategoTerm dslProgramAterm = i.current();
		
		b = HybridInterpreterHelper.safeInvoke(i, "origin-location");
		Assert.assertTrue(b);
		Assert.assertNotNull(i.current());
		Assert.assertEquals("(1,0,14,20)", i.current().toString());
		
		GlobalFunctionsScript script = new GlobalFunctionsScript();
		script.init(dslProgramAterm);
		
		i.setCurrent(script.getApplication());
		b = HybridInterpreterHelper.safeInvoke(i, "origin-location");
		Assert.assertTrue(b);
		Assert.assertNotNull(i.current());
		Assert.assertEquals("(1,0,14,20)", i.current().toString());
		
		i.setCurrent(script.getResetScheduleFunction());
		b = HybridInterpreterHelper.safeInvoke(i, "origin-location");
		Assert.assertTrue(b);
		Assert.assertNotNull(i.current());
		Assert.assertEquals("(3,1,8,1)", i.current().toString());
		
		i.setCurrent(script.getResetScheduleBody());
		b = HybridInterpreterHelper.safeInvoke(i, "origin-location");
		Assert.assertTrue(b);
		Assert.assertNotNull(i.current());
		Assert.assertEquals("(3,25,8,1)", i.current().toString());
		
		i.setCurrent(script.getValidateEmailFunction());
		b = HybridInterpreterHelper.safeInvoke(i, "origin-location");
		Assert.assertTrue(b);
		Assert.assertNotNull(i.current());
		Assert.assertEquals("(10,4,12,1)", i.current().toString());
		
		i.setCurrent(script.getValidateEmailBody());
		b = HybridInterpreterHelper.safeInvoke(i, "origin-location");
		Assert.assertTrue(b);
		Assert.assertNotNull(i.current());
		Assert.assertEquals("(10,49,12,1)", i.current().toString());
		
		i.uninit();
		i.shutdown();
	}
	
	@Test
	public void extractOriginFromTermLoadedByOtherHybridInterpreter() {
		GlobalFunctionsScript script = new GlobalFunctionsScript();
		script.init();
		
		HybridInterpreter i = HybridInterpreterHelper.createHybridInterpreter();
		boolean b = true;
		
		i.setCurrent(script.getApplication());
		b = HybridInterpreterHelper.safeInvoke(i, "origin-location");
		Assert.assertTrue(b);
		Assert.assertNotNull(i.current());
		Assert.assertEquals("(1,0,14,20)", i.current().toString());
		
		i.setCurrent(script.getResetScheduleFunction());
		b = HybridInterpreterHelper.safeInvoke(i, "origin-location");
		Assert.assertTrue(b);
		Assert.assertNotNull(i.current());
		Assert.assertEquals("(3,1,8,1)", i.current().toString());
		
		i.setCurrent(script.getResetScheduleBody());
		b = HybridInterpreterHelper.safeInvoke(i, "origin-location");
		Assert.assertTrue(b);
		Assert.assertNotNull(i.current());
		Assert.assertEquals("(3,25,8,1)", i.current().toString());
		
		i.setCurrent(script.getValidateEmailFunction());
		b = HybridInterpreterHelper.safeInvoke(i, "origin-location");
		Assert.assertTrue(b);
		Assert.assertNotNull(i.current());
		Assert.assertEquals("(10,4,12,1)", i.current().toString());
		
		i.setCurrent(script.getValidateEmailBody());
		b = HybridInterpreterHelper.safeInvoke(i, "origin-location");
		Assert.assertTrue(b);
		Assert.assertNotNull(i.current());
		Assert.assertEquals("(10,49,12,1)", i.current().toString());
		
		i.uninit();
		i.shutdown();
	}
}
