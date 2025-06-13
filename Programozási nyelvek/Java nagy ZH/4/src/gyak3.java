import java.util.concurrent.atomic.AtomicReference;

public class gyak3 {
    public static void main(String[] args) {
        AtomicReference<String> stringAtomicReference = new AtomicReference<>("Idle");
        final int threadCount = 10;

        Thread[] threads = new Thread[threadCount];

        for (int i = 0; i < threadCount; i++) {
            threads[i] = new Thread(() -> {
                // Tevékeny várakozás
                while (!stringAtomicReference.compareAndSet("Idle", "InProgress")) {
                    // Ciklusban ellenőrizzük az állapotot
                }

                try {
                    Thread.sleep(100); // 100 ms alvás
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }

                stringAtomicReference.set("Idle"); // Visszaállítás "Idle"-ra
            });

            threads[i].start();
        }

        // Megvárjuk a szálak befejeződését
        for (int i = 0; i < threadCount; i++) {
            try {
                threads[i].join();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
