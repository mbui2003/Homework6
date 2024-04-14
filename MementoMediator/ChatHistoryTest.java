import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class ChatHistoryTest {
    private ChatHistory chatHistory;

    @BeforeEach
    public void setUp() {
        chatHistory = new ChatHistory();
    }

    @Test
    public void testAddMessage() {
        Message expectedMessage = new Message("Janet", List.of("Bob"), "Hello Bob!");
        int expectedChatHistoryLength = 1;

        chatHistory.addMessage(expectedMessage);

        Message actualMessage = chatHistory.getMessages().getFirst();
        int actualChatHistoryLength = chatHistory.getMessages().size();

        assertEquals(expectedMessage, actualMessage);
        assertEquals(expectedChatHistoryLength, actualChatHistoryLength);
    }

    @Test
    public void testGetLastMessageSentByValidUser() {
        Message message1 = new Message("Janet", List.of("Bob"), "Hello Bob!");
        Message message2 = new Message("Janet", List.of("Bill"), "Hello Bill!");
        chatHistory.addMessage(message1);
        chatHistory.addMessage(message2);

        Message actualLastMessageSent = chatHistory.getLastMessageSentByUser("Janet");

        assertEquals(message2, actualLastMessageSent);
    }

    @Test
    public void testGetLastMessageSentByInvalidUser() {
        Message message1 = new Message("Janet", List.of("Bob"), "Hello Bob!");
        Message message2 = new Message("Janet", List.of("Bill"), "Hello Bill!");
        chatHistory.addMessage(message1);
        chatHistory.addMessage(message2);

        assertNull(chatHistory.getLastMessageSentByUser("Random Non-Existing User"));
    }

    @Test
    public void testGetMessages() {
        List<Message> expectedMessages = new ArrayList<>();
        expectedMessages.add(new Message("Janet", List.of("Bob"), "Hey Bob!"));
        expectedMessages.add(new Message("Bob", List.of("Bill"), "Hey Bill!"));
        expectedMessages.add(new Message("Bill", List.of("Janet"), "Hey Janet!"));

        for (Message message : expectedMessages) {
            chatHistory.addMessage(message);
        }

        List<Message> actualMessages = chatHistory.getMessages();

        assertEquals(expectedMessages.size(), actualMessages.size());
        for (int i = 0; i < expectedMessages.size(); i++) {
            assertEquals(expectedMessages.get(i), actualMessages.get(i));
        }
    }

    @Test
    public void testRemoveMessage() {
        Message message1 = new Message("Janet", List.of("Bob"), "Hello Bob!");
        Message message2 = new Message("Janet", List.of("Bill"), "Hello Bill!");
        int expectedChatHistoryLengthAfterRemoval = 1;
        chatHistory.addMessage(message1);
        chatHistory.addMessage(message2);

        chatHistory.removeMessage(message1);

        int actualChatHistoryLengthAfterRemoval = chatHistory.getMessages().size();

        assertEquals(message2, chatHistory.getMessages().getFirst());
        assertEquals(expectedChatHistoryLengthAfterRemoval, actualChatHistoryLengthAfterRemoval);
    }

    @Test
    public void testDisplay() {
        Message message1 = new Message("Janet", List.of("Bob"), "Hello Bob!");
        Message message2 = new Message("Janet", List.of("Bill"), "Hello Bill!");
        chatHistory.addMessage(message1);
        chatHistory.addMessage(message2);

        chatHistory.display();
    }
}