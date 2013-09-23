package scaladores20

import akka.actor.{Actor, Props}

class SmasherEntry extends Actor {
  override def preStart(): Unit = {
    val dataSmasherActor = context.actorOf(Props[DataSmasherActor])
    dataSmasherActor ! Start
  }
  
  def receive = {
    case Done => context.stop(self)
  }
}

object SmasherMain {
  def main(args: Array[String]) = akka.Main.main(Array("scaladores20.SmasherEntry"))
}
