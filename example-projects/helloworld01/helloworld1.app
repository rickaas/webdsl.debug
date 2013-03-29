application helloworld1

define page root(){
  list {
    listitem { "Hello world 01" }
    listitem { "count: " output(globalCallCount.count) }
    listitem { "bodyCount: " output(globalCallCount.bodyCount) }
    listitem { "exitCount: " output(globalCallCount.exitCount) }
  }
  form {
    submit save() { "Increase" }
  }
  action save() {
    globalCallCount.inc();
  }
}

section

var globalCallCount := CallCount{};

init {
  globalCallCount.start();
}

entity CallCount {
  count :: Int
  
  bodyCount :: Int

  exitCount :: Int

  function start() {
    count := 0;
	bodyCount := 0;
	exitCount := 0;
  }

  function inc() {
    //try {
	  bodyCount := bodyCount + 1;
	  return;
	//} finally {
	//  exit();
	//}
  }
  function exit() {
    exitCount := exitCount + 1;
  }
}
