package actors

import akka.actor.Props
import akka.actor.ActorRef
import akka.actor.Actor

class ChatWebSocketActor(out: ActorRef, manager: ActorRef) extends Actor {
  import ChatWebSocketActor._
  
  manager ! ChatManager.NewChatter(self)
  
  def receive = {
    case s: String => manager ! ChatManager.Message(s)
    case SendMessage(m) => out ! m
    case m => println("Unhandled message in ChatWebSocketActor: "+m)
  }
}

object ChatWebSocketActor {
  def props(out: ActorRef, manager: ActorRef) = Props(new ChatWebSocketActor(out, manager))
  
  case class SendMessage(m: String)
}