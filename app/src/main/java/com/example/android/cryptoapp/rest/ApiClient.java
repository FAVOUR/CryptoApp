package com.example.android.cryptoapp.rest;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by OZMA NIG COM LTD on 05-Oct-17.
 */

public class ApiClient {

    private static final String CRYPTOCOMPARE_BASE_URL = "https://min-api.cryptocompare.com/data/";

    private static Retrofit retrofit=null;

    public static Retrofit getClient(){

        if(retrofit==null){
               retrofit=new Retrofit.Builder().baseUrl(CRYPTOCOMPARE_BASE_URL )
                       .addConverterFactory(GsonConverterFactory.create())
                       .build();

        }
        return retrofit;
    }


}
