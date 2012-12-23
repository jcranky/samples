package com.jcranky.tdc2011

case class User(nome: String, idade: Int)

object User {
  def printaCaseClass(user: User): Unit = {
    println(user.nome)
    println(user.idade)
    println(user.hashCode)
    println(user.toString)
    println(user.equals(User("jcranky", 99)))
  }
  
  def printaUser(user: User) = user match {
    case User(_, idade) if idade > 18 => println("maior de idade")
    case _ => println("algum outro user")
  }
}
