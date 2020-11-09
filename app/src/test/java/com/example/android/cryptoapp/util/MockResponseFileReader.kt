package com.example.android.cryptoapp.util

import java.io.InputStream
import java.io.InputStreamReader

class MockResponseFileReader(path:String ) {

    val content:String
    init {
          //You can as well write a test for this flow
//       var inputStream = ApplicationProvider.getApplicationContext<>().
            var inputStreamReader = InputStreamReader( this.javaClass.classLoader.getResourceAsStream(path))

          content = inputStreamReader.readText()

            inputStreamReader.close()

    }
}