package com.jcranky.tdc2012

import akka.actor.ActorSystem
import com.typesafe.config.ConfigFactory
import org.specs2.mutable.After

trait ActorScope extends After {
  implicit val system = ActorSystem("TDC2012-Demo", ConfigFactory.load("test-app.conf"))
  def after = system.shutdown
}
