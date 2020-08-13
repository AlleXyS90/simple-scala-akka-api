name := "simple-scala-akka-api"

version := "0.1"

scalaVersion := "2.13.3"

val akkaHttpVersion = "10.1.11"

libraryDependencies ++= Seq(
  "com.typesafe.akka" %% "akka-actor"           % "2.6.8",
  "com.typesafe.akka" %% "akka-stream"          % "2.6.8",
  // akka http
  "com.typesafe.akka" %% "akka-http"            % akkaHttpVersion,
  "com.typesafe.akka" %% "akka-http-core"       % akkaHttpVersion,
  "com.typesafe.akka" %% "akka-http-spray-core.json" % akkaHttpVersion
)
