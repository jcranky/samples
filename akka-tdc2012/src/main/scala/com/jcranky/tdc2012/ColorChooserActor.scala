package com.jcranky.tdc2012

import akka.actor.{Actor, Props}
import akka.dispatch.Await
import akka.pattern.ask
import akka.util.duration._
import akka.util.Timeout
import scala.util.Random

class ColorChooserActor extends Actor {
  val rPartChooser = context.actorOf(Props[ColorPartChooserActor], "r-color-part-chooser")
  val gPartChooser = context.actorOf(Props[ColorPartChooserActor], "g-color-part-chooser")
  val bPartChooser = context.actorOf(Props[ColorPartChooserActor], "b-color-part-chooser")
  implicit val timeout = Timeout(60 seconds)
  
  def receive = {
    case FindColor(positions) => 
      positions foreach {pos =>
        val localSender = sender
        val colorFuture = for {
          r <- (rPartChooser ? FindColorPart).mapTo[ColorPartFound]
          g <- (gPartChooser ? FindColorPart).mapTo[ColorPartFound]
          b <- (bPartChooser ? FindColorPart).mapTo[ColorPartFound]
        } yield (r.value, g.value, b.value)
      
//        val color = Await.result(colorFuture, 5 seconds)
//        sender ! ColorFound(pos, color)

        colorFuture onSuccess {
          case color => localSender ! ColorFound(pos, color)
        }
        colorFuture onFailure {
          case e => println("problema!! " + e.getMessage)
        }
      }
  }
}

class ColorPartChooserActor extends Actor {
  def receive = {
    case FindColorPart => sender ! ColorPartFound(Random.nextFloat)
  }
}
