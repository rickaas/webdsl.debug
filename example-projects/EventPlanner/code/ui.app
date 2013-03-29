module code/ui

  // edit an event
  define eventEdit(e:Event){
      label("Name:"){
        input(e.name){
          validate(e.name.length()>0,"name required")
        }
      }
      label("Description:"){
        input(e.description)
      }
      label("Slots:"){
        for(slot : Slot in e.slots) {
          input(slot.time)
          <br />
        }
        validate( And[s.time.length()>0 | s:Slot in e.slots],"time slot description required for each slot")
      }
      // allow to add new slots in the fly
      submit action{ e.slots.add(Slot{}); } [ignore-validation]{"add slot"}
  }

  // page for Event administration
  define page admin(e:ALink) {
    title{"administration"}
	commonHeader()
	block("main-container") {
	    form{
	      eventEdit(e.event)
	      submit action{ return participants(e.event); } { "save event" }
	    }
    }
  }
  
  define preferenceEdit(p : Preference) {
  	form {
  		column { output(p.slot.time) }
  		column { 
  			radio(p.option, [p_yes,p_no,p_maybe])
  		}
  		column {
  			submit save() { "save" }
  		}
  	} action save() {
  		p.save();
  		return userPreferenceEdit(p.userPref); 
  	}
  }

  define page userPreferenceEdit(up : UserPreference) {
  	title{"User preferences"}
  	commonHeader()
  	block("main-container") {
		block("event-name") {
  			output(up.event.name)
	    }
	    block("event-description") {
	    	//"Event Description" 
	    	output(up.event.description)
	    }
  		block("participant-name") {
  			"Availability for " output(up.user)
  		}
  		form {
		    table {
		    	// header row
		    	row {column{"Slot"} column{"Available?"}}
		    	for(p:Preference in up.prefs) {
					row {
		        		preferenceEdit(p)
		        	}
		      	}
	      	}
      	} 
      	submit action{ 
      		up.save(); 
      		return participants(up.event); 
      	} { "save preference" }
  	}
  }

  define page event(e:PLink){
    var up := UserPreference{}

    title{"participants"}
    commonHeader()
    block("main-container") {
    	
  		block("event-name") {
  			//"Event Name" 
  			output(e.event.name)
	    }
	    block("event-description") {
	    	//"Event Description" 
	    	output(e.event.description)
	    }
	    form{
	      label("Name:"){
	        input(up.user)
	      }
	      label("Slots:"){
	        table {
	          for(slot : Slot in e.event.slots) {
	            row {
	              pickOption(up,slot)
	            }
	          }
	        }
	      }
	      submit action{ e.event.userPrefs.add(up); return participants(e.event); } { "save preference" }
	    }
	}
    //showEvent(e.event)
  }

  define pickOption(up:UserPreference,slot:Slot){
    var p := Preference{}
    column {
      output(slot.time)
    }
    column {
      radio(p.option, [p_yes,p_no,p_maybe])
    }
    databind{
      up.prefs.add(Preference{slot := slot option := p.option});
    }
  }

  define showEvent(e:Event){
  	block("view-event") {
  		block("event-name") {
  			//"Event Name" 
  			output(e.name)
  			navigate participants(e) { "(view)" }
  			navigate admin(e.aLink) { "(edit-slots)" }
  			navigate event(e.pLink) { "(participate)" }
  			navigate completed(e) { "(invite)" }
  			//output(e.aLink)
  			// label("administration link") {
  			// 	"B:"
  			// 	//navigate admin(e.aLink) { "Foo" }
  			// }
	    }
	    block("event-description") {
	    	//"Event Description" 
	    	output(e.description)
	    }
	    //output(e.organizer) // can be null
	    table {
	    	// header row
			row {
	        	column {}//empty column above user names
	        	for(s:Slot in e.slots){
	          		column {
	              		output(s.time)
	          		}
	        	}
	      	}
	      
		    if (e.userPrefs.length == 0) {
		    	row { column { "No participants..." } }
		    } else {
				for(up : UserPreference in e.userPrefs){
		        	row {
	          			// name of the user
	          			column {
	          				output(up.user)
	          				navigate userPreferenceEdit(up) {"edit user"}
	          			}
	          			// the time slots
	          			for(s:Slot in e.slots){
	            			column {output(up.getPrefForSlot(s).option.name)}
	          			}
	        		}
	      		}
	      	}
	    }
	}
  }

  define page participants(e:Event){
  	commonHeader()
	block("main-container") {
    	showEvent(e)
    }
  }
  
  define showAllEvents(){
    "All events: "
    <br />
    for(e:Event){
      showEvent(e)
    } separated-by{ <hr />  }
  }


