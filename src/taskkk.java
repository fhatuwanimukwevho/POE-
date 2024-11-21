/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import javax.swing.JOptionPane;

public class taskkk {
    private String[] taskNames;
    private String[] taskDescriptions;
    private String[] developerDetails;
    private int[] taskDurations;
    private String[] taskIDs;
    private String[] taskStatuses;
    private int taskCount;

    public taskkk(int numberOfTasks) {
        taskNames = new String[numberOfTasks];
        taskDescriptions = new String[numberOfTasks];
        developerDetails = new String[numberOfTasks];
        taskDurations = new int[numberOfTasks];
        taskIDs = new String[numberOfTasks];
        taskStatuses = new String[numberOfTasks];
        taskCount = 0;
    }

    public boolean checkTaskDescription(String description) {
        return description.length() <= 50;
    }

    public String createTaskID(int taskNumber) {
        String taskNameInitials = taskNames[taskNumber].substring(0, 2).toUpperCase();
        String developerLastThree = developerDetails[taskNumber]
                .substring(developerDetails[taskNumber].length() - 3).toUpperCase();
        return taskNameInitials + ":" + taskNumber + ":" + developerLastThree;
    }

    public void addTask() {
        if (taskCount >= taskNames.length) {
            JOptionPane.showMessageDialog(null, "Task limit reached. Cannot add more tasks.");
            return;
        }

        try {
            String taskName = JOptionPane.showInputDialog("Enter task name:");
            String taskDescription = JOptionPane.showInputDialog("Enter task description (max 50 characters):");
            if (!checkTaskDescription(taskDescription)) {
                JOptionPane.showMessageDialog(null, "Task description too long. Please try again.");
                return;
            }

            String developer = JOptionPane.showInputDialog("Enter developer details:");
            int duration = Integer.parseInt(JOptionPane.showInputDialog("Enter task duration (in hours):"));

            // Task Status Menu
            String[] statuses = {"To Do", "Doing", "Done"};
            String status = (String) JOptionPane.showInputDialog(
                    null,
                    "Select task status:",
                    "Task Status",
                    JOptionPane.QUESTION_MESSAGE,
                    null,
                    statuses,
                    statuses[0]
            );

            taskNames[taskCount] = taskName;
            taskDescriptions[taskCount] = taskDescription;
            developerDetails[taskCount] = developer;
            taskDurations[taskCount] = duration;
            taskStatuses[taskCount] = status;
            taskIDs[taskCount] = createTaskID(taskCount);

            taskCount++;
            JOptionPane.showMessageDialog(null, "Task successfully added!");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "An error occurred while adding the task: " + e.getMessage());
        }
    }

    public void viewTasks() {
        if (taskCount == 0) {
            JOptionPane.showMessageDialog(null, "No tasks available to display.");
            return;
        }

        StringBuilder taskDetails = new StringBuilder("Task Details:\n");
        for (int i = 0; i < taskCount; i++) {
            taskDetails.append("\nTask ").append(i + 1).append(":\n")
                    .append("Task ID: ").append(taskIDs[i]).append("\n")
                    .append("Task Name: ").append(taskNames[i]).append("\n")
                    .append("Task Description: ").append(taskDescriptions[i]).append("\n")
                    .append("Developer: ").append(developerDetails[i]).append("\n")
                    .append("Duration: ").append(taskDurations[i]).append(" hours\n")
                    .append("Status: ").append(taskStatuses[i]).append("\n");
        }

        JOptionPane.showMessageDialog(null, taskDetails.toString());
    }

    public void calculateTotalHours() {
        int totalHours = 0;
        for (int i = 0; i < taskCount; i++) {
            totalHours += taskDurations[i];
        }
        JOptionPane.showMessageDialog(null, "Total Task Duration: " + totalHours + " hours.");
    }
}

