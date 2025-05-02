public static void main(String[] args) throws InterruptedException {
    System.out.println("Hello");

    Thread.currentThread().join();

    System.out.println("Word!");

    /*
    * A "World!" szöveg nem kerül kiíratásra, mert a Thread.currentThread().join();
    * hívás blokkolja a fő szálat, amíg az be nem fejeződik. Mivel a fő szál soha
    *  nem fejeződik be, a program soha nem jut el a "World!" kiíratásához.
    * */
}
