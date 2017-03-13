package com.example

import slick.jdbc.JdbcProfile
import slick.jdbc.MySQLProfile.api._
import slick.jdbc.MySQLProfile


trait DBComponent {
  val driver: JdbcProfile
  val db: Database

}
