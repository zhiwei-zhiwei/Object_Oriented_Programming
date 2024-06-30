package oop1;

public class Transaction {
    private double amount;
    private String type;
    private String date;
    private String createdAt;

    // Constructor


    public Transaction() {
        this.amount = 0.0;
        this.type = "";
        this.date = "";
        this.createdAt = "";
    }

    public static double convertToSpecialDouble(double value) {
        return Double.NaN; // Using NaN to represent '***'
    }

    public double getAmount() {
        return PIIProtector.decryptDouble(amount);
    }

    public void setAmount(double amount) {
        this.amount = PIIProtector.encryptDouble(amount);
    }

    public String getType() {
        return PIIProtector.decryptString(type);
    }

    public void setType(String type) {
        this.type = PIIProtector.encryptString(type);
    }

    public String getDate() {
        return PIIProtector.decryptString(date);
    }

    public void setDate(String date) {
        this.date = PIIProtector.encryptString(date);
    }

    public String getCreatedAt() {
        return PIIProtector.decryptString(createdAt);
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = PIIProtector.encryptString(createdAt);
    }
}

