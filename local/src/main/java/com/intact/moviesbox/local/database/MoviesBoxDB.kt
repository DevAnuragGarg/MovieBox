package com.intact.moviesbox.local.database

import android.content.Context
import androidx.annotation.NonNull
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.intact.moviesbox.local.entity.MovieEntity

@Database(entities = [MovieEntity::class], version = 1, exportSchema = false)
abstract class MoviesBoxDB : RoomDatabase() {

    companion object {
        private val LOCK = Any()
        private const val DATABASE_NAME = "movie.db"

        @Volatile
        private var INSTANCE: MoviesBoxDB? = null

        fun getInstance(@NonNull context: Context): MoviesBoxDB {

            if (INSTANCE == null) {
                synchronized(LOCK) {

                    if (INSTANCE == null) {
                        INSTANCE = Room.databaseBuilder(
                            context,
                            MoviesBoxDB::class.java,
                            DATABASE_NAME
                        )
                            .fallbackToDestructiveMigration()
                            .build()
                    }
                }
            }
            return INSTANCE!!
        }
    }

    abstract fun getMovieDAO(): MovieDAO
}