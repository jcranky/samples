package com.jcranky.tdc2012

import akka.actor.{Actor, Props}
import akka.routing.FromConfig
import scala.collection.mutable.ListBuffer

class ColorChooserCoordinator(coord: Coordinator = new Coordinator) extends Actor {
  val colorChooserRouter = context.actorOf(
    Props[ColorChooserActor].withRouter(FromConfig()), "color-chooser-router")
  
  def receive = {
    case FindColorForRange(init, end) => 
      var buf = ListBuffer[Position]()
      coord.positions(init, end) foreach(pos => {
          buf += pos
          if (buf.size >= 5000) {
            colorChooserRouter.tell(FindColor(buf.toList), sender)
            buf.clear
          }
        })
  }
}

class Coordinator {
  def positions(init: Position, end: Position) =
    for {x <- init.x to end.x; y <- init.y to end.y} yield Position(x, y)
}
