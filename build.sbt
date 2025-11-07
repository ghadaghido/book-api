name := "books"

version := "1.0"

lazy val root = (project in file("."))
  .enablePlugins(PlayJava)
  .settings(
    scalaVersion := "2.13.12",
    libraryDependencies ++= Seq(
      guice,

      // PostgreSQL driver
      "org.postgresql" % "postgresql" % "42.7.3",

      // JPA / Hibernate with Jakarta support
      "jakarta.persistence" % "jakarta.persistence-api" % "3.1.0",
      "org.hibernate.orm" % "hibernate-core" % "6.2.7.Final",

      // Play JPA integration (Jakarta version)
      "org.playframework" %% "play-java-jpa" % "3.0.9",

      // JSON handling
      "com.fasterxml.jackson.core" % "jackson-databind" % "2.14.3"
    )
  )
