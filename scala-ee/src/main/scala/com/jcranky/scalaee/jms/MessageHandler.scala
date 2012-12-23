package com.jcranky.scalaee.jms

import javax.jms.{ConnectionFactory, Destination, Session, MessageListener, Message}
import javax.naming.InitialContext

class MessageHandler {
  val ctx = new InitialContext  
  val connFactory: ConnectionFactory = ctx.lookup("ConnectionFactory").asInstanceOf[ConnectionFactory]
    
  def send(msg: String, destName: String) = {
    val conn = connFactory.createConnection
    val session = conn.createSession(false, Session.AUTO_ACKNOWLEDGE)
    val dest: Destination = ctx.lookup(destName).asInstanceOf[Destination]
    
    val producer = session.createProducer(dest)
    
    val textMsg = session.createTextMessage
    textMsg.setText(msg)
    
    producer.send(textMsg)
    conn.close
  }
  
  def receive(destName: String) = {
    val conn = connFactory.createConnection
    val session = conn.createSession(false, Session.AUTO_ACKNOWLEDGE)
    val dest: Destination = ctx.lookup(destName).asInstanceOf[Destination]
    
    val consumer = session.createConsumer(dest)
    conn.start
    
    val msg = consumer.receive(1000)  // synch
    
    // asynch
    consumer.setMessageListener(new MessageListener() {
        def onMessage(msg: Message) = {
          println("received: " + msg)
        }
      })
  }
}
