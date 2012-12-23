package com.jcranky.tdc2012

import akka.pattern.ask
import akka.testkit.TestActorRef
import akka.util.Timeout
import org.specs2.mutable.Specification

class ColorChooserActorSpec extends Specification {
  "the color chooser actor" should {
    "ask for the individual color components from its child actors and send a color back to the caller" in new ActorScope {
      val colorChooserActor = TestActorRef[ColorChooserActor]
      val futureColor = colorChooserActor.ask(FindColor(Position(0, 0)))(Timeout(3000)).mapTo[ColorFound]
      
      futureColor.isCompleted must beTrue
      futureColor.value.get.right.get.pos must_== Position(0, 0)
      futureColor.value.get.right.get.rgb must beAnInstanceOf[Tuple3[Float, Float, Float]]
    }
  }
}
