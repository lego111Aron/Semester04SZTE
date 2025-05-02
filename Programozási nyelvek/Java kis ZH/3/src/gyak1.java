import java.util.concurrent.Semaphore;
import java.util.Random;

class SharedInt {
    private int value;
    private final Semaphore semaphore = new Semaphore(1);

    public void setValue(int newValue) {
        try {
            semaphore.acquire();
            value = newValue;
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            semaphore.release();
        }
    }

    public void decrement() {
        try {
            semaphore.acquire();
            value--;
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            semaphore.release();
        }
    }

    public int getValue() {
        return value;
    }
}

class Modifier implements Runnable {
    private final SharedInt sharedInt;
    private final Random random = new Random();

    public Modifier(SharedInt sharedInt) {
        this.sharedInt = sharedInt;
    }

    @Override
    public void run() {
        if (random.nextBoolean()) {
            sharedInt.setValue(random.nextInt(100));
        } else {
            sharedInt.decrement();
        }
    }
}

public class gyak1 {
    public static void main(String[] args) {
        SharedInt sharedInt = new SharedInt();
        Thread[] threads = new Thread[20];

        for (int i = 0; i < 20; i++) {
            threads[i] = new Thread(new Modifier(sharedInt));
            threads[i].start();
        }

        for (int i = 0; i < 20; i++) {
            try {
                threads[i].join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.println("Final value: " + sharedInt.getValue());
    }
}