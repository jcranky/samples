package com.jcranky.qconsp2015

import play.api.libs.json.{JsArray, JsString, JsValue}

object qcon6UpperBounds {
  val jsString = JsString("qconsp")
  val jsArray = JsArray(Seq(jsString, JsString("jcranky")))

  def jsonPrint[T <: JsValue](t: T) = println(t.toString)

//  def jsonPrint(json: JsValue) = println(json.toString)
}
