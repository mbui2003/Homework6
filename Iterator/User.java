import java.util.Iterator;
import java.util.List;
public class User implements IterableByUser {
    private String name;
    private ChatServer chatServer;
    private ChatHistory chatHistory;
    private MessageMemento lastMessageMemento;

    public User(String name, ChatServer chatServer) {
        this.name = name;
        this.chatServer = chatServer;
        this.chatHistory = new ChatHistory();
        this.lastMessageMemento = new MessageMemento();
    }

    public void sendMessage(List<String> recipients, String content) {
        Message message = new Message(this.name, recipients, content);
        chatHistory.addMessage(message);
        lastMessageMemento.setSender(this.name);
        lastMessageMemento.setRecipients(recipients);
        lastMessageMemento.setTimestamp(message.getTimestamp());
        lastMessageMemento.setMessageContent(content);
        chatServer.sendMessage(message);
    }

    public void receiveMessage(Message message) {
        chatHistory.addMessage(message);
        System.out.println(name + " received message from " + message.getSender() + ": " + message.getMessageContent());
    }

    public void undoLastMessage() {
        if (!lastMessageMemento.getSender().isEmpty()) {
            Message message = new Message(
                    lastMessageMemento.getSender(),
                    lastMessageMemento.getRecipients(),
                    lastMessageMemento.getMessageContent(),
                    lastMessageMemento.getTimestamp()
            );
            chatHistory.removeMessage(message);
            System.out.println(name + " undid the last message sent.");
        } else {
            System.out.println("No message to undo.");
        }
    }

    @Override
    public Iterator<Message> iterator(User userToSearchWith) {
        return chatHistory.iterator(userToSearchWith);
    }

    public String getName() {
        return name;
    }

    public ChatHistory getChatHistory() {
        return chatHistory;
    }
}