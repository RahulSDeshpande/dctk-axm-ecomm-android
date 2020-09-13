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
fun <T> Iterable<T>?.toList() =
    if (this != null && this is Collection) {
        when (size) {
            0 -> emptyList()
            1 -> listOf(if (this is List) get(0) else iterator().next())
            else -> toMutableList()
        }
    } else {
        mutableListOf()
    }

/**
 * Converts any collection to an instance of [ArrayList]
 */
fun <E> Collection<E>?.toArrayList(): ArrayList<E> =
    if (this == null) arrayListOf()
    else ArrayList(this)

/**
 * Converts any [Map] to an instance of [HashMap]
 */
fun <K, V> Map<K, V>?.toHashMap(): HashMap<K, V> =
    if (this == null) HashMap()
    else HashMap(this)

/**
 * Adds new value to the list of not already exists
 */
fun <E> ArrayList<E>?.addIfNotExists(value: E) {
    if (this != null && contains(value).not()) {
        add(value)
    }
}