package com.jcranky.qconsp2015

import play.api.libs.json.{JsValue, JsString}

object qcon11ImplicitsClass {
  val jsString = JsString("qconsp")

  val jsValue: JsValue = jsString
  val jsStringCast: JsString = jsValue.asInstanceOf[JsString]
}
