package scaladores20

case object Start
case object Done

case class ProcessFile(file: String, encoding: String)
case class Result(stateValues: Map[String, BigDecimal])

case class StateData(state: String, data: List[String])
case class StateTotal(state: String, value: BigDecimal)
