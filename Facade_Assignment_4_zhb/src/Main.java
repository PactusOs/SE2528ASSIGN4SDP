import facade.OrderF;
import subsystem.Cart;
import subsystem.Customer;

public class Main {
    public static void main(String[] args) {
        OrderF facade=new OrderF();
        Customer amanda=new Customer("Amanda","Astana",1500);
        Customer linda=new Customer("Linda","Almaty",100);
        Customer rose=new Customer("Rose","Astana",3000);
        int orderId=facade.placeOrder(new Cart("Laptop",2,500),amanda);
        facade.placeOrder(new Cart("Laptop",3,500),linda);
        facade.cancelOrder(orderId);
        facade.placeOrder(new Cart("Laptop",5,500),rose);
        System.out.println("Amanda's balance: "+amanda.balance);
    }
}
