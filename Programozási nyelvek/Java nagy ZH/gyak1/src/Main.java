import java.util.concurrent.Semaphore;

public class Main {
    public static void main(String[] args) {
        // Létrehozzuk a tömböt és inicializáljuk negatív számokkal
        double[] array = {-1.0, -2.0};
        Semaphore semaphore = new Semaphore(0); // Szinkronizáció kezdőértékkel

        // Első szál: tömb első értékének módosítása
        Thread thread1 = new Thread(() -> {
            array[0] = 10.5; // Például 10.5-re módosítjuk
            System.out.println("Első szál: Módosította az első értéket: " + array[0]);
            semaphore.release(); // Jelzés a másik szálnak
        });

        // Második szál: összeadás végrehajtása
        Thread thread2 = new Thread(() -> {
            try {
                semaphore.acquire(); // Várakozás a jelzésre
                array[1] += array[0]; // Hozzáadás
                System.out.println("Második szál: Hozzáadta az első értéket a másodikhoz: " + array[1]);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        // Szálak indítása
        thread1.start();
        thread2.start();

        // Main szál: várakozás a szálak befejezésére
        try {
            thread1.join();
            thread2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Végső tömb kiírása
        System.out.println("Végső tömb tartalma: [" + array[0] + ", " + array[1] + "]");
    }
}
