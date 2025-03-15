public class gyak2 {
    public static void main(String[] args) {
        ParkingLot parkingLot = new ParkingLot();
        for (int i = 0; i < 100; i++) {
            new Car(parkingLot).start();
        }
    }
}

class ParkingLot {
    private final int capacity = 10;
    private int cars = 0;

    public synchronized void enter() {
        while (cars == capacity) {
            try {
                wait();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        cars++;
        System.out.println(Thread.currentThread().getName() + " entered. Cars in lot: " + cars);
//        notifyAll();
    }

    public synchronized void leave() {
        /*while (cars == 0) {
            try {
                wait();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }*/
        cars--;
        System.out.println(Thread.currentThread().getName() + " left. Cars in lot: " + cars);
        notifyAll();
    }
}

class Car extends Thread {
    private final ParkingLot parkingLot;

    public Car(ParkingLot parkingLot) {
        this.parkingLot = parkingLot;
    }

    @Override
    public void run() {
        parkingLot.enter();
        parkingLot.leave();
    }
}