public class gyak1 {
    public static void main(String[] args) throws InterruptedException {
        System.out.println("Hello");

        Thread.currentThread().join();

        System.out.println("Word!");
    }
}

/*Az alábbi programrészletben a `main` metódus futtatásakor a következő lépések történnek:

1. Kiírja a konzolra a "Hello" szöveget.
2. A `Thread.currentThread().join();` hívás blokkolja a fő szálat, amíg az aktuális szál be nem fejeződik. Mivel ez a hívás a fő szálban történik, ez egy végtelen blokkolást eredményez, és a program soha nem lép tovább a következő utasításra.
3. A "Word!" szöveg soha nem kerül kiírásra a konzolra, mert a program a `join` hívásnál megakad.

A program tehát a "Hello" kiírása után végtelenül blokkolva marad.*/
