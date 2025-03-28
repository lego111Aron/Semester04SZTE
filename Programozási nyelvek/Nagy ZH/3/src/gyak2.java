/*
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class gyak2 {
    public static void main(String[] args) {
        ParkingLot parkingLot = new ParkingLot();
        final int threadsCount = 100;
        Thread[] threads = new Thread[threadsCount];

        for (int i = 0; i < threadsCount; i++) {
            threads[i] = new Car(parkingLot);
            threads[i].start();
        }
    }
}

class ParkingLot {
    private final int capacity = 10;
    private int state = 0;
    private final Lock lock = new ReentrantLock();
    private final Condition notFull = lock.newCondition();
    private final Condition notEmpty = lock.newCondition();

    public void enter() throws InterruptedException {
        lock.lock();
        // Meg lehet-e oldani if-el?
        while (state == capacity) {
            notFull.await();
        }

        state++;
        notEmpty.signal();
        lock.unlock();
    }

    public void leave() throws InterruptedException {
        lock.lock();
        // Kell-e vizsgálni, hogy üres-e a parkoló?
        while (state == 0) {
            notEmpty.await();
        }

        state--;
        notFull.signal();
        lock.unlock();
    }
}

class Car extends Thread {
    private final ParkingLot parkingLot;
    Car(ParkingLot parkingLot) {
        this.parkingLot = parkingLot;
    }

    @Override
    public void run() {
        try {
            parkingLot.enter();
//            Thread.sleep(100); // parkolás
            parkingLot.leave();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}*/


import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class gyak2 {
    public static void main(String[] args) {
        ParkingLot parkingLot = new ParkingLot();
        final int threadsCount = 100;
        Thread[] threads = new Thread[threadsCount];

        for (int i = 0; i < threadsCount; i++) {
            threads[i] = new Car(parkingLot);
            threads[i].start();
        }
    }
}

class ParkingLot {
    private final Semaphore capacity = new Semaphore(10);

    public void enter() throws InterruptedException {
        capacity.acquire();
    }

    public void leave() throws InterruptedException {
        capacity.release();
    }
}

class Car extends Thread {
    private final ParkingLot parkingLot;
    Car(ParkingLot parkingLot) {
        this.parkingLot = parkingLot;
    }

    @Override
    public void run() {
        try {
            parkingLot.enter();
//            Thread.sleep(100); // parkolás
            parkingLot.leave();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}