Java-based code, Just run under a Java environment.

Suggestion: Use IntelliJ IDEA.

For the required code comments, please check the comments in the main.java.

Here is the comments (Same as comments in main.java): \
In the original code, each FinancialReportNotificationManager has to control a single report, which takes a lot of
time to create a report and send a report. Meanwhile, it doesn't protect the PPI in the original code.

In my solution, I decided to make the FinancialReportNotificationManager to be a singleton class, which just needs one
manager to manage all reports (A structure like "one to many" in this case). Meanwhile, apply a map whose key is Account
and value is String (report), which could save plant of time. The Map structure could solve Bart's problem (financial
reporting is taking "way to long"). The Singleton structure ensures the system is under the object-oriented structure.

Moreover, by applying a new function in Account and Transaction in order to protect personal information, and encrypt
the sensitive messages.