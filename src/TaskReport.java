/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import javax.swing.JOptionPane;

public class TaskReport {
    // Arrays to store task information
    private String[] developers;
    private String[] taskNames;
    private String[] taskIDs;
    private int[] taskDurations;
    private String[] taskStatuses;
    private int taskCount;

    public TaskReport() {
        // Initialize the arrays to a fixed size
        developers = new String[100]; // Maximum of 100 tasks
        taskNames = new String[100];
        taskIDs = new String[100];
        taskDurations = new int[100];
        taskStatuses = new String[100];
        taskCount = 0; // Track the number of tasks
    }

    // Add a task to the report
    public void addTask(String developer, String taskName, String taskID, int duration, String status) {
        if (taskCount < developers.length) {
            developers[taskCount] = developer;
            taskNames[taskCount] = taskName;
            taskIDs[taskCount] = taskID;
            taskDurations[taskCount] = duration;
            taskStatuses[taskCount] = status;
            taskCount++; // Increment the task count
        } else {
            JOptionPane.showMessageDialog(null, "Task limit reached. Cannot add more tasks.");
        }
    }

    // Display tasks with status 'Done'
    public void displayTasksWithStatusDone() {
        StringBuilder tasksWithDoneStatus = new StringBuilder("Tasks with status 'Done':\n");
        boolean found = false;
        for (int i = 0; i < taskCount; i++) {
            if ("Done".equalsIgnoreCase(taskStatuses[i])) {
                tasksWithDoneStatus.append("Developer: ").append(developers[i])
                        .append(", Task: ").append(taskNames[i])
                        .append(", Duration: ").append(taskDurations[i])
                        .append(" hours\n");
                found = true;
            }
        }
        if (found) {
            JOptionPane.showMessageDialog(null, tasksWithDoneStatus.toString());
        } else {
            JOptionPane.showMessageDialog(null, "No tasks with status 'Done'.");
        }
    }

    // Display task with the longest duration
    public void displayLongestDurationTask() {
        int maxDuration = 0;
        String taskDetails = "";
        for (int i = 0; i < taskCount; i++) {
            if (taskDurations[i] > maxDuration) {
                maxDuration = taskDurations[i];
                taskDetails = "Developer: " + developers[i] +
                              ", Task: " + taskNames[i] +
                              ", Duration: " + taskDurations[i] + " hours";
            }
        }
        if (!taskDetails.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Task with longest duration:\n" + taskDetails);
        } else {
            JOptionPane.showMessageDialog(null, "No tasks available.");
        }
    }

    // Search for a task by name
    public void searchTaskByName(String taskName) {
        String taskDetails = "";
        for (int i = 0; i < taskCount; i++) {
            if (taskNames[i].equalsIgnoreCase(taskName)) {
                taskDetails = "Task Name: " + taskNames[i] +
                              ", Developer: " + developers[i] +
                              ", Status: " + taskStatuses[i];
                break;
            }
        }
        if (!taskDetails.isEmpty()) {
            JOptionPane.showMessageDialog(null, taskDetails);
        } else {
            JOptionPane.showMessageDialog(null, "Task not found.");
        }
    }

    // Search for tasks by developer
    public void searchTasksByDeveloper(String developer) {
        StringBuilder tasksForDeveloper = new StringBuilder("Tasks assigned to " + developer + ":\n");
        boolean found = false;
        for (int i = 0; i < taskCount; i++) {
            if (developers[i].equalsIgnoreCase(developer)) {
                tasksForDeveloper.append("Task: ").append(taskNames[i])
                        .append(", Status: ").append(taskStatuses[i])
                        .append("\n");
                found = true;
            }
        }
        if (found) {
            JOptionPane.showMessageDialog(null, tasksForDeveloper.toString());
        } else {
            JOptionPane.showMessageDialog(null, "No tasks found for developer " + developer);
        }
    }

    // Delete a task by name
    public void deleteTaskByName(String taskName) {
        boolean taskFound = false;
        for (int i = 0; i < taskCount; i++) {
            if (taskNames[i].equalsIgnoreCase(taskName)) {
                // Shift the remaining tasks down
                for (int j = i; j < taskCount - 1; j++) {
                    developers[j] = developers[j + 1];
                    taskNames[j] = taskNames[j + 1];
                    taskIDs[j] = taskIDs[j + 1];
                    taskDurations[j] = taskDurations[j + 1];
                    taskStatuses[j] = taskStatuses[j + 1];
                }
                taskCount--; // Decrease task count
                taskFound = true;
                JOptionPane.showMessageDialog(null, "Task '" + taskName + "' successfully deleted.");
                break;
            }
        }
        if (!taskFound) {
            JOptionPane.showMessageDialog(null, "Task '" + taskName + "' not found.");
        }
    }

    // Display full report of all tasks
    public void displayReport() {
        StringBuilder report = new StringBuilder("Task Report:\n");
        for (int i = 0; i < taskCount; i++) {
            report.append("Task ID: ").append(taskIDs[i])
                  .append(", Task Name: ").append(taskNames[i])
                  .append(", Developer: ").append(developers[i])
                  .append(", Duration: ").append(taskDurations[i])
                  .append(" hours, Status: ").append(taskStatuses[i])
                  .append("\n");
        }
        if (taskCount > 0) {
            JOptionPane.showMessageDialog(null, report.toString());
        } else {
            JOptionPane.showMessageDialog(null, "No tasks available.");
        }
    }
}
