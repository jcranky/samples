package com.jcranky.tdc2011

import scala.actors.Actor

object ActorTester {
  def test: Unit = {
    MeuActor ! "Oi mundo!"
    MeuActor.start
    MeuActor ! "Oi mundo denovo!"
  }
}

object MeuActor extends Actor {
  def act = {
    while(true) {
      receive {
//        case msg: String => println("recebi: " + msg)
        case msg => println("recebi: " + msg)
      }
    }
  }
}
