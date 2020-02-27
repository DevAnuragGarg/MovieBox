package com.intact.moviesbox.remote.mapper

/**
 * mapper class to convert one class into another
 */
interface Mapper<T, E> {

    fun from(e: E): T
    fun to(t: T): E
}