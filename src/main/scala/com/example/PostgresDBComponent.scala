package com.example

import slick.jdbc.JdbcProfile
import slick.jdbc.PostgresProfile.api._
import slick.jdbc.PostgresProfile

trait PostgresDBComponent {
  val driver : JdbcProfile = PostgresProfile
  val db : Database = Database.forConfig("myPostgresDB")

}
