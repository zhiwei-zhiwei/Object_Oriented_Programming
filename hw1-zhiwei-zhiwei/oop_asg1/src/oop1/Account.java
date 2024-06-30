package oop1;

import java.util.Objects;

public class Account {
    private String accountNumber;
    private double balance;
    private String email;

    // Constructor
    public Account() {
        this.accountNumber = "1";
        this.balance = 0.0;
        this.email = "";
    }

//    public static String encrypt(String input) {
//        return input.replaceAll("[0-9a-zA-Z]", "*");
//    }


    public String getAccountNumber() {
        return PIIProtector.decryptString(accountNumber);
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = PIIProtector.encryptString(accountNumber);
    }

    public double getBalance() {
        return PIIProtector.decryptDouble(balance);
    }

    public void setBalance(double balance) {
        this.balance = PIIProtector.encryptDouble(balance);
    }

    public String getEmail() {
        return PIIProtector.decryptString(email);
    }

    public void setEmail(String email) {
        this.email = PIIProtector.encryptString(email);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Account account = (Account) o;
        return Double.compare(balance, account.balance) == 0 && Objects.equals(accountNumber, account.accountNumber) && Objects.equals(email, account.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(accountNumber, balance, email);
    }
}

