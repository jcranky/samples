package com.jcranky.qconsp2015

import play.api.libs.json.{JsValue, JsString}

object qcon9Contravariancia {
//  trait Function1[-T1, +R]

//  val func1: JsValue => String = (x: AnyRef) => x.toString    // OK
  val func2: JsValue => String = (x: JsString) => x.toString  // não compila
}
