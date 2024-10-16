package kz.grandera.vlifetesttaskapp.core.coroutines

import kotlin.coroutines.CoroutineContext
import kotlin.reflect.KClass

/**
 * @param exceptions - exceptions to exclude from handling
 *
 * Code example:
 *  safeLaunch(ExcludeExceptions(*TIMEOUT_EXCEPTIONS)) {
 *      // do something
 *  }.invokeOnFailure {
 *      if (it::class in TIMEOUT_EXCEPTIONS) {
 *          // handle timeout
 *      }
 *  }
 */
public class ExcludeExceptions(
    public vararg val exceptions: KClass<out Exception>
) : CoroutineContext.Element {

    public companion object Key : CoroutineContext.Key<ExcludeExceptions>

    override val key: CoroutineContext.Key<*> = Key

    public fun contains(throwable: Throwable): Boolean = throwable::class in exceptions
}