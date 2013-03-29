package webdsl.debugger.instrumentation.generate;

import junit.framework.Assert;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.spoofax.interpreter.terms.IStrategoTerm;

import util.DebugInfoBuilder;
import util.HybridInterpreterHelper;
import util.WebdslTestConstants;
import util.webdsl_scripts.GlobalFunctionsScript;

public class TryGenerateEnterInGlobalFunctions extends TryGenerateBase {

	@Before
	public void setupProgram() {
		// init the HybridInterpreter
		i = HybridInterpreterHelper.createHybridInterpreter();

		String program_location = WebdslTestConstants.WEBDSL_SCRIPTS_TESTCASES_DIR + "/fragments/functions/global/globalfunctions.app";
		String dsldi_location = WebdslTestConstants.WEBDSL_SCRIPTS_DIR + "/dsldis/EnterFunction.dsldi";
		TryGenerateBase.setupHI(i, program_location, dsldi_location);
	}

	@Test
	public void testFunction() {
		// all generate strategies require that GET-CURRENT-DEBUG-INFORMATION is set
		DebugInfoBuilder builder = new DebugInfoBuilder();
		builder.setFilename("webdsl_scripts/testcases/fragments/functions/global/globalfunctions.app");
		builder.setLocation(10, 4, 12, 1);
		builder.setData("validateEmail");
		this.initCurrentDebugInformation(builder);

		boolean b = false;
		
		b = HybridInterpreterHelper.safeInvoke(i, "GET-DSL-PROGRAM-SOURCE");
		Assert.assertTrue(b);
		GlobalFunctionsScript program = new GlobalFunctionsScript();
		program.init(i.current());

		IStrategoTerm before = program.getValidateEmailFunction();
		callTryGenerate(WebdslTestConstants.GEN_FUNCTION_ENTER, before);
		
		// instrumented version
		GlobalFunctionsScript instrumentedProgram = new GlobalFunctionsScript();
		String instrumented_program_location = WebdslTestConstants.WEBDSL_SCRIPTS_INSTRUMENTED_DIR + "/" + WebdslTestConstants.WEBDSL_SCRIPTS_TESTCASES_DIR + "/fragments/functions/global/globalfunctions.app.enter.app";
		instrumentedProgram.initFromFile(instrumented_program_location, WebdslTestConstants.PARSE_WEBDSL_FILE);
		String part = instrumentedProgram.getApplication().toString();
		part = instrumentedProgram.getApplication().getSubterm(1).toString();
		part = instrumentedProgram.getApplication().getSubterm(1).getSubterm(2).toString();
		String current = i.current().toString();
		Assert.assertEquals(part, current);
	}
	
	@Test
	public void testFunctionNoReturn() {
		// all generate strategies require that GET-CURRENT-DEBUG-INFORMATION is set
		DebugInfoBuilder builder = new DebugInfoBuilder();
		builder.setFilename("webdsl_scripts/testcases/fragments/functions/global/globalfunctions.app");
		builder.setLocation(3, 1, 8, 1);
		builder.setData("resetSchedule");
		this.initCurrentDebugInformation(builder);
		
		boolean b = false;
		
		b = HybridInterpreterHelper.safeInvoke(i, "GET-DSL-PROGRAM-SOURCE");
		Assert.assertTrue(b);
		GlobalFunctionsScript program = new GlobalFunctionsScript();
		program.init(i.current());
		
		callTryGenerate(WebdslTestConstants.GEN_FUNCTION_ENTER, program.getResetScheduleFunction());
		
		// instrumented version
		GlobalFunctionsScript instrumentedProgram = new GlobalFunctionsScript();
		String instrumented_program_location = WebdslTestConstants.WEBDSL_SCRIPTS_INSTRUMENTED_DIR + "/" + WebdslTestConstants.WEBDSL_SCRIPTS_TESTCASES_DIR + "/fragments/functions/global/globalfunctions.app.enter.app";
		instrumentedProgram.initFromFile(instrumented_program_location, WebdslTestConstants.PARSE_WEBDSL_FILE);

		Assert.assertEquals(instrumentedProgram.getApplication().getSubterm(1).getSubterm(1).toString(), i.current().toString());
	}

	
	@After
	public void tearDownInterpreter() {
		i.uninit();
		i.shutdown();
	}
}
