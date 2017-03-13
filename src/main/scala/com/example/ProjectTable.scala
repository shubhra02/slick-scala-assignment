package com.example

import com.example.Models.Project
import slick.jdbc.PostgresProfile.api._

import scala.concurrent.Future
import slick.model.ForeignKey

 trait ProjectTable extends EmployeeTable{

   this : MySqlDBComponent =>
   import driver.api._
  class ProjectTable(tag: Tag) extends Table[Project](tag, "projectTable"){
    val projectId = column[Int]("projectid", O.PrimaryKey)
    val empId = column[Int]("empid")
    val pName = column[String]("project_name")
    val projectDuration = column[Double]("project_duration")
    val empProjectFK = foreignKey("emp_project_fk", empId, employeeTableQuery)(_.id)

    def * = (projectId, empId, pName, projectDuration) <> (Project.tupled, Project.unapply)
  }
   val projectQuery = TableQuery[ProjectTable]
}

trait ProjectRepo extends ProjectTable {
  this : MySqlDBComponent =>
  import driver.api._            //object of db to hit query to db

  def create = db.run(projectQuery.schema.create)
  def insert(pro: Project) = db.run {
    projectQuery += pro
  }
  def delete(id: Int): Future[Int] = {
    val query = projectQuery.filter(data => data.projectId === id)
    val delQuery = query.delete
    db.run(delQuery)

  }

  def update(pId: Int, name: String): Future[Int] = {
    val query = projectQuery.filter(_.projectId === pId)
    val res = query.map(_.pName).update(name)
    db.run(res)
  }

  def upsert(proj: Project): Future[Int] = db.run{
    projectQuery.insertOrUpdate(proj)

  }

  def getAllProjects: Future[Seq[Project]] = db.run{
    projectQuery.result
  }

  def oldProjects: Future[Seq[Project]] = db.run{
    projectQuery.filter(_.projectDuration > 2.0).result
  }



}
object ProjectRepo extends ProjectRepo with MySqlDBComponent{

}
