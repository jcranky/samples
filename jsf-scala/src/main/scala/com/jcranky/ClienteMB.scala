package com.jcranky

import javax.faces.bean.ManagedBean
import scala.collection.JavaConverters._
import scala.reflect.BeanProperty

@ManagedBean(name = "clienteMB")
class ClienteMB {
  def getHello = "Hello do MB em Scala"

  def getClientes = List(
    Cliente("Paulo 'JCranky'", "paulo.siqueira@gmail.com"),
    Cliente("Teste", "teste@teste.com")
  ).asJava
}

case class Cliente(@BeanProperty nome: String, @BeanProperty email: String)
