module project/ac
imports webdsldebuglib/debugevents
imports tag/release
access control rules
  predicate
  mayAccess
  (
  p : Project
  )
  {
  !
  p.private
  ||
  securityContext.principal in p.members
  }
  rule page roadmap ( p : Project ) {
    mayAccess(p) && (from Tag as t left join t . tags as ts where t . _project = ~p and ts . _name = ~"release"limit 1).length > 0
  }