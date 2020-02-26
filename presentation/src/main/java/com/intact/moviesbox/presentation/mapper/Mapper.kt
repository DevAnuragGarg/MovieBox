package com.intact.moviesbox.presentation.mapper

/**
 * this class is used for converting data from domain
 * layer format to presentation layer format
 */
interface Mapper<T, E> {
    fun from(e: E): T
    fun to(t: T): E
}
