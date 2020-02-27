package com.intact.moviesbox.remote.mapper

/**
 * mapper class to convert data to remote layers or vice versa
 */
interface Mapper<T, E> {

    fun from(e: E): T

    fun to(t: T): E
}