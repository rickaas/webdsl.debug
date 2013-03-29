module functiononly

define page makeTestSet() {
  form { 
    "Make a test set"
    submit action {
      var s := "string";
    } { "Execute" }
  }
}
