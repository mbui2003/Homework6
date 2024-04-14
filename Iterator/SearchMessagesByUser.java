import java.util.Iterator;
import java.util.List;

public class SearchMessagesByUser implements Iterator<Message> {
    private List<Message> messages;
    private String userToSearchWith;
    private int currentIndex;

    public SearchMessagesByUser(List<Message> messages, User userToSearchWith) {
        this.messages = messages;
        this.userToSearchWith = userToSearchWith.getName();
        this.currentIndex = 0;
    }

    @Override
    public boolean hasNext() {
        while (currentIndex < messages.size()) {
            Message message = messages.get(currentIndex);
            if (message.getSender().equals(userToSearchWith) || message.getRecipients().contains(userToSearchWith)) {
                return true;
            }
            currentIndex++;
        }
        return false;
    }

    @Override
    public Message next() {
        if (hasNext()) {
            return messages.get(currentIndex++);
        } else {
            return null;
        }
    }
}
