module parenthesis-snippet2

function obtainPageViewStatisticsGraph() : String {
	
	for(p : Project in projects where p.name != "YellowGrass") {
		stats.add(
			ProjectIntTuple(
				p,
				select count(*)
				from RequestLogEntry
				where	_start > ~bound and 
						(
							_requestedURL like ~("http://yellowgrass.org/issue/"+p.name+"/%") or 
							_requestedURL = ~("http://yellowgrass.org/project/"+p.name)
						) and
						_userAgent not like ~"%bot%"
			)
		);
	}
}