package yoda.commands;

import yoda.TaskList;
import yoda.exceptions.InvalidInputException;
import yoda.tasks.Task;

public class UnmarkCommand extends Command {
    private TaskList taskList;
    private String input;

    public UnmarkCommand(TaskList taskList, String input) {
        this.taskList = taskList;
        this.input = input;
    }

    public void run() throws InvalidInputException {
        if (!hasValidFormat(input)) {
            throw new InvalidInputException("Unmark... which one?");
        }
        String[] splitInput = input.split(" ", 2);
        int index = Integer.parseInt(splitInput[1]);
        Task currentTask = taskList.get(index - 1);
        currentTask.markNotDone();
        System.out.println("Marked this as not done, I have");
        System.out.printf("%s\n", taskList.get(index - 1));
    }

    private boolean hasValidFormat(String input) {
        String[] splitInput = input.split(" ", 2);
        if (splitInput.length == 2) {
            if (splitInput[1].matches("\\d+")) {
                return Integer.parseInt(splitInput[1]) <= taskList.getLength();
            } else {
                return false;
            }
        } else {
            return false;
        }
    }
}
