package util.webdsl_scripts;

import java.io.File;

import junit.framework.Assert;

import org.junit.Test;
import org.spoofax.interpreter.terms.IStrategoTerm;
import org.strategoxt.HybridInterpreter;

import util.HybridInterpreterHelper;
import util.SELUtil;
import util.WebdslTestConstants;

public class EntityFunctionsScripts extends ParsedProgramBase {

	@Test
	public void testParsing() {
		this.init();
		
		Assert.assertNotNull(this.getApplication());
		Assert.assertEquals("ApplicationDefs", SELUtil.getConstructor(this.getApplication()));
		Assert.assertEquals("Application", SELUtil.getSort(this.getApplication()));
		
		Assert.assertNotNull(this.getEntity());
		Assert.assertEquals("EntityNoSuper", SELUtil.getConstructor(this.getEntity()));
		Assert.assertEquals("Entity", SELUtil.getSort(this.getEntity()));
		
		Assert.assertNotNull(this.getEntityBody());
		Assert.assertEquals(null, SELUtil.getConstructor(this.getEntityBody()));
		Assert.assertEquals("EntityBodyDeclaration*", SELUtil.getSort(this.getEntityBody()));
		
		Assert.assertNotNull(this.getNewHourFunction());
		Assert.assertEquals("Function", SELUtil.getConstructor(this.getNewHourFunction()));
		Assert.assertEquals("Function", SELUtil.getSort(this.getNewHourFunction()));
		
		Assert.assertNotNull(this.getNewHourFunctionBody());
		Assert.assertEquals("Block", SELUtil.getConstructor(this.getNewHourFunctionBody()));
		Assert.assertEquals("Block", SELUtil.getSort(this.getNewHourFunctionBody()));
		
		Assert.assertNotNull(this.getShiftByHoursFunction());
		Assert.assertEquals("Function", SELUtil.getConstructor(this.getShiftByHoursFunction()));
		Assert.assertEquals("Function", SELUtil.getSort(this.getShiftByHoursFunction()));
		
		Assert.assertNotNull(this.getShiftByHoursFunctionBody());
		Assert.assertEquals("Block", SELUtil.getConstructor(this.getShiftByHoursFunctionBody()));
		Assert.assertEquals("Block", SELUtil.getSort(this.getShiftByHoursFunctionBody()));
		
		Assert.assertEquals(1, this.getShiftByHoursFunctionBody().getSubtermCount()); //subterm is an array
		Assert.assertEquals(3, this.getShiftByHoursFunctionBody().getSubterm(0).getSubtermCount());
		
		// statements in shiftByHours
		{
			IStrategoTerm stat1 = this.getShiftByHoursFunctionBody().getSubterm(0).getSubterm(0);
			Assert.assertEquals("Stat", SELUtil.getConstructor(stat1));
			Assert.assertEquals("Statement", SELUtil.getSort(stat1));
			
			IStrategoTerm stat2 = this.getShiftByHoursFunctionBody().getSubterm(0).getSubterm(1);
			Assert.assertEquals("Stat", SELUtil.getConstructor(stat2));
			Assert.assertEquals("Statement", SELUtil.getSort(stat2));
			
			IStrategoTerm stat3 = this.getShiftByHoursFunctionBody().getSubterm(0).getSubterm(2);
			Assert.assertEquals("Return", SELUtil.getConstructor(stat3));
			Assert.assertEquals("Statement", SELUtil.getSort(stat3));
		}
		
		// newHour
//		tryNewWeek();
//  		var execute := false;
//  		hourCounter := hourCounter + 1;
//  		if (hourCounter >= intervalInHours) { 
//  			hourCounter := 0; execute := true; 
//  		}
		Assert.assertEquals(4, this.getNewHourFunctionBody().getSubterm(0).getSubtermCount());
		{
				IStrategoTerm stat1 = this.getNewHourFunctionBody().getSubterm(0).getSubterm(0);
				Assert.assertEquals("Stat", SELUtil.getConstructor(stat1));
				Assert.assertEquals("Statement", SELUtil.getSort(stat1));
				
				IStrategoTerm stat2 = this.getNewHourFunctionBody().getSubterm(0).getSubterm(1);
				Assert.assertEquals("VarDeclInitInferred", SELUtil.getConstructor(stat2));
				Assert.assertEquals("VarDeclStat", SELUtil.getSort(stat2));
				
				IStrategoTerm stat3 = this.getNewHourFunctionBody().getSubterm(0).getSubterm(2);
				Assert.assertEquals("Stat", SELUtil.getConstructor(stat3));
				Assert.assertEquals("Statement", SELUtil.getSort(stat3));
				
				IStrategoTerm stat4 = this.getNewHourFunctionBody().getSubterm(0).getSubterm(3);
				Assert.assertEquals("IfNoElse", SELUtil.getConstructor(stat4));
				Assert.assertEquals("Statement", SELUtil.getSort(stat4));
		}
	}
	
	@Override
	public void init() {
		String program_location = WebdslTestConstants.WEBDSL_SCRIPTS_TESTCASES_DIR + "/fragments/functions/entity/entityfunctions.app";
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
	
	// EntityNoSuper(name, [Property, Property, Property, PropertyNoAnno, FunctionNoReturn, FunctionNoReturn, FunctionNoReturn])
	public IStrategoTerm getEntity() {
		IStrategoTerm app = this.getApplication();
		IStrategoTerm defs = app.getSubterm(1);
		return defs.getSubterm(0); // EntityNoSuper
	}
	
	public IStrategoTerm getEntityBody() {
		IStrategoTerm entity = this.getEntity();
		IStrategoTerm body = entity.getSubterm(1);
		return body;
	}
	
	// Function("newHour",[],SimpleSort("Void"),Block([Stat(ThisCall("tryNewWeek",[])),VarDeclInitInferred("execute",False),Stat(Assignment(Var("hourCounter"),Add(Var("hourCounter"),Int("1")))),IfNoElse(LargerThanOrEqual(Var("hourCounter"),Var("intervalInHours")),Block([Stat(Assignment(Var("hourCounter"),Int("0"))),Stat(Assignment(Var("execute"),True))]))]))
	public IStrategoTerm getNewHourFunction() {
		IStrategoTerm func = getEntityBody().getSubterm(4);
		return func;
	}
	
	public IStrategoTerm getNewHourFunctionBody() {
		return getNewHourFunction().getSubterm(3);
	}
	
	// Function("shiftByHours", [..], SimpleSort("Int"), Block())
	public IStrategoTerm getShiftByHoursFunction() {
		IStrategoTerm func = getEntityBody().getSubterm(5);
		return func;
	}
	
	public IStrategoTerm getShiftByHoursFunctionBody() {
		return getShiftByHoursFunction().getSubterm(3);
	}
}
