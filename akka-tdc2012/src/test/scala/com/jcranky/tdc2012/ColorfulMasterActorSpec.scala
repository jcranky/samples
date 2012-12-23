package com.jcranky.tdc2012

import akka.testkit.TestActorRef
import org.specs2.mock.Mockito
import org.specs2.mutable.Specification

class ColorfulMasterActorSpec extends Specification with Mockito {
  "the color chooser master actor" should {
    "send message to the color coordinators" in new ActorScope {
      val masterInstance = mock[ColorfulMaster]
      val master = TestActorRef(new ColorfulMasterActor(masterInstance))
      
      master ! StartColorPicking
      
      there was one(masterInstance).positionsToColor(any)
    }
    
    "get the correct positions to color" in {
      val colorMaster = new ColorfulMaster(800, 600, (x, y, c) => Unit)
      val postList = colorMaster.positionsToColor(2)
      
      postList(0) must_== FindColorForRange(Position(1, 1), Position(400, 600))
      postList(1) must_== FindColorForRange(Position(401, 1), Position(800, 600))
    }
  }
}
