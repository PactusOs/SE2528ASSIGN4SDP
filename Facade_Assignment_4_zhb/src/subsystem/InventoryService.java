package subsystem;

import java.util.HashMap;
import java.util.Map;

public class InventoryService {
    private final Map<String,Integer> stock=new HashMap<>();
    public InventoryService() {
        stock.put("Laptop",5);
        stock.put("Mouse",10);
    }
    public boolean reserve(String product,int quantity) {
        if(quantity<=0||stock.getOrDefault(product,0)<quantity) return false;
        stock.put(product,stock.get(product)-quantity);
        return true;
    }
    public void release(String product,int quantity) {
        stock.put(product,stock.getOrDefault(product,0)+quantity);
    }
}
