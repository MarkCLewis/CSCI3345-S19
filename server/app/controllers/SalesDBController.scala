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
import play.api.libs.json.Json

import edu.trinity.webapps.shared.SharedSales._

@Singleton
class SalesDBController @Inject() (protected val dbConfigProvider: DatabaseConfigProvider, cc: ControllerComponents)(implicit ec: ExecutionContext)
  extends AbstractController(cc) with HasDatabaseConfigProvider[JdbcProfile] {
  
  def index = Action { implicit request =>
    Ok(views.html.salesIndex())
  }

  def customers = Action.async { implicit request =>
    val customers = SalesQueries.customers(db)
    customers.map(custs => Ok(Json.toJson(custs)))
  }
  
  def customerOrders(cid: Int) = Action.async { implicit request =>
    val products = SalesQueries.customerOrders(cid, db)
    products.map(prods => Ok(Json.toJson(prods)))
  }
}