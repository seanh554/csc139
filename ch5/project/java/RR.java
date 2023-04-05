import java.util.*;

// Round-robin scheduling, allocates CPU to a process for up to 1 time quantum
public class RR implements Algorithm {
    private List<Task> queue;

    public RR(List<Task> queue) {
        this.queue = queue;
    }

    // Runs tasks until the queue is empty, each task gets a time slice
    public void schedule() {
        Task currentTask = null;
        int slice;

        while (!queue.isEmpty()) {
            currentTask = this.pickNextTask();

            slice = currentTask.getBurst();

            // Tasks only gets a certain amount of time, if more is needed, they are added to end of the queue
            if (slice > 10) {
                CPU.run(currentTask, 10);
                currentTask.setBurst(currentTask.getBurst() - 10);
                queue.add(currentTask);
            }
            else {
                CPU.run(currentTask, slice);                
            }
        }
    }

    // Returns the next task in the queue
    public Task pickNextTask() {
        if (!queue.isEmpty()) {
            return queue.remove(0);
        }
        else {
            return null;
        }
    }
}

