package com.howtographql.scala.sangria

import sangria.schema.{Field, ListType, ObjectType}
import com.howtographql.scala.models._
import sangria.relay.Node.GlobalIdFieldName
import sangria.relay._
import sangria.schema._


object GraphQLSchema {


  def idFields[T : Identifiable] = fields[Unit, T](
 //   Node.globalIdField,
    Field(GlobalIdFieldName, StringType, resolve = ctx ⇒ implicitly[Identifiable[T]].id(ctx.value))

  //  Field("idsxxxx", StringType, resolve = ctx ⇒ implicitly[Identifiable[T]].id(ctx.value))
  )
  // 1
  val LinkType = ObjectType[Unit, Link](
    "Link",
    fields[Unit, Link](
      Field("id", IntType, resolve = _.value.id),
      Field("url", StringType, resolve = _.value.url),
      Field("description", StringType, resolve = _.value.description)
    )
  )

  val ProgramOverviewTemplateType = ObjectType[Unit, ProgramOverviewTemplate](
    "ProgramOverviewTemplate",
    fields[Unit, ProgramOverviewTemplate](
      Field("id", StringType, resolve = _.value.id),
      Field("title", StringType, resolve = _.value.title),
      Field("name", StringType, resolve = _.value.name),
      Field("description", StringType, resolve = _.value.description)
    )
  )

  implicit lazy val ActivityType : ObjectType[MyContext,Activity]= ObjectType[Unit, Activity](
    "Activity",
    () =>idFields [Activity] ++ fields[Unit, Activity](
//      Field("id", StringType, resolve = _.value.id),
      Field("status", StringType, resolve = _.value.status),
      Field("title", StringType, resolve = _.value.title),
      Field("subActivities", ListType(ActivityType), resolve = _.value.subActivities)
    )
  )

  implicit lazy val SubActivityType : ObjectType[Unit,SubActivity]= ObjectType[Unit, SubActivity](
    "SubActivity",
    () =>idFields [SubActivity] ++ fields[Unit, SubActivity](
//      Field("id", StringType, resolve = _.value.id),
      Field("status", StringType, resolve = _.value.status),
      Field("title", StringType, resolve = _.value.title),
      Field("activities", ListType(ActivityType), resolve = _.value.activities)
    )
  )

  val UpcomingProgramOverviewActivityType = ObjectType[Unit, UpcomingProgramOverviewActivity](
    "UpcomingProgramOverviewActivity",
    fields[Unit, UpcomingProgramOverviewActivity](
      Field("start", StringType, resolve = _.value.start),
      Field("end", StringType, resolve = _.value.end)
    )
  )

  val ProgramOverviewType = ObjectType[Unit, ProgramOverview](
    "ProgramOverview",
    fields[Unit, ProgramOverview](
      Field("programOverviewTemplate", ProgramOverviewTemplateType, resolve = _.value.programOverviewTemplate),
      Field("programOverviewReward", ListType(ActivityType), resolve = _.value.programOverviewReward),
      Field("upcomingProgramOverviewRewards", ListType(UpcomingProgramOverviewActivityType), resolve = _.value.upcomingProgramOverviewRewards)
    )
  )

  val QueryType = ObjectType(
    "Query",
    fields[MyContext, Unit](
      Field("allLinks", ListType(LinkType), resolve = c => c.ctx.dao.allLinks),
      Field("programOverview", ProgramOverviewType, resolve = c => c.ctx.dao.getProgramOverview)
    )
  )

  val SchemaDefinition = Schema(QueryType)

}