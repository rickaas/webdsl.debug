package webdsl.debugger.instrumentation.programs;

import java.io.File;

import org.junit.Before;

import junit.framework.Assert;

import util.WebdslTestConstants;
import webdsl.debugger.instrumentation.events.EventInstrumentationBase;

public abstract class ProgramInstrumentBase extends EventInstrumentationBase {
	
	protected String sourceDSLLocation = null;
	
	protected String getGeneratedLocation() {
		return WebdslTestConstants.WEBDSL_SCRIPTS_GENERATED_DIR +"/" + sourceDSLLocation;
	}
	
	protected void cleanupGenerated() {
		File f = new File(getGeneratedLocation());
		if (f.exists())
		{
			Assert.assertTrue(f.delete());
		}
		Assert.assertFalse(f.exists());
	}
	
	
	@Before
	public void setup() {
		initHI();
		loadDSLDI(WebdslTestConstants.WEBDSL_SCRIPTS_DIR + "/dsldis/FunctionStepEnterExit.dsldi");

		// register extract and generate strategies:
		registerExtractFunction("extract-step-info");
		registerGenerateFunction("gen-function-step");

		registerExtractFunction("extract-function-info");
		registerGenerateFunction("gen-function-enter");
		registerGenerateFunction("gen-function-exit");

		
		this.setOutputBasePath(WebdslTestConstants.WEBDSL_SCRIPTS_GENERATED_DIR);
		this.setLanguageID("Webdsl");
		
		this.setPostInstrumentation("webdsl-post-instrumentation");
		this.addWriteFile("Webdsl", "write-webdsl-as-dsl");

	}
}
