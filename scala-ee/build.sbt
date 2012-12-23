
name := "Java EE Snippets written in Scala"

scalaVersion := "2.9.2"

libraryDependencies ++= Seq(
    "com.sun.messaging.mq" % "fscontext" % "4.6-b01",
    "org.glassfish.main.javaee-api" % "javax.jms" % "3.1.2",
    "org.specs2" %% "specs2" % "1.11" % "test"
)

// TODO: implement the setup and cleanup bellow

testOptions in Test += Tests.Setup( () => println("start glassfish or other here!") )

testOptions in Test += Tests.Cleanup( () => println("stop glassfish or other here!") )

seq(netbeans.NetbeansTasks.netbeansSettings:_*)
