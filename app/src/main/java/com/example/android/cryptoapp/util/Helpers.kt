package com.example.android.cryptoapp.util


import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import com.example.android.cryptoapp.R
import com.google.gson.Gson
import retrofit2.HttpException
import timber.log.Timber
import java.io.IOException

object Helpers {
     const val CRYPTOCOMPARE_BASE_URL = "https://min-api.cryptocompare.com/data/"
     const val DATA = "data"

     @JvmStatic
     fun FragmentActivity.setupFragmentManager(fragment: Fragment, backStackValue:String?) {
        let { activity ->
            activity.supportFragmentManager.beginTransaction()
                    .replace(R.id.viewContainer, fragment, null)
                    .addToBackStack(backStackValue)
                    .commit()

        }
    }

    fun mapResponseThrowable(exception: Throwable): String {
        return  when (exception) {
            is HttpException -> {
                Timber.e(Gson().toJson(exception))
                return  try {

//                    exception.response()?.errorBody()?.source()?.let {
//                        val moshiInsatnce=  moshi.adapter(RequestError::class.java)
//                        val requestError =   moshiInsatnce .fromJson(it)
//                        requestError!!.message
//                    } ?:
                    "Server Error"

                }catch (e:Throwable){
                    "Server Error"
                }
            }

            is IOException -> {
                Timber.e(Gson().toJson(exception))
                "An Error Occurred"
            }
            else -> {
                "Unknown Error"
            }
        }
    }
}