package edu.trinity.webapps

import org.scalajs.dom
import scala.scalajs.js
import org.querki.jquery._

import scala.scalajs.js.annotation._
import play.api.libs.json._
import play.api.libs.functional.syntax._


@js.native
@JSGlobal
class Customer(val id: Int, val name: String, val addr1: String, val addr2: Option[String], val city: String, val state: String, val zip: String) extends js.Object
//  case class Customer(id: Int, name: String, addr1: String, addr2: Option[String] = None, city: String, state: String, zip: String)


@js.native
@JSGlobal
class Product(val id: Int, val name: String, val price: Int) extends js.Object

object SalesClient {
//  implicit def optionFormat[T: Format]: Format[Option[T]] = new Format[Option[T]]{
//    override def reads(json: JsValue): JsResult[Option[T]] = json.validateOpt[T]
//
//    override def writes(o: Option[T]): JsValue = o match {
//      case Some(t) ⇒ implicitly[Writes[T]].writes(t)
//      case None ⇒ JsNull
//    }
//  }
//  
//  implicit val customerRowWrites: Writes[Customer] = (
//    (JsPath \ "id").write[Int] and
//    (JsPath \ "name").write[String] and
//    (JsPath \ "addr1").write[String] and
//    (JsPath \ "addr2").write[Option[String]] and
//    (JsPath \ "city").write[String] and
//    (JsPath \ "state").write[String] and
//    (JsPath \ "zip").write[String])(unlift(Customer.unapply))
//
//  implicit val customerRowReads: Reads[Customer] = (
//    (JsPath \ "id").read[Int] and
//    (JsPath \ "name").read[String] and
//    (JsPath \ "addr1").read[String] and
//    (JsPath \ "addr2").read[Option[String]] and
//    (JsPath \ "city").read[String] and
//    (JsPath \ "state").read[String] and
//    (JsPath \ "zip").read[String])(Customer.apply _)
  
  
  def pageSetup(): Unit = {
    println("Setup the sales page.")
    addCustomers()
  }

  def addCustomers(): Unit = {
    $.getJSON("/customers", success = (o, s, j) => {
      for(c <- o.asInstanceOf[js.Array[Customer]]) {
        val custPar = $(s"<p>${c.name}</p>")
        $("#sales-stuff").append(custPar)
        custPar.click(() => addOrders(c.id))
      }
    })
    $("#sales-stuff").append(s"<div id='orders'></div>")
  }

  def addOrders(id: Int): Unit = {
    $("#orders").empty
    $.getJSON("/customerOrders/"+id, success = (o, s, j) => {
      for(c <- o.asInstanceOf[js.Array[Product]]) {
        val orderPar = $(s"<p>${c.name} ${c.price}</p>")
        $("#orders").append(orderPar)
      }
    })
  }
}