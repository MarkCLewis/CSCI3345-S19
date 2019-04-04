package controllers

import javax.inject._
import play.api.mvc._
import scala.concurrent.Future
import models._

import play.api.db.slick.DatabaseConfigProvider
import play.api.db.slick.HasDatabaseConfigProvider

import slick.jdbc.JdbcProfile
import scala.concurrent.ExecutionContext

import slick.jdbc.MySQLProfile.api._ // This line determines what type of database you are connecting to.

@Singleton
class SalesDBController @Inject() (protected val dbConfigProvider: DatabaseConfigProvider, cc: ControllerComponents)(implicit ec: ExecutionContext)
  extends AbstractController(cc) with HasDatabaseConfigProvider[JdbcProfile] {

  def customers = Action.async { implicit request =>
    val customers = SalesQueries.customers(db)
    customers.map(custs => Ok(custs.length+"\n"+custs.mkString("\n")))
  }
  
  def customerOrders(cid: Int) = Action.async { implicit request =>
    val products = SalesQueries.customerOrders(cid, db)
    products.map(prods => Ok(prods.length+"\n"+prods.mkString("\n")))
  }
}