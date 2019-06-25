package com.howtographql.scala.models
import sangria.relay.Identifiable

case class Activity(id:String,
                    status:String,
                    title:String,
                    subActivities:Seq[Activity] = Seq.empty
                   )

object Activity {
  implicit object ActivityIdentifiable extends Identifiable[Activity] {
    def id(faction: Activity) = faction.id
  }
}