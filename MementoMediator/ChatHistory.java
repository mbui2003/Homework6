import java.util.ArrayList;
import java.util.List;

class ChatHistory {
    private List<Message> messages;

    public ChatHistory() {
        this.messages = new ArrayList<>();
    }

    public void addMessage(Message message) {
        messages.add(message);
    }

    public Message getLastMessageSentByUser(String sender) {
        for (int i = messages.size() - 1; i >= 0; i--) {
            Message message = messages.get(i);
            if (message.getSender().equals(sender)) {
                return message;
            }
        }
        return null; // No message sent by the specified user
    }

    public List<Message> getMessages() {
        return messages;
    }

    public void removeMessage(Message message) {
        messages.removeIf(m -> m.equals(message));
    }

    public void display() {
        System.out.println("Chat History:");
        for (Message message : messages) {
            System.out.println(message.getTimestamp() + " - " + message.getSender() + " to " + message.getRecipients()
                    + ": " + message.getMessageContent());
        }
    }
}
