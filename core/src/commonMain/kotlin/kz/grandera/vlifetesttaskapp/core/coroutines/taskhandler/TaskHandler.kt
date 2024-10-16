package kz.grandera.vlifetesttaskapp.core.coroutines.taskhandler

public class TaskHandler {
    private val tasks = HashMap<String, Task>()

    public infix fun handle(task: Task) {
        when (task.getStrategy()) {
            Task.Strategy.KeepFirst -> handleKeepFirst(task)
            Task.Strategy.KillFirst -> handleKillFirst(task)
        }
    }

    public fun cancel(id: String) {
        tasks[id]?.let { cancel(it) }
        tasks.remove(id)
    }

    public fun cancelAll() {
        tasks.keys.forEach { tasks[it]?.cancel() }
        tasks.clear()
    }

    private fun handleKeepFirst(task: Task) {
        val previousTask = tasks[task.getId()]
        if (previousTask?.getStatus() == Task.Status.InProgress) return
        execute(task)
    }

    private fun handleKillFirst(task: Task) {
        cancel(task)
        execute(task)
    }

    private fun execute(task: Task) {
        tasks[task.getId()] = task
        task.execute { tasks.remove(task.getId()) }
    }

    private fun cancel(task: Task) {
        tasks[task.getId()]?.cancel()
        tasks.remove(task.getId())
    }
}