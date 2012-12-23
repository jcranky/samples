package com.jcranky.tdc2011

class Animal

trait Fala { def fala }

trait Anda { def andar = println("andando") }

trait Nada { def nadar = println("nadando") }

class Pato extends Animal with Anda with Nada with Fala {
  override def fala = println("quack!")
}
