package com.jcranky.qconsp2015

object qcon13TypeClasses {
  //def sum[B >: A](implicit num: Numeric[B]): B

//  implicit object StringIsNumeric extends StringIsNumeric

  val soma = List(1,2,3,4).sum
//  val somaStr = List("1","2","3","4").sum


//  trait StringIsNumeric extends Numeric[String] {
//    override def plus(x: String, y: String): String = x + y
//    override def toDouble(x: String): Double = Double.unbox(x)
//    override def toFloat(x: String): Float = Float.unbox(x)
//    override def toInt(x: String): Int = Int.unbox(x)
//    override def negate(x: String): String = x
//    override def fromInt(x: Int): String = x.toString
//    override def toLong(x: String): Long = Long.unbox(x)
//    override def times(x: String, y: String): String = x * y
//    override def minus(x: String, y: String): String = x - y
//    override def compare(x: String, y: String): Int = x.compareTo(y)
//  }
}
