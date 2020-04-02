package com.intact.moviesbox.local.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.intact.moviesbox.local.entity.MovieEntity
import io.reactivex.Completable
import io.reactivex.Single

@Dao
interface MovieDAO {

    @Query("SELECT * FROM movie WHERE movie_id = :movieId")
    fun getMovieDetail(movieId: Long): Single<MovieEntity>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun saveMovie(movieEntity: MovieEntity): Completable

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun saveAllMovies(movieEntities: List<MovieEntity>): Completable
}