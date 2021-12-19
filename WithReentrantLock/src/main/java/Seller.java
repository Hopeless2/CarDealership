import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class Seller {
    private static final int SELL_PROCESS_TIME = 1000;
    private static final int RECEIVE_PROCESS_TIME = 1000;
    private int sellCounter = 0;
    private int makeCounter = 0;
    List<Car> cars = new ArrayList<>();
    private final Condition condition;
    private final ReentrantLock locker;

    public Seller() {
        locker = new ReentrantLock(true);
        condition = locker.newCondition();
    }

    public int getSellCounter() {
        return sellCounter;
    }

    public int getMakeCounter() {
        return makeCounter;
    }

    public void sellCar() {
        try {
            sellCounter++;
            System.out.println(Thread.currentThread().getName() + " зашел в автосалон");
            locker.lock();
            while (cars.size() == 0) {
                System.out.println("Авто нет!");
                condition.await();
            }
            Thread.sleep(SELL_PROCESS_TIME);
            System.out.println(Thread.currentThread().getName() + " поехал домой на новом авто");
            cars.remove(0);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            locker.unlock();
        }
    }

    public void receiveCar() {
        try {
            locker.lock();
            System.out.println(Thread.currentThread().getName() + " выпустил 1 авто");
            Thread.sleep(RECEIVE_PROCESS_TIME);
            cars.add(new Car());
            condition.signal();
            makeCounter++;
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            locker.unlock();
        }
    }
}
