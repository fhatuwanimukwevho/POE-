/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import javax.swing.JOptionPane;
/**
 *
 *  @ST10452212 Fhatuwani Mukwevho
 */

public class main {

    public static void main(String[] args) {
        Login loginManager = new Login(); // Manages user login and registration
        TaskReport taskReportManager = new TaskReport(); // Manages tasks and reports
        boolean exit = false; // Controls the main program loop
        boolean isLoggedIn = false; // Tracks the login state of the user
        String loggedInUsername = null; // Stores the username of the logged-in user

        while (!exit) { // Main loop continues until the user chooses to exit
            try {
                // Display menu based on whether the user is logged in
                String menu;
                if (!isLoggedIn) {
                    // Menu for users who are not logged in
                    menu = JOptionPane.showInputDialog(
                            "Choose an option:\n" +
                            "1. Login\n" +
                            "2. Register\n" +
                            "0. Exit"
                    );
                } else {
                    // Menu for users who are logged in
                    menu = JOptionPane.showInputDialog(
                            "Choose an option:\n" +
                            "1. Add Task\n" +
                            "2. View Tasks with Status 'Done'\n" +
                            "3. View Task with Longest Duration\n" +
                            "4. Search for Task by Name\n" +
                            "5. Search for Tasks by Developer\n" +
                            "6. Delete Task by Name\n" +
                            "7. Display Full Task Report\n" +
                            "0. Logout"
                    );
                }

                // Parse the user's choice
                int choice = Integer.parseInt(menu);

                if (!isLoggedIn) { // Actions for unauthenticated users
                    switch (choice) {
                        case 1: // Login
                            String username = JOptionPane.showInputDialog("Enter your username:");
                            String password = JOptionPane.showInputDialog("Enter your password:");

                            boolean loginSuccess = loginManager.loginUser(username, password); // Check credentials
                            if (loginSuccess) {
                                isLoggedIn = true; // Mark user as logged in
                                loggedInUsername = username; // Store the logged-in username
                                // Display welcome message after successful login
                                JOptionPane.showMessageDialog(null, "Welcome to EasyKanban!");
                            }
                            // Display login status message
                            JOptionPane.showMessageDialog(null, loginManager.returnLoginStatus(loginSuccess, username));
                            break;

                        case 2: // Register
                            String firstName = JOptionPane.showInputDialog("Enter your first name:");
                            String lastName = JOptionPane.showInputDialog("Enter your last name:");
                            String regUsername = JOptionPane.showInputDialog("Create a username:");
                            String regPassword = JOptionPane.showInputDialog("Create a password:");

                            // Attempt to register the user
                            String response = loginManager.registerUser(firstName, lastName, regUsername, regPassword);
                            JOptionPane.showMessageDialog(null, response);

                            if (response.equals("User has been registered successfully.")) {
                                isLoggedIn = true; // Automatically log in the user
                                loggedInUsername = regUsername;
                                // Display welcome message after successful registration
                                JOptionPane.showMessageDialog(null, "Welcome to EasyKanban!");
                            }
                            break;

                        case 0: // Exit
                            exit = true; // Exit the program
                            JOptionPane.showMessageDialog(null, "Exiting... Goodbye!");
                            break;

                        default:
                            JOptionPane.showMessageDialog(null, "Invalid choice. Please try again.");
                    }
                } else { // Actions for authenticated users
                    switch (choice) {
                        case 1: // Add Task
                            String developer = JOptionPane.showInputDialog("Enter developer details:");
                            String taskName = JOptionPane.showInputDialog("Enter task name:");
                            String taskDescription = JOptionPane.showInputDialog("Enter task description (max 50 characters):");
                            if (taskDescription.length() > 50) {
                                JOptionPane.showMessageDialog(null, "Task description too long. Please try again.");
                                break;
                            }
                            int duration = Integer.parseInt(JOptionPane.showInputDialog("Enter task duration (in hours):"));
                            String status = JOptionPane.showInputDialog("Enter task status (e.g., To Do, In Progress, Done):");
                            String taskID = taskName.substring(0, 2).toUpperCase() + ":" + taskReportManager.hashCode() + ":" + developer.substring(developer.length() - 3).toUpperCase();

                            // Add the task to the report manager
                            taskReportManager.addTask(developer, taskName, taskID, duration, status);
                            break;

                        case 2: // View Tasks with Status 'Done'
                            taskReportManager.displayTasksWithStatusDone();
                            break;

                        case 3: // View Task with Longest Duration
                            taskReportManager.displayLongestDurationTask();
                            break;

                        case 4: // Search for Task by Name
                            String searchTaskName = JOptionPane.showInputDialog("Enter the task name to search:");
                            taskReportManager.searchTaskByName(searchTaskName);
                            break;

                        case 5: // Search for Tasks by Developer
                            String searchDeveloper = JOptionPane.showInputDialog("Enter the developer's name:");
                            taskReportManager.searchTasksByDeveloper(searchDeveloper);
                            break;

                        case 6: // Delete Task by Name
                            String deleteTaskName = JOptionPane.showInputDialog("Enter the task name to delete:");
                            taskReportManager.deleteTaskByName(deleteTaskName);
                            break;

                        case 7: // Display Full Task Report
                            taskReportManager.displayReport();
                            break;

                        case 0: // Logout
                            isLoggedIn = false; // Log the user out
                            loggedInUsername = null; // Clear the logged-in username
                            JOptionPane.showMessageDialog(null, "You have logged out.");
                            break;

                        default:
                            JOptionPane.showMessageDialog(null, "Invalid choice. Please try again.");
                    }
                }
            } catch (NumberFormatException e) {
                // Handle invalid menu input
                JOptionPane.showMessageDialog(null, "Invalid input. Please enter a valid number.");
            } catch (Exception e) {
                // Handle unexpected errors
                JOptionPane.showMessageDialog(null, "An unexpected error occurred: " + e.getMessage());
                e.printStackTrace(); // Print error details for debugging
            }
        }
    }
}
