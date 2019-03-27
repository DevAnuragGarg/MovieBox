package com.intact.filmireview.data.interceptor

import okhttp3.Interceptor
import okhttp3.Response
import timber.log.Timber
import java.io.IOException

/*
* Created by Anurag on 18-March-2019
*
* Interceptor file to print the
*
*/

class APIInterceptor : Interceptor {

    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {

        // generate the request
        val request = chain.request()

        // print the request
        Timber.d("**********************       REQUEST INITIATED     **********************")
        Timber.d("REQUEST URL -> %s", request.url())
        Timber.d("REQUEST HEADERS -> %s", request.headers())
        Timber.d("**********************       REQUEST FINISHED     **********************")

        // generate the response
        val response = chain.proceed(chain.request())

        // print the response
        Timber.d("**********************       RESPONSE INITIATED     **********************")
        Timber.d("RESPONSE CODE -> %s", response.code())
        Timber.d("RESPONSE HEADERS -> %s", response.headers())
        Timber.d("**********************       RESPONSE FINISHED     **********************")

        // returning the response object
        return response
    }
}