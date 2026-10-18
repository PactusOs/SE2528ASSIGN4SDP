package subsystem;
public class Payment {
    public boolean charge(Customer customer,int amount) {
        if(amount<=0||customer.balance<amount) return false;
        customer.balance-=amount;
        return true;
    }
    public void refund(Customer customer,int amount) {
        customer.balance+=amount;
    }
}
