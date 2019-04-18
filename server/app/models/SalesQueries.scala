package models

import slick.jdbc.MySQLProfile.api._
import scala.concurrent.duration.Duration
import scala.concurrent.Await
import scala.concurrent.Future
import Tables._
import scala.concurrent.ExecutionContext
import edu.trinity.webapps.shared.SharedSales._

object SalesQueries {
  def customers(db: Database)(implicit ec: ExecutionContext): Future[Seq[CustomerRow]] = {
    db.run {
      (for (c <- Customer) yield c).result
    }
  }

  def products(db: Database)(implicit ec: ExecutionContext): Future[Seq[ProductRow]] = {
    db.run {
      (for (p <- Product) yield p).result
    }
  }
  
  def customerOrders(cid: Int, db: Database)(implicit ec: ExecutionContext): Future[Seq[ProductRow]] = {
    db.run {
      val joinedData = for { 
        oa <- OrderAssoc
        if oa.cid === cid
        p <- Product
        if oa.pid === p.id
      } yield {
        p
      }
      joinedData.result
    }
  }
}