//package models
//
//import play.api.data.Form
//import play.api.data.Forms.mapping
//
//case class Event(name: String, price: Int)
//override def toString() = s"The name of the event is: $name and the price is: $price"
//}
//
//object Event {
//  val createCDForm = Form(
//    mapping(
//      "name" -> nonEmptyText,
//      "price" -> number
//    )(Event.apply)(Event.unapply)
//  )
//  val cds = ArrayBuffer(
//    Event("CD 1", 123),
//    Event("CD 2", 456),
//    Event("CD 3", 789)
//  )
//
//}
