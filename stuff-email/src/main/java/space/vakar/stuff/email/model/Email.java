package space.vakar.stuff.email.model;

import java.util.Objects;

public class Email {

    private String recipientEmail;
    private String subject;
    private String message;

    public Email() {
    }

    public Email(String recipientEmail, String subject, String message) {
        this.recipientEmail = recipientEmail;
        this.subject = subject;
        this.message = message;
    }

    public String getRecipientEmail() {
        return recipientEmail;
    }

    public void setRecipientEmail(String recipientEmail) {
        this.recipientEmail = recipientEmail;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Email that = (Email) o;
        return Objects.equals(recipientEmail, that.recipientEmail) &&
                Objects.equals(subject, that.subject) &&
                Objects.equals(message, that.message);
    }

    @Override
    public int hashCode() {
        return Objects.hash(recipientEmail, subject, message);
    }

    @Override
    public String toString() {
        return "EmailModel{" +
                "recipientEmail='" + recipientEmail + '\'' +
                ", subject='" + subject + '\'' +
                ", message='" + message + '\'' +
                '}';
    }
}
