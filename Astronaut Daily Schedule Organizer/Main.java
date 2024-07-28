import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        ScheduleManager scheduleManager = ScheduleManager.getInstance();
        TaskObserver taskObserver = new TaskObserver();
        scheduleManager.addObserver(taskObserver);

        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("1. Add Task");
            System.out.println("2. Remove Task");
            System.out.println("3. View Tasks");
            System.out.println("4. Exit");

            int choice = scanner.nextInt();
            scanner.nextLine(); 

            switch (choice) {
                case 1:
                    System.out.print("Enter description: ");
                    String description = scanner.nextLine();
                    System.out.print("Enter start time (HH:MM): ");
                    String startTime = scanner.nextLine();
                    System.out.print("Enter end time (HH:MM): ");
                    String endTime = scanner.nextLine();
                    System.out.print("Enter priority: ");
                    String priority = scanner.nextLine();

                    Task task = TaskFactory.createTask(description, startTime, endTime, priority);
                    scheduleManager.addTask(task);
                    break;
                case 2:
                    System.out.print("Enter description of task to remove: ");
                    String taskDescription = scanner.nextLine();
                    scheduleManager.removeTask(taskDescription);
                    break;
                case 3:
                    for (Task t : scheduleManager.viewTasks()) {
                        System.out.println(t);
                    }
                    break;
                case 4:
                    System.exit(0);
                default:
                    System.out.println("Invalid choice");
            }

            for (String notification : taskObserver.getNotifications()) {
                System.out.println(notification);
            }
        }
    }
}
