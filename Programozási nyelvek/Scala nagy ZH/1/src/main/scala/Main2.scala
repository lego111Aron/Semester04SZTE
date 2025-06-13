import scala.annotation.tailrec

def insert[T](item:T, list:List[T]): List[T] = {
  @tailrec
  def insert[T](item: T, list: List[T], i:Int, res:List[T]): List[T] = {
    list match {
      case Nil => if(i%3==0) res :+ item else res
      case h::t =>
        if(i%3==0)
          insert(item, list, i+1, res :+ item)
        else
          insert(item, t, i+1, res :+ h)
    }
  }

  insert(item, list, 1, List())
}

def specialLazyList(): LazyList[Int] = {
  def generate(n: Int): LazyList[Int] = {
    val base = n
    val multiplied = if (n % 3 == 0) base * 2 else base
    val modified = if (n % 5 == 0) multiplied + 3 else multiplied
    modified #:: generate(n + 1)
  }

  generate(1)
}

case class Idotartam(ora:Int, perc:Int, masoddperc:Int) {
  def hozzaad(extramasodperc:Int): Idotartam = {
    val ossz = masoddperc + perc*60 + ora*3600 + extramasodperc
    val mOra = ossz / 3600
    val ossz2 = ossz % 3600
    val mPerc = ossz2 / 60
    val ossz3 = ossz % 60
    Idotartam(mOra, mPerc, ossz3)
  }
}

object Main2 extends App {
  println(70/60)
  println(70%60)

  val ido = new Idotartam(0,118,59)
  println(ido.hozzaad(1))

  val list1 = List(1, 2, 3, 4, 5, 6, 7)
  val list2 = List('a', 'b', 'c', 'd')
  println(insert(8, list1))
  println(insert('5', list2))
  println(insert(5, list2))

  val first20 = specialLazyList().take(20).toList
  println(first20)
  
  /*println(primE(17))
  println(primE(55))
  println(list1.filter(item => primE(item)))*/

  /*// Example usage
  val numbers = List(2, 4, 6, 8)
  val allEven = forallInt(numbers, _ % 2 == 0)
  println(allEven) // Output: true

  val notAllEven = forallInt(numbers, _ > 5)
  println(notAllEven) // Output: false*/

  /*// Példa használat
  val result = countABC("abccabdefec")
  println(result) // Eredmény: Map(a -> 2, b -> 2, c -> 2)*/
}
