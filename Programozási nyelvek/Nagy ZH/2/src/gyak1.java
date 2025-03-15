import java.util.Random;
import java.util.SimpleTimeZone;

public class gyak1 {
    public static void main(String[] args) {
        SharedInt sharedInt = new SharedInt();
        Thread[] modifiers = new Thread[20];
        for (int i = 0; i < 20; i++){
            modifiers[i] = new Thread(new Modifier(sharedInt));
            modifiers[i].start();
        }

        for (int i = 0; i < 20; i++){
            try {
                modifiers[i].join();
//                System.out.println(sharedInt.number);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

        System.out.println("----------------");
        System.out.println(sharedInt.number);
    }
}

class SharedInt {
    int number;

    synchronized public void setValue(int newValue) {
        number = newValue;
    }

    public void decrement() {
        synchronized (this) {
            number--;
        }
    }
}

class Modifier implements Runnable {
    private Random random = new Random();
    private SharedInt sharedInt;
    Modifier(SharedInt sharedInt) {
        this.sharedInt = sharedInt;
    }

    @Override
    public void run() {
        if (random.nextBoolean())
            sharedInt.setValue(random.nextInt(100));
        else
            sharedInt.decrement();
    }
}