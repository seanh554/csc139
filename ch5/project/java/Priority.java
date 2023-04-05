import java.util.*;

// Priority scheduling, CPU is allocated to process with the highest priority
public class Priority implements Algorithm {
    private List<Task> queue;

    public Priority(List<Task> queue) {
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

    // Returns the task with the highest priority, removes it from the queue
    public Task pickNextTask() {
        Iterator<Task> iter = queue.iterator();
        Task highestPriority = iter.next();
        Task currentTask = null;

        while (iter.hasNext()) {
            currentTask = iter.next();

            // Does the smaller the number mean higher priority?
            if (currentTask.getPriority() < highestPriority.getPriority()) {
                highestPriority = currentTask;
            }
        }

        queue.remove(highestPriority);

        return highestPriority;
    }
}
