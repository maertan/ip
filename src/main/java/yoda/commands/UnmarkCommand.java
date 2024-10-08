package yoda.commands;

import yoda.TaskList;
import yoda.exceptions.YodaException;
import yoda.tasks.Task;

/**
 * Represents a command to mark a task as not done.
 */
public class UnmarkCommand extends Command {
    private TaskList taskList;
    private String input;

    /**
     * Constructs an UnmarkCommand with the specified task list and input
     *
     * @param taskList Task list from which the specified task will be
     *                 marked not done.
     * @param input User input containing the index of the task to be marked.
     */
    public UnmarkCommand(TaskList taskList, String input) {
        this.taskList = taskList;
        this.input = input;
    }

    /**
     * Executes the command to mark a task as not done.
     *
     * @return Yoda's response as a string
     * @throws YodaException if input format is valid or task is out of bounds
     */
    public String run() throws YodaException {
        checkFormat();
        return handle();
    }

    private String handle() {
        String[] splitInput = input.split(" ", 2);
        int index = Integer.parseInt(splitInput[1]);
        Task currentTask = taskList.get(index - 1);
        currentTask.markNotDone();
        String message = "Marked this as not done, I have" + String.format("%s\n", taskList.get(index - 1));
        return message;
    }

    private void checkFormat() throws YodaException {
        if (!hasValidFormat()) {
            throw new YodaException("Unmark... which one?" + "\n"
                    + "Command should be in format: unmark [number]");
        }
    }

    /**
     * Checks if input format is valid and index is in bounds of task list.
     *
     * @return true if input format is valid and index is in bounds of task list.
     */
    public boolean hasValidFormat() {
        String[] splitInput = input.split(" ", 2);
        if (splitInput.length == 2) {
            if (splitInput[1].matches("\\d+")) {
                int index = Integer.parseInt(splitInput[1]);
                return index <= taskList.getLength() && index > 0;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }
}
