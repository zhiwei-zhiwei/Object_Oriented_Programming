package oop1;
import java.util.HashMap;
import java.util.Map;
public class FinancialReportNotificationManager {
    private static FinancialReportNotificationManager instance;
    private final Map<Account, String> accountReportMap;
    private final NotificationEngine notifier;
    private Account account;
    private Transaction[] transaction;
    private FinancialReportNotificationManager() {
        this.accountReportMap = new HashMap<>();
        this.notifier = new NotificationEngine();
    }

    public static synchronized FinancialReportNotificationManager getInstance() {
        if (instance == null) {
            instance = new FinancialReportNotificationManager();
        }
        return instance;
    }

    public void generateAndSendReport(Account account, Transaction[] transactions) {
        String report = createReport(transactions);
        accountReportMap.put(account, report);
        sendReport(account, report);
    }

    private String createReport(Transaction[] transactions) {
        StringBuilder reportBuilder = new StringBuilder();
        for (Transaction t : transactions) {
            reportBuilder.append("amount: ").append(t.getAmount()).append(", type: ").append(t.getType())
                    .append(", date: ").append(t.getDate()).append(", creat at: ").append(t.getCreatedAt())
                    .append("\n");
        }
        return reportBuilder.toString();
    }

    private void sendReport(Account account, String report) {
        notifier.deliver("reporter@example.com", account.getEmail(), "Your Financial Report", report);
    }
}
