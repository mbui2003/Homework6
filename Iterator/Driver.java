import java.util.Iterator;
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

        matthew.sendMessage(List.of("Nathan", "Jennifer"), "Hello Nathan and Jennifer!");
        System.out.println();
        matthew.sendMessage(List.of("Jennifer"), "Hi Jennifer!");
        System.out.println();

        nathan.sendMessage(List.of("Matthew"), "Hi Matthew!");
        System.out.println();

        jennifer.sendMessage(List.of("Matthew"), "Hello Matthew!");
        System.out.println();

        // Matthew iterates over messages sent or received from Nathan
        System.out.println("Messages sent or received from Nathan:");
        iterateMessages(matthew, "Nathan");
    }

    private static void iterateMessages(User user, String username) {
        Iterator<Message> iterator = user.iterator(user);
        while (iterator.hasNext()) {
            Message message = iterator.next();
            if (message.getSender().equals(username) || message.getRecipients().contains(username)) {
                System.out.println(message.getSender() + " -> " + message.getRecipients() + ": " + message.getMessageContent());
            }
        }
    }
}