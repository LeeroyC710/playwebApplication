package models

import play.api.data.Form
import play.api.data.Forms._

case class CD(name: String, price: Int){
  override def toString() = s"The name of the CD is: $name and the price is: $price"
}

object CD {
  val createCDForm = Form(
    mapping(
      "name" -> nonEmptyText,
      "price" -> number
    )(CD.apply)(CD.unapply)
  )
  val cds = ArrayBuffer(
    CD("CD 1", 123),
    CD("CD 2", 456),
    CD("CD 3", 789)
  )

}