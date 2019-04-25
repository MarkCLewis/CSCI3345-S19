package edu.trinity.webapps

import slinky.core._
import slinky.web.ReactDOM
import slinky.web.html._
import org.scalajs.dom._

object ReactWithSlinky {
  def runApp: Unit = {
    ReactDOM.render(
      h1("Hello, world!"),
      document.getElementById("content"))
  }
}