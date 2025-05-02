import java.util.concurrent.Semaphore;

public class gyak2 {
}

class ParkingLot2 {
    private final Semaphore capacity = new Semaphore(10);

    public void enter() {
        try {
            capacity.acquire(1);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public void leavel() {
        capacity.release();
//        capacity.notify();
    }
}

class Car {
    
}