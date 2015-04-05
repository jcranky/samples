package com.jcranky.qconsp2015

import java.io.File

import scala.io.Source

object qcon2infFunctions {
  def soma(x: Int, y: Int) = x + y

  def multiplica(x: Double, y: Double) = x * y

  // qual o tipo do retorno?
  def podeEntrar(nome: String, idade: Int) =
    if (nome == "jcranky") true
    else if (idade >= 18) true
    else false

  // qual o tipo do retorno?
  // leArquivo(new File("build.sbt"))
  def leArquivo(f: File) = try {
    Source.fromFile(f).getLines().mkString("\n")
  } catch {
    case e: Exception => throw new RuntimeException("qcon exception")
  }
}
