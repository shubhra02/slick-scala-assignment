package com.example

import slick.jdbc.JdbcProfile
import slick.jdbc.MySQLProfile.api._
import slick.jdbc.MySQLProfile

trait MySqlDBComponent{

 val driver : JdbcProfile =  MySQLProfile
  val db : Database = Database.forConfig("mysqlDB")

}
