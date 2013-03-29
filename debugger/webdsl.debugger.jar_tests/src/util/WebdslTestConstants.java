package util;

public class WebdslTestConstants {

	public static String WEBDSL_SCRIPTS_DIR = "webdsl_scripts";
	public static String WEBDSL_SCRIPTS_GENERATED_DIR = WEBDSL_SCRIPTS_DIR+"/generated";
	public static String WEBDSL_SCRIPTS_INSTRUMENTED_DIR = WEBDSL_SCRIPTS_DIR+"/instrumented";
	public static String WEBDSL_SCRIPTS_TESTCASES_DIR = WEBDSL_SCRIPTS_DIR+"/testcases";
	
	public static String LIB_DIR = "lib";
	public static String LIB_INSTRUMENTATION_DIR = LIB_DIR + "/instrumentation";

	public static String DSLDI_JAVA_JAR = LIB_INSTRUMENTATION_DIR + "/dsldi-java.jar";
	public static String DSLDI_JAR = LIB_INSTRUMENTATION_DIR + "/dsldi.jar";
	public static String LIBDSLDI_JAR = LIB_INSTRUMENTATION_DIR + "/libdsldi.jar";
	
	public static String LIB_WEBDSL_DIR = LIB_DIR + "/webdsl";
	public static String ACODA_JAR = LIB_WEBDSL_DIR + "/acoda.jar";
	public static String WEBDSL_EDITOR_JAR = LIB_WEBDSL_DIR + "/webdsl_editor.jar";
	public static String WEBDSL_JAR = LIB_WEBDSL_DIR + "/webdsl.jar";
	
	public static String WEBDSL_DI_JAR =  LIB_WEBDSL_DIR + "/webdsl-di.jar";
	
	
	public static String PARSE_WEBDSL_FILE = "parse-webdsl-file";
	
	
	public static String EXTRACT_FUNCTION_INFO = "extract-function-info";
	public static String EXTRACT_TEMPLATE_INFO = "extract-template-info";
	
	public static String GEN_FUNCTION_ENTER = "gen-function-enter";
	public static String GEN_TEMPLATE_ENTER = "gen-template-enter";
	
	public static String GEN_FUNCTION_EXIT = "gen-function-exit";
}
