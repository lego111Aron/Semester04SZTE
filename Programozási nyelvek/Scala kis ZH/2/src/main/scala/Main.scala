import scala.annotation.tailrec

object Main extends App {
  println(multiplication(41, 2))
  println(multiplication(4, 5))
}

def fibonacci(n: Int): Int = {
  @tailrec
  def fibonacci(n:Int, res:Int, next:Int): Int = {
    n match
      case n if n <= 0 => res
      case n => fibonacci(n - 1, next, res+next)
  }
  fibonacci(n-1, 0, 1)
}

def multiplication(a:Int, b:Int): Int = {
  def multiplication(a:Int, b:Int, res:Int): Int = {
    a match
      case 0 => res
      case n => multiplication(a-1, b, res+b)
  }

  multiplication(a, b, 0)
}

def multiplicationIf(a:Int, b:Int): Int = {
  def multiplicationIf(a:Int, b:Int, res:Int): Int = {
    if(a == 0)
      res
    else
      multiplicationIf(a-1, b, res+b)
  }

  multiplicationIf(a, b, 0)
}