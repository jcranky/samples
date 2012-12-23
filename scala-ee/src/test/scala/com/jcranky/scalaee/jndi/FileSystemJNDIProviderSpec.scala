package com.jcranky.scalaee.jndi

import java.io.File
import java.util.Hashtable
import javax.naming.directory.{InitialDirContext, SearchControls}
import javax.naming.{Context, InitialContext, NotContextException}
import org.specs2.mutable.Specification
import scala.collection.JavaConverters._

class FileSystemJNDIProviderSpec extends Specification {
  "the jndi file system provider" should {
    val config = Map(
      Context.INITIAL_CONTEXT_FACTORY -> "com.sun.jndi.fscontext.RefFSContextFactory",
      Context.PROVIDER_URL -> ("file:///tmp")
    ) asJava
    val ctx = new InitialContext(new Hashtable[String, String](config))
  
    "create and delete a directory" in {
      ctx.createSubcontext("jcranky")
      new File("/tmp/jcranky").exists must beTrue
      
      ctx.destroySubcontext("jcranky")
      new File("/tmp/jcranky").exists must beFalse
    }
    
    "throw exception if trying to handle the ctx as a DirContext" in {
      val dirCtx = new InitialDirContext(new Hashtable[String, String](config))
      dirCtx.search("somewhere", "something", new SearchControls()) must throwA[NotContextException](
        message="Not an instance of DirContext")
    }
  }
}
