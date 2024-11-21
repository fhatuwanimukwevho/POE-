/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author FHATUWANI
 */
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class TaskReportTest {
    @Test
    void testAddTask() {
        TaskReport report = new TaskReport();
        report.addTask("John Doe", "Develop Feature", "DF:1:DOE", 5, "To Do");

        assertEquals(1, report.getTaskCount());
        assertEquals("John Doe", report.getDeveloper(0));
        assertEquals("Develop Feature", report.getTaskName(0));
        assertEquals("DF:1:DOE", report.getTaskID(0));
        assertEquals(5, report.getTaskDuration(0));
        assertEquals("To Do", report.getTaskStatus(0));
    }

    @Test
    void testDeleteTaskByName() {
        TaskReport report = new TaskReport();
        report.addTask("John Doe", "Develop Feature", "DF:1:DOE", 5, "To Do");
        report.deleteTaskByName("Develop Feature");

        assertEquals(0, report.getTaskCount());
    }

    @Test
    void testDisplayLongestDurationTask() {
        TaskReport report = new TaskReport();
        report.addTask("Alice", "Bug Fix", "BF:1:ICE", 3, "Done");
        report.addTask("Bob", "Develop Feature", "DF:2:BOB", 8, "To Do");

        String result = report.getLongestDurationTask();
        assertTrue(result.contains("Bob"));
        assertTrue(result.contains("Develop Feature"));
    }

    @Test
    void testSearchTasksByDeveloper() {
        TaskReport report = new TaskReport();
        report.addTask("Alice", "Bug Fix", "BF:1:ICE", 3, "Done");
        report.addTask("Alice", "Test Code", "TC:2:ICE", 4, "To Do");

        String result = report.getTasksByDeveloper("Alice");
        assertTrue(result.contains("Bug Fix"));
        assertTrue(result.contains("Test Code"));
    }
}
