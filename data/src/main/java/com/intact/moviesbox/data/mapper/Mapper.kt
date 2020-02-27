package com.intact.moviesbox.data.mapper

/**
 * mapper class to convert domain to data or vice versa
 */
interface Mapper<T, E> {

    fun from(e: E): T

    fun to(t: T): E
}