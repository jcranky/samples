package com.jcranky.tdc2012

import akka.dispatch.Await
import akka.pattern.ask
import akka.testkit.TestActorRef
import akka.util.Duration
import akka.util.Timeout
import org.specs2.mutable.Specification

class PingPongActorSpec extends Specification {
  "the ping pong actor" should {
    "reply with pong after receiving ping" in new ActorScope {
      val actor = TestActorRef[PingPongActor]
      val result = actor.ask(Ball("ping"))(Timeout(5000))
      val ball = Await.result(result, Duration("5 seconds")).asInstanceOf[Ball]
      
      ball.side must_== "pong"
    }
  }
}
