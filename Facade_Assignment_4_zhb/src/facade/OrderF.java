package facade;
import java.util.HashMap;
import java.util.Map;
import subsystem.Cart;
import subsystem.Customer;
import subsystem.InventoryService;
import subsystem.NotificationService;
import subsystem.Payment;
import subsystem.ShippingService;

public class OrderF {
    private final InventoryService inventory=new InventoryService();
    private final Payment payment=new Payment();
    private final ShippingService shipping=new ShippingService();
    private final NotificationService notification=new NotificationService();
    private final Map<Integer,subsystem.Order> orders=new HashMap<>();
    private int nextId=1;

    public int placeOrder(Cart cart,Customer customer) {
        if(!inventory.reserve(cart.product(),cart.quantity())) {
            notification.send(customer.name,"Not enough");
            return -1;
        }
        int amount=cart.total();
        if(!payment.charge(customer,amount)) {
            inventory.release(cart.product(),cart.quantity());
            notification.send(customer.name,"Payment declined");
            return -1;
        }
        int id=nextId++;
        String tracking=shipping.ship(id,customer.address);
        orders.put(id,new subsystem.Order(cart,customer,amount));
        notification.send(customer.name,"OrderF "+id+" placed, Tracking: "+tracking);
        return id;
    }
    public boolean cancelOrder(int id) {
        subsystem.Order order=orders.remove(id);
        if(order==null)
            return false;
        shipping.cancel(id);
        payment.refund(order.customer(),order.amount());
        inventory.release(order.cart().product(),order.cart().quantity());
        notification.send(order.customer().name,"OrderF "+id+" canceled");
        return true;
    }
}
