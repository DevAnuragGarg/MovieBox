package com.intact.moviesbox.domain.util

import com.intact.moviesbox.domain.entities.*

class TestDataGenerator {

    companion object {
        fun getPopularMoviesList(): PopularMoviesDomainDTO {
            val arrayList = ArrayList<MovieDomainDTO>()
            arrayList.add(
                MovieDomainDTO(
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
                    "/xnjvwfDulnOCy8qtYX0iqydmMhk.jpg",
                    6.8f,
                    "2020-01-01",
                    "/xcUf6yIheo78btFqihlRLftdR3M.jpg",
                    "Dolittle",
                    "en"
                )
            )
            return PopularMoviesDomainDTO(pageNumber = "1", totalPages = "500", movies = arrayList)
        }

        fun getTrendingMoviesList(): TrendingMoviesDomainDTO {
            val arrayList = ArrayList<MovieDomainDTO>()
            arrayList.add(
                MovieDomainDTO(
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
                    "/xnjvwfDulnOCy8qtYX0iqydmMhk.jpg",
                    6.8f,
                    "2020-01-01",
                    "/xcUf6yIheo78btFqihlRLftdR3M.jpg",
                    "Dolittle",
                    "en"
                )
            )
            return TrendingMoviesDomainDTO(pageNumber = "1", totalPages = "500", movies = arrayList)
        }

        fun getNowPlayingMoviesList(): NowPlayingMoviesDomainDTO {
            val arrayList = ArrayList<MovieDomainDTO>()
            arrayList.add(
                MovieDomainDTO(
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
                    "/xnjvwfDulnOCy8qtYX0iqydmMhk.jpg",
                    6.8f,
                    "2020-01-01",
                    "/xcUf6yIheo78btFqihlRLftdR3M.jpg",
                    "Dolittle",
                    "en"
                )
            )
            return NowPlayingMoviesDomainDTO(
                pageNumber = "1",
                totalPages = "500",
                movies = arrayList
            )
        }

        fun getTopRatedMoviesList(): TopRatedMoviesDomainDTO {
            val arrayList = ArrayList<MovieDomainDTO>()
            arrayList.add(
                MovieDomainDTO(
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
                    "/xnjvwfDulnOCy8qtYX0iqydmMhk.jpg",
                    6.8f,
                    "2020-01-01",
                    "/xcUf6yIheo78btFqihlRLftdR3M.jpg",
                    "Dolittle",
                    "en"
                )
            )
            return TopRatedMoviesDomainDTO(pageNumber = "1", totalPages = "500", movies = arrayList)
        }

        fun getUpcomingMoviesList(): UpcomingMoviesDomainDTO {
            val arrayList = ArrayList<MovieDomainDTO>()
            arrayList.add(
                MovieDomainDTO(
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
                    "/xnjvwfDulnOCy8qtYX0iqydmMhk.jpg",
                    6.8f,
                    "2020-01-01",
                    "/xcUf6yIheo78btFqihlRLftdR3M.jpg",
                    "Dolittle",
                    "en"
                )
            )
            return UpcomingMoviesDomainDTO(pageNumber = "1", totalPages = "500", movies = arrayList)
        }
    }
}