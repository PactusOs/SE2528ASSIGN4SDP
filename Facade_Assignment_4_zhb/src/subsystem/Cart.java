package subsystem;

public record Cart(String product,int quantity,int price) {
    public Cart {
        if(product==null||product.isBlank()||quantity<=0||price<=0) {
            throw new IllegalArgumentException("Not correct cart");}
    }
    public int total() {
        return quantity*price;
    }
}
