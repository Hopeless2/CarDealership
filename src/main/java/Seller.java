public class Seller {
    Dealership dealership;
    private static final int SELL_PROCESS_TIME = 1000;
    private static final int RECEIVE_PROCESS_TIME = 1000;

    public Seller(Dealership dealership) {
        this.dealership = dealership;
    }

    public synchronized void sellCar(){
        try {
            System.out.println(Thread.currentThread().getName() + " зашел в автосалон");
            while (dealership.getCars().size() == 0) {
                System.out.println("Авто нет!");
                wait();
            }
            Thread.sleep(SELL_PROCESS_TIME);
            dealership.getCars().remove(0);
            System.out.println(Thread.currentThread().getName() + " поехал домой на новом авто");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public synchronized void receiveCar(){
        try {
            System.out.println(Thread.currentThread().getName() + " выпустил 1 авто");
            Thread.sleep(RECEIVE_PROCESS_TIME);
            dealership.getCars().add(new Car());
            notify();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
