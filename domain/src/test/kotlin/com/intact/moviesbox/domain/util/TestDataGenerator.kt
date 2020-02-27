package com.intact.moviesbox.domain.util

import com.intact.moviesbox.domain.entities.MovieEntity
import com.intact.moviesbox.domain.entities.NowPlayingMoviesEntity

class TestDataGenerator {

    companion object {
        fun getPopularMoviesList(): NowPlayingMoviesEntity {
            val arrayList = ArrayList<MovieEntity>()
//            arrayList.add(
//                MovieEntity(
//                    392,
//                    429203,
//                    null,
//                    "4.5",
//                    "The Old Man & the Gun",
//                    "287.96",
//                    "/keym7MPn1icW1wWfzMnW3HeuzWU.jpg",
//                    "en",
//                    "Fast & Furious Presents: Hobbs & Shaw",
//                    "/hpgda6P9GutvdkDX5MUJ92QG9aj.jpg",
//                    false,
//                    "The true story of Forrest Tucker, from his audacious escape from San Quentin at the age of 70 to an unprecedented string of heists that confounded authorities and enchanted the public.  Wrapped up in the pursuit are a detective, who becomes captivated with Forrest’s commitment to his craft, and a woman, fullmazahd.in who loves him in spite of his chosen profession.",
//                    "2018-09-28"
//                )
//            )
            return NowPlayingMoviesEntity(pageNumber = "1", totalPages = "500", movies = arrayList)
        }
    }
}