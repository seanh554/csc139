import java.util.*;

// Combination of priority and RR scheduling
public class PriorityRR implements Algorithm {
    private List<Task> queue;
    private boolean uniquePriority;

    public PriorityRR(List<Task> queue) {
        this.queue = queue;
        uniquePriority = true;
    }

    // Runs tasks until the queue is empty
    public void schedule() {
        Task currentTask = null;
        int slice;

        while (!queue.isEmpty()) {
            currentTask = this.pickNextTask();
            slice = currentTask.getBurst();

            // Schedules tasks round-robin style for tasks with the same priority
            if (slice > 10 && !getUniquePriority()) {
                CPU.run(currentTask, 10);
                currentTask.setBurst(currentTask.getBurst() - 10);
                queue.add(currentTask);
            }
            else {
                // No need to do RR if there is only one task with that specific priority
                CPU.run(currentTask, slice);
            }
        }
    }

    // Returns the task with the highest priority
    public Task pickNextTask() {
        Iterator<Task> iter = queue.iterator();
        Task highestPriority = iter.next();
        Task currentTask = null;
        setUniquePriority(true);

        while (iter.hasNext()) {
            currentTask = iter.next();

            // Does a smaller the number mean higher or lower priority?
            if (currentTask.getPriority() < highestPriority.getPriority()) {
                highestPriority = currentTask;
                setUniquePriority(true);
            }
            else if (currentTask.getPriority() == highestPriority.getPriority()) {
                setUniquePriority(false);
            }
        }

        queue.remove(highestPriority);

        return highestPriority;
    }

    public boolean getUniquePriority() {
        return uniquePriority;
    }

    public void setUniquePriority(boolean b) {
        uniquePriority = b;
    }
}
