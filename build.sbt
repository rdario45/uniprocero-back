name := """backend"""
organization := "co.com.uniandes"

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayJava)

scalaVersion := "2.13.0"

libraryDependencies += guice
libraryDependencies += "com.github.javafaker" % "javafaker" % "0.2"
libraryDependencies += "io.vavr" % "vavr" % "0.10.0"
