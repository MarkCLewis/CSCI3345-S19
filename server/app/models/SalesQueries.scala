package models

import slick.jdbc.MySQLProfile.api._
import scala.concurrent.duration.Duration
import scala.concurrent.Await
import scala.concurrent.Future
import Tables._
import scala.concurrent.ExecutionContext

object SalesQueries {
  def customers(db: Database)(implicit ec: ExecutionContext): Future[Seq[CustomerRow]] = {
    db.run {
      (for (c <- Customer) yield c).result
    }
  }

}