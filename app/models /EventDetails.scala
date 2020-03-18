package models

import play.api.data.Form
import play.api.data.Forms._

case class EventDetails( post :String )

object EventDetails
{
  val eventForm :Form[EventDetails] = Form(
    mapping(
      "Corana Takeover" -> nonEmptyText
    )(EventDetails.apply)(EventDetails.unapply)
  )
}
