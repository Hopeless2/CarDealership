import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class Seller {
    Dealership dealership;
    private static final int SELL_PROCESS_TIME = 1000;
    private static final int RECEIVE_PROCESS_TIME = 1000;
    private final Condition condition;
    private final ReentrantLock locker;

    public Seller(Dealership dealership) {
        this.dealership = dealership;
        locker = new ReentrantLock(true);
        condition = locker.newCondition();
    }

    public void sellCar() {
        try {
            System.out.println(Thread.currentThread().getName() + " зашел в автосалон");
            locker.lock();
            while (dealership.getCars().size() == 0) {
                System.out.println("Авто нет!");
                condition.await();
            }
            Thread.sleep(SELL_PROCESS_TIME);
            System.out.println(Thread.currentThread().getName() + " поехал домой на новом авто");
            dealership.getCars().remove(0);
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
            dealership.getCars().add(new Car());
            condition.signal();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            locker.unlock();
        }
    }
}
