package com.jcranky.qconsp2015

import play.api.libs.json.{JsValue, JsArray, JsString}

object qcon1inferencia extends App {
  val idade = 99
  val nome = "jcranky"

  val jsString = JsString("qcon jsString")
  val jsArray = JsArray(Seq(JsString("qcon jsArray"), jsString))


  // tipo pai: explicitar!
//  val jsValue: JsValue = jsString
  val jsValue: JsValue = jsArray
}
