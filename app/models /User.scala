//package models
//
//import models.Event
//import play.api.libs.json.OFormat
////import reactivemongo.bson.BSONObjectID
//
//
//object User {
//  def apply(age: Int,
//            firstName: String,
//            lastName: String,
//            feeds: List[Feed]),
//}
//case class User (
//                age: Option[Int],
//                firstName: String,
//                lastName: String,
//                feeds: Seq[Feed])
//
////case class Feed(
////                 name: String,
////                 url: String)
//
//object JsonFormats {
//
//  import play.api.libs.json.Json
////  import reactivemongo.play.json.collection.JSONCollection
////  import play.api.libs.json.__
//
//  // Generates Writes and Reads for Feed and User thanks to Json Macros
//   implicit val feedFormat: OFormat[Feed] = Json.format[Feed]
//  implicit val userFormat: OFormat[User] = Json.format[User]
////  implicit val loginDetailsFormat: OFormat[LoginDetails] = Json.format[LoginDetails]
//
//}
