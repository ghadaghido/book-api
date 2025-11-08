name := "books"

version := "1.0"

lazy val root = (project in file("."))
  .enablePlugins(PlayJava, UniversalPlugin)
  .settings(
    scalaVersion := "2.13.12",

    // Library dependencies
    libraryDependencies ++= Seq(
      guice,

      // PostgreSQL JDBC driver
      "org.postgresql" % "postgresql" % "42.7.3",

      // JPA & Hibernate
      "jakarta.persistence" % "jakarta.persistence-api" % "3.1.0",
      "org.hibernate.orm" % "hibernate-core" % "6.2.7.Final",

      // Play JPA integration
      "org.playframework" %% "play-java-jpa" % "3.0.9",

      // JSON support
      "com.fasterxml.jackson.core" % "jackson-databind" % "2.14.3",

      // Swagger annotations
      "io.swagger.core.v3" % "swagger-annotations" % "2.2.15",

      // JUnit 4
      "junit" % "junit" % "4.13.2" % Test,

      // Play test helpers
      "org.playframework" %% "play-test" % "3.0.9" % Test
    ),

    // Run JUnit with full output
    testOptions += Tests.Argument(TestFrameworks.JUnit, "-v", "-a"),

    // Fork JVM during tests
    fork in Test := true
  )
