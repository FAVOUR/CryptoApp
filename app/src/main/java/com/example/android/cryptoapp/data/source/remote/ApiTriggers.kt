package com.example.android.cryptoapp.data.source.remote

import android.nfc.Tag
import android.util.Log
import com.example.android.cryptoapp.util.ErrorUtils
import com.example.android.cryptoapp.util.Helpers.mapResponseThrowable
import com.example.android.cryptoapp.util.Result
import com.google.gson.Gson
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import okhttp3.ResponseBody
import retrofit2.HttpException
import retrofit2.Response
import retrofit2.Retrofit
import timber.log.Timber
import java.io.IOException
import java.lang.Exception
/**This class carters for making the api call and mapping the response into a sealed class */
abstract class  ApiTriggers<T> {
     val  TAG :String = "BaseApiService"
    protected suspend fun <T> apiCall(networkRequest :suspend  () ->Response<T>): Response<T> {

       return   try {
           networkRequest.invoke()

        }catch (e:Throwable){
           throw Throwable(e)
        }


    }

    protected fun <T> apiResponse(response: Response<T>, retrofit: Retrofit, errorHandler : (Response<T>,Retrofit) -> T ):Result<T> {
        try {
            return if (response.isSuccessful) {
                if (response.body() != null) {
                    Result.Success(response.body()!!)
                }
                else {
                    val response = errorHandler.invoke(response, retrofit)
                    Result.Error("Server Error ")
                }
            } else {
                val response = errorHandler.invoke(response, retrofit)
                val message = if (response == null) "No response from Server" else ""
                Result.Error(message)
            }
        }catch (e:Throwable){
            return Result.Error(mapResponseThrowable(e))
        }
    }




}