package kz.grandera.vlifetesttaskapp.core.coroutines.taskhandler

/**
 * An abstraction for task management purposes via strategies
 */
public interface Task {

    public sealed class Status {
        public data object Created : Status()
        public data object InProgress : Status()
        public data object Completed : Status()
        public data object Cancelled : Status()
    }

    /**
     * Strategy of the launching a new task with the same id if the previous is not completed
     */
    public sealed class Strategy {
        /**
         * Awaiting previous task response, new task is not launching
         */
        public data object KeepFirst : Strategy()

        /**
         * Previous task is cancelling, new task is launching
         */
        public data object KillFirst : Strategy()
    }

    public fun getId(): String

    public fun execute(onFinish: () -> Unit)

    public fun cancel()

    public fun getStatus(): Status

    public fun getStrategy(): Strategy
}