package models
// AUTO-GENERATED Slick data model
/** Stand-alone Slick data model for immediate use */
object Tables extends {
  val profile = slick.jdbc.MySQLProfile
} with Tables

/** Slick data model trait for extension, choice of backend or usage in the cake pattern. (Make sure to initialize this late.) */
trait Tables {
  val profile: slick.jdbc.JdbcProfile
  import profile.api._
  import slick.model.ForeignKeyAction
  // NOTE: GetResult mappers for plain SQL are only generated for tables where Slick knows how to map the types of all columns.
  import slick.jdbc.{GetResult => GR}

  /** DDL for all tables. Call .create to execute. */
  lazy val schema: profile.SchemaDescription = Customer.schema ++ OrderAssoc.schema ++ Product.schema
  @deprecated("Use .schema instead of .ddl", "3.0")
  def ddl = schema

  /** Entity class storing rows of table Customer
   *  @param id Database column id SqlType(INT), AutoInc, PrimaryKey
   *  @param name Database column name SqlType(VARCHAR), Length(50,true)
   *  @param addr1 Database column addr1 SqlType(VARCHAR), Length(50,true)
   *  @param addr2 Database column addr2 SqlType(VARCHAR), Length(50,true), Default(None)
   *  @param city Database column city SqlType(VARCHAR), Length(50,true)
   *  @param state Database column state SqlType(CHAR), Length(2,false)
   *  @param zip Database column zip SqlType(CHAR), Length(7,false) */
  case class CustomerRow(id: Int, name: String, addr1: String, addr2: Option[String] = None, city: String, state: String, zip: String)
  /** GetResult implicit for fetching CustomerRow objects using plain SQL queries */
  implicit def GetResultCustomerRow(implicit e0: GR[Int], e1: GR[String], e2: GR[Option[String]]): GR[CustomerRow] = GR{
    prs => import prs._
    CustomerRow.tupled((<<[Int], <<[String], <<[String], <<?[String], <<[String], <<[String], <<[String]))
  }
  /** Table description of table customer. Objects of this class serve as prototypes for rows in queries. */
  class Customer(_tableTag: Tag) extends profile.api.Table[CustomerRow](_tableTag, Some("sales"), "customer") {
    def * = (id, name, addr1, addr2, city, state, zip) <> (CustomerRow.tupled, CustomerRow.unapply)
    /** Maps whole row to an option. Useful for outer joins. */
    def ? = ((Rep.Some(id), Rep.Some(name), Rep.Some(addr1), addr2, Rep.Some(city), Rep.Some(state), Rep.Some(zip))).shaped.<>({r=>import r._; _1.map(_=> CustomerRow.tupled((_1.get, _2.get, _3.get, _4, _5.get, _6.get, _7.get)))}, (_:Any) =>  throw new Exception("Inserting into ? projection not supported."))

    /** Database column id SqlType(INT), AutoInc, PrimaryKey */
    val id: Rep[Int] = column[Int]("id", O.AutoInc, O.PrimaryKey)
    /** Database column name SqlType(VARCHAR), Length(50,true) */
    val name: Rep[String] = column[String]("name", O.Length(50,varying=true))
    /** Database column addr1 SqlType(VARCHAR), Length(50,true) */
    val addr1: Rep[String] = column[String]("addr1", O.Length(50,varying=true))
    /** Database column addr2 SqlType(VARCHAR), Length(50,true), Default(None) */
    val addr2: Rep[Option[String]] = column[Option[String]]("addr2", O.Length(50,varying=true), O.Default(None))
    /** Database column city SqlType(VARCHAR), Length(50,true) */
    val city: Rep[String] = column[String]("city", O.Length(50,varying=true))
    /** Database column state SqlType(CHAR), Length(2,false) */
    val state: Rep[String] = column[String]("state", O.Length(2,varying=false))
    /** Database column zip SqlType(CHAR), Length(7,false) */
    val zip: Rep[String] = column[String]("zip", O.Length(7,varying=false))
  }
  /** Collection-like TableQuery object for table Customer */
  lazy val Customer = new TableQuery(tag => new Customer(tag))

