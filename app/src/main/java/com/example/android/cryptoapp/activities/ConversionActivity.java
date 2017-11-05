package com.example.android.cryptoapp.activities;

import android.annotation.TargetApi;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.android.cryptoapp.R;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;

import jp.wasabeef.picasso.transformations.CropCircleTransformation;

public class ConversionActivity extends AppCompatActivity {
    EditText btc_amount;
    EditText eth_amount;
    ImageView currencyImage;
    ImageView btcImage;
    ImageView ethImage;
    TextView currency_amount;
    TextView btcExchangeRate;
    TextView ethExchangeRate;
    TextView currencySymbol_1;
    TextView currencySymbol_2;
    TextView currency_Name;
    TextView currencyAbbreviation;

    int image;
    boolean check;

    String currencyAbr;
    String currencySymbol;
    String currencyName;

    Double btcRate;
    Double ethRate;
    DecimalFormat format;



    @TargetApi(Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_conversion);

        check = getIntent().getExtras() == null;
        btcExchangeRate = (TextView) findViewById(R.id.btc_exchangerate);
        ethExchangeRate = (TextView) findViewById(R.id.eth_exchangerate);
        currencySymbol_1 = (TextView) findViewById(R.id.currency_symbol);
        currencySymbol_2 = (TextView) findViewById(R.id.currency_symbol_2);
        currencyAbbreviation = (TextView) findViewById(R.id.abrivation);
        currency_Name = (TextView) findViewById(R.id.currency_name);


        format = new DecimalFormat();
        format.setGroupingUsed(true);
        format.setMaximumIntegerDigits(20);
        format.setMaximumFractionDigits(3);

        currency_amount=(TextView) findViewById(R.id.currency_amount);
        btc_amount = (EditText) findViewById(R.id.btc_amount);
        eth_amount = (EditText) findViewById(R.id.eth_amount);
        btc_amount.addTextChangedListener(generalWatcher);
        eth_amount.addTextChangedListener(generalWatcher);




        if (!check) {

            image = getIntent().getExtras().getInt("image");
            btcRate = getIntent().getExtras().getDouble("btcRate");
            currencyAbr = getIntent().getStringExtra("currencyAbr");
            currencySymbol = getIntent().getStringExtra("currencySymbol");
            currencyName = getIntent().getStringExtra("currencyName");
            ethRate = getIntent().getExtras().getDouble("ethRate");


        }

         //Set the currency Image
        currencyImage = (ImageView) findViewById(R.id.currency_image);
        Picasso.with(getBaseContext()).load(image)
                .transform(new CropCircleTransformation())
                .into(currencyImage);

        //Set the btc Image
        btcImage = (ImageView) findViewById(R.id.btc_logo);
        Picasso.with(getBaseContext()).load(R.drawable.btc)
                .transform(new CropCircleTransformation())
                .into(btcImage);

        //Set the eth Image
        ethImage = (ImageView) findViewById(R.id.eth_logo);
        Picasso.with(getBaseContext()).load(R.drawable.eth)
                .transform(new CropCircleTransformation())
                .into(ethImage);

        btcExchangeRate.setText(format.format(btcRate));
        ethExchangeRate.setText(format.format(ethRate));
        currencySymbol_1.setText(currencySymbol);
        currencySymbol_2.setText(currencySymbol);
        currencyAbbreviation.setText(currencyAbr);
        currency_Name.setText(currencyName);
    }


    private final TextWatcher generalWatcher =new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {



        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            if(btc_amount.getText().hashCode()==charSequence.hashCode()) {
                String sBtcValue;
                if(charSequence.length()>0){

                    Float fBtcValue =  Float.parseFloat(btc_amount.getText().toString().trim());
                    sBtcValue = format.format(btcRate * fBtcValue);

                    currency_amount.setTextColor(Color.parseColor("#ab7d0b"));
                    currency_amount.setText(currencySymbol + sBtcValue);


                }

                else{
                    currency_amount.setText("");

                }

            }


            else if(eth_amount.getText().hashCode()==charSequence.hashCode()) {
                String sEthValue;
                if(charSequence.length()>0){

                    Float fEthValue =  Float.parseFloat(eth_amount.getText().toString().trim());

                    sEthValue = format.format(ethRate * fEthValue);
                    currency_amount.setTextColor(Color.parseColor("#5b6abd"));
                    currency_amount.setText(currencySymbol + sEthValue);


                }

                else{

                    currency_amount.setText("");

                }
            }


        }

        @Override
        public void afterTextChanged(Editable editable) {
        }

    };
//    //addCurrency button
//    public void convert(View view) {
//       try{
//        if (currency_amount.getText() != null) {
//
//            Float currencyFigure = Float.parseFloat(currency_amount.getText().toString().trim());
//
//
//            String fBtcRate = format.format(currencyFigure / btcRate);
//
//            btc_amount.setText(fBtcRate);
//
//
//            String fEthRate = format.format(currencyFigure / ethRate);
//
//            eth_amount.setText(fEthRate);
//
//
//        } }
//
//        catch (NumberFormatException e){
//
//            currency_amount.setText("");
//            eth_amount.setText("");
//            btc_amount.setText("");
//
//            Toast.makeText(getBaseContext(), "Enter an amount in " + currencyAbr  ,Toast.LENGTH_LONG).show();
//
//        }
//    }
}

