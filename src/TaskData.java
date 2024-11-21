/**
 * TaskData class serves as a blueprint for storing individual task information.
 */
public class TaskData {
    private String developer;
    private String taskName;
    private String taskID;
    private int taskDuration;
    private String taskStatus;

    // Constructor to initialize task details
    public TaskData(String developer, String taskName, String taskID, int taskDuration, String taskStatus) {
        this.developer = developer;
        this.taskName = taskName;
        this.taskID = taskID;
        this.taskDuration = taskDuration;
        this.taskStatus = taskStatus;
    }

    // Getter methods for accessing task details
    public String getDeveloper() {
        return developer;
    }

    public String getTaskName() {
        return taskName;
    }

    public String getTaskID() {
        return taskID;
    }

    public int getTaskDuration() {
        return taskDuration;
    }

    public String getTaskStatus() {
        return taskStatus;
    }
}

