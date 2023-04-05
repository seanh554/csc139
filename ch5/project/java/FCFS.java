import java.util.*;

// First-come first-serve, the process that requests the CPU is allocated the CPU first
public class FCFS implements Algorithm {
    private List<Task> queue;

    public FCFS(List<Task> queue) {
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

    // Returns the next task in the queue, removes it from the queue
    public Task pickNextTask() {
        if (!queue.isEmpty()) {
            return queue.remove(0);
        }
        else {
            return null;
        }
    }
}
