import java.util.ArrayList;
import java.util.List;

public class Dealership {
    Seller seller = new Seller(this);
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
        seller.sellCar();
    }

    public void acceptCar() {
        makeCounter++;
        seller.receiveCar();
    }

    List<Car> getCars() {
        return cars;
    }

}
