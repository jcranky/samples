package com.jcranky.tdc2012

import akka.actor.{Actor, ActorSystem, Props}
import java.awt.Color

class ColorfulMasterActor(colorful: ColorfulMaster) extends Actor {
  val colorCoordinator = context.actorOf(Props(new ColorChooserCoordinator()), "color-chooser-coordinator")
  val colorCoordinator2 = context.actorOf(Props(new ColorChooserCoordinator()), "color-chooser-coordinator-2")
  
  def receive = {
    case StartColorPicking =>
      colorful.start
      val posList = colorful.positionsToColor(1)
      colorCoordinator ! posList(0)
//      colorCoordinator2 ! posList(1)
      
    case ColorFound(pos, (r, g, b)) =>
      colorful.oneMore
      colorful.paintColor(pos.x, pos.y, new Color(r, g, b))
  }
}

class ColorfulMaster(width: Int, height: Int, val paintColor: (Int, Int, Color) => Unit) {
  private val totalColorsCount = (width-1) * (height-1)
  private var currentColorsCount = 0
  private var startTime: Long = _
  private var timeSpent: Long = _
  
  def start = startTime = System.currentTimeMillis
  def oneMore = {
    currentColorsCount += 1
    if (totalColorsCount == currentColorsCount) {
      timeSpent = System.currentTimeMillis - startTime
      println("[Timing] painting everything took %d milliseconds".format(timeSpent))
    }
  }
  def positionsToColor(coordNumber: Int) = 1 to coordNumber map {x =>
    FindColorForRange(
      Position(width / coordNumber * x - width / coordNumber + 1, 1),
      Position(width / coordNumber * x, height))
  }
}

object ColorfulSystem {
  val system = ActorSystem("TDC2012-Demo")
}
