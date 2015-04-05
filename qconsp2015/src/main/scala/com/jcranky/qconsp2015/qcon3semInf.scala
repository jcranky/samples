package com.jcranky.qconsp2015

object qcon3semInf {
  def factorial(x: Int): Int =
    if (x == 1) x
    else factorial(x - 1) * x

  // tipos obrigatórios, apesar de óbvios... =/
  def soma(x: Int = 1, y: Int = 2) = x + y
}
