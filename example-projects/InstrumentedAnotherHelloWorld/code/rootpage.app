module code/rootpage

	define page root(){ 
		debug_template_enter("code/rootpage", 
								"", 
								"root")
		title { "Page title" }
		header{ "Hello world." }
		var s1 := doStuff(1,9);
		var s2 := doStuff(5,2);
		list {
		  listitem { "doStuff(1,9)=" output(s1)}
		  listitem { "doStuff(5,2)=" output(s2)}
		  listitem { "doStuff(5,2)=" output(s2)}
		  listitem { "callCount=" output(callCount.count)}
		  listitem { "callCount=" output(callCount.count)}
		}
		debug_template_exit("code/rootpage", 
						"", 
						"root")

	}
	
	define page foo() {
		debug_template_enter("code/rootpage", 
						"", 
						"root")

		header {"Foo"}
		debug_template_exit("code/rootpage", 
						"", 
						"root")

	}