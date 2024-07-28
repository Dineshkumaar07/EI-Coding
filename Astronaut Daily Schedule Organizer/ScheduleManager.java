import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.logging.Logger;

public class ScheduleManager {
    private static ScheduleManager instance;
    private List<Task> tasks;
    private List<Observer> observers;
    private static final Logger logger = Logger.getLogger(ScheduleManager.class.getName());

    private ScheduleManager() {
        tasks = new ArrayList<>();
        observers = new ArrayList<>();
    }

    public static synchronized ScheduleManager getInstance() {
        if (instance == null) {
            instance = new ScheduleManager();
        }
        return instance;
    }

    public void addObserver(Observer observer) {
        observers.add(observer);
    }

    public void addTask(Task task) {
        if (!isConflict(task)) {
            tasks.add(task);
            notifyObservers("Task added successfully. No conflicts.");
            logger.info("Task added successfully. No conflicts.");
        } else {
            notifyObservers("Error: Task conflicts with existing task.");
            logger.warning("Error: Task conflicts with existing task.");
        }
    }

    public void removeTask(String description) {
        boolean removed = tasks.removeIf(task -> task.getDescription().equals(description));
        if (removed) {
            notifyObservers("Task removed successfully.");
        } else {
            notifyObservers("Error: Task not found.");
        }
    }

    public List<Task> viewTasks() {
        Collections.sort(tasks, (t1, t2) -> t1.getStartTime().compareTo(t2.getStartTime()));
        return tasks;
    }

    private boolean isConflict(Task newTask) {
        for (Task task : tasks) {
            if (task.getStartTime().isBefore(newTask.getEndTime()) && newTask.getStartTime().isBefore(task.getEndTime())) {
                return true;
            }
        }
        return false;
    }

    private void notifyObservers(String message) {
        for (Observer observer : observers) {
            observer.update(message);
        }
    }
}
