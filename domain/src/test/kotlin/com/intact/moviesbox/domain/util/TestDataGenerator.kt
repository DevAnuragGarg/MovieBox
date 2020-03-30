package com.intact.moviesbox.domain.util

import com.intact.moviesbox.domain.entities.*

class TestDataGenerator {

    companion object {
        fun getPopularMoviesList(): PopularMoviesEntity {
            val arrayList = ArrayList<MovieEntity>()
            arrayList.add(
                MovieEntity(
                    392,
                    429203,
                    null,
                    "The Invisible Man",
                    894,
                    0,
                    false,
                    "",
                    "",
                    "",
                    "After losing his wife seven years earlier, the eccentric Dr. John Dolittle",
                    213.017f,
                    6.8f,
                    "/xnjvwfDulnOCy8qtYX0iqydmMhk.jpg",
                    "2020-01-01",
                    "/xcUf6yIheo78btFqihlRLftdR3M.jpg",
                    "Dolittle",
                    "en"
                )
            )
            return PopularMoviesEntity(pageNumber = "1", totalPages = "500", movies = arrayList)
        }

        fun getTrendingMoviesList(): TrendingMoviesEntity {
            val arrayList = ArrayList<MovieEntity>()
            arrayList.add(
                MovieEntity(
                    392,
                    429203,
                    null,
                    "The Invisible Man",
                    894,
                    0,
                    false,
                    "",
                    "",
                    "",
                    "After losing his wife seven years earlier, the eccentric Dr. John Dolittle",
                    213.017f,
                    6.8f,
                    "/xnjvwfDulnOCy8qtYX0iqydmMhk.jpg",
                    "2020-01-01",
                    "/xcUf6yIheo78btFqihlRLftdR3M.jpg",
                    "Dolittle",
                    "en"
                )
            )
            return TrendingMoviesEntity(pageNumber = "1", totalPages = "500", movies = arrayList)
        }

        fun getNowPlayingMoviesList(): NowPlayingMoviesEntity {
            val arrayList = ArrayList<MovieEntity>()
            arrayList.add(
                MovieEntity(
                    392,
                    429203,
                    null,
                    "The Invisible Man",
                    894,
                    0,
                    false,
                    "",
                    "",
                    "",
                    "After losing his wife seven years earlier, the eccentric Dr. John Dolittle",
                    213.017f,
                    6.8f,
                    "/xnjvwfDulnOCy8qtYX0iqydmMhk.jpg",
                    "2020-01-01",
                    "/xcUf6yIheo78btFqihlRLftdR3M.jpg",
                    "Dolittle",
                    "en"
                )
            )
            return NowPlayingMoviesEntity(pageNumber = "1", totalPages = "500", movies = arrayList)
        }

        fun getTopRatedMoviesList(): TopRatedMoviesEntity {
            val arrayList = ArrayList<MovieEntity>()
            arrayList.add(
                MovieEntity(
                    392,
                    429203,
                    null,
                    "The Invisible Man",
                    894,
                    0,
                    false,
                    "",
                    "",
                    "",
                    "After losing his wife seven years earlier, the eccentric Dr. John Dolittle",
                    213.017f,
                    6.8f,
                    "/xnjvwfDulnOCy8qtYX0iqydmMhk.jpg",
                    "2020-01-01",
                    "/xcUf6yIheo78btFqihlRLftdR3M.jpg",
                    "Dolittle",
                    "en"
                )
            )
            return TopRatedMoviesEntity(pageNumber = "1", totalPages = "500", movies = arrayList)
        }

        fun getUpcomingMoviesList(): UpcomingMoviesEntity {
            val arrayList = ArrayList<MovieEntity>()
            arrayList.add(
                MovieEntity(
                    392,
                    429203,
                    null,
                    "The Invisible Man",
                    894,
                    0,
                    false,
                    "",
                    "",
                    "",
                    "After losing his wife seven years earlier, the eccentric Dr. John Dolittle",
                    213.017f,
                    6.8f,
                    "/xnjvwfDulnOCy8qtYX0iqydmMhk.jpg",
                    "2020-01-01",
                    "/xcUf6yIheo78btFqihlRLftdR3M.jpg",
                    "Dolittle",
                    "en"
                )
            )
            return UpcomingMoviesEntity(pageNumber = "1", totalPages = "500", movies = arrayList)
        }
    }
}