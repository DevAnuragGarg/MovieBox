package com.intact.moviesbox.local.mapper

/**
 * mapper class to convert data to local entity or vice versa
 */
interface Mapper<T, E> {

    fun from(e: E): T

    fun to(t: T): E
}