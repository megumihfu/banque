package entities;
import java.util.UUID;

public class BankAccount extends Entity{
    private double balance;
    private double limit;
    private User user;

    public BankAccount(UUID id, double balance, double limit, User user) {
        // Initialize the balance and other properties.
        super(id);
        this.balance = balance;
        this.limit = limit;
        this.user = user;
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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
