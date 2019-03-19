import org.scalatest._
import org.scalatestplus.play._
import play.api.http.MimeTypes
import play.api.test._
import org.scalatestplus.play.guice.GuiceOneServerPerSuite

class BrowserApplicationSpec extends PlaySpec with GuiceOneServerPerSuite with OneBrowserPerSuite with HtmlUnitFactory {
  "The Name App" must {
    "remember then forget a name" in {
      go to s"http://localhost:$port/enterName"
      click on "name"
      textField("name").value = "Mark"
      submit()
      eventually {
        find("#greeting").foreach(e => e.text mustBe "Hello, Mark.")
        go to s"http://localhost:$port/forgetName"
        eventually {
          find("#greeting").foreach(e => e.text mustBe "Hello, unnamed.")
        }
      }
    }
  }
}