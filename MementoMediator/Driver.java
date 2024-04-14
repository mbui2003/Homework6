import java.util.List;

public class Driver {
    public static void main(String[] args) {
        // Create chat server with 3 users
        ChatServer server = new ChatServer();

        User matthew = new User("Matthew", server);
        User nathan = new User("Nathan", server);
        User jennifer = new User("Jennifer", server);

        server.register(matthew);
        server.register(nathan);
        server.register(jennifer);

        // Send messages to one or more users through the chat server
        // Receive messages from other users
        // When a message is received, output indicating the sender and the message content will be displayed
        matthew.sendMessage(List.of("Nathan", "Jennifer"), "Hello Nathan and Jennifer!");
        System.out.println();
        nathan.sendMessage(List.of("Matthew"), "Hi Matthew!");
        System.out.println();
        jennifer.sendMessage(List.of("Nathan"), "Hey Nathan!");
        System.out.println();

        // View the chat history for a specific user
        System.out.println("Users' chat history before undoing a message:");
        matthew.getChatHistory().display();
        System.out.println();
        nathan.getChatHistory().display();
        System.out.println();
        jennifer.getChatHistory().display();
        System.out.println();

        // Undo the last message sent using the Memento design pattern
        nathan.undoLastMessage();
        System.out.println();

        System.out.println("Users' chat history after undoing a message:");
        matthew.getChatHistory().display();
        System.out.println();
        nathan.getChatHistory().display();
        System.out.println();
        jennifer.getChatHistory().display();
        System.out.println();

        // Block messages from specific users using the Mediator design pattern
        server.blockUser(matthew, "Nathan");

        // Since message is sent from a blocked user, message is not received and no output is displayed
        nathan.sendMessage(List.of("Matthew"), "This message should be blocked");
        System.out.println();

        System.out.println("Users' chat history after blocked user sends a message:");
        matthew.getChatHistory().display();
        System.out.println();
        nathan.getChatHistory().display();
        System.out.println();
        jennifer.getChatHistory().display();
        System.out.println();
    }
}