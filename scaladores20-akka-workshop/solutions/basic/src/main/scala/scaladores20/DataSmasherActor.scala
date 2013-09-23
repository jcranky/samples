package scaladores20

import akka.actor.{Actor, Props}
import akka.pattern.ask
import akka.util.Timeout
import java.text.NumberFormat
import java.util.Locale
import scala.concurrent.duration._

/**
 * Main / supervisor actor.
 * 
 * We obviously need a better way to get the file ... this is just a test scenario.
 * Data source: http://www.portaldatransparencia.gov.br/planilhas/index.asp
 */
class DataSmasherActor extends Actor {
  import context.dispatcher
  implicit val timeout = Timeout(5 seconds)
  
  var repeats = 150
  
  def receive = {
    case Start =>
      val startTime = System.nanoTime
      
      val splitter = context.actorOf(Props[DataSplitterActor])
      
      1 to 150 foreach { x=>
        val future = splitter ? ProcessFile("src/main/resources/TransfRecAcoesEstadoMunicipios-2012.csv", "iso-8859-1")
      
        val requester = sender
        future.onSuccess {
          case Result(stateValues) => stateValues.foreach {
              case (state, value) =>
                val formatter = NumberFormat.getCurrencyInstance(new Locale("pt", "BR"))
                //println(s"$state - ${formatter.format(value)}")
            }
          
            repeats -= 1
            if (repeats == 0) {
              requester ! Done
              println(s"Finished in ${(System.nanoTime - startTime) / 1E9} seconds")
            }
        }
        future.onFailure {
          case e: Exception => e.printStackTrace
        }
      }
  }
}
