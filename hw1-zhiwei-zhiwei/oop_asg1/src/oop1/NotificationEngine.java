package oop1;

public class NotificationEngine {
    private String from;
    private String to;
    private String subject;
    private String body;

    public NotificationEngine() {}

    public void deliver(String from, String to, String subject, String body) {
        System.out.println("Email sent from: " + from + ", to: " + to + ", subject: " + subject + ", body: " + body);
    }



}
