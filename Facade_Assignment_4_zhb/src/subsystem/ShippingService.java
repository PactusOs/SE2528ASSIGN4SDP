package subsystem;
import java.util.HashMap;
import java.util.Map;
public class ShippingService {
    private final Map<Integer,String> shipments=new HashMap<>();
    public String ship(int orderId,String address) {
        shipments.put(orderId,address);
        return "TRK-"+orderId;
    }
    public void cancel(int orderId) {
        shipments.remove(orderId);
    }
}
