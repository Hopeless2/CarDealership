public class Automaker implements Runnable{
    Dealership dealership = Dealership.get();
    private static final int MAKE_TIMING = 5000;
    @Override
    public void run() {
        while(Dealership.makeCounter < 10){
            try {
                Thread.sleep(MAKE_TIMING);
                new Thread(null, dealership::acceptCar, "Производитель автомобилей").start();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
