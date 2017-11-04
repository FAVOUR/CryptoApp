package com.example.android.cryptoapp;

/**
 * Created by OZMA NIG COM LTD on 13-Oct-17.
 */

public class Results {

    String mName;
    String mAbr;
    Double mFirstExRate;
    Double mSecondExRate;
    String mSymbol;
    int mImage;


    public Results(int image, Double firstExRate, Double secondExRate, String name, String abbrivation, String Symbol){
        mImage=image;
         mName = name;
         mAbr= abbrivation;
         mFirstExRate=firstExRate;
         mSecondExRate=secondExRate;
         mSymbol = Symbol;

    }



    public String getName(){

        return  mName;
    }

    public String getAbbrivation()
    {
        return  mAbr;
    }


    public int getImage(){

        return   mImage;
    }

    public Double getFirstExRate(){

        return mFirstExRate;
    }


    public Double getSecondExRate(){

        return  mSecondExRate;
    }

    public String getSymbol(){

        return mSymbol;
    }

}
