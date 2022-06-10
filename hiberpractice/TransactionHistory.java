package hiberpractice;

import hiberpractice.annotation.Column;
import hiberpractice.annotation.PrimaryKey;

public class TransactionHistory {
    @PrimaryKey
    private long transactionId;

    @Column
    private int accountNumber;
    @Column
    private String transactionType;
    @Column
    private double amount;
    @Column
    private String name;

    public TransactionHistory(int accountNumber, String name, String transactionType, double amount) {
        this.accountNumber = accountNumber;
        this.transactionType = transactionType;
        this.amount = amount;
        this.name = name;
    }

    public TransactionHistory() {
    }

    public long getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(long transactionId) {
        this.transactionId = transactionId;
    }

    public int getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(int accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(String transactionType) {
        this.transactionType = transactionType;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "TransactionHistory{" +
                "transactionId=" + transactionId +
                ", accountNumber=" + accountNumber +
                ", transactionType='" + transactionType + "\'" +
                ", amount=" + amount +
                ", name='" + name + "\'" +
                '}';
    }
}
