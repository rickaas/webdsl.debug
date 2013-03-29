application helloworld1

imports webdsldebuglib/debugevents

define page root(){
  debug_template_enter("helloworld1.app", "6,1,6,10", "root")
  debug_template_step("helloworld1.app", "7,1,7,10")
  list {
    debug_template_step("helloworld1.app", "9,1,9,10")
    listitem { "Hello world 02 DI" }
  }
  debug_template_step("helloworld1.app", "12,1,12,10")
  showCounter(globalCallCount)
  debug_template_step("helloworld1.app", "14,1,14,10")
  form {
    debug_template_step("helloworld1.app", "16,1,16,10")
    submit save() { "Increase" }
  }
  action save() {
    WebDSLDebugEvents.functionEnter("helloworld1.app", "20,3,20,10", "save");
    WebDSLDebugEvents.statementStep("helloworld1.app","21,1,21,10");
    globalCallCount.inc();
    WebDSLDebugEvents.functionExit("helloworld1.app", "23,1,23,10,", "init");
  }
  debug_template_exit("helloworld1.app", "25,1,25,10", "root")
}

define showCounter(counter : CallCount) {
  debug_template_enter("helloworld1.app", "29,1,29,10", "showCounter")
  debug_template_var("helloworld1.app", "30,1,30,10", "counter")
  debug_template_step("helloworld1.app", "31,1,31,10")
  list {
    debug_template_step("helloworld1.app", "33,1,33,10")
    listitem { "count: " output(counter.count) }
    debug_template_step("helloworld1.app", "35,1,35,10")
    listitem { "bodyCount: " output(counter.bodyCount) }
    debug_template_step("helloworld1.app", "37,1,37,10")
    listitem { "exitCount: " output(counter.exitCount) }
  }
  debug_template_exit("helloworld1.app", "40,1,40,10", "showCounter")
}

section

var globalCallCount := CallCount{};

init {
  WebDSLDebugEvents.functionEnter("helloworld1.app", "48,3,48,70", "init");
  WebDSLDebugEvents.statementStep("helloworld1.app","49,1,49,10");
  globalCallCount.start();
  WebDSLDebugEvents.functionExit("helloworld1.app", "51,3,51,70,", "init");
}

entity CallCount {
  count :: Int
  
  bodyCount :: Int

  exitCount :: Int

  function start() {
    WebDSLDebugEvents.functionEnter("helloworld1.app", "62,5,62,70", "start");
    WebDSLDebugEvents.var("helloworld1.app", "63,1,63,10", "this");
    try {
      WebDSLDebugEvents.statementStep("helloworld1.app","65,1,65,14");
      count := 0;
      WebDSLDebugEvents.statementStep("helloworld1.app","67,1,67,16");
	  bodyCount := 0;
      WebDSLDebugEvents.statementStep("helloworld1.app","69,1,69,18");
	  exitCount := 0;
    } finally {
      WebDSLDebugEvents.functionExit("helloworld1.app", "72,7,72,70", "start");
    }
  }

  function inc() {
    WebDSLDebugEvents.functionEnter("helloworld1.app", "77,5,77,70", "inc");
    WebDSLDebugEvents.var("helloworld1.app", "78,1,78,10", "this");
    try {
      try {
        WebDSLDebugEvents.statementStep("helloworld1.app","81,1,81,10");
	    bodyCount := bodyCount + 1;
        WebDSLDebugEvents.statementStep("helloworld1.app","83,1,83,16");
	    return;
	  } finally {
        WebDSLDebugEvents.statementStep("helloworld1.app","86,1,86,16");
	    exit();
	  }
    } finally {
      WebDSLDebugEvents.functionExit("helloworld1.app", "90,7,90,70,", "inc");
    }
  }
  function exit() {
    WebDSLDebugEvents.functionEnter("helloworld1.app", "94,5,94,70", "exit");
    WebDSLDebugEvents.var("helloworld1.app", "95,1,95,10", "this");
    try {
      WebDSLDebugEvents.statementStep("helloworld1.app","97,1,97,14");
      exitCount := exitCount + 1;
    } finally {
      WebDSLDebugEvents.functionExit("helloworld1.app", "100,7,100,70", "exit");
    }
  }
}
