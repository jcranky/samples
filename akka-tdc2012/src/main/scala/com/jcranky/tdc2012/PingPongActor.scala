package com.jcranky.tdc2012

import akka.actor.Actor

class PingPongActor extends Actor {
  def receive = {
    case Ball("ping") => sender ! Ball("pong")
  }
}

case class Ball(side: String)
