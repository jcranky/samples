
import sbt._
import Keys._
import akka.sbt.AkkaKernelPlugin
import akka.sbt.AkkaKernelPlugin.{ Dist, outputDirectory, distJvmOptions}
     
object ColorfulDemoBuild extends Build {
  
  val Organization = "com.jcranky"
  val Version = "1.0"
  val ScalaVersion = "2.9.2"
     
  lazy val ColorKernel = Project(
    id = "color-kernel",
    base = file("."),
    settings = defaultSettings ++ AkkaKernelPlugin.distSettings ++ Seq(
      libraryDependencies ++= Dependencies.colorKernel ++ Dependencies.testDeps,
      distJvmOptions in Dist := "-Xms256M -Xmx1024M",
      outputDirectory in Dist := file("target/color-dist")
    )
  )
     
  lazy val buildSettings = Defaults.defaultSettings ++ Seq(
    organization := Organization,
    version := Version,
    scalaVersion := ScalaVersion,
    crossPaths := false,
    organizationName := "JCranky",
    organizationHomepage := Some(url("http://jcranky.com"))
  )
  lazy val defaultSettings = buildSettings ++ Seq(
    resolvers += "Typesafe Repo" at "http://repo.typesafe.com/typesafe/releases/",
     
    // compile options
    scalacOptions ++= Seq("-encoding", "UTF-8", "-deprecation", "-unchecked"),
    javacOptions ++= Seq("-Xlint:unchecked", "-Xlint:deprecation")
  )
}
     
object Dependencies {
  object V {
    val Akka = "2.0.2"
  }
     
  val colorKernel = Seq(
    "com.typesafe.akka" % "akka-actor" % V.Akka,
    "com.typesafe.akka" % "akka-remote" % V.Akka,
    "com.typesafe.akka" % "akka-kernel" % V.Akka,
    "com.typesafe.akka" % "akka-slf4j" % V.Akka,
    "ch.qos.logback" % "logback-classic" % "1.0.0"
  )
  
  val testDeps = Seq(
    "com.typesafe.akka" % "akka-testkit" % V.Akka % "test",
    "org.specs2" %% "specs2" % "1.11" % "test",
    "org.mockito" % "mockito-core" % "1.9.0" % "test"
  )
}
