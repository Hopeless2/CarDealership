public class Main {
    public static void main(String[] args) {
        Runnable customer = new Customer();
        Runnable automaker = new Automaker();

        new Thread(customer).start();
        new Thread(automaker).start();

    }

}
