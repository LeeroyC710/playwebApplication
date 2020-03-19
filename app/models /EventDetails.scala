import play.api.libs.json.OFormat

package models {

  case class EventDetails(
                   Event: String,
                   Time: String,
                   Place : String,
                   feeds: List[Feed])

  case class Feed(
                   name: String,
                   url: String)

  object JsonFormats {

    import play.api.libs.json.Json

    implicit val eventFormat: OFormat[Event] = Json.format[Event]
    implicit val feedFormat: OFormat[Feed] = Json.format[Feed]

  }

}