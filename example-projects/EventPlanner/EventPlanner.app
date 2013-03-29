application EventPlanner

  imports code/data
  imports code/lib
  imports code/ac
  imports code/ui
  imports code/invite
  imports code/rootpage
  
  
  access control rules 
  
    rule template showEvent(e:Event){
      e.organizer == null || e.organizer == principal
    }