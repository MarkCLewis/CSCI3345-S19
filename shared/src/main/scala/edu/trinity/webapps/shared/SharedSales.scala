package edu.trinity.webapps.shared

import play.api.libs.json._
import play.api.libs.functional.syntax._


object SharedSales {
  /**
   * Entity class storing rows of table Customer
   *  @param id Database column id SqlType(INT), AutoInc, PrimaryKey
   *  @param name Database column name SqlType(VARCHAR), Length(50,true)
   *  @param addr1 Database column addr1 SqlType(VARCHAR), Length(50,true)
   *  @param addr2 Database column addr2 SqlType(VARCHAR), Length(50,true), Default(None)
   *  @param city Database column city SqlType(VARCHAR), Length(50,true)
   *  @param state Database column state SqlType(CHAR), Length(2,false)
   *  @param zip Database column zip SqlType(CHAR), Length(7,false)
   */
  case class CustomerRow(id: Int, name: String, addr1: String, addr2: Option[String] = None, city: String, state: String, zip: String)

  /**
   * Entity class storing rows of table OrderAssoc
   *  @param pid Database column pid SqlType(INT)
   *  @param cid Database column cid SqlType(INT)
   *  @param quantity Database column quantity SqlType(INT)
   */
  case class OrderAssocRow(pid: Int, cid: Int, quantity: Int)

  /**
   * Entity class storing rows of table Product
   *  @param id Database column id SqlType(INT), AutoInc, PrimaryKey
   *  @param name Database column name SqlType(VARCHAR), Length(200,true)
   *  @param price Database column price SqlType(INT)
   */
  case class ProductRow(id: Int, name: String, price: Int)

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
