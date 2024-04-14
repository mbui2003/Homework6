import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class SearchMessagesByUserTest {
    private List<Message> messages;
    private User user;
    private SearchMessagesByUser iterator;

    @BeforeEach
    public void setUp() {
        messages = new ArrayList<>();
        user = new User("Alice", new ChatServer());
        iterator = new SearchMessagesByUser(messages, user);
    }

    @Test
    public void testHasNextWithNoMessages() {
        assertFalse(iterator.hasNext());
    }

    @Test
    public void testHasNextWithMessage() {
        messages.add(new Message("Alice", List.of("Bob"), "Hello, Bob!"));

        assertTrue(iterator.hasNext());
    }

    @Test
    public void testNextWithNoMessages() {
        assertNull(iterator.next());
    }

    @Test
    public void testNextWithMessage() {
        Message expectedMessage = new Message("Alice", List.of("Bob"), "Hello, Bob!");
        messages.add(expectedMessage);
        assertEquals(expectedMessage, iterator.next());
    }

    @Test
    public void testNextWithMultipleMessages() {
        Message message1 = new Message("Alice", List.of("Bob"), "Hello, Bob!");
        Message message2 = new Message("Charlie", List.of("Alice"), "Hi, Alice!");
        messages.add(message1);
        messages.add(message2);

        assertEquals(message1, iterator.next());
        assertEquals(message2, iterator.next());
        assertFalse(iterator.hasNext());
    }
}