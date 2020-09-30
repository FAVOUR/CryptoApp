package com.example.android.cryptoapp.util

import java.lang.Exception

sealed class Result <out T> {
      data class Success<out T> (val data:T):Result<T>()
      data class Error(val exception: Exception) : Result<Nothing>()
      object  Loading : Result<Nothing>()


    override fun toString(): String {
      return  when(this){
               is Success<T> -> "Success[$data]"
               is Error -> "Error [exception = $exception]"
               Loading -> "Loading"
      }

    }
}