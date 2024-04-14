import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MessageMementoTest {
    private MessageMemento memento;

    @BeforeEach
    public void setUp() {
        memento = new MessageMemento();
    }

    @Test
    public void testMessageMemento() {
        String expectedSender = "";
        List<String> expectedRecipients = new ArrayList<String>();
        Date expectedTimestamp = new Date(0);
        String expectedMessageContent = "";

        assertEquals(expectedSender, memento.getSender());
        assertEquals(expectedRecipients, memento.getRecipients());
        assertEquals(expectedTimestamp, memento.getTimestamp());
        assertEquals(expectedMessageContent, memento.getMessageContent());
    }

    @Test
    public void testSetSender() {
        String expectedSender = "Janet";

        memento.setSender(expectedSender);

        String actualSender = memento.getSender();

        assertEquals(expectedSender, actualSender);
    }

    @Test
    public void testSetRecipients() {
        List<String> expectedRecipients = new ArrayList<>();
        expectedRecipients.add("Bob");
        expectedRecipients.add("Bill");

        memento.setRecipients(expectedRecipients);

        List<String> actualRecipients = memento.getRecipients();

        assertEquals(expectedRecipients, actualRecipients);
    }

    @Test
    public void testSetTimestamp() {
        Date expectedTimestamp = new Date();

        memento.setTimestamp(expectedTimestamp);

        Date actualTimestamp = memento.getTimestamp();

        assertEquals(expectedTimestamp, actualTimestamp);
    }

    @Test
    public void testSetMessageContent() {
        String expectedMessageContent = "Hello Bob and Bill!";

        memento.setMessageContent(expectedMessageContent);

        String actualMessageContent = memento.getMessageContent();

        assertEquals(expectedMessageContent, actualMessageContent);
    }

    @Test
    public void testGetSender() {
        String expectedSender = "Janet";

        memento.setSender(expectedSender);

        String actualSender = memento.getSender();

        assertEquals(expectedSender, actualSender);
    }

    @Test
    public void testGetRecipients() {
        List<String> expectedRecipients = new ArrayList<>();
        expectedRecipients.add("Bob");
        expectedRecipients.add("Bill");

        memento.setRecipients(expectedRecipients);

        List<String> actualRecipients = memento.getRecipients();

        assertEquals(expectedRecipients, actualRecipients);
    }

    @Test
    public void testGetTimestamp() {
        Date expectedTimestamp = new Date();

        memento.setTimestamp(expectedTimestamp);

        Date actualTimestamp = memento.getTimestamp();

        assertEquals(expectedTimestamp, actualTimestamp);
    }

    @Test
    public void testGetMessageContent() {
        String expectedMessageContent = "Hello Bob and Bill!";

        memento.setMessageContent(expectedMessageContent);

        String actualMessageContent = memento.getMessageContent();

        assertEquals(expectedMessageContent, actualMessageContent);
    }
}