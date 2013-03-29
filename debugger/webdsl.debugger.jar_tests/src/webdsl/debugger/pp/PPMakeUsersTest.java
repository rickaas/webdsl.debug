package webdsl.debugger.pp;

import static org.junit.Assert.*;
import junit.framework.Assert;

import org.junit.After;
import org.junit.Test;

import util.HybridInterpreterHelper;
import util.WebdslTestConstants;
import webdsl.debugger.instrumentation.events.EventInstrumentationBase;

public class PPMakeUsersTest extends EventInstrumentationBase {

	@Test
	public void ppMakeUsersWithCompiler() throws Exception {
		// init the HybridInterpreter
		i = HybridInterpreterHelper.loadWebDSLCompiler();

		boolean b;
		
		// parse make-users.app and to AST
		String program_location = WebdslTestConstants.WEBDSL_SCRIPTS_TESTCASES_DIR + "/programs/performance01/make-users.app";
		i.setCurrent(i.getFactory().makeString(program_location));
		b = HybridInterpreterHelper.safeInvoke(i, WebdslTestConstants.PARSE_WEBDSL_FILE);
		Assert.assertTrue(b);
		
		System.out.println(i.current());
		
		b = HybridInterpreterHelper.safeInvoke(i, "pp-webdsl-to-string");
		Assert.assertTrue(b);
		
		System.out.println(i.current());
		
		// call pretty-printer on AST and write result to generated
		// stratego signature: write-webdsl-as-dsl = ?(output-filename, dsl-ast)
		String base = WebdslTestConstants.WEBDSL_SCRIPTS_GENERATED_DIR;
	}

	@Test
	public void ppMakeUsersWithInstrumentation() throws Exception {
		this.initHI(); // load all jars
		boolean b;
		
		// parse make-users.app and to AST
		String program_location = WebdslTestConstants.WEBDSL_SCRIPTS_TESTCASES_DIR + "/programs/performance01/make-users.app";
		i.setCurrent(i.getFactory().makeString(program_location));
		b = HybridInterpreterHelper.safeInvoke(i, WebdslTestConstants.PARSE_WEBDSL_FILE);
		Assert.assertTrue(b);
		
		System.out.println(i.current());
		
		b = HybridInterpreterHelper.safeInvoke(i, "pp-webdsl-to-string");
		Assert.assertTrue(b);
		
		System.out.println(i.current());
		
		// call pretty-printer on AST and write result to generated
		// stratego signature: write-webdsl-as-dsl = ?(output-filename, dsl-ast)
		String base = WebdslTestConstants.WEBDSL_SCRIPTS_GENERATED_DIR;
	}
	
	@Test
	public void ppACSnippets() throws Exception {
		this.initHI(); // load all jars
		boolean b;
		
		// parse make-users.app and to AST
		String program_location = WebdslTestConstants.WEBDSL_SCRIPTS_TESTCASES_DIR + "/fragments/accesscontrol/ac-snippet.app";
		i.setCurrent(i.getFactory().makeString(program_location));
		b = HybridInterpreterHelper.safeInvoke(i, WebdslTestConstants.PARSE_WEBDSL_FILE);
		Assert.assertTrue(b);
		
		System.out.println(i.current());
		
		b = HybridInterpreterHelper.safeInvoke(i, "pp-webdsl-to-string");
		Assert.assertTrue(b);
		
		System.out.println(i.current());
		
		// call pretty-printer on AST and write result to generated
		// stratego signature: write-webdsl-as-dsl = ?(output-filename, dsl-ast)
		String base = WebdslTestConstants.WEBDSL_SCRIPTS_GENERATED_DIR;
	}
	
	@Test
	public void ppParenthesisSnippet1() {
		this.initHI(); // load all jars
		boolean b;
		
		// parse make-users.app and to AST
		String program_location = WebdslTestConstants.WEBDSL_SCRIPTS_TESTCASES_DIR + "/fragments/pp-examples/parenthesis-snippet1.app";
		i.setCurrent(i.getFactory().makeString(program_location));
		b = HybridInterpreterHelper.safeInvoke(i, WebdslTestConstants.PARSE_WEBDSL_FILE);
		Assert.assertTrue(b);
		
		System.out.println(i.current());
		b = HybridInterpreterHelper.safeInvoke(i, "parenthesize-WebDSL");
		Assert.assertTrue(b);
		System.out.println(i.current());
		b = HybridInterpreterHelper.safeInvoke(i, "pp-webdsl-to-string");
		Assert.assertTrue(b);
		
		System.out.println(i.current());
		
		// call pretty-printer on AST and write result to generated
		// stratego signature: write-webdsl-as-dsl = ?(output-filename, dsl-ast)
		String base = WebdslTestConstants.WEBDSL_SCRIPTS_GENERATED_DIR;
	}
	
	@Test
	public void ppParenthesisSnippet2() {
		this.initHI(); // load all jars
		boolean b;
		
		// parse make-users.app and to AST
		String program_location = WebdslTestConstants.WEBDSL_SCRIPTS_TESTCASES_DIR + "/fragments/pp-examples/parenthesis-snippet2.app";
		i.setCurrent(i.getFactory().makeString(program_location));
		b = HybridInterpreterHelper.safeInvoke(i, WebdslTestConstants.PARSE_WEBDSL_FILE);
		Assert.assertTrue(b);
		
		System.out.println(i.current());
		b = HybridInterpreterHelper.safeInvoke(i, "parenthesize-WebDSL");
		Assert.assertTrue(b);
		System.out.println(i.current());
		b = HybridInterpreterHelper.safeInvoke(i, "pp-webdsl-to-string");
		Assert.assertTrue(b);
		
		System.out.println(i.current());
		
		// call pretty-printer on AST and write result to generated
		// stratego signature: write-webdsl-as-dsl = ?(output-filename, dsl-ast)
		String base = WebdslTestConstants.WEBDSL_SCRIPTS_GENERATED_DIR;
	}
	
	@After
	public void tearDownInterpreter() {
		i.uninit();
		i.shutdown();
	}

}
