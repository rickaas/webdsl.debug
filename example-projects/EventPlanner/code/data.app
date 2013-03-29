module code/data

  entity Event {
    name :: String
    description :: String
    slots -> List<Slot>
    userPrefs -> List<UserPreference>
    aLink -> ALink
    pLink -> PLink
  }

// administration
  entity ALink {
    event -> Event (inverse=Event.aLink)
  }
// participation
  entity PLink {
    event -> Event (inverse=Event.pLink)
  }

  entity Slot {
    time :: String
    event -> Event (inverse = Event.slots)
    prefs -> List<Preference>
  }

  // a time slot, for a user, for an event
  entity Preference {
    slot -> Slot (inverse = Slot.prefs)
    option -> PrefOption
    comment :: WikiText
    userPref -> UserPreference
  }

  // For each User in an Event keep track of its attendence
  entity UserPreference {
    prefs -> List<Preference> (inverse = Preference.userPref)
    user :: String
    event -> Event (inverse = Event.userPrefs)
    validate(user.length()>0,"name required")
    function getPrefForSlot(s:Slot):Preference{
      for(pr:Preference in prefs){
        if(pr.slot == s){
          return pr;
        }
      }
      return null;
    }
  }

  entity PrefOption {
    name :: String
  }

  var p_yes := PrefOption{ name := "yes" }
  var p_no := PrefOption{ name := "no" }
  var p_maybe := PrefOption{ name := "maybe" }

  init{
    var meetingEvent := Event{
      name := "Important meeting"
    };
    var meetingSlot1 := Slot{
      event := meetingEvent
      time := "16 Oct 8:30"
    };
    var meetingSlot2 := Slot{
      event := meetingEvent
      time := "16 Oct 13:30"
    };

	meetingEvent.aLink := ALink{};
	meetingEvent.pLink := PLink{};
	meetingEvent.invitees := [User{}];

	meetingEvent.organizer := user_test;
    meetingEvent.save();
    
    var partyEvent := Event{
      name := "Party"
    };
    var pslot1 := Slot{
      event := partyEvent
      time := "16 Oct 18:30"
    };
    var pslot2 := Slot{
      event := partyEvent
      time := "17 Oct 18:30"
    };
    var pslot3 := Slot{
      event := partyEvent
      time := "18 Oct 18:30"
    };
// 	partyEvent.organizer := user_admin;

	partyEvent.aLink := ALink{};
	partyEvent.pLink := PLink{};
	partyEvent.invitees := [User{}];
    partyEvent.save();

    var holidayEvent := Event{
      name := "Holiday"
    };
    var hslot1 := Slot{
      event := holidayEvent
      time := "1 Sep - 5 Sep"
    };
    var hslot2 := Slot{
      event := holidayEvent
      time := "10 Sep - 13 Sep"
    };
    var hslot3 := Slot{
      event := holidayEvent
      time := "18 Sep - 20 Sep"
    };
    holidayEvent.aLink := ALink{};
	holidayEvent.pLink := PLink{};
	holidayEvent.invitees := [User{}];
    holidayEvent.save();
    
  }

