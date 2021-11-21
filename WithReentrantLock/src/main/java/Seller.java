import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class Seller {
    Dealership dealership;
    private static final int SELL_PROCESS_TIME = 1000;
    private static final int RECEIVE_PROCESS_TIME = 1000;
    ReentrantLock locker;
    Condition condition;

    public Seller(Dealership dealership, ReentrantLock lock) {
        this.dealership = dealership;
        locker = lock;
        condition = locker.newCondition();
    }

    public boolean sellCar() {
        if (locker.tryLock()) {
            System.out.println(Thread.currentThread().getName() + " зашел в автосалон");
            try {
                while (dealership.getCars().size() == 0) {
                    condition.await();
                }
                Thread.sleep(SELL_PROCESS_TIME);
                dealership.getCars().remove(0);
                System.out.println(Thread.currentThread().getName() + " !!!!!!поехал домой на новом авто");
                return false;
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                locker.unlock();
            }
        }
        return true;
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
        } finally {
            locker.unlock();
        }
    }

}
