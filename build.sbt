name := "play-ebean-example"

version := "0.0.1-SNAPSHOT"

scalaVersion := "2.11.8"

lazy val root = (project in file(".")).enablePlugins(PlayJava, PlayEbean)
  
libraryDependencies += jdbc
libraryDependencies += "com.adrianhurt" %% "play-bootstrap" % "1.0-P25-B3"
libraryDependencies += "mysql" % "mysql-connector-java" % "5.1.21"

// play json 组件
libraryDependencies += "com.typesafe.play" %% "play-json" % "2.6.0-M1"


//  解决Vagrant里的热更新
PlayKeys.fileWatchService := play.runsupport.FileWatchService.sbt(2000)