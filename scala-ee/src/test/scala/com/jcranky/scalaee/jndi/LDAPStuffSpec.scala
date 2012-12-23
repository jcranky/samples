package com.jcranky.scalaee.jndi

import javax.naming.ldap.{LdapName, Rdn}
import org.specs2.mutable.Specification
import scala.collection.JavaConverters._

class LDAPStuffSpec extends Specification {
  "playing around with LDAP and DirContexts" should {
    "break the full LDAP name in pieces" in {
      val name = new LdapName("c=BR, o=alto_austral")
      name.getRdns.asScala must contain(new Rdn("c", "BR"), new Rdn("o", "alto_austral")).only
    }
  }
}
