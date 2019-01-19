package restaurant.thread;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class Worker {
    private static Executor taskExecutor = Executors.newCachedThreadPool();

    public static void newTask(Runnable task) {
        taskExecutor.execute(task);
    }
}
