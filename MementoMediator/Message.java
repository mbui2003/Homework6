import java.util.Date;
import java.util.List;
import java.util.Objects;

public class Message {
    private String sender;
    private List<String> recipients;
    private Date timestamp;
    private String messageContent;

    public Message(String sender, List<String> recipients, String messageContent) {
        this(sender, recipients, messageContent, new Date());
    }

    public Message(String sender, List<String> recipients, String messageContent, Date timestamp) {
        this.sender = sender;
        this.recipients = recipients;
        this.messageContent = messageContent;
        this.timestamp = timestamp;
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

    public MessageMemento createMemento() {
        MessageMemento memento = new MessageMemento();
        memento.setSender(sender);
        memento.setRecipients(recipients);
        memento.setTimestamp(timestamp);
        memento.setMessageContent(messageContent);
        return memento;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null || getClass() != obj.getClass())
            return false;
        Message message = (Message) obj;
        return Objects.equals(sender, message.sender) &&
                Objects.equals(recipients, message.recipients) &&
                Objects.equals(timestamp, message.timestamp) &&
                Objects.equals(messageContent, message.messageContent);
    }
}