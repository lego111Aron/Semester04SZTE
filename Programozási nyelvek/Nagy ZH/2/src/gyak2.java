public class gyak2 {
    public static void main(String[] args) {
        System.out.println("Started...");

        ParkingLot parkingLot = new ParkingLot();
        Thread[] threads = new Thread[100];
        for (int i = 0; i < 100; i++) {
            threads[i] = new Car(parkingLot);
            threads[i].start();
        }
    }
}

class ParkingLot {
    private final int capacity = 10;
    private int load;
    ParkingLot() {
        this.load = 0;
    }

    synchronized public void enter() throws InterruptedException {
        while (load == capacity) {
            System.out.println("Car is waiting...");
            this.wait();
        }

        System.out.println("Car entered to the parking lot.");
        load++;
        notifyAll();
    }

    synchronized public void leave() {
        /*while (load == 0) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }*/
        System.out.println("Car is left.");
        load--;
        notifyAll();
    }
}

class Car extends Thread {
    private ParkingLot parkingLot;
    Car(ParkingLot parkingLot) {
        this.parkingLot = parkingLot;
    }

    @Override
    public void run() {
        try {
            parkingLot.enter();
            // Add a delay to simulate the car staying in the parking lot for a while
//            Thread.sleep(100);
            parkingLot.leave();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}