import Company.employees

// Trait: közös viselkedés
trait Workable {
  def work(): Unit = println("Working...")
}

// Alaposztály – nem final, hogy lehessen örökölni
class Employee(val name: String, val salary: Int) extends Workable {
  def displayInfo(): String = {
    work()
    s"Name: $name, Salary: $salary"
  }
}

// Leszármazott osztály, final – ne lehessen belőle örökölni
final class Manager(name: String, salary: Int, val bonus: Int)
  extends Employee(name, salary) {

  override def displayInfo(): String = {
    work()
    s"Name: $name, Salary: $salary, Bonus: $bonus"
  }
}

// Objektum a céghez
object Company {
  // Privát, belső alkalmazotti lista
  private var employees: Vector[Employee] = Vector()

  // Alkalmazott hozzáadása a megadott indexre
  def addEmployee(emp: Employee, index: Int): Unit = {
    if (index >= 0 && index <= employees.length)
      employees = employees.patch(index, Seq(emp), 0)
    else
      println(s"Hibás index: $index")
  }

  // Alkalmazott információ lekérdezése
  def getEmployeeInfo(index: Int): String = {
    employees.lift(index) match {
      case Some(emp) => emp.displayInfo()
      case None => s"Nincs alkalmazott a(z) $index. indexen."
    }
  }

  // Alkalmazott eltávolítása
  def fireEmployee(index: Int): Unit = {
    if (index >= 0 && index < employees.length)
      employees = employees.patch(index, Nil, 1)
    else
      println(s"Hibás index: $index")
  }
}

// Tesztelés App trait-tel
object Main extends App {
  val emp1 = new Employee("Kovács Béla", 400000)
  val emp2 = new Manager("Szabó Ágnes", 600000, 150000)
  val emp3 = new Employee("Tóth László", 350000)

  Company.addEmployee(emp1, 0)
  Company.addEmployee(emp2, 1)
  Company.addEmployee(emp3, 1) // középre szúrjuk

  println(Company.getEmployeeInfo(0))
  println(Company.getEmployeeInfo(1))
  println(Company.getEmployeeInfo(2))

  println("-- Kirúgás után --")
  Company.fireEmployee(1)

  println(Company.getEmployeeInfo(0))
  println(Company.getEmployeeInfo(1))
}

trait Workable2 {
  def work(): String = {
    "Working..."
  }
}

class Employee2(val name:String, val salary:Int) extends Workable2 {
  def displayInfo(): String = {
    work()
    s"nev ──> $name, fizetes ──> $salary"
  }
}

final class Manager2(name:String, salary:Int, val bonus:Int) extends Employee2(name, salary) with Workable2 {
  override def displayInfo(): String = {
    work() + "\n" + super.displayInfo() + s", bonus ──> $bonus"
  }
}

object Company2 {
  var employees:Vector[Employee2] = Vector()

  def addEmployee(Employee: Employee2, Index:Int): Unit = {
    employees = employees.patch(Index, Seq(Employee), 0)
  }

  def getEmployeeInfo(Index:Int): String = {
    employees.lift(Index) match
      case Some(emp) => emp.displayInfo()
      case None => "Hiba"
  }

  def fireEmployee(Index:Int): Unit = {
    employees = employees.patch(Index, Nil, 1)
  }
}
