import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class gyak1 {
    public static void main(String[] args) throws InterruptedException {
        ParkingLot parkingLot = new ParkingLot();
        ExecutorService executorService = Executors.newFixedThreadPool(5);

        for (int i = 0; i < 100; i++) {
            executorService.submit(new Car(parkingLot));
            Thread.sleep(100);
        }

        executorService.shutdown();
    }
}

class ParkingLot {
    final int capacity = 10;
    private int state = 0;
    private final Lock lock = new ReentrantLock();
    private final Condition notFull = lock.newCondition();
    private final Condition notEmpty = lock.newCondition();

    public void enter() throws InterruptedException {
        lock.lock();
        while (capacity == state) {
            notFull.await();
        }

        state++;
        notEmpty.signal();
        lock.unlock();
    }

    public void parking() throws InterruptedException {
        Thread.sleep(300);
    }

    public void leave() throws InterruptedException {
        lock.lock();
        while (state == 0) {
            notEmpty.await();
        }

        state--;
        notFull.signal();
        lock.unlock();
    }
}

class Car implements Callable<Void> {
    private final ParkingLot parkingLot;
    Car(ParkingLot parkingLot) {
        this.parkingLot = parkingLot;
    }

    @Override
    public Void call() throws Exception {
        parkingLot.enter();
        parkingLot.parking();
        parkingLot.leave();
        return null;
    }
}