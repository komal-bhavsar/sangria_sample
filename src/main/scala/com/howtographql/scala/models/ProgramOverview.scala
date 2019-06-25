package com.howtographql.scala.models

import sangria.relay.Identifiable

case class ProgramOverview(userId: String,
                           programOverviewTemplate: ProgramOverviewTemplate,
                           programOverviewReward: List[Activity],
                           upcomingProgramOverviewRewards:List[UpcomingProgramOverviewActivity])

//object ProgramOverview {
//  implicit object ProgramOverviewIdentifiable extends Identifiable[ProgramOverview] {
//    def id(faction: ProgramOverview) = faction.userId
//  }
//}