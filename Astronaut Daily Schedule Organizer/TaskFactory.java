import java.time.LocalTime;

public class TaskFactory {
    public static Task createTask(String description, String startTime, String endTime, String priority) {
        LocalTime start = LocalTime.parse(startTime);
        LocalTime end = LocalTime.parse(endTime);
        return new Task(description, start, end, priority);
    }
}
