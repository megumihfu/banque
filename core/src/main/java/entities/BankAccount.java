package entities;
import java.util.UUID;

public class BankAccount extends Entity{
    private double balance;
    private double limit;

    public BankAccount(UUID id, double balance, double limit) {
        // Initialize the balance and other properties.
        super(id);
        this.balance = balance;
        this.limit = limit;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public void setLimit(double limit){
        this.limit = limit;
    }

    public double getLimit(){
        return limit;
    }
}
