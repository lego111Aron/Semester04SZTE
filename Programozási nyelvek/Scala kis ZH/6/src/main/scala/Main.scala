import scala.annotation.tailrec

object Main extends App {
  println(fibonacci(7))

  val list = List(1,2,3,4,5)
  println(reverse(list))
}

def reverse(list: List[Int]): List[Int] = {
  @tailrec
  def reverse(list: List[Int], res:List[Int]): List[Int] = {
    list match
      case Nil => res
      case last :: init => reverse(init, last :: res)
  }
  reverse(list, List())
}

def fibonacci(n:Int): Int = {
  def fibonacci(n:Int, res:Int, next:Int): Int = {
    n match
      case 0 | -1 => res
//      case n if(n <= 0) => res
      case n => fibonacci(n-1, next, res+next)
  }
  fibonacci(n-1, 0, 1)
}