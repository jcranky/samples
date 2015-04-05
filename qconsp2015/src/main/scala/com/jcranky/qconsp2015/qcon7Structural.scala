package com.jcranky.qconsp2015

import java.io.{FileInputStream, File}

object qcon7Structural {
  type Closeable = {def close(): Unit}

//  def close[C <: {def close() : Unit}](c: C) =

  def close[C <: Closeable](c: C) =
    try {
      println("fechando arquivo")
      c.close()
    } catch {
      case e: Exception =>
    }

  close(new FileInputStream(new File("build.sbt")))
}
