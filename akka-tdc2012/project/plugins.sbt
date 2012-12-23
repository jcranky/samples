
resolvers ++= Seq(
    "remeniuk repo" at "http://remeniuk.github.com/maven",
    "Typesafe Repo" at "http://repo.typesafe.com/typesafe/releases/"
)

libraryDependencies += "org.netbeans" %% "sbt-netbeans-plugin" % "0.1.4"

addSbtPlugin("com.typesafe.akka" % "akka-sbt-plugin" % "2.0.2")
