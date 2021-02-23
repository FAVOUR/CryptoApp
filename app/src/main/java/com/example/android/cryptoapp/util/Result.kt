package com.example.android.cryptoapp.util

import java.lang.Exception

sealed class Result <out T> {
      data  class Success<out T>(val data:T):Result<T>()
//      data class Error<out T>(val errorMessage: String,val data: T?) : Result<T>()
      data class Error(val errorMessage: String) : Result<Nothing>()
      object  Loading : Result<Nothing>()


    override fun toString(): String {
      return  when(this){
               is Success<T> -> "Success[$data]"
//               is Error<T> -> "Error [exception = $errorMessage, data=$data]"
               is Error -> "Error [exception = $errorMessage]"
               Loading -> "Loading"
      }

    }


}