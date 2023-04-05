import java.util.*;

// Shortest job first, CPU is assigned to the process that has the next smallest burst
public class SJF implements Algorithm {
    private List<Task> queue;

    public SJF(List<Task> queue) {
        this.queue = queue;
    }

    // Runs tasks until the queue is empty
    public void schedule() {
        Task currentTask = null;

        while (!queue.isEmpty()) {
            currentTask = this.pickNextTask();

            CPU.run(currentTask, currentTask.getBurst());
        }
    }

    // Returns the task with the next smallest burst and removes it from the queue
    public Task pickNextTask() {
        Iterator<Task> iter = queue.iterator();
        Task shortestTask = iter.next();
        Task currentTask = null;

        while (iter.hasNext()) {
            currentTask = iter.next();

            if (currentTask.getBurst() < shortestTask.getBurst()) {
                shortestTask = currentTask;
            }
        }

        queue.remove(shortestTask);

        return shortestTask;
    }
}

