package com.example.android.cryptoapp.util

import retrofit2.Response
import retrofit2.Retrofit
import java.io.IOException
import java.lang.NullPointerException

/**
 * parses error response body
 *
 */
object ErrorUtils {


    inline fun <reified T>parseError(response: Response<T>?, retrofit: Retrofit): T {
        if(response == null) {
            throw NullPointerException("The response object of cannot be null")
        }
        val converter = retrofit.responseBodyConverter<T>(T::class.java, arrayOfNulls(0))
        return try {
            converter.convert(response.errorBody())!!
        } catch (e: IOException) {
            throw Throwable("An Exception Occurred ")

        }
    }

//    inline fun <reified T,E>parseError(response: Response<E>?, retrofit: Retrofit): T? {
//        if(response == null) {
//            return null
//        }
//        val converter = retrofit.responseBodyConverter<T>(T::class.java, arrayOfNulls(0))
//        return try {
//              converter.convert(response.errorBody()!!)
//        } catch (e: IOException) {
//           null
//        }
//    }






}