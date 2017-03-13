import com.example.Models.{Dependent, Employee, Project}
import com.example._

import scala.concurrent.Await
import scala.concurrent.duration.Duration
import scala.concurrent.ExecutionContext.Implicits.global

object ForAllRelations extends App{

  /**
    * for Employee table
    */
  val tableCreate = Await.result(EmployeeRepo.create, Duration.Inf)
  val insertRes = EmployeeRepo.insert(Employee(11, "ankit",2.0))
  val res = insertRes.map{
    res => s"$res row inserted"
  }.recover {
    case ex: Throwable => ex.getMessage
  }

  val deleteRes = EmployeeRepo.delete(14)

  val updateRes = Await.result(EmployeeRepo.update(12, "ankit"), Duration.Inf)

  val getAllRes = Await.result(EmployeeRepo.getAllEmployees, Duration.Inf)
  val upsertRes = Await.result(EmployeeRepo.upsert(Employee(15, "shubhra", 3.0)), Duration.Inf)
  val customRes = Await.result(EmployeeRepo.seniorEmployees, Duration.Inf)


  /**
    * for Project table
    */
  /*val tableCreate = Await.result(ProjectRepo.create, Duration.Inf)
  val insertRes = ProjectRepo.insert(Project(1,11,"Carbon Data", 2.5))
  val res = insertRes.map{
    res => s"$res row inserted"
  }.recover {
    case ex: Throwable => ex.getMessage
  }

  val deleteRes = ProjectRepo.delete(1)

  val updateRes = Await.result(ProjectRepo.update(1, "Hp"), Duration.Inf)

  val getAllRes = Await.result(ProjectRepo.getAllProjects, Duration.Inf)
  val upsertRes = Await.result(ProjectRepo.upsert(Project(2, 11, "google", 3.0)), Duration.Inf)
  val customRes = Await.result(ProjectRepo.oldProjects, Duration.Inf)
*/

  /**
    * for Dependent Table
    */
/*val tableCreate = Await.result(DependentRepo.create, Duration.Inf)
val insertRes = DependentRepo.insert(Dependent(115,11,"Varun", "brother", 25))
val res = insertRes.map{
res => s"$res row inserted"
}.recover {
case ex: Throwable => ex.getMessage
}

val deleteRes = DependentRepo.delete(111)

val updateRes = Await.result(DependentRepo.update(1, "Hp"), Duration.Inf)

val getAllRes = Await.result(DependentRepo.getAllDependents, Duration.Inf)
val upsertRes = Await.result(DependentRepo.upsert(Dependent(112, 11, "Anshul", "Sister", 24)), Duration.Inf)
val customRes = Await.result(DependentRepo.customQuery, Duration.Inf)*/


Thread.sleep(10000)


}
