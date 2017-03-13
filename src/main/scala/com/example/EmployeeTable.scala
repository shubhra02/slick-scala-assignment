package com.example

import com.example.Models.Employee
import slick.jdbc.PostgresProfile.api._

import scala.concurrent.Future
trait EmployeeTable {
  this : MySqlDBComponent =>
  import driver.api._
   class EmployeeTable(tag: Tag) extends Table[Employee](tag, "emptable"){
     val id = column[Int]("id", O.PrimaryKey)
     val name = column[String]("name")
    val experience = column[Double]("experience")

     def * = (id, name, experience) <> (Employee.tupled, Employee.unapply)

   }
  protected val employeeTableQuery = TableQuery[EmployeeTable]

}

trait EmployeeRepo extends EmployeeTable {
  this : MySqlDBComponent =>
  import driver.api._            //object of db to hit query to db

  def create: Future[Unit] = db.run(employeeTableQuery.schema.create)
  def insert(emp: Employee) = db.run {
    employeeTableQuery += emp
  }
  def delete(id: Int): Future[Int] = {
    val query = employeeTableQuery.filter(x => x.id === id)
    val delQuery = query.delete
    db.run(delQuery)

  }
  def update(id: Int, name: String): Future[Int] = {
    val query = employeeTableQuery.filter(_.id === id)
          val res = query.map(_.name).update(name)
        db.run(res)
  }

  def upsert(employee: Employee): Future[Int] = db.run{
    employeeTableQuery.insertOrUpdate(employee)

  }

  def getAllEmployees: Future[Seq[Employee]] = db.run{
    employeeTableQuery.result
  }

  def seniorEmployees: Future[Seq[Employee]] = db.run{
    employeeTableQuery.filter(_.experience > 2.0).result
  }


}

object EmployeeRepo extends EmployeeRepo with MySqlDBComponent{

}