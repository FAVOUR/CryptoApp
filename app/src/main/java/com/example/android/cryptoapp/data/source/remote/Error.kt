package com.example.android.cryptoapp.data.source.remote


/**
 * This class is meant for getting the specific errors needed from the  error body of the request
 * */

data class Error(val status_code: Int = 0,
                 val status_message: String? = "Unknown Error")