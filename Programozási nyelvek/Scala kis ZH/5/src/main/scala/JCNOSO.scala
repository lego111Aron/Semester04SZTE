import scala.annotation.tailrec

// 1. feladat:
case class Komplex(re:Double, img:Double) {
  def szoroz(other:Komplex): Komplex = {
    val nRe = re*other.re - img*other.img
    val nImg = re*other.img + other.re*img
    Komplex(nRe, nImg)
  }
}

// 2. feladat:
object JCNOSO {

  // szóval először azokat a változókat fogadom amiket a feladat is megadott
  def insertion(list:List[Int], item:Int): List[Int] = {
    @tailrec
    def insertion(list:List[Int], item:Int, i:Int, res:List[Int]): List[Int] = {
      list match
        case Nil => if(i % 3 == 0) res :+ item else res
        case head :: tail =>
          if(i % 3 == 0)
            insertion(list, item, i+1, res :+ item)
          else
            insertion(tail, item, i+1, res :+ head)
    }

//    :: -- ezzel tudsz elemhez listát/elemet adni a végeredmény lista
//    :+ ezzel listához elemet tudsz adni a végeredmény lista lesz

    // Tudom, hogy kell egy ciklusváltozó, mivel a lista legelejére nem akarom beszúrni az elemet 1-től indítom
    insertion(list, item, 1, List()) // Illetve létrehozok egy üres listát ebben lesz a végeredmény
  }

  def avg(list:List[Double]): Option[Double] = {
    @tailrec
    def avg(list:List[Double], i:Int, sum:Double): Option[Double] = {
      list match
        case Nil => if(i==0) None else Some(sum/i)
        case head :: tail => avg(tail, i+1, sum+head)
    }

    avg(list, 0, 0)
  }

  // A megoldasok tesztelese
  def main(args: Array[String]): Unit = {
    val kom = new Komplex(1.2, 5.9)
    val kom2 = new Komplex(2.8, 1.0)
    println(kom.szoroz(kom2))

    val list = List(1,2,3,4) // létrehozom a listár
    println(insertion(list = list, item = 5)) // itt nem kell kiírni a list = és az item = -t

    val list2 = List(1.8, 2.2, 3.4, 4.1) // létrehozom a listár
    println(avg(list2)) // itt nem kell kiírni a list = és az item = -t

  }
}
