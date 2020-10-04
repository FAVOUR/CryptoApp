package com.example.android.cryptoapp.data.source.remote

import com.example.android.cryptoapp.currency_data.CurrencyAbbreviation
import com.example.android.cryptoapp.currency_data.JsonResponse
import com.example.android.cryptoapp.data.source.IRemoteCryptoRateDataSource
import com.example.android.cryptoapp.data.source.local.db.CryptoCurrencyData
import com.example.android.cryptoapp.data.source.local.db.CurrencyDao
import com.example.android.cryptoapp.domain.model.CryptoCurrencyRates
import com.example.android.cryptoapp.util.Result
import com.example.android.cryptoapp.util.asDataBaseModel
import com.google.gson.Gson
import com.squareup.moshi.Moshi
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.HttpException
import retrofit2.Response
import timber.log.Timber
import java.io.IOException
import java.lang.Exception

class RemoteCryptoRateDataSource( private val apiClient:ApiClient, private val dispatcher: CoroutineDispatcher = Dispatchers.IO,val moshi: Moshi):IRemoteCryptoRateDataSource,BaseApiService() {
    override suspend fun refreshCryptoRates(cryptoCurrencyAbbreviation: CurrencyAbbreviation) {


    }

    override suspend fun getCryptoRates(cryptoCurrencyAbbreviation: String): Result<List<CryptoCurrencyData>> {
       var  result :Result<List<CryptoCurrencyData>>  = Result.Loading //TODO Test to see if there is need for this
        val client = ApiClient.MainApiClient.client?.create(CryptoCurrencyService::class.java)
        val cryptoList = apiCall { client?.getJsonResponses_(cryptoCurrencyAbbreviation)!!  }

           //TODO Find out a better way to deal with this the domain model at this stage
           result = when(cryptoList){
               is Result.Success->{
                      Result.Success(cryptoList.data.asDataBaseModel(CurrencyAbbreviation.USA_DOLLAR))
               }
               is Result.Error->{
                   Result.Error(cryptoList.errorMessage)
               }
               is Result.Loading->{
                   Result.Loading
               }
            }

        return  result

    }

    override suspend fun getCryptoRate(cryptoCurrencyAbbreviation: CurrencyAbbreviation): Result<List<CryptoCurrencyData>> {
        var  result :Result<List<CryptoCurrencyData>>  = Result.Loading //TODO Test to see if there is need for this
        val client = ApiClient.MainApiClient.client?.create(CryptoCurrencyService::class.java)
        val cryptoList = apiCall { client?.getJsonResponses_(cryptoCurrencyAbbreviation.name)!!  }

        //TODO Find out a better way to deal with this the domain model at this stage
        result = when(cryptoList){
            is Result.Success->{
                Result.Success(cryptoList.data.asDataBaseModel(cryptoCurrencyAbbreviation))
            }
            is Result.Error->{
                Result.Error(cryptoList.errorMessage)
            }
            is Result.Loading->{
                Result.Loading
            }
        }

        return  result

    }

    override fun mapResponseThrowable(e: Throwable): String {
        return  when (e) {
            is HttpException -> {
                Timber.e(Gson().toJson(e))
            return  try {

                e.response()?.errorBody()?.source()?.let {
                  val moshiInsatnce=  moshi.adapter(RequestError::class.java)
                val requestError =   moshiInsatnce .fromJson(it)
                    requestError!!.message
                } ?:"Server Error"

            }catch (e:Throwable){
                "Server Error"
            }
               }

            is IOException -> {
                Timber.e(Gson().toJson(e))
                "An Error Occurred"
            }
            else -> {
                "Unknown Error"
            }
}
    }


    //TODO Implement this with retrofit converterFactory.
    override fun ParseCustomError(errorClass: ResponseBody): String {


//          val actualClass:RequestError?
//            try {
//                actualClass  = errorClass as? RequestError
//
//            } catch (e:Exception){
//                return "Error from serve Please retry later "
//            }

//        if(actualClass != null) {


//        }

        /*     return try {
            throwable.response()?.errorBody()?.source()?.let {
                val moshiAdapter = Moshi.Builder().build().adapter(ErrorResponse::class.java)
                moshiAdapter.fromJson(it)
            }
        } catch (exception: Exception) {
            null
        }*/

      return ""
    }

}