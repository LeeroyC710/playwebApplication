package models

import play.api.data.Form
import play.api.data.Forms._

case class Event( event :String, Time: String, place: String ){
  override def toString() = s"Name of event is: $Event and the Event is: $Time and the place is: $Place"
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
