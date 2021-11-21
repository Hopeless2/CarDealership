import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.ReentrantLock;

public class Dealership {
    Seller seller = new Seller(this, new ReentrantLock(true));
    List<Car> cars = new ArrayList<>(10);
    public static int sellCounter = 1;
    public static int makeCounter = 1;
    private static Dealership instance = null;

    private Dealership() {
    }

    public static Dealership get() {
        if (instance == null) instance = new Dealership();
        return instance;
    }

    public void sellCar() {
        sellCounter++;
        boolean isSell = true;
        while (isSell) {
            isSell = seller.sellCar();
        }
    }

    public void acceptCar() {
        makeCounter++;
        seller.receiveCar();
    }

    List<Car> getCars() {
        return cars;
    }

}
