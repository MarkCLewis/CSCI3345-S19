import org.scalatest._
import org.scalatestplus.play._
import play.api.http.MimeTypes
import play.api.test._
import org.scalatestplus.play.guice.GuiceOneServerPerSuite

class BrowserTempSpec extends PlaySpec with GuiceOneServerPerSuite with OneBrowserPerSuite with HtmlUnitFactory {
  "The TempApp" must {
    "provide a web driver" in {
      go to s"http://localhost:$port/tempWelcome"
      pageTitle mustBe "Historical Temps for SA"
      click on "month"
      textField("month").value = "5"
      click on "year"
      textField("year").value = "1986"
      submit()
      eventually { pageTitle mustBe "Temp Table for 5/1986" }
    }
  }
}