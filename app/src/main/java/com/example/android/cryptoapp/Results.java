package com.example.android.cryptoapp;


public class Results {

    private String mName;
    private String mAbr;
    private Double mFirstExRate;
    private Double mSecondExRate;
    private String mSymbol;
    private int mImage;


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
