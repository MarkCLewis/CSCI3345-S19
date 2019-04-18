package edu.trinity.webapps

import org.scalajs.dom
import scala.scalajs.js
import org.querki.jquery._

import scala.scalajs.js.annotation._
import play.api.libs.json._
import play.api.libs.json.Reads._
import play.api.libs.functional.syntax._

import edu.trinity.webapps.shared.SharedSales._

object SalesClient {
  def pageSetup(): Unit = {
    println("Setup the sales page.")
    addCustomers()
  }

  def addCustomers(): Unit = {
    $.getJSON("/customers", success = (o, s, j) => {
      for(c <- Json.parse(js.JSON.stringify(o)).as[Array[CustomerRow]]) {
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
      for(c <- Json.parse(js.JSON.stringify(o)).as[Array[ProductRow]]) {
        val orderPar = $(s"<p>${c.name} ${c.price}</p>")
        $("#orders").append(orderPar)
      }
    })
  }
}