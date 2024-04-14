import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class MessageTest {
    private String sender;
    private List<String> recipients;
    private Date timestamp;
    private String messageContent;
    private Message message;

    @BeforeEach
    public void setUp() {
        sender = "Janet";
        recipients = new ArrayList<>();
        recipients.add("Bob");
        recipients.add("Bill");
        timestamp = new Date();
        messageContent = "Hello Bob and Bill!";
        message = new Message(sender, recipients, messageContent);
    }

    @Test
    public void testMessageConstructorWithThreeParameters() {
        assertEquals(sender, message.getSender());
        assertEquals(recipients, message.getRecipients());
        assertEquals(messageContent, message.getMessageContent());
    }

    @Test
    public void testMessageConstructorWithFourParameters() {
        message = new Message(sender, recipients, messageContent, timestamp);

        assertEquals(sender, message.getSender());
        assertEquals(recipients, message.getRecipients());
        assertEquals(messageContent, message.getMessageContent());
        assertEquals(timestamp, message.getTimestamp());
    }

    @Test
    public void testGetSender() {
        String actualSender = message.getSender();

        assertEquals(sender, actualSender);
    }

    @Test
    public void testGetRecipients() {
        List<String> actualRecipients = message.getRecipients();

        assertEquals(recipients, actualRecipients);
    }

    @Test
    public void testGetMessageContent() {
        String actualMessageContent = message.getMessageContent();

        assertEquals(messageContent, actualMessageContent);
    }

    @Test
    public void testGetTimestamp() {
        Date actualTimestamp = message.getTimestamp();

        assertEquals(timestamp, actualTimestamp);
    }

    @Test
    public void testCreateMemento() {
        MessageMemento memento = message.createMemento();

        assertEquals(sender, memento.getSender());
        assertEquals(recipients, memento.getRecipients());
        assertEquals(messageContent, memento.getMessageContent());
        assertEquals(timestamp, memento.getTimestamp());
    }

    @Test
    public void testEqualsMethodWithEqualMessages() {
        Message message1 = new Message(sender, recipients, messageContent);
        Message message2 = new Message(sender, recipients, messageContent);

        assertEquals(message1, message2);
    }

    @Test
    public void testEqualsMethodWithDifferentMessages() {
        String firstMessageContent = "Hello Bob and Bill!";
        String secondMessageContent = "How are you guys doing today?";
        Message message1 = new Message(sender, recipients, firstMessageContent);
        Message message2 = new Message(sender, recipients, secondMessageContent);

        assertNotEquals(message1, message2);
    }
}