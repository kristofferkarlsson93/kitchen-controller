name := "kitchencontroller"

version := "0.1"

scalaVersion := "2.12.8"

val akkaVersion = "2.5.20"
val akkaHttpVersion = "10.1.7"

val circeVersion = "0.10.0"
val playVersion = "2.6.1"

libraryDependencies ++= Seq(
  "com.typesafe.akka" %% "akka-actor" % akkaVersion,
  "com.typesafe.akka" %% "akka-stream" % akkaVersion,
  "com.typesafe.akka" %% "akka-stream-testkit" % akkaVersion,
  "com.typesafe.akka" %% "akka-http" % akkaHttpVersion,
  "com.typesafe.akka" %% "akka-http-testkit" % akkaHttpVersion,
  "de.heikoseeberger" %% "akka-http-circe" % "1.25.2",
  "de.heikoseeberger" %% "akka-http-play-json" % "1.14.0",
  "io.circe" %% "circe-core" % circeVersion,
  "io.circe" %% "circe-generic" % circeVersion,
  "io.circe" %% "circe-parser" % circeVersion,
  "org.scalactic" %% "scalactic" % "3.0.5",
  "org.scalatest" %% "scalatest" % "3.0.5" % "test",
  "com.typesafe.play" %% "play-json" % playVersion,
  "org.mongodb.scala" %% "mongo-scala-driver" % "2.4.2",
  "ch.megard" %% "akka-http-cors" % "0.4.0"
)