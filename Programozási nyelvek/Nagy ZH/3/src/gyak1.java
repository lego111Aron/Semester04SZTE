/*
import java.util.Random;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class gyak1 {
    public static void main(String[] args) throws InterruptedException {
        System.out.println("The program is started");
        SharedInt sharedInt = new SharedInt();
        final int threadsCount = 20;
        Thread[] threads = new Thread[threadsCount];

        for (int i = 0; i < threadsCount; i++) {
            threads[i] = new Thread(new Modifier(sharedInt));
            threads[i].start();
        }

        for (int i = 0; i < threadsCount; i++)
            threads[i].join();

        System.out.println(sharedInt.number);
    }
}

class SharedInt {
    int number;
    private final Lock lock = new ReentrantLock();
//    private final Condition condition = lock.newCondition();

    public void setValue(int newValue) {
        lock.lock();
        number = newValue;
        lock.unlock();
    }

    public void decrement() {
        lock.lock();
        number--;
        lock.unlock();
    }
}

class Modifier implements Runnable {
    private SharedInt sharedInt;
    private final Random random = new Random();
    Modifier(SharedInt sharedInt) {
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
}*/


import java.util.Random;
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class gyak1 {
    public static void main(String[] args) throws InterruptedException {
        System.out.println("The program is started");
        SharedInt sharedInt = new SharedInt();
        final int threadsCount = 20;
        Thread[] threads = new Thread[threadsCount];

        for (int i = 0; i < threadsCount; i++) {
            threads[i] = new Thread(new Modifier(sharedInt));
            threads[i].start();
        }

        for (int i = 0; i < threadsCount; i++)
            threads[i].join();

        System.out.println(sharedInt.number);
    }
}

class SharedInt {
    int number;
    private Semaphore semaphore = new Semaphore(1);

    public void setValue(int newValue) throws InterruptedException {
        semaphore.acquire();
        number = newValue;
        semaphore.release();
    }

    public void decrement() throws InterruptedException {
        semaphore.acquire();
        number--;
        semaphore.release();
    }
}

class Modifier implements Runnable {
    private SharedInt sharedInt;
    private final Random random = new Random();
    Modifier(SharedInt sharedInt) {
        this.sharedInt = sharedInt;
    }

    @Override
    public void run() {
        try {
            if (random.nextBoolean()) {
                sharedInt.setValue(random.nextInt(100));
            } else {
                sharedInt.decrement();
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}