import java.util.Random;

public class gyak1 {
    public static void main(String[] args) throws InterruptedException {
        SharedInt sharedInt = new SharedInt();
        Thread[] threads = new Thread[20];

        for (int i = 0; i < 20; i++) {
            threads[i] = new Thread(new Modifier(sharedInt));
            threads[i].start();
        }

        for (int i = 0; i < 20; i++) {
            threads[i].join();
        }

        System.out.println("Final value: " + sharedInt.value);
    }
}

class SharedInt {
    int value;

    // Metódus szintű kölcsönös kizárás
    public synchronized void setValue(int newValue) {
        this.value = newValue;
    }

    // Blokk szintű kölcsönös kizárás
    public void decrement() {
        synchronized (this) {
            this.value--;
        }
    }
}

class Modifier implements Runnable {
    private final SharedInt sharedInt;
    private final Random random = new Random();

    Modifier (SharedInt sharedInt) {
        this.sharedInt = sharedInt;
    }

    @Override
    public void run() {
        if (random.nextBoolean()) {
            sharedInt.setValue(random.nextInt(100)); // Random value between 0 and 99
        } else {
            sharedInt.decrement();
        }

        try {
            Thread.sleep(100); // Sleep to simulate some delay
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        /*while (true) {
            if (random.nextBoolean()) {
                sharedInt.setValue(random.nextInt(100)); // Random value between 0 and 99
            } else {
                sharedInt.decrement();
            }
            try {
                Thread.sleep(100); // Sleep to simulate some delay
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                break;
            }
        }*/
    }
}