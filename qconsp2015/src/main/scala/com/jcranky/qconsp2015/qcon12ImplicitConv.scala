package com.jcranky.qconsp2015

import play.api.libs.json.{JsValue, JsArray, JsString}

object qcon12ImplicitConv {
  implicit def stringToJsString(str: String): JsString = JsString(str)
//  implicit def listToJsArray(list: List[_ <: JsValue]): JsArray = JsArray(list)

  val jsString: JsString = "qconsp"
//  val jsArray: JsArray = List("qconsp", "jcranky").map(stringToJsString)
}
