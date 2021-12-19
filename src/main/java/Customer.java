public class Customer implements Runnable {
    Seller seller;
    private static final int RE_BUY_TIMING = 3000;

    public Customer(Seller seller) {
        this.seller = seller;
    }

    @Override
    public void run() {
        while (seller.getSellCounter() < 10) {
            try {
                seller.sellCar();
                Thread.sleep(RE_BUY_TIMING);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
