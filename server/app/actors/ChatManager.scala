package actors

import akka.actor.Actor
import akka.actor.ActorRef

class ChatManager extends Actor {
  import ChatManager._
  
  var chatters = List[ActorRef]()
  
  def receive = {
    case NewChatter(nc) => chatters ::= nc
    case Message(m) => for(c <- chatters) c ! ChatWebSocketActor.SendMessage(m)
    case m => println("Unhandled message in ChatManager: "+m)
  }
}

object ChatManager {
  case class NewChatter(c: ActorRef)
  case class Message(m: String)
}