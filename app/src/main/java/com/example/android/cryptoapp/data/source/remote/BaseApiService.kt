package com.example.android.cryptoapp.data.source.remote

import android.nfc.Tag
import android.util.Log
import com.example.android.cryptoapp.util.Result
import com.google.gson.Gson
import okhttp3.ResponseBody
import retrofit2.Response
import timber.log.Timber
import java.lang.Exception

abstract class BaseApiService {
     val  TAG :String = "BaseApiService"
    protected suspend fun <T:Any> apiCall(networkRequest :suspend  () ->Response<T>):Result<T>{

        val response: Response<T>
        try {
           response= networkRequest.invoke()

        }catch (e:Throwable){
           Log.e("Tag", Gson().toJson(e.message))
           return Result.Error( mapResponseThrowable(e))
        }

       return  if(response.isSuccessful){
                   Result.Success(response.body()!!)
       } else {
         return  if (response.body() == null) {
             Result.Error("No response from Server")
         }else{

             Timber.e( Gson().toJson(response.errorBody()))
             //Todo Use the retrofit converter class
//             Result.Error(ParseCustomError(response.errorBody()))
             Result.Error("")
        }
            }

    }

    abstract  fun mapResponseThrowable(e:Throwable):String

    abstract fun ParseCustomError(errorClass: ResponseBody):String

}