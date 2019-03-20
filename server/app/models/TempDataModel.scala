package models

case class TempData(day: Int, dayOfYear: Int, month: Int, stateId: String,
  year: Int, precip: Double, tave: Double, tmax: Double, tmin: Double)

object TempDataModel {
  private val file = if (new java.io.File("data/SanAntonioTemps.csv").exists) "data/SanAntonioTemps.csv" else "../data/SanAntonioTemps.csv"
  private val source = scala.io.Source.fromFile(file)
  private val lines = source.getLines().drop(2)
  val data = lines.map { line =>
    val p = line.split(",")
    TempData(p(0).toInt, p(1).toInt, p(2).toInt, p(3),
      p(4).toInt, p(5).toDouble, p(6).toDouble, p(7).toDouble, p(8).toDouble)
  }.toArray

  def monthData(month: Int, year: Int): Array[TempData] = {
    data.filter(td => td.month == month && td.year == year)
  }

  def averageHighForYear(year: Int): Double = {
    val highs = data.filter(_.year == year).map(_.tmax)
    highs.sum / highs.length
  }
}