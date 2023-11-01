package entities;
import java.util.UUID;

import datatransferobject.IRequestHandle;
import datatransferobject.ImplementIRequestHandle;
import usecases.DisplayInfoAccount;
import usecases.DisplayRIB;


public class BankAccount extends Entity{
    private double balance;
    private double limit;
    private final User user;
    private final String accountNumber;
    private final String iban;
    private final String bicCode;

    IRequestHandle reqHandle = new ImplementIRequestHandle();
    DisplayRIB display = new DisplayRIB(reqHandle);

    public BankAccount(UUID id, double balance, double limit, User user) {
        super(id);
        this.balance = balance;
        this.limit = limit;
        this.user = user;
        this.accountNumber = display.generateRandomAccountNumber();
        this.iban = display.generateRandomIBAN();
        this.bicCode = display.generateRandomBICCode();
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

    public String getAccountNumber() {
        return accountNumber;
    }

    public String getIban() {
        return iban;
    }

    public String getBicCode() {
        return bicCode;
    }
}