  /** Entity class storing rows of table OrderAssoc
   *  @param pid Database column pid SqlType(INT)
   *  @param cid Database column cid SqlType(INT)
   *  @param quantity Database column quantity SqlType(INT) */
  case class OrderAssocRow(pid: Int, cid: Int, quantity: Int)
  /** GetResult implicit for fetching OrderAssocRow objects using plain SQL queries */
  implicit def GetResultOrderAssocRow(implicit e0: GR[Int]): GR[OrderAssocRow] = GR{
    prs => import prs._
    OrderAssocRow.tupled((<<[Int], <<[Int], <<[Int]))
  }
  /** Table description of table order_assoc. Objects of this class serve as prototypes for rows in queries. */
  class OrderAssoc(_tableTag: Tag) extends profile.api.Table[OrderAssocRow](_tableTag, Some("sales"), "order_assoc") {
    def * = (pid, cid, quantity) <> (OrderAssocRow.tupled, OrderAssocRow.unapply)
    /** Maps whole row to an option. Useful for outer joins. */
    def ? = ((Rep.Some(pid), Rep.Some(cid), Rep.Some(quantity))).shaped.<>({r=>import r._; _1.map(_=> OrderAssocRow.tupled((_1.get, _2.get, _3.get)))}, (_:Any) =>  throw new Exception("Inserting into ? projection not supported."))

    /** Database column pid SqlType(INT) */
    val pid: Rep[Int] = column[Int]("pid")
    /** Database column cid SqlType(INT) */
    val cid: Rep[Int] = column[Int]("cid")
    /** Database column quantity SqlType(INT) */
    val quantity: Rep[Int] = column[Int]("quantity")

    /** Foreign key referencing Customer (database name order_assoc_ibfk_2) */
    lazy val customerFk = foreignKey("order_assoc_ibfk_2", cid, Customer)(r => r.id, onUpdate=ForeignKeyAction.NoAction, onDelete=ForeignKeyAction.Cascade)
    /** Foreign key referencing Product (database name order_assoc_ibfk_1) */
    lazy val productFk = foreignKey("order_assoc_ibfk_1", pid, Product)(r => r.id, onUpdate=ForeignKeyAction.NoAction, onDelete=ForeignKeyAction.Cascade)
  }
  /** Collection-like TableQuery object for table OrderAssoc */
  lazy val OrderAssoc = new TableQuery(tag => new OrderAssoc(tag))

  /** Entity class storing rows of table Product
   *  @param id Database column id SqlType(INT), AutoInc, PrimaryKey
   *  @param name Database column name SqlType(VARCHAR), Length(200,true)
   *  @param price Database column price SqlType(INT) */
  case class ProductRow(id: Int, name: String, price: Int)
  /** GetResult implicit for fetching ProductRow objects using plain SQL queries */
  implicit def GetResultProductRow(implicit e0: GR[Int], e1: GR[String]): GR[ProductRow] = GR{
    prs => import prs._
    ProductRow.tupled((<<[Int], <<[String], <<[Int]))
  }
  /** Table description of table product. Objects of this class serve as prototypes for rows in queries. */
  class Product(_tableTag: Tag) extends profile.api.Table[ProductRow](_tableTag, Some("sales"), "product") {
    def * = (id, name, price) <> (ProductRow.tupled, ProductRow.unapply)
    /** Maps whole row to an option. Useful for outer joins. */
    def ? = ((Rep.Some(id), Rep.Some(name), Rep.Some(price))).shaped.<>({r=>import r._; _1.map(_=> ProductRow.tupled((_1.get, _2.get, _3.get)))}, (_:Any) =>  throw new Exception("Inserting into ? projection not supported."))

    /** Database column id SqlType(INT), AutoInc, PrimaryKey */
    val id: Rep[Int] = column[Int]("id", O.AutoInc, O.PrimaryKey)
    /** Database column name SqlType(VARCHAR), Length(200,true) */
    val name: Rep[String] = column[String]("name", O.Length(200,varying=true))
    /** Database column price SqlType(INT) */
    val price: Rep[Int] = column[Int]("price")
  }
  /** Collection-like TableQuery object for table Product */
  lazy val Product = new TableQuery(tag => new Product(tag))
}
