package edu.trinity.webapps

import org.scalajs.dom
import org.querki.jquery._

object CanvasDrawing {
    private var x = 100
    private var y = 100
    private val canvas = dom.document.getElementById("canvas").asInstanceOf[dom.raw.HTMLCanvasElement]
    private val context = canvas.getContext("2d")

    $("#canvas").click(() => {
        y -= 10
        drawToCanvas()
    })

    def drawToCanvas(): Unit = {

        context.fillRect(x, y, 30, 30)
    }
}