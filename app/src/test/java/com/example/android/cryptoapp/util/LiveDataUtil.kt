package com.example.android.cryptoapp.util

import android.util.TimeUtils
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import java.util.concurrent.CountDownLatch
import java.util.concurrent.TimeUnit


fun <T> LiveData<T>.getOrAwaitNextValue(
        time:Long =2L ,
       timeUnit: TimeUnit=TimeUnit.SECONDS,
        code:()->Unit={}):T{

        val latch = CountDownLatch(1)
         var data_:T? =null
        val observer = object : Observer<T> {
                override fun onChanged(t: T) {
                        latch.countDown()
                        data_=t
                        this@getOrAwaitNextValue.removeObserver(this)                }

        }

        this.observeForever(observer)

        try {


                code.invoke()

                if (!latch.await(time, timeUnit)) {
                        this.removeObserver(observer)
                }
        }catch (e:Throwable){
                this.removeObserver(observer)

        }finally {
                this.removeObserver(observer)

        }
        return data_ as T
}