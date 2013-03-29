package util.webdsl_scripts;

import java.io.File;

import junit.framework.Assert;

import org.junit.Test;
import org.spoofax.interpreter.terms.IStrategoTerm;
import org.strategoxt.HybridInterpreter;

import util.HybridInterpreterHelper;
import util.SELUtil;
import util.WebdslTestConstants;

public class GlobalFunctionsScript extends ParsedProgramBase {

	@Test
	public void testParsing() {
		this.init();
		
		Assert.assertNotNull(this.getApplication());
		Assert.assertEquals("ApplicationDefs", SELUtil.getConstructor(this.getApplication()));
		Assert.assertEquals("Application", SELUtil.getSort(this.getApplication()));
		
		Assert.assertNotNull(this.getResetScheduleFunction());
		Assert.assertEquals("Function", SELUtil.getConstructor(this.getResetScheduleFunction()));
		Assert.assertEquals("Function", SELUtil.getSort(this.getResetScheduleFunction()));
		
		Assert.assertNotNull(this.getResetScheduleBody());
		Assert.assertEquals("Block", SELUtil.getConstructor(this.getResetScheduleBody()));
		Assert.assertEquals("Block", SELUtil.getSort(this.getResetScheduleBody()));
		
		Assert.assertNotNull(this.getValidateEmailFunction());
		Assert.assertEquals("Function", SELUtil.getConstructor(this.getValidateEmailFunction()));
		Assert.assertEquals("Function", SELUtil.getSort(this.getValidateEmailFunction()));
		
		Assert.assertNotNull(this.getValidateEmailBody());
		Assert.assertEquals("Block", SELUtil.getConstructor(this.getValidateEmailBody()));
		Assert.assertEquals("Block", SELUtil.getSort(this.getValidateEmailBody()));
	}
	
	@Override
	public void init() {
		String program_location = WebdslTestConstants.WEBDSL_SCRIPTS_TESTCASES_DIR + "/fragments/functions/global/globalfunctions.app";
		File alngFile = new File(program_location);
		Assert.assertTrue(alngFile.exists());
		
		HybridInterpreter i = HybridInterpreterHelper.createHybridInterpreter();
		
		i.setCurrent(i.getFactory().makeString(program_location));
		boolean b = HybridInterpreterHelper.safeInvoke(i, WebdslTestConstants.PARSE_WEBDSL_FILE);
		i.getCompiledContext().printStackTrace();
		Assert.assertTrue(b);
		this.dslProgramAterm = i.current();
		
		i.uninit();
		i.shutdown();
	}

	// ApplicationDefs
	public IStrategoTerm getApplication() {
		return this.dslProgramAterm; // Application(name, [FunctionNoReturn, Function])
	}

	// FunctionNoReturn(name, args*, body)
	public IStrategoTerm getResetScheduleFunction() {
		IStrategoTerm app = this.getApplication();
		IStrategoTerm defs = app.getSubterm(1);
		return defs.getSubterm(0); // Function
	}
	
	public IStrategoTerm getResetScheduleBody() {
		return getResetScheduleFunction().getSubterm(3); // Block
	}

	// Function(name, args*, return-value, body)
	public IStrategoTerm getValidateEmailFunction() {
		IStrategoTerm app = this.getApplication();
		IStrategoTerm defs = app.getSubterm(1);
		return defs.getSubterm(1); // Function
	}
	
	public IStrategoTerm getValidateEmailBody() {
		return getValidateEmailFunction().getSubterm(3); // Block
	}

}
