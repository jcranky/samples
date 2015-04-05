package com.jcranky.qconsp2015

import play.api.libs.json.{JsNumber, JsValue}

object qcon8CoInVariance {
  
  // List[+A]
  val nums1: List[JsValue] =
    List[JsValue](JsNumber(1), JsNumber(2), JsNumber(3))

  val nums2: List[JsValue] =
    List[JsNumber](JsNumber(1), JsNumber(2), JsNumber(3))


  // Set[A]
  val numsSet1: Set[JsValue] = Set[JsValue](JsNumber(1), JsNumber(2), JsNumber(3))
//  val numsSet2: Set[JsValue] = Set[JsNumber](JsNumber(1), JsNumber(2), JsNumber(3))
}
