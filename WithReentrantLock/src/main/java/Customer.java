public class Customer implements Runnable {
    Dealership dealership = Dealership.get();
    private static final int RE_BUY_TIMING = 3000;

    @Override
    public void run() {
        while(Dealership.sellCounter < 10){
            try {
                dealership.sellCar();
                Thread.sleep(RE_BUY_TIMING);
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        }
    }


}
