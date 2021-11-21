public class Customer implements Runnable {
    Dealership dealership = Dealership.get();
    private static final int BUY_TIMING = 3000;

    @Override
    public void run() {
        while (Dealership.sellCounter < 10) {
            try {
                Thread.sleep(BUY_TIMING);
                new Thread(null, dealership::sellCar, "Покупатель " + Dealership.sellCounter).start();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
