package com.hellmund.library.actions

/**
 * This interface represents data objects on which actions can be performed. For instance, a contact object can be
 * edited, removed and shared. To accommodate this behavior, data classes that implement this interface can provide
 * a list of [Action]s that can be performed on it.
 */
interface Actionable {

    /**
     * Returns a list of [Action]s that can be performed on objects of the class that implements [Actionable].
     *
     * @return A list of [Action]s
     */
    fun getActions(): List<Action>

}