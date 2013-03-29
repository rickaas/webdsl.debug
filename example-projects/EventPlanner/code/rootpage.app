module code/rootpage

  define page root(){
  	commonHeader()
  	
	block("main-container") {
	    form{
	      submitlink action{
	        var e := Event{
	          organizer := securityContext.principal
	          slots := [ Slot{ } ]
	        };
	        e.save();
	        return new(e);
	      } { "Create new event" }
	    }
	    
	    for(e:Event){
	      showEvent(e)
	    } separated-by{ <hr />  }
	}
  }
  
  define page foo() {
  	header {"Foo"}
  }

  
  define commonHeader() {
  	includeCSS("my_css.css")
  	block("top-container") {
	    block("head") { 
	    	header { navigate root() {"Event Planner"} } 
	    }
	    block("login") {
	    	auth()
	    }
    }
  }
