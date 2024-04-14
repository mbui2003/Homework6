import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MessageMemento {
    private String sender;
    private List<String> recipients;
    private Date timestamp;
    private String messageContent;

    public MessageMemento() {
        this.sender = "";
        this.recipients = new ArrayList<>();
        this.timestamp = new Date(0);
        this.messageContent = "";
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public void setRecipients(List<String> recipients) {
        this.recipients = recipients;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public void setMessageContent(String messageContent) {
        this.messageContent = messageContent;
    }

    public String getSender() {
        return sender;
    }

    public List<String> getRecipients() {
        return recipients;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public String getMessageContent() {
        return messageContent;
    }
}