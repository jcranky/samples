package scaladores20

import akka.actor.Actor

class DataExtractorActor extends Actor {
  def receive = {
    case StateData(state, data) =>
      val totalValue = data.foldLeft(BigDecimal(0))(
        (total, line) => total + BigDecimal(line.split(";").last.replaceAll(",", ".")))
      
      sender ! StateTotal(state, totalValue)
  }
}
