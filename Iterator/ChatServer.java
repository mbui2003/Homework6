import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class ChatServer {
    private Map<String, User> users;
    private Map<User, List<String>> blockedUsers;

    public ChatServer() {
        this.users = new HashMap<>();
        this.blockedUsers = new HashMap<>();
    }

    public void register(User user) {
        users.put(user.getName(), user);
        blockedUsers.put(user, new ArrayList<>());
    }

    public void unregister(User user) {
        if (users.containsKey(user.getName())) {
            users.remove(user.getName());
            blockedUsers.remove(user);
            System.out.println("User " + user.getName() + " has been unregistered.");
        } else {
            System.out.println("User " + user.getName() + " is not registered.");
        }
    }

    public void sendMessage(Message message) {
        for (String recipientName : message.getRecipients()) {
            User recipient = users.get(recipientName);
            if (recipient != null) {
                List<String> blockedSenders = blockedUsers.get(recipient);
                if (blockedSenders == null || !blockedSenders.contains(message.getSender())) {
                    recipient.receiveMessage(message);
                } else {
                    System.out.println("Message from " + message.getSender() + " blocked by " + recipientName);
                }
            } else {
                System.out.println("User " + recipientName + " is not registered.");
            }
        }
    }

    public void blockUser(User user, String username) {
        if (users.containsKey(username)) {
            blockedUsers.get(user).add(username);
            System.out.println(user.getName() + " blocked messages from " + username);
        } else {
            System.out.println("User " + username + " does not exist.");
        }
    }

    public Map<String, User> getUsers() {
        return users;
    }

    public Map<User, List<String>> getBlockedUsers() {
        return blockedUsers;
    }
}