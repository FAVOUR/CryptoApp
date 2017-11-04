package com.example.android.cryptoapp.currency_data;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by OZMA NIG COM LTD on 05-Oct-17.
 */

public class JsonResponse {


        @SerializedName("BTC")
        @Expose
        private Btc btc;

        public Btc getBTC() {
            return btc;
        }


    @SerializedName("ETH")
    @Expose
    private Eth eth;

    public Eth getETH() {
        return eth;
    }
}
