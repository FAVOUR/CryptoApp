package com.example.android.cryptoapp.rest;

import com.example.android.cryptoapp.currency_data.JsonResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by OZMA NIG COM LTD on 05-Oct-17.
 */

public interface  CryptoCurrencyService {

    @GET("pricemulti?fsyms=BTC,ETH")
    Call<JsonResponse> getJsonResponse(@Query("tsyms") String tsyms);

}
