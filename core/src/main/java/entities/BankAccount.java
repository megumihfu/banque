package entities;
import java.util.List;
import java.util.UUID;

import datatransferobject.IRequestHandle;
import datatransferobject.ImplementIRequestHandle;
import usecases.DisplayInfoAccount;
import usecases.DisplayRIB;
import usecases.Transaction;
import java.util.ArrayList;

public class BankAccount extends Entity{
    private double balance;
    private double cap;
    private double limit;
    private final User user;
    private final String accountNumber;
    private final String iban;
    private final String bicCode;
    private List<Transaction> incomingTransactions;
    private List<Transaction> outgoingTransactions;

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
        this.incomingTransactions = new ArrayList<>();
        this.outgoingTransactions = new ArrayList<>();

    }

    public double getCap() {
        return cap;
    }

    public void setCap(double cap) {
        this.cap = cap;
    }

    public double getBalance() {
        return balance;
    }
    public void addIncomingTransaction(Transaction transaction) {
        incomingTransactions.add(transaction);
    }
    public void addOutgoingTransaction(Transaction transaction) {
        outgoingTransactions.add(transaction);
    }

    // Pour obtenir la liste des transactions entrantes
    public List<Transaction> getIncomingTransactions() {
        return incomingTransactions;
    }
    public List<Transaction> getOutgoingTransactions() {
        return outgoingTransactions;
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
