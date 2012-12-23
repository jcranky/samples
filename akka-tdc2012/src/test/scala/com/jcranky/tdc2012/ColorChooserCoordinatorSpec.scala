package com.jcranky.tdc2012

import akka.actor.Props
import akka.testkit.TestActorRef
import org.specs2.mock.Mockito
import org.specs2.mutable.Specification

class ColorChooserCoordinatorSpec extends Specification with Mockito {
  "the color chooser coordinator actor" should {
    "call the position generator method to get positions to send to the subordinated actors" in new ActorScope {
      val coord = mock[Coordinator]
      val actor = TestActorRef(
        Props(new ColorChooserCoordinator(coord)),
        TestActorRef(new ColorfulMasterActor(mock[ColorfulMaster]), "color-master"),
        "color-chooser-coordinator")
      
      val (init, end) = (Position(0, 0), Position(10, 10))
      
      actor ! FindColorForRange(init, end)
      
      there was one(coord).positions(init, end)
    }
  }
  
  "the coordinator" should {
    val coord = new Coordinator()
    
    "generated the list of positions" in {
      coord.positions(Position(1, 1), Position(2, 2)) must contain(
        Position(1, 1), Position(1, 2), Position(2, 1), Position(2, 2)
      ).only
    }
  }
}
