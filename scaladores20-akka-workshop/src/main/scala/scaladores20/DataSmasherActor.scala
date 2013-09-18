package scaladores20

import akka.actor.Actor
import java.text.NumberFormat
import java.util.Locale
import scala.io.Source

/**
 * Main / supervisor actor.
 */
class DataSmasherActor extends Actor {
  def receive = {
    case Start =>
      val startTime = System.nanoTime      
      // obviously we need a better way to get the file ... this is just a test scenario
      // data source: http://www.portaldatransparencia.gov.br/planilhas/index.asp
        
      1 to 150 foreach { x =>
      
        // determining file to be processed
        val data = Source.fromFile("src/main/resources/TransfRecAcoesEstadoMunicipios-2012.csv", "iso-8859-1")
        
        // grouping cities by states
        // tail used to remove the headers
        val dataPerState = data.getLines.toList.tail.groupBy(line => line.split(";")(3))
        
        // extracting data
        val valuePerState = dataPerState.map{
          case (state, entries) => (state, entries.foldLeft(0d)(
                (total, line) => total + line.split(";").last.replaceAll(",", ".").toDouble))
        }
        
        valuePerState.foreach {
          case (state, value) =>
            val formatter = NumberFormat.getCurrencyInstance(new Locale("pt", "BR"))
            //println(s"$state - ${formatter.format(value)}")
        }
      }
      
      println(s"Finished in ${(System.nanoTime - startTime) / 1E9} seconds")
      
      sender ! Done
  }
}

