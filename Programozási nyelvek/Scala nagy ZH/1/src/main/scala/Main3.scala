object HanoiTornyai {

  def hanoi(n: Int, from: String, to: String, aux: String): List[String] = {
    @scala.annotation.tailrec
    def loop(stack: List[(Int, String, String, String)], acc: List[String]): List[String] = {
      stack match {
        case Nil => acc.reverse
        case (1, f, t, _) :: rest =>
          loop(rest, s"Lépés: $f -> $t" :: acc)
        case (k, f, t, a) :: rest =>
          val newStack =
            (k - 1, a, t, f) ::
              (1, f, t, a) ::
              (k - 1, f, a, t) ::
              rest
          loop(newStack, acc)
      }
    }

    loop(List((n, from, to, aux)), Nil)
  }

  def main(args: Array[String]): Unit = {
    val korongokSzama = 3
    println(s"Hanoi tornyai megoldása $korongokSzama koronggal (tailrekurzív megoldás):")
    val lepesek = hanoi(korongokSzama, "A", "C", "B")
    lepesek.foreach(println)
  }
}
