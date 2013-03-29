package webdsl.debugger.instrumentation.events;

import java.io.File;
import java.io.IOException;

import junit.framework.Assert;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.spoofax.interpreter.terms.IStrategoTerm;
import org.spoofax.terms.ParseError;

import util.HybridInterpreterHelper;
import util.WebdslTestConstants;

public class FunctionStepTests extends EventInstrumentationBase {
	private String sourceDSLLocation = null;
	
	private String getGeneratedLocation() {
		return WebdslTestConstants.WEBDSL_SCRIPTS_GENERATED_DIR +"/" + sourceDSLLocation;
	}
	private String getInstrumentedLocation() {
		return WebdslTestConstants.WEBDSL_SCRIPTS_INSTRUMENTED_DIR +"/" + sourceDSLLocation;
	}
	
	private void cleanupGenerated() {
		File f = new File(getGeneratedLocation());
		if (f.exists()) {
			Assert.assertTrue(f.delete());
		}
		Assert.assertFalse(f.exists());
	}
	
	
	@Before
	public void setupProgram() throws ParseError, IOException {
		initHI();
		loadDSLDI(WebdslTestConstants.WEBDSL_SCRIPTS_DIR + "/dsldis/FunctionStep.dsldi");

		// register extract and generate strategies:
		registerExtractFunction("extract-step-info");
		registerGenerateFunction("gen-function-step");

		this.setOutputBasePath(WebdslTestConstants.WEBDSL_SCRIPTS_GENERATED_DIR);
		this.setLanguageID("Webdsl");
		
		this.setPostInstrumentation("webdsl-post-instrumentation");
		//this.addWriteFile("ActionLanguage", "write-actionlanguage-to-file");
		this.addWriteFile("Webdsl", "write-webdsl-as-dsl"); // RL: this is the correct one
		//this.addWriteFile("ActionLanguage", "write-actionlanguage-as-ast");
	}
	
	@Test
	public void instrumentGlobalScript() {
		sourceDSLLocation = WebdslTestConstants.WEBDSL_SCRIPTS_TESTCASES_DIR + "/fragments/functions/global/globalfunctions.app";
		cleanupGenerated();
		
		loadDSLProgram(sourceDSLLocation);
		// try-instrument = ?(dsl-program-filename, dsl-program-aterm)
		i.setCurrent(this.getFilenameDSLProgramTuple());
		i.getCompiledContext().printStackTrace();
		Assert.assertTrue(HybridInterpreterHelper.safeInvoke(i, "try-instrument"));
		
		System.out.println(i.current().toString());
		IStrategoTerm succesReport = makeSuccess(this.sourceDSLLocation, this.getGeneratedLocation());
		Assert.assertEquals(succesReport.toString(), i.current().toString());
		
		Assert.assertTrue(new File(this.getGeneratedLocation()).exists());
		compareDslProgramSourceWithReferenceFile(this.getInstrumentedLocation() + ".step.app");
	}
	
	@Test
	public void instrumentEntityScript() {
		sourceDSLLocation = WebdslTestConstants.WEBDSL_SCRIPTS_TESTCASES_DIR + "/fragments/functions/entity/entityfunctions.app";
		cleanupGenerated();
		
		loadDSLProgram(sourceDSLLocation);
		// try-instrument = ?(dsl-program-filename, dsl-program-aterm)
		i.setCurrent(this.getFilenameDSLProgramTuple());
		i.getCompiledContext().printStackTrace();
		Assert.assertTrue(HybridInterpreterHelper.safeInvoke(i, "try-instrument"));
		
		System.out.println(i.current().toString());
		IStrategoTerm succesReport = makeSuccess(this.sourceDSLLocation, this.getGeneratedLocation());
		Assert.assertEquals(succesReport.toString(), i.current().toString());
		
		Assert.assertTrue(new File(this.getGeneratedLocation()).exists());
		compareDslProgramSourceWithReferenceFile(this.getInstrumentedLocation() + ".step.app");
	}
	
	@After
	public void tearDownInterpreter() {
		i.uninit();
		i.shutdown();
	}
}
