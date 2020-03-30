package com.intact.moviesbox.data.interceptors

import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response
import timber.log.Timber
import java.io.IOException

/**
 * Interceptor file to print the logs for the api hit
 */

class APIInterceptor : Interceptor {

    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {

        // generate the request
        val request: Request = chain.request()

        // print the request
        Timber.d("**********************       REQUEST INITIATED     **********************")
        Timber.d("REQUEST URL -> %s", request.url)
        Timber.d("REQUEST HEADERS -> %s", request.headers)
        Timber.d("**********************       REQUEST FINISHED     **********************")

        // generate the response
        val response = chain.proceed(chain.request())

        // print the response
        Timber.d("**********************       RESPONSE INITIATED     **********************")
        Timber.d("RESPONSE CODE -> %s", response.code)
        Timber.d("RESPONSE HEADERS -> %s", response.headers)
        Timber.d("RESPONSE BODY -> %s", response.body)
        Timber.d("**********************       RESPONSE FINISHED     **********************")

        // returning the response object
        return response
    }
}