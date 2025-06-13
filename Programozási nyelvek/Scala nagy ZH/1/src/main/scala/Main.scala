import scala.annotation.tailrec

def insert[T](item:T, list:List[T]): List[T] = {
  @tailrec
  def insert[T](item:T, list:List[T], i:Int, res:List[T]): List[T] = {
    list match {
      case Nil => if (i % 2 == 0) res :+ item else res
      case h :: tail =>
        if (i % 3 == 0)
          insert(item = item, list = list, i = i+1, res = res :+ item)
        else
          insert(item = item, list = tail, i = i+1, res = res :+ h)
    }
  }
  insert(item, list, 1, List())
}

def primE(number:Int): Boolean = {
  @tailrec
  def primE(number: Int, i:Int): Boolean = {
    if (i * i > number) true
    else if (number % i == 0) false
    else primE(number, i + 1)
  }

  if (number <= 1) false else primE(number, 2)
}

@tailrec
def forallInt(list:List[Int], condition:Int => Boolean): Boolean = {
  list match {
    case Nil => true
    case h :: t => if (condition(h)) forallInt(t, condition) else false
  }
}

def countABC(string: String): Map[Char, Int] = {
  @tailrec
  def countABC(chars:List[Char], map:Map[Char, Int]): Map[Char, Int] = {
    chars match {
      case Nil => map
      case h :: t => countABC(t, map + (h -> (map.getOrElse(h, 0) + 1)))
    }
  }

  countABC(string.toList, Map())
}

object Main extends App {
  val list1 = List(1, 2, 3, 4, 5, 6, 7)
  /*val list2 = List('a', 'b', 'c', 'd')
  println(insert(8, list1))
  println(insert('5', list2))
  println(insert(5, list2))*/

  /*println(primE(17))
  println(primE(55))
  println(list1.filter(item => primE(item)))*/

  /*// Example usage
  val numbers = List(2, 4, 6, 8)
  val allEven = forallInt(numbers, _ % 2 == 0)
  println(allEven) // Output: true

  val notAllEven = forallInt(numbers, _ > 5)
  println(notAllEven) // Output: false*/

  // Példa használat
  val result = countABC("abccabdefec")
  println(result) // Eredmény: Map(a -> 2, b -> 2, c -> 2)
}

