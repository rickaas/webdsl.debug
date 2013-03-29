package webdsl.debugger.instrumentation.extract;

import junit.framework.Assert;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import util.DebugInfoBuilder;
import util.HybridInterpreterHelper;
import util.WebdslTestConstants;
import util.webdsl_scripts.GlobalFunctionsScript;

public class TryExtractFromGlobalFunctions extends TryExtractBase {

	@Before
	public void setupProgram() {
		// init the HybridInterpreter
		i = HybridInterpreterHelper.createHybridInterpreter();
	}
	
	@Test
	public void testFunction() {
		String program_location = WebdslTestConstants.WEBDSL_SCRIPTS_TESTCASES_DIR + "/fragments/functions/global/globalfunctions.app";
		String dsldi_location = WebdslTestConstants.WEBDSL_SCRIPTS_DIR + "/dsldis/EnterFunction.dsldi";
		TryExtractBase.setupHI(i, program_location, dsldi_location);
		
		boolean b = false;
		
		b = HybridInterpreterHelper.safeInvoke(i, "GET-DSL-PROGRAM-SOURCE");
		i.getCompiledContext().printStackTrace();
		Assert.assertTrue(b);
		
		// parse justprint.alng and get the FunctionDeclVoid aterm
		GlobalFunctionsScript program = new GlobalFunctionsScript();
		program.init(i.current());
		
		System.out.println(program.getValidateEmailFunction().toString());
		callTryExtract(WebdslTestConstants.EXTRACT_FUNCTION_INFO, program.getValidateEmailFunction());
		
		// GET-CURRENT-DEBUG-INFORMATION should contain the debug information
		b = HybridInterpreterHelper.safeInvoke(i, "GET-CURRENT-DEBUG-INFORMATION");
		Assert.assertTrue(b);
		
		DebugInfoBuilder builder = new DebugInfoBuilder();
		builder.setFilename("webdsl_scripts/testcases/fragments/functions/global/globalfunctions.app");
		builder.setLocation(10, 4, 12, 1);
		builder.setData("validateEmail");
		Assert.assertEquals(builder.makeEnter(i.getFactory()).toString(), i.current().toString());
	}
	
	@Test
	public void testFunctionNoReturn() {
		String program_location = WebdslTestConstants.WEBDSL_SCRIPTS_TESTCASES_DIR + "/fragments/functions/global/globalfunctions.app";
		String dsldi_location = WebdslTestConstants.WEBDSL_SCRIPTS_DIR + "/dsldis/EnterFunction.dsldi";
		TryExtractBase.setupHI(i, program_location, dsldi_location);
		
		boolean b = false;
		
		b = HybridInterpreterHelper.safeInvoke(i, "GET-DSL-PROGRAM-SOURCE");
		i.getCompiledContext().printStackTrace();
		Assert.assertTrue(b);
		
		// parse justprint.alng and get the FunctionDeclVoid aterm
		GlobalFunctionsScript program = new GlobalFunctionsScript();
		program.init(i.current());
		
		callTryExtract(WebdslTestConstants.EXTRACT_FUNCTION_INFO, program.getResetScheduleFunction());
		
		// GET-CURRENT-DEBUG-INFORMATION should contain the debug information
		b = HybridInterpreterHelper.safeInvoke(i, "GET-CURRENT-DEBUG-INFORMATION");
		Assert.assertTrue(b);
		
		DebugInfoBuilder builder = new DebugInfoBuilder();
		builder.setFilename("webdsl_scripts/testcases/fragments/functions/global/globalfunctions.app");
		builder.setLocation(3, 1, 8, 1);
		builder.setData("resetSchedule");
		Assert.assertEquals(builder.makeEnter(i.getFactory()).toString(), i.current().toString());
	}
	
//	@Test
//	public void testRDefT() {
//		String program_location = StrTestConstants.STR_SCRIPTS_TESTCASES_DIR + "/fragments/fargs/ruledef.str";
//		String dsldi_location = StrTestConstants.STR_SCRIPTS_DIR + "/dsldis/EnterRule.dsldi";
//		TryExtractBase.setupHI(i, program_location, dsldi_location);
//		
//		boolean b = false;
//		
//		b = HybridInterpreterHelper.safeInvoke(i, "GET-DSL-PROGRAM-SOURCE");
//		i.getCompiledContext().printStackTrace();
//		Assert.assertTrue(b);
//		
//		// parse justprint.alng and get the FunctionDeclVoid aterm
//		RuledefScript program = new RuledefScript();
//		program.init(i.current());
//		
//		callTryExtract(StrTestConstants.EXTRACT_RULE_INFO, program.getRuleRDefT());
//		
//		// GET-CURRENT-DEBUG-INFORMATION should contain the debug information
//		b = HybridInterpreterHelper.safeInvoke(i, "GET-CURRENT-DEBUG-INFORMATION");
//		Assert.assertTrue(b);
//		
//		DebugInfoBuilder builder = new DebugInfoBuilder();
//		builder.setFilename("str_scripts/testcases/fragments/fargs/ruledef.str");
//		builder.setLocation(15, 2, 18, 20);
//		builder.setData("main");
//		Assert.assertEquals(builder.makeEnter(i.getFactory()).toString(), i.current().toString());
//	}
	
	@After
	public void tearDownInterpreter() {
		i.uninit();
		i.shutdown();
	}
}
