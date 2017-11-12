package com.example.android.cryptoapp.currency_data;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class Eth {

    @SerializedName("AUD")
    @Expose
    private Double aud;
    @SerializedName("EGP")
    @Expose
    private Double egp;
    @SerializedName("GBP")
    @Expose
    private Double gbp;
    @SerializedName("EUR")
    @Expose
    private Double eur;
    @SerializedName("GEL")
    @Expose
    private Double gel;
    @SerializedName("GHS")
    @Expose
    private Double ghs;
    @SerializedName("HKD")
    @Expose
    private Double hkd;
    @SerializedName("ILS")
    @Expose
    private Double ils;
    @SerializedName("JMD")
    @Expose
    private Double jmd;
    @SerializedName("JPY")
    @Expose
    private Double jpy;
    @SerializedName("MYR")
    @Expose
    private Double myr;
    @SerializedName("NGN")
    @Expose
    private Double ngn;
    @SerializedName("PHP")
    @Expose
    private Double php;
    @SerializedName("QAR")
    @Expose
    private Double qar;
    @SerializedName("RUB")
    @Expose
    private Double rub;
    @SerializedName("ZAR")
    @Expose
    private Double zar;
    @SerializedName("CHF")
    @Expose
    private Double chf;

    @SerializedName("TWD")
    @Expose
    private Double twd;

    @SerializedName("THB")
    @Expose
    private Double thb;
    @SerializedName("USD")
    @Expose
    private Double uSD;

    public Double getAUD() {
        return aud;
    }

    public Double getCHF() {
        return chf;
    }

    public String getAUDSymbol() {
        return "$";
    }

    public String getCHFSymbol() {
        return "₣";
    }


    public Double getEGP() {
        return egp;
    }

    public String getEGPSymbol() {
        return "£";
    }

    public Double getEUR() {
        return eur;
    }

    public String getEURSymbol() {
        return "€";
    }

    public Double getGBP() {
        return gbp;
    }

    public String getGBPSymbol() {
        return "£";
    }


    public Double getGEL() {
        return gel;
    }

    public String getGELSymbol() {
        return "ლ";
    }

    public Double getGHS() {
        return ghs;
    }

    public String getGHSSymbol() {
        return "₵";
    }

    public Double getHKD() {
        return hkd;
    }

    public String getHKDSymbol() {
        return "$";
    }

    public Double getILS() {
        return ils;
    }

    public String getILSSymbol() {
        return "₪";
    }

    public Double getJMD() {
        return jmd;
    }

    public String getJMDSymbol() {
        return "$";
    }

    public Double getJPY() {
        return jpy;
    }

    public String getJPYSymbol() {
        return "¥";
    }

    public Double getMYR() {
        return myr;
    }

    public String getMYRSymbol() {
        return "RM";
    }


    public Double getNGN() {

        return ngn;
    }

    public String getNGNSymbol() {
        return "₦";
    }

    public Double getPHP() {
        return php;
    }

    public String getPHPSymbol() {
        return "₱";
    }

    public Double getQAR() {
        return qar;
    }

    public String getQARSymbol() {
        return "ر.ق";
    }


    public Double getRUB() {
        return rub;
    }

    public String getRUBSymbol() {
        return "р";
    }


    public Double getTHB() {
        return thb;
    }

    public String getTHBSymbol() {
        return "฿";
    }


    public Double getTWD() {
        return twd;
    }

    public String getTWDSymbol() {
        return "$";
    }


    public Double getUSD() {
        return uSD;
    }

    public String getUSDSymbol() {
        return "$";
    }

    public Double getZAR() {
        return zar;
    }

    public String getZARSymbol() {
        return "R";
    }

}