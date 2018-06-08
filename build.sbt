name := "CreditCardFraudDetection"

version := "1.0"

scalaVersion := "2.12.6"

lazy val akkaVersion = "2.5.12"

libraryDependencies ++= Seq(
  "com.typesafe.akka" %% "akka-actor" % akkaVersion,
  "com.typesafe.akka" %% "akka-testkit" % akkaVersion,
  "org.scalatest" %% "scalatest" % "3.0.5" % "test"
)

libraryDependencies += "ch.qos.logback" % "logback-classic" % "1.2.3"