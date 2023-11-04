package usecases;

public class Transaction {
    private final double amount;
    private final String senderName;
    private final String receiverName;

    public Transaction(double amount, String senderName, String receiverName) {
        this.amount = amount;
        this.senderName = senderName;
        this.receiverName = receiverName;
    }

    public double getAmount() {
        return amount;
    }

    public String getSenderName() {
        return senderName;
    }
    @Override
    public String toString() {
        return "Transaction from " + senderName + " to " + receiverName + " of amount " + amount;
    }
}