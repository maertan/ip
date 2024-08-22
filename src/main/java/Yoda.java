import java.util.Objects;
import java.util.Scanner;
import java.util.ArrayList;
public class Yoda {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String line = "____________________________________________________________\n";
        String welcomeMessage = "Hello! For you, what can I do?\n";
        String exitMessage = "Bye. See you again soon, I hope to.\n";

        ArrayList<Task> tasks = new ArrayList<>();


        System.out.println(line + welcomeMessage + line);

        while (true) {
            String input = scanner.nextLine().trim();
            String[] splitInput = input.split(" ");


            if (Objects.equals(splitInput[0], "bye")) {
                System.out.println(exitMessage + line);
                break;
            } else if (Objects.equals(splitInput[0], "list")) {
                System.out.println(line);
                System.out.println("Do or do not, there is no try.");
                Task[] taskArray = tasks.toArray(new Task[0]);
                for (int i = 0; i < taskArray.length; i++) {
                    System.out.printf("%d. %s\n", i + 1, taskArray[i]);
                }
                System.out.println(line);
            } else if (Objects.equals(splitInput[0], "mark")) {
                int index = Integer.parseInt(splitInput[1]);
                Task currentTask = tasks.get(index - 1);
                currentTask.markDone();
                System.out.println(line + "Good job! Marked this as done, I have");
                System.out.printf("%s\n" + line, tasks.get(index - 1));

            } else if (Objects.equals(splitInput[0], "unmark")) {
                int index = Integer.parseInt(splitInput[1]);
                Task currentTask = tasks.get(index - 1);
                currentTask.markNotDone();
                System.out.println(line + "Marked this as not done, I have");
                System.out.printf("%s\n" + line, tasks.get(index - 1));
            }
            else {
                tasks.add(new Task(input));
                System.out.println(line + input + "\n" + line);
            }
        }
    }
}
