package scaladores20

import akka.actor.{Actor, Props}
import akka.pattern.ask
import akka.routing.RoundRobinRouter
import akka.util.Timeout
import scala.collection.immutable.HashMap
import scala.concurrent.Future
import scala.concurrent.duration._
import scala.io.Source

class DataSplitterActor extends Actor {
  import context.dispatcher
  implicit val timeout = Timeout(5 seconds)
  
  val dataExtractors = context.actorOf(Props[DataExtractorActor].withRouter(RoundRobinRouter(nrOfInstances = 4)))
  
  def receive = {
    case ProcessFile(file, encoding) =>
      val data = Source.fromFile(file, encoding)
      // tail used to remove the headers
      val stateData = data.getLines.toList.tail.groupBy(line => line.split(";")(3))
      val futures = stateData.map { case (state, data) =>
          (dataExtractors ? StateData(state, data)).mapTo[StateTotal]
      }
      
      val requester = sender
      val emptyResult = Result(HashMap[String, BigDecimal]())
      
      val future = Future.fold(futures)(emptyResult) { (result, stateTotal) =>
        Result(result.stateValues + (stateTotal.state -> stateTotal.value))}
      
      future.onSuccess {
        case result: Result => requester ! result
      }
      future.onFailure {
        case e: Exception => e.printStackTrace
      }
  }
}
