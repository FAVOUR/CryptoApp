package com.example.android.cryptoapp.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.android.cryptoapp.R;
import com.example.android.cryptoapp.currency_data.Btc;
import com.example.android.cryptoapp.currency_data.Eth;
import com.example.android.cryptoapp.currency_data.JsonResponse;
import com.example.android.cryptoapp.rest.ApiClient;
import com.example.android.cryptoapp.rest.CryptoCurrencyService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class EditorActivity extends AppCompatActivity {


    private Spinner currencySpinner;



    CryptoCurrencyService cryptoClient;
    JsonResponse jsonResponse;

    Btc btcConversionRates;
    Eth ethConversionRates;

    Double conversionFromBtc;
    Double conversionFromEth;
    String baseCurrencies;
    String currencyAbr;
    String currencySymbol;
    String currencyName;
    int image;

    RelativeLayout loading;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editor);

        cryptoClient = ApiClient.getClient().create(CryptoCurrencyService.class);


        currencySpinner = (Spinner) findViewById(R.id.currencyName);

        loading = (RelativeLayout) findViewById(R.id.loading);

       setTitle(R.string.editor_activity_title);
        spinnerForCurrency();

    }


    /**
     * Setup the dropdown spinner that allows the user to select the currency of choice.
     */

    private String spinnerForCurrency() {
        // Create Adapter for spinner. The list options are from the String array
        ArrayAdapter currencySpinnerAdapter = ArrayAdapter.createFromResource(this,
                R.array.currency_options, android.R.layout.simple_spinner_item);

        // Specify dropdown layout style - simple list view with 1 item per line
        currencySpinnerAdapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);

        // Apply the Adapter to the spinner
        currencySpinner.setAdapter(currencySpinnerAdapter);

        // Set the currency Selected to the constant values
        currencySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selection = (String) parent.getItemAtPosition(position);
                if (!TextUtils.isEmpty(selection)) {

                    //Use the position of item chosen to get the currencyAbr and currencyName
                    currencyAbr =checkSpinner(position);
                    currencyName=selection;


                }else {
                    currencyAbr =getString(R.string.none);
                    currencyName=getString(R.string.none);
                }
            }



            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        return currencyAbr;
    }

    public void addCurrency(View view){


        loading.setVisibility(View.VISIBLE);
        
        Call<JsonResponse> ok = cryptoClient.getJsonResponse(currencyAbr);
        ok.enqueue(new Callback<JsonResponse>() {

                       @Override
                       public void onResponse(Call<JsonResponse> call, Response<JsonResponse> response) {


                           jsonResponse = response.body();

                           if( jsonResponse !=null) {

                               btcConversionRates =jsonResponse.getBTC();
                               ethConversionRates =jsonResponse.getETH();

                               //check for the BTC rates
                               conversionFromBtc= confirmBtcRates(currencyAbr);

                               //check for the Eth rates
                               conversionFromEth= confirmEthRates(currencyAbr);



                           }


                           Intent intent = new Intent(EditorActivity.this, ListActivity.class);
                           intent.putExtra("image",image);
                           intent.putExtra("btcRate",conversionFromBtc);
                           intent.putExtra("currencySymbol", currencySymbol);
                           intent.putExtra("ethRate", conversionFromEth);
                           intent.putExtra("currencyAbr", currencyAbr);
                           intent.putExtra("currencyName", currencyName);



                           startActivity(intent);


                           loading.setVisibility(View.GONE);

                       }

                       @Override
                       public void onFailure(Call<JsonResponse> call, Throwable t) {
                           loading.setVisibility(View.GONE);
                           Toast.makeText(getBaseContext(), "Check your internet connection ", Toast.LENGTH_LONG).show();

                       }

                   }


        );



    }

    //    Checks the spinner and returns the abbreviation and image resource of the currency selected
    private String checkSpinner(int position){
        String CurrencyAbbreviation ="";
        switch (position){
            case 1:
                CurrencyAbbreviation =getString(R.string.Australia_Dollar);
                image=R.drawable.aud;
                break;
            case 2:
                CurrencyAbbreviation =getString(R.string.Egypt_Pound);
                image=R.drawable.egp;
                break;
            case 3:
                CurrencyAbbreviation =getString(R.string.Great_Britain_Pound);
                image=R.drawable.gbp;
                break;
            case 4:
                CurrencyAbbreviation =getString(R.string.German_Euro);
                image=R.drawable.eur;
                break;
            case 5:
                CurrencyAbbreviation =getString(R.string.Georgia_Lari);
                image=R.drawable.gel;
                break;
            case 6:
                CurrencyAbbreviation =getString(R.string.Ghana_New_Cedi);
                image=R.drawable.ghs;
                break;
            case 7:
                CurrencyAbbreviation =getString(R.string.Hong_Kong_Dollar);
                image=R.drawable.hdk;
                break;
            case 8:
                CurrencyAbbreviation =getString(R.string.Israel_New_Shekel);
                image=R.drawable.ils;
                break;
            case 9:
                CurrencyAbbreviation =getString(R.string.Jamaica_Dollar);
                image=R.drawable.jmd;
                break;
            case 10:
                CurrencyAbbreviation =getString(R.string.Japan_Yen);
                image=R.drawable.jpy;
                break;
            case 11:
                CurrencyAbbreviation =getString(R.string.Malaysia_Ringgit);
                image=R.drawable.myr;
                break;
            case 12:
                CurrencyAbbreviation =getString(R.string.Nigeria_Naira);
                image=R.drawable.ngn;
                break;
            case 13:
                CurrencyAbbreviation =getString(R.string.Philippines_Peso);
                image=R.drawable.php;
                break;
            case 14:
                CurrencyAbbreviation =getString(R.string.Qatar_Rial);
                image=R.drawable.qar;
                break;
            case 15:
                CurrencyAbbreviation =getString(R.string.Russia_Rouble);
                image=R.drawable.rub;
                break;
            case 16:
                CurrencyAbbreviation =getString(R.string.South_Africa_Rand);
                image=R.drawable.zar;
                break;
            case 17:
                CurrencyAbbreviation =getString(R.string.Switzerland_Franc);
                image=R.drawable.chf;
                break;
            case 18:
                CurrencyAbbreviation = getString(R.string.Taiwan_Dollar);
                image=R.drawable.twd;
                break;
            case 19:
                CurrencyAbbreviation =getString(R.string.Thailand_Baht);
                image=R.drawable.thb;
                break;
            case 20:
                CurrencyAbbreviation =getString(R.string.usa_Dollar);
                image=R.drawable.usd;
                break;
            default:
                CurrencyAbbreviation =getString(R.string.none);
        }

        return CurrencyAbbreviation;
    }


    //confirm BTC rates after putting  the currency type as parameter of this method
    public  double confirmBtcRates(String currency){
        double BtcRates =0.0;
        switch (currency){
            case "AUD":
                BtcRates = btcConversionRates.getAUD();
                break;
            case "EGP":
                BtcRates =btcConversionRates.getEGP();
                break;
            case "GBP":
                BtcRates =btcConversionRates.getGBP();
                break;
            case "EUR":
                BtcRates =btcConversionRates.getEUR();
                break;
            case "GEL":
                BtcRates =btcConversionRates.getGEL();
                break;
            case "GHS":
                BtcRates = btcConversionRates.getGHS();
                break;
            case "HKD":
                BtcRates =btcConversionRates.getHKD();
                break;
            case "ILS":
                BtcRates =btcConversionRates.getILS();
                break;
            case "JMD":
                BtcRates = btcConversionRates.getJMD();
                break;
            case "JPY":
                BtcRates =btcConversionRates.getJPY();
                break;
            case "MYR":
                BtcRates =btcConversionRates.getMYR();
                break;
            case "NGN":
                BtcRates = btcConversionRates.getNGN();
                break;
            case "PHP":
                BtcRates =btcConversionRates.getPHP();
                break;
            case "QAR":
                BtcRates =btcConversionRates.getQAR();
                break;
            case "RUB":
                BtcRates = btcConversionRates.getRUB();
                break;
            case "ZAR":
                BtcRates =btcConversionRates.getZAR();
                break;
            case "CHF":
                BtcRates =btcConversionRates.getCHF();
                break;
            case "TWD":
                BtcRates =btcConversionRates.getTWD();
                break;
            case "THB":
                BtcRates = btcConversionRates.getTHB();
                break;
            case "USD":
                BtcRates =btcConversionRates.getUSD();
                break;
            default:
                BtcRates =0.0;
        }
        return BtcRates;
    }


    //confirm ETH rates after putting the currency type as parameter of this method
    protected double confirmEthRates(String currency){
        double ethRates =0.0;
        switch (currency){
            case "AUD":
                ethRates = ethConversionRates.getAUD();
                currencySymbol=ethConversionRates.getAUDSymbol();


                break;
            case "EGP":
                ethRates =ethConversionRates.getEGP();
                currencySymbol=ethConversionRates.getEGPSymbol();

                break;
            case "EUR":
                ethRates =ethConversionRates.getEUR();
                currencySymbol=ethConversionRates.getEURSymbol();
                break;
            case "GBP":
                ethRates =ethConversionRates.getGBP();
                currencySymbol=ethConversionRates.getGBPSymbol();
                break;
            case "GEL":
                ethRates =ethConversionRates.getGEL();
                currencySymbol=ethConversionRates.getGELSymbol();
                break;
            case "GHS":
                ethRates = ethConversionRates.getGHS();
                currencySymbol=ethConversionRates.getGHSSymbol();
                break;
            case "HKD":
                ethRates =ethConversionRates.getHKD();
                currencySymbol=ethConversionRates.getHKDSymbol();
                break;
            case "ILS":
                ethRates =ethConversionRates.getILS();
                currencySymbol=ethConversionRates.getILSSymbol();
                break;
            case "JMD":
                ethRates = ethConversionRates.getJMD();
                currencySymbol=ethConversionRates.getJMDSymbol();
                break;
            case "JPY":
                ethRates =ethConversionRates.getJPY();
                currencySymbol=ethConversionRates.getJPYSymbol();
                break;
            case "MYR":
                ethRates =ethConversionRates.getMYR();
                currencySymbol=ethConversionRates.getMYRSymbol();
                break;
            case "NGN":
                ethRates = ethConversionRates.getNGN();
                currencySymbol=ethConversionRates.getNGNSymbol();
                break;
            case "PHP":
                ethRates =ethConversionRates.getPHP();
                currencySymbol=ethConversionRates.getPHPSymbol();
                break;
            case "QAR":
                ethRates =ethConversionRates.getQAR();
                currencySymbol=ethConversionRates.getQARSymbol();
                break;
            case "RUB":
                ethRates = ethConversionRates.getRUB();
                currencySymbol=ethConversionRates.getRUBSymbol();
                break;
            case "ZAR":
                ethRates =ethConversionRates.getZAR();
                currencySymbol=ethConversionRates.getZARSymbol();
                break;
            case "CHF":
                ethRates =ethConversionRates.getCHF();
                currencySymbol=ethConversionRates.getCHFSymbol();
                break;
            case "TWD":
                ethRates =ethConversionRates.getTWD();
                currencySymbol=ethConversionRates.getTWDSymbol();
                break;
            case "THB":
                ethRates = ethConversionRates.getTHB();
                currencySymbol=ethConversionRates.getTHBSymbol();
                break;
            case "USD":
                ethRates =ethConversionRates.getUSD();
                currencySymbol=ethConversionRates.getUSDSymbol();
                break;
            default:
                ethRates =0.0;
        }
        return ethRates;
    }


}

