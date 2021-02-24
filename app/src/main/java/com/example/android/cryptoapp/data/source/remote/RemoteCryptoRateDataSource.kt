package com.example.android.cryptoapp.data.source.remote

import com.example.android.cryptoapp.currency_data.CurrencyAbbreviation
import com.example.android.cryptoapp.currency_data.CryptoRatesResponse
import com.example.android.cryptoapp.data.source.IRemoteCryptoRateDataSource
import com.example.android.cryptoapp.data.source.local.db.CryptoCurrencyData
import com.example.android.cryptoapp.util.ErrorUtils
import com.example.android.cryptoapp.util.Result
import com.example.android.cryptoapp.util.asDataBaseModel
import com.squareup.moshi.Moshi
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import retrofit2.Retrofit
import javax.inject.Inject

class RemoteCryptoRateDataSource @Inject constructor( private val client:CryptoCurrencyService, private val ioDispatcher: CoroutineDispatcher,val moshi: Moshi,val retrofit: Retrofit):IRemoteCryptoRateDataSource,ApiTriggers<CryptoRatesResponse>() {
    override suspend fun refreshCryptoRates(cryptoCurrencyAbbreviation: CurrencyAbbreviation) = withContext(ioDispatcher) {
    }

    override suspend fun getSpecifiedCurrencyRates(currencyAbbreviation : Array<out CurrencyAbbreviation?>?): Result<List<CryptoCurrencyData>> = withContext(ioDispatcher) {
       var  result :Result<List<CryptoCurrencyData>>  = Result.Loading //TODO make it reactive so thet loading will stem from here
        val responseFromServer =  apiCall{ client?.getSpecifiedCurrencyRate(currencyAbbreviation?: arrayOf(CurrencyAbbreviation.NONE)) }

        //Gets the raw API response and formats it.
        val formattedResponse = apiResponse(response = responseFromServer,retrofit = retrofit){ response,retrofit ->
            ErrorUtils.parseError(response, retrofit)
        }
           result = when(formattedResponse){
               is Result.Success->{
                      Result.Success(formattedResponse.data.asDataBaseModel(CurrencyAbbreviation.USA_DOLLAR))
               }
               is Result.Error->{
                   Result.Error(formattedResponse.errorMessage)
               }
               is Result.Loading->{
                   Result.Loading
               }
            }

        return@withContext result

    }

    override suspend fun getACurrencyRate(currencyAbbreviation : CurrencyAbbreviation): Result<CryptoCurrencyData>  {
        var  result :Result<CryptoCurrencyData>  = Result.Loading //Workout to see that loading is emitted first before other options in the sealed class
        val responseFromServer = apiCall { client?.getACurrencyRate(currencyAbbreviation.abbr)!!}

       //Gets the raw API response and formats it.
       val formattedResponse = apiResponse(response = responseFromServer,retrofit = retrofit){ response,retrofit ->
             ErrorUtils.parseError(response, retrofit)
          }


        //Make this process  a higher order function so that this will not be done over and over again for every network call
        //TODO Find out a better way to deal with this the domain model at this stage
        result = when(formattedResponse){
            is Result.Success->{
//                 Timber.d(Gson().toJson(cryptoList.data))

                //With this You can now use DataTransferObject<T> in for example getCryptoRate() method do the database conversion
                //So if that class does not extend this object do not border to convert to database model as the data us not needed by the database
                //You may want to create an interface that the datamodel must extend for the sake of converting to Domain object. or may stick to something like the current
                // DataTransferobject class you already have.
                Result.Success(formattedResponse.data.asDataBaseModel(currencyAbbreviation))
            }
            is Result.Error->{
                Result.Error(formattedResponse.errorMessage) //TODO
            }
            is Result.Loading->{
                Result.Loading
            }
        }

        return  result

    }




    //TODO Implement this with retrofit converterFactory and make Generic
// override fun <ret T> parseRemoteError(errorClass: Response<T>): String {
//
//        retrofit.converterFactories().get()
//
//
//    }

}