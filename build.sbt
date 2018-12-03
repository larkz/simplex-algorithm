name := "simplex-algorithm"
version := "0.5"
scalaVersion := "2.11.11"

libraryDependencies ++= Seq(
  "io.spray" %% "spray-json" % "1.3.3",
  "com.typesafe" % "config" % "1.2.1"
)

resolvers += Classpaths.typesafeReleases

mainClass in(Compile, run) := Some("main.SimplexMain")
mainClass in(Compile, packageBin) := Some("main.SimplexMain")

