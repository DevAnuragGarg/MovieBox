package com.intact.filmireview.presentation.model

/**
 * At any point of time there can be only one state in UI. It can be
 * either loading, completed successfully or error. We don't want to
 * supply individual state. Live data can hold only one type of data.
 * This class is a representation of UI state at any point of time.
 */

data class Resource<out T>(val status: Status, val data: T?, val message: String?) {

    companion object {
        fun <T> success(data: T?): Resource<T> {
            return Resource(Status.SUCCESS, data, null)
        }

        fun <T> error(msg: String, data: T? = null): Resource<T> {
            return Resource(Status.ERROR, data, msg)
        }

        fun <T> loading(): Resource<T> {
            return Resource(Status.LOADING, null, null)
        }
    }
}

enum class Status {
    LOADING,
    ERROR,
    SUCCESS
}