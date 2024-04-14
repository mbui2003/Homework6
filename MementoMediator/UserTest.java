import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertNotNull;


public class UserTest {
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
    private ChatServer chatServer;
    private User user;

    @BeforeEach
    public void setUp() {
        System.setOut(new PrintStream(outputStreamCaptor));
        chatServer = new ChatServer();
        user = new User("Alice", chatServer);
    }

    @Test
    public void testSendMessage() {
        List<String> recipients = new ArrayList<>();
        recipients.add("Bob");
        recipients.add("Charlie");
        int expectedChatHistoryLength = 1;
        String expectedContent = "Hello, Bob and Charlie!";

        user.sendMessage(recipients, expectedContent);

        ChatHistory chatHistory = user.getChatHistory();
        int actualChatHistoryLength = chatHistory.getMessages().size();
        String actualContent = chatHistory.getMessages().getFirst().getMessageContent();

        assertEquals(expectedChatHistoryLength, actualChatHistoryLength);
        assertEquals(expectedContent, actualContent);
    }

    @Test
    public void testReceiveMessage() {
        Message expectedMessage = new Message("Bob", List.of("Alice"), "Hello, Alice!");
        int expectedChatHistoryLength = 1;

        user.receiveMessage(expectedMessage);

        ChatHistory chatHistory = user.getChatHistory();
        int actualChatHistoryLength = chatHistory.getMessages().size();
        Message actualMessage = chatHistory.getMessages().getFirst();

        assertEquals(expectedChatHistoryLength, actualChatHistoryLength);
        assertEquals(expectedMessage, actualMessage);
    }

    @Test
    public void testUndoLastMessageWithMessageToUndo() {
        Message message = new Message("Alice", List.of("Bob"), "Hello, Bob!");
        chatServer.sendMessage(message);

        user.undoLastMessage();

        ChatHistory chatHistory = user.getChatHistory();

        assertTrue(chatHistory.getMessages().isEmpty());
    }

    @Test
    public void testUndoLastMessageWithNoMessageToUndo() {
        String expectedOutput = "No message to undo.\n";

        user.undoLastMessage();

        String actualOutput = outputStreamCaptor.toString();

        assertEquals(expectedOutput, actualOutput);
    }

    @Test
    public void testGetName() {
        String expectedName = "Alice";

        String actualName = user.getName();

        assertEquals(expectedName, actualName);
    }

    @Test
    public void testGetChatHistory() {
        ChatHistory chatHistory = user.getChatHistory();

        assertNotNull(chatHistory);
    }
}