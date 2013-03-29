module code/lib

  define ignore-access-control override errorTemplateInput(messages : List<String>){
    elements()
    for(ve: String in messages){
      block()[style := "width:100%; clear:left; float:left; color: #FF0000;"]{
        output(ve)
      }
    }
  }