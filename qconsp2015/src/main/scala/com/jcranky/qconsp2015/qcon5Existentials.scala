package com.jcranky.qconsp2015

object qcon5Existentials {

  // não compila
//  def printList(list: List) = list.mkString(",")

  def printList(list: List[_]) = list.mkString(",")
}
