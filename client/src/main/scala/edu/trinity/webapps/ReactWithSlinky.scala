package edu.trinity.webapps

import scala.scalajs.js.Date

import org.scalajs.dom._

import slinky.core._
import slinky.core.annotations.react
import slinky.core.facade.ReactElement
import slinky.web.ReactDOM
import slinky.web.html._
import org.scalajs.dom.html

object ReactWithSlinky {
  def runApp: Unit = {
    ReactDOM.render(
      div(
        h1("Hello, world!"),
        Timer(),
        TodoApp()),
      document.getElementById("content"))
  }

  /**
   * Example from https://slinky.dev/
   */
  @react class Timer extends Component {
    type Props = Unit
    case class State(seconds: Int)

    override def initialState = State(seconds = 0)

    def tick(): Unit = {
      setState(prevState =>
        State(prevState.seconds + 1))
    }

    private var interval = -1

    override def componentDidMount(): Unit = {
      interval = window.setInterval(() => tick(), 1000)
    }

    override def componentWillUnmount(): Unit = {
      window.clearInterval(interval)
    }

    override def render(): ReactElement = {
      div(
        "Seconds: ", state.seconds.toString)
    }
  }

  
  /**
   * Example from https://slinky.dev/
   */
  case class TodoItem(text: String, id: Long)

  @react class TodoApp extends Component {
    type Props = Unit
    case class State(items: Seq[TodoItem], text: String)

    override def initialState = State(Seq.empty, "")

    def handleChange(e: SyntheticEvent[html.Input, Event]): Unit = {
      val eventValue = e.target.value
      setState(_.copy(text = eventValue))
    }

    def handleSubmit(e: SyntheticEvent[html.Form, Event]): Unit = {
      e.preventDefault()

      if (state.text.nonEmpty) {
        val newItem = TodoItem(
          text = state.text,
          id = Date.now().toLong)

        setState(prevState => {
          State(
            items = prevState.items :+ newItem,
            text = "")
        })
      }
    }

    override def render() = {
      div(
        h3("TODO"),
        TodoList(items = state.items),
        form(onSubmit := (handleSubmit(_)))(
          input(
            onChange := (handleChange(_)),
            value := state.text),
          button(s"Add #${state.items.size + 1}")))
    }
  }
  
  @react class TodoList extends StatelessComponent {
    case class Props(items: Seq[TodoItem])

    override def render() = {
      ul(
        props.items.map { item =>
          li(key := item.id.toString)(item.text)
        })
    }
  }

}