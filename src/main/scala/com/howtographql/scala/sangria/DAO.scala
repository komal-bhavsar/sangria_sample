package com.howtographql.scala.sangria
import slick.jdbc.H2Profile.api._
import DBSchema._
import com.howtographql.scala.models.{Activity, ProgramOverview, ProgramOverviewTemplate, SubActivity, UpcomingProgramOverviewActivity}

class DAO(db: Database) {
  def allLinks = db.run(Links.result)

  def getProgramOverview:ProgramOverview  = programOverviewData


  val template = ProgramOverviewTemplate(
    id = "1234",
    name = "Test",
    title = "Test",
    description = "Test"
  )

  val subActivity1 = Activity(
    id = "11",
    status = "Pending",
    title = "Activity 11",
    subActivities = List.empty
  )

  val subActivity2 = subActivity1.copy(id = "11")
  val subActivity3 = subActivity1.copy(id = "13")
  val subActivity4 = subActivity1.copy(id = "14")
  val subActivity5 = subActivity1.copy(id = "15")
  val subActivity6 = subActivity1.copy(id = "16")

  val activity1 = Activity(
    id = "zzz",
    status = "Pending",
    title = "Activity 1",
    subActivities = Seq(
      subActivity1,subActivity2
    )
  )

  val activity2 = activity1.copy(
    id = "2",
    subActivities = Seq(
      subActivity3,subActivity4
    )
  )

  val activity3 = activity1.copy(
    id = "3",
    subActivities = Seq(
      subActivity5,subActivity6
    )
  )

  val activities = List(activity1,activity2, activity3)

  val upcomingProgramOverviewActivity1 = UpcomingProgramOverviewActivity(
    start = "S1",
    end = "E1"
  )

  val upcomingProgramOverviewActivity2 = upcomingProgramOverviewActivity1.copy(start = "S2")
  val upcomingProgramOverviewActivity3 = upcomingProgramOverviewActivity1.copy(end = "E2")


  val upcomingActivities = List(upcomingProgramOverviewActivity1,
    upcomingProgramOverviewActivity2, upcomingProgramOverviewActivity3)

  val programOverviewData =ProgramOverview("1", template, activities, upcomingActivities);

}


