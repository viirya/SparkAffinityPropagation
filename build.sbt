name := "spark-affinitypropagation"

version := "1.0"

scalaVersion := "2.12.14"

scalacOptions := Seq("-unchecked", "-deprecation", "-encoding", "utf8", "-feature")

libraryDependencies ++= {
  Seq(
    "org.scalatest"                 %%  "scalatest"                 % "3.2.9"       % "test",
    "org.apache.spark"              %%  "spark-core"                % "3.1.1"       % "provided",
    "org.apache.spark"              %%  "spark-mllib"               % "3.1.1"       % "provided",
    "org.apache.spark"              %%  "spark-graphx"              % "3.1.1"       % "provided"
  )
}

mergeStrategy in assembly ~= ( (old) => {
    case PathList("META-INF", "ECLIPSEF.RSA" ) => MergeStrategy.discard
    case PathList("META-INF", "mailcap" ) => MergeStrategy.discard
    case PathList("com", "esotericsoftware", "minlog", ps ) if ps.startsWith("Log") => MergeStrategy.discard
    case PathList("plugin.properties" ) => MergeStrategy.discard
    case PathList("META-INF", ps @ _* ) => MergeStrategy.discard
    case PathList("javax", "activation", ps @ _* ) => MergeStrategy.first
    case PathList("org", "apache", "commons", ps @ _* ) => MergeStrategy.first
    case PathList("org", "apache", "hadoop", "yarn", ps @ _* ) => MergeStrategy.first
    case x => old(x)
  }
)

