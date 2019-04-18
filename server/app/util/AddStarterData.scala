package util

import slick.jdbc.MySQLProfile.api._
import java.util.concurrent.ConcurrentHashMap
import scala.concurrent.duration.Duration
import scala.concurrent.Await
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future
import models._
import Tables._
import edu.trinity.webapps.shared.SharedSales._

object AddStarterData extends App {
  val db = Database.forURL("jdbc:mysql://localhost/sales?user=mlewis&password=password&nullNamePatternMatchesAll=true&serverTimezone=UTC", user="mlewis", password="password", driver="com.mysql.cj.jdbc.Driver")
    Await.result(db.run(DBIO.seq(
      Customer.delete,
      Product.delete,
      OrderAssoc.delete
    )), Duration.Inf)
    Await.result(db.run(DBIO.seq(
      sqlu"ALTER TABLE customer AUTO_INCREMENT = 1;",
      sqlu"ALTER TABLE product AUTO_INCREMENT = 1;"
    )), Duration.Inf)
    Await.result(db.run(DBIO.seq(
        Customer += CustomerRow(1, "Mark Lewis", "CSI 270H", None, "San Antonio", "TX", "78212"),
        Customer += CustomerRow(2, "Jason Hughes", "Game Company", None, "Austin", "TX", "78745"),
        Customer += CustomerRow(3, "Jason McLane", "Oil Company", None, "Houston", "TX", "78105"),

        Product ++= Seq(
          ProductRow(1, "Juggling Balls", 500),
          ProductRow(2, "Scala Book", 5500),
          ProductRow(3, "Aerobie", 1250),
          ProductRow(4, "Art", 1000000)),

        OrderAssoc ++= Seq(
          OrderAssocRow(1, 1, 1),
          OrderAssocRow(2, 1, 1),
          OrderAssocRow(3, 2, 3),
          OrderAssocRow(4, 3, 2))
    )), Duration.Inf)
    db.close()
}