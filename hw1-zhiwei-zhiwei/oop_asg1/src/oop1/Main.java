package oop1;

/*
In the original code, each FinancialReportNotificationManager has to control a single report, which takes a lot of
time to create a report and send a report. Meanwhile, it doesn't protect the PPI in the original code.

In my solution, I decided to make the FinancialReportNotificationManager to be a singleton class, which just needs one
manager to manage all reports (A structure like "one to many" in this case). Meanwhile, apply a map whose key is Account
and value is String (report), which could save plant of time. The Map structure could solve Bart's problem (financial
reporting is taking "way to long"). The Singleton structure ensures the system is under the object-oriented structure.

Moreover, by applying a new function in Account and Transaction in order to protect personal information, and encrypt
the sensitive messages.
 */


public class Main {
    public static void main(String[] args) {
        Account account1 = new Account();
        account1.setAccountNumber("12345");
        account1.setBalance(20);
        account1.setEmail("joe@blow.com");

        Transaction t1 = new Transaction();
        t1.setAmount(10);
        t1.setDate("Monday");
        t1.setType("Deposit");
        t1.setCreatedAt("01/28/2024");

        Transaction[] transactions = { t1 };

        FinancialReportNotificationManager manager = FinancialReportNotificationManager.getInstance();
        manager.generateAndSendReport(account1, transactions);

    }
}
