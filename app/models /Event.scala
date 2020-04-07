package models

import play.api.data.Form
import play.api.data.Forms._

case class Event(event :String, time: String, place: String ){
  override def toString() = s"Name of event is: $event and the Event is: $time and the place is: $place"
}
object Event {
  val postEvent = Form(
    mapping(
      "Event" -> nonEmptyText,
    "Time" -> nonEmptyText,
  "Place" -> nonEmptyText
  )(Event.apply)(Event.unapply)
  )
}
