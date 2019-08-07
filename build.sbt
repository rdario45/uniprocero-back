name := """backend"""
organization := "co.com.uniandes"

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayJava)

scalaVersion := "2.13.0"

PlayKeys.devSettings := Seq("play.server.http.port" -> "9001")

libraryDependencies += guice
libraryDependencies += jdbc
libraryDependencies += javaJdbc
libraryDependencies += evolutions
libraryDependencies += "com.github.javafaker" % "javafaker" % "1.0.0"
libraryDependencies += "io.vavr" % "vavr" % "0.10.0"
libraryDependencies += "org.postgresql" % "postgresql" % "42.2.6"
libraryDependencies += "org.jdbi" % "jdbi" % "2.78"
libraryDependencies += "io.vavr" % "vavr-jackson" % "0.9.0"
libraryDependencies += "org.apache.commons" % "commons-lang3" % "3.0"
