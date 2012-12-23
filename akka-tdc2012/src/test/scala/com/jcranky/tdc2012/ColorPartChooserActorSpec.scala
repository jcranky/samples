package com.jcranky.tdc2012

import akka.dispatch.Await
import akka.pattern.ask
import akka.testkit.TestActorRef
import akka.util.{Duration, Timeout}
import org.specs2.mutable.Specification

class ColorPartChooserActorSpec extends Specification {
  "the color part chooser actor" should {
    "reply with a float color component value after receiving a FindColorPart message" in new ActorScope {
      val colorPartActor = TestActorRef[ColorPartChooserActor]
      val futureResult = colorPartActor.ask(FindColorPart)(Timeout(5000)).mapTo[ColorPartFound]
      val result = Await.result(futureResult, Duration("5 seconds"))
      
      result.value must be_>=(0f)
    }
  }
}
