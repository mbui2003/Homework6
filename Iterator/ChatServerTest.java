import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class ChatServerTest {
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    private ChatServer chatServer;

    @BeforeEach
    public void setUp() {
        System.setOut(new PrintStream(outputStreamCaptor));
        chatServer = new ChatServer();
    }

    @Test
    public void testRegister() {
        User bob = new User("Bob", chatServer);

        chatServer.register(bob);

        assertTrue(chatServer.getUsers().containsKey("Bob"));
    }

    @Test
    public void testUnregister() {
        User bob = new User("Bob", chatServer);
        chatServer.register(bob);

        chatServer.unregister(bob);

        assertFalse(chatServer.getUsers().containsKey("Bob"));
    }

    @Test
    public void testUnregisterNonExistingUser() {
        User bob = new User("Bob", chatServer);
        String expectedOutput = "User Bob is not registered.\n";

        chatServer.unregister(bob);

        String actualOutput = outputStreamCaptor.toString();

        assertEquals(expectedOutput, actualOutput);
    }

    @Test
    public void testSendMessage() {
        User bob = new User("Bob", chatServer);
        User bill = new User("Bill", chatServer);
        chatServer.register(bob);
        chatServer.register(bill);
        Message message = new Message("Bob", List.of("Bill"), "Hello Bill!");
        String expectedOutput = "Bill received message from Bob: Hello Bill!\n";

        chatServer.sendMessage(message);

        String actualOutput = outputStreamCaptor.toString();

        assertEquals(expectedOutput, actualOutput);
    }

    @Test
    public void testSendMessageToUnregisteredUser() {
        User bob = new User("Bob", chatServer);
        Message message = new Message("Bob", List.of("Bill"), "Hi Bill!");
        String expectedOutput = "User Bill is not registered.\n";

        chatServer.sendMessage(message);

        String actualOutput = outputStreamCaptor.toString();


        assertEquals(expectedOutput, actualOutput);
    }

    @Test
    public void testBlockUser() {
        User bob = new User("Bob", chatServer);
        User bill = new User("Bill", chatServer);
        chatServer.register(bob);
        chatServer.register(bill);

        chatServer.blockUser(bob, "Bill");

        assertTrue(chatServer.getBlockedUsers().get(bob).contains("Bill"));
    }

    @Test
    public void testBlockNonExistingUser() {
        User bob = new User("Bob", chatServer);
        chatServer.register(bob);
        String expectedOutput = "User Bill does not exist.\n";

        chatServer.blockUser(bob, "Bill");

        String actualOutput = outputStreamCaptor.toString();

        assertEquals(expectedOutput, actualOutput);
    }

    @Test
    public void testGetUsers() {
        User bob = new User("Bob", chatServer);
        User bill = new User("Bill", chatServer);
        chatServer.register(bob);
        chatServer.register(bill);

        Map<String, User> users = chatServer.getUsers();

        assertTrue(users.containsKey("Bob"));
        assertTrue(users.containsKey("Bill"));
        assertEquals(2, users.size());
    }

    @Test
    public void testGetBlockedUsers() {
        User bob = new User("Bob", chatServer);
        User bill = new User("Bill", chatServer);
        chatServer.register(bob);
        chatServer.register(bill);
        chatServer.blockUser(bob, "Bill");

        Map<User, List<String>> blockedUsers = chatServer.getBlockedUsers();

        List<String> blockedByBob = blockedUsers.get(bob);
        assertTrue(blockedByBob.contains("Bill"));
    }
}