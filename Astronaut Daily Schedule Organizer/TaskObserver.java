import java.util.ArrayList;
import java.util.List;

public class TaskObserver implements Observer {
    private List<String> notifications;

    public TaskObserver() {
        notifications = new ArrayList<>();
    }

    @Override
    public void update(String message) {
        notifications.add(message);
    }

    public List<String> getNotifications() {
        return notifications;
    }
}
