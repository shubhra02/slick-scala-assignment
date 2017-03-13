package com.example

import com.example.Models.{Dependent, Project}

import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global
import slick.model.ForeignKey

trait DependentTable extends EmployeeTable{

  this : MySqlDBComponent =>
  import driver.api._
  class DependentTable(tag: Tag) extends Table[Dependent](tag, "dependentTable"){
    val dependentId = column[Int]("dependentid", O.PrimaryKey)
    val referenceEmpId = column[Int]("empid")
    val dependentName = column[String]("dependent_name")
    val relation = column[String]("relation")
    val age = column[Int]("age")
    val empDependentFK = foreignKey("emp_dependent_fk", referenceEmpId, employeeTableQuery)(_.id)

    def * = (dependentId, referenceEmpId, dependentName, relation, age) <> (Dependent.tupled, Dependent.unapply)
  }
  val DependentQuery = TableQuery[DependentTable]
}

trait DependentRepo extends DependentTable {
  this : MySqlDBComponent =>
  import driver.api._            //object of db to hit query to db

  def create = db.run(DependentQuery.schema.create)

  def insert(dep: Dependent) = db.run {
    DependentQuery += dep
  }

  def delete(d_id: Int): Future[Int] = {
    val query = DependentQuery.filter(data => data.dependentId === d_id)
    val delQuery = query.delete
    db.run(delQuery)

  }

  def update(pId: Int, d_name: String): Future[Int] = {
    val query = DependentQuery.filter(_.dependentId === pId)
    val res = query.map(_.dependentName).update(d_name)
    db.run(res)
  }

  def upsert(dep: Dependent): Future[Int] = db.run{
    DependentQuery.insertOrUpdate(dep)

  }

  def getAllDependents: Future[Seq[Dependent]] = db.run{
    DependentQuery.result
  }

  def customQuery: Future[Seq[Dependent]] = db.run{
    DependentQuery.filter(_.referenceEmpId === 11).result
  }



}
object DependentRepo extends DependentRepo with MySqlDBComponent{

}

