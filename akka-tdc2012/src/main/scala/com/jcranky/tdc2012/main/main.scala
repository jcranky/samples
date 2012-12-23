package com.jcranky.tdc2012.main

import akka.actor.Props
import akka.kernel.Bootable
import com.jcranky.tdc2012._
import java.awt.Color

object main extends App {
  val (width, height) = (1000, 500)
  
  val master = ColorfulSystem.system.actorOf(Props(
      new ColorfulMasterActor(new ColorfulMaster(width, height, showUI()))), "color-master")
  master ! StartColorPicking
  
  def showUI(): (Int, Int, Color) => Unit = {
    import java.awt._
    import java.awt.event.{WindowAdapter, WindowEvent}
    import javax.swing._

    val panel = new JPanel()
    panel.setPreferredSize(new Dimension(width, height))
  
    val frame = new JFrame("Akka TDC 2012 Demo")
    frame.setLayout(new BorderLayout())
    frame.add(panel)
    frame.pack()
    frame.addWindowListener(new WindowAdapter() {
        override def windowClosing(e: WindowEvent) = ColorfulSystem.system.shutdown
      })
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE)
    frame.setVisible(true)
    
    (x: Int, y: Int, color: Color) => {
      val g = frame.getGraphics
      g.setColor(color)
      g.drawLine(x, y, x, y)
    }
  }
}

class ColorKernel extends Bootable {
  def startup = {
    ColorfulSystem.system.actorOf(Props[PingPongActor], "dummy")
  }
  
  def shutdown = {
    ColorfulSystem.system.shutdown
  }
}
