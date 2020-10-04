package com.example.android.cryptoapp.data.source.remote

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class RequestError(val status:Int=400, val message:String="Unknown Error Try again Later")