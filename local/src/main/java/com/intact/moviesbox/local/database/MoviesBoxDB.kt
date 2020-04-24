package com.intact.moviesbox.local.database

import android.content.Context
import androidx.annotation.NonNull
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.intact.moviesbox.local.entity.MovieEntity
import java.util.concurrent.Executors

/**
 * Do you need to add default data to your database, right after it was created or when the
 * database is opened? Use RoomDatabase#Callback! Call the addCallback method when building
 * your RoomDatabase and override either onCreate or onOpen. onCreate will be called when the
 * database is created for the first time, after the tables have been created. onOpen is
 * called when the database was opened. Since the DAOs can only be accessed once these methods
 *  return
 */
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
                            .addCallback(object : RoomDatabase.Callback() {
                                override fun onCreate(db: SupportSQLiteDatabase) {
                                    super.onCreate(db)
                                    Executors.newSingleThreadExecutor().execute {
                                        getInstance(context).getMovieDAO().deleteAllMovies()
                                    }
                                }
                            })
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