package com.intact.moviesbox.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "movie")
data class MovieEntity(
    @PrimaryKey @ColumnInfo(name = "movie_id") val id: Long,
    @ColumnInfo(name = "run_time") val runtime: Int?,
    @ColumnInfo(name = "budget") val budget: Long?,
    @ColumnInfo(name = "title") val title: String,
    @ColumnInfo(name = "voteCount") val voteCount: Int,
    @ColumnInfo(name = "revenue") val revenue: Long?,
    @ColumnInfo(name = "adult") val adult: Boolean,
    @ColumnInfo(name = "status") val status: String?,
    @ColumnInfo(name = "imbd_id") val imdbId: String?,
    @ColumnInfo(name = "tag_line") val tagLine: String?,
    @ColumnInfo(name = "overview") val overview: String,
    @ColumnInfo(name = "popularity") val popularity: Float,
    @ColumnInfo(name = "poster_path") val posterPath: String,
    @ColumnInfo(name = "vote_average") val voteAverage: Float?,
    @ColumnInfo(name = "release_date") val releaseDate: String,
    @ColumnInfo(name = "backdrop_path") val backdropPath: String?,
    @ColumnInfo(name = "original_title") val originalTitle: String,
    @ColumnInfo(name = "original_language") val originalLanguage: String
)