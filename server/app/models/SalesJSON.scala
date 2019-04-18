package models

import Tables._
import play.api.libs.json._
import play.api.libs.functional.syntax._

object SalesJSON {
      /**
   * This allows for the reading of option types.
   */
  implicit def optionFormat[T: Format]: Format[Option[T]] = new Format[Option[T]]{
    override def reads(json: JsValue): JsResult[Option[T]] = json.validateOpt[T]

    override def writes(o: Option[T]): JsValue = o match {
      case Some(t) ⇒ implicitly[Writes[T]].writes(t)
      case None ⇒ JsNull
    }
  }
  
  implicit val customerRowWrites: Writes[CustomerRow] = (
    (JsPath \ "id").write[Int] and
    (JsPath \ "name").write[String] and
    (JsPath \ "addr1").write[String] and
    (JsPath \ "addr2").write[Option[String]] and
    (JsPath \ "city").write[String] and
    (JsPath \ "state").write[String] and
    (JsPath \ "zip").write[String])(unlift(CustomerRow.unapply))

  implicit val customerRowReads: Reads[CustomerRow] = (
    (JsPath \ "id").read[Int] and
    (JsPath \ "name").read[String] and
    (JsPath \ "addr1").read[String] and
    (JsPath \ "addr2").read[Option[String]] and
    (JsPath \ "city").read[String] and
    (JsPath \ "state").read[String] and
    (JsPath \ "zip").read[String])(CustomerRow.apply _)


  implicit val orderAssocRowWrites: Writes[OrderAssocRow] = (
    (JsPath \ "pid").write[Int] and
    (JsPath \ "cid").write[Int] and
    (JsPath \ "quanitity").write[Int]
  )(unlift(OrderAssocRow.unapply))

  implicit val orderAssocRowReads: Reads[OrderAssocRow] = (
    (JsPath \ "pid").read[Int] and
    (JsPath \ "cid").read[Int] and
    (JsPath \ "quantity").read[Int]
  )(OrderAssocRow.apply _)


  implicit val productRowWrites: Writes[ProductRow] = (
    (JsPath \ "id").write[Int] and
    (JsPath \ "name").write[String] and
    (JsPath \ "price").write[Int]
  )(unlift(ProductRow.unapply))

  implicit val productRowReads: Reads[ProductRow] = (
    (JsPath \ "id").read[Int] and
    (JsPath \ "name").read[String] and
    (JsPath \ "price").read[Int]
  )(ProductRow.apply _)

}