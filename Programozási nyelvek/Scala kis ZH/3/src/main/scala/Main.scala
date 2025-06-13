import scala.annotation.tailrec

object Main extends App {
  val li = List()
  val list3 = 4 :: 5 :: 2 :: 6 :: Nil
  val list4 = 5.8 :: 0.2 :: 1.9 :: Nil
  println(list3)
  println(reverse(list3))
  println(len(list4))
  println(average(list4))
}

def reverse(list:List[Int]): List[Int] = {
  @tailrec
  def reverse(list:List[Int], res:List[Int]): List[Int] = {
    list match {
      case Nil => res
      case head :: tail => reverse(tail, head :: res)
    }
  }

  reverse(list, List())
}

def len(list: List[Double]): Int = {
  def len(list: List[Double], leng:Int): Int = {
    list match
      case Nil => leng
      case head :: tail => len(tail, leng+1)
  }
  len(list, 0)
}

def average(list: List[Double]): Option[Double] = {
  @tailrec
  def average(list:List[Double], sum:Double, count:Int): Option[Double] = {
    list match
      case Nil => if(count == 0) None else Some(sum/count)
      case head :: tail => average(tail, sum + head, count + 1)
  }

  average(list, 0.0, 0)
}