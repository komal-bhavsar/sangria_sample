package com.howtographql.scala.models

import sangria.relay.{Identifiable, Node}

case class SubActivity(id:String,
                       status:String,
                       title:String,
                       activities:List[Activity]
                      )extends Node

object SubActivity {
  implicit object SubActivityIdentifiable extends Identifiable[SubActivity] {
    def id(faction: SubActivity) = faction.id
  }
}