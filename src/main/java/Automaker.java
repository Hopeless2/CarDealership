public class Automaker implements Runnable {
    private static final int MAKE_TIMING = 5000;
    Seller seller;

    public Automaker(Seller seller) {
        this.seller = seller;
    }

    @Override
    public void run() {
        while (seller.getMakeCounter() < 10) {
            try {
                Thread.sleep(MAKE_TIMING);
                seller.receiveCar();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
