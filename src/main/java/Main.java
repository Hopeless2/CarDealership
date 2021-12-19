public class Main {

    public static void main(String[] args) {
        Seller seller = new Seller();

        new Thread(null, new Automaker(seller), "Производитель авто").start();

        new Thread(null, new Customer(seller), "Покупатель 1").start();

        new Thread(null, new Customer(seller), "Покупатель 2").start();

        new Thread(null, new Customer(seller), "Покупатель 3").start();

        new Thread(null, new Customer(seller), "Покупатель 4").start();
    }
}
