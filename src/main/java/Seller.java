import java.util.ArrayList;
import java.util.List;

public class Seller {
    private static final int SELL_PROCESS_TIME = 1000;
    private static final int RECEIVE_PROCESS_TIME = 1000;
    private int sellCounter = 0;
    private int makeCounter = 0;
    List<Car> cars = new ArrayList<>();

    public Seller() {

    }

    public int getSellCounter() {
        return sellCounter;
    }

    public int getMakeCounter() {
        return makeCounter;
    }

    public synchronized void sellCar() {
        try {
            sellCounter++;
            System.out.println(Thread.currentThread().getName() + " зашел в автосалон");
            while (cars.size() == 0) {
                System.out.println("Авто нет!");
                wait();
            }
            Thread.sleep(SELL_PROCESS_TIME);
            cars.remove(0);
            System.out.println(Thread.currentThread().getName() + " поехал домой на новом авто");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public synchronized void receiveCar() {
        try {
            System.out.println(Thread.currentThread().getName() + " выпустил 1 авто");
            Thread.sleep(RECEIVE_PROCESS_TIME);
            cars.add(new Car());
            notify();
            makeCounter++;
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
