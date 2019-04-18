package edu.trinity.webapps

import edu.trinity.webapps.shared.SharedMessages
import org.scalajs.dom

object ScalaJSExample {

  def main(args: Array[String]): Unit = {
    if(dom.document.getElementById("scalajsShoutOut") != null) {
      dom.document.getElementById("scalajsShoutOut").textContent = SharedMessages.itWorks
    }
    if(dom.document.getElementById("sales-stuff") != null) SalesClient.pageSetup()
  }
  
  case class NotAString(i: Int, s: String)
  val a: Any = NotAString(5, "hi")
  a.asInstanceOf[NotAString].i*2 
}
