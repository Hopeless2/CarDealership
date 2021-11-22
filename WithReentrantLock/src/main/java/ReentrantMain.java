public class ReentrantMain {
    private static final int BUY_TIMING = 2000;
    public static void main(String[] args) throws InterruptedException {
        new Thread(null, new Automaker(), "Производитель авто").start();

        new Thread(null, new Customer(), "Покупатель " + Dealership.sellCounter).start();
        Thread.sleep(BUY_TIMING);

        new Thread(null, new Customer(), "Покупатель " + Dealership.sellCounter).start();
        Thread.sleep(BUY_TIMING);

        new Thread(null, new Customer(), "Покупатель " + Dealership.sellCounter).start();
        Thread.sleep(BUY_TIMING);

        new Thread(null, new Customer(), "Покупатель " + Dealership.sellCounter).start();
    }

}
