package com.rahulografy.axmecomm.util.ext

/**
 * Kotlin extension to get the class name.
 *
 * This can also be used a TAGs for logging.
 *
 * @return Caller class' name
 */
val Any.TAG: String get() = javaClass.simpleName

/**
 * Returns a [List] containing all elements.
 */
fun <T> Iterable<T>?.toList(): List<T>? {
    if (this is Collection) {
        return when (size) {
            0 -> emptyList()
            1 -> listOf(if (this is List) get(0) else iterator().next())
            else -> this.toMutableList()
        }
    }
    return this?.toMutableList()
}