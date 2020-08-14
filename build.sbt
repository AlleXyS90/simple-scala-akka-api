name := "simple-scala-akka-api"

version := "0.1"

scalaVersion := "2.13.3"

val akkaVersion = "2.6.8"
val akkaHttpVersion = "10.2.0"

libraryDependencies ++= Seq(
  "com.typesafe.akka" %% "akka-actor"                 % akkaVersion,
  "com.typesafe.akka" %% "akka-stream"                % akkaVersion,
  "com.typesafe.akka" %% "akka-slf4j"                 % akkaVersion,
  "com.typesafe.akka" %% "akka-testkit"               % akkaVersion,
    // akka http
  "com.typesafe.akka" %% "akka-http"                  % akkaHttpVersion,
  "com.typesafe.akka" %% "akka-http-core"             % akkaHttpVersion,
  "com.typesafe.akka" %% "akka-http-spray-core.json"  % akkaHttpVersion
)
