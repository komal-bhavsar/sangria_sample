package com.howtographql.scala.models

import sangria.relay.Node

case class ProgramOverviewTemplate(id:String,
                                   name:String,
                                   title:String,
                                   description:String)extends Node

