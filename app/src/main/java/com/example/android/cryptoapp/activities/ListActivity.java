package com.example.android.cryptoapp.activities;


import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;

import com.example.android.cryptoapp.R;
import com.example.android.cryptoapp.Results;
import com.example.android.cryptoapp.adapter.RatesAdapter;
import com.example.android.cryptoapp.currency_data.Btc;
import com.example.android.cryptoapp.currency_data.Eth;
import com.example.android.cryptoapp.rest.ApiClient;
import com.example.android.cryptoapp.rest.CryptoCurrencyService;

import java.util.ArrayList;
import java.util.List;


public class ListActivity extends AppCompatActivity implements RatesAdapter.ListItemClickListiner {


    RecyclerView resultRv;
    RatesAdapter resultAdapter;
    LinearLayoutManager layoutManager;
    List<Results> results;
    List<Results> soFar;
    Results output;
    int image;
    String currencyAbr;
    String currencySymbol;
    String currencyName;
    boolean check;
    Double btcRate;
    Double ethRate;
    CryptoCurrencyService cryptoClient;
    Btc btcConversionRates;
    Eth ethConversionRates;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);


        check = getIntent().getExtras() == null;

        layoutManager = new LinearLayoutManager(getBaseContext());


        results = new ArrayList<>();
        soFar = new ArrayList<>();


        resultAdapter = new RatesAdapter(getApplicationContext(), results, ListActivity.this);
        resultRv = (RecyclerView) findViewById(R.id.rv_members);


        resultRv.setLayoutManager(layoutManager);
        resultRv.setAdapter(resultAdapter);
        cryptoClient = ApiClient.getClient().create(CryptoCurrencyService.class);

        if (!check) {

            image = getIntent().getExtras().getInt("image");
            btcRate = getIntent().getExtras().getDouble("btcRate");
            currencyAbr = getIntent().getStringExtra("currencyAbr");
            currencySymbol = getIntent().getStringExtra("currencySymbol");
            currencyName = getIntent().getStringExtra("currencyName");
            ethRate = getIntent().getExtras().getDouble("ethRate");
            resultRv.setHasFixedSize(true);
            output= new Results(image, btcRate, ethRate,currencyName,currencyAbr,currencySymbol);
            resultAdapter.add(output);
        }




    }

    @Override
    public void onBackPressed() {

        AlertDialog.Builder builder;
        builder = new AlertDialog.Builder(this);
        builder.setTitle("Delete Entry")
                .setMessage("Are sure you wat to exit?")
                .setPositiveButton(android.R.string.yes,
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                ListActivity.this.finish();
                            }
                        })


                .setNegativeButton(android.R.string.no,
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }
    @Override

    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_list, menu);

        return true;
    }
    @Override
    public boolean onPrepareOptionsMenu(Menu menu){
        //Let ApCompactActivity call the onPrepareOptionsMenu(menu) method
        super.onPrepareOptionsMenu(menu);
        MenuItem menuItem_1;
        if(resultAdapter.getItemCount()>0){
            MenuItem menuItem = menu.findItem(R.id.add_new_card);
            menuItem.setVisible(false);
            menuItem_1 = menu.findItem(R.id.replace);
            menuItem_1.setVisible(true);

        }

        else{
            menuItem_1 = menu.findItem(R.id.replace);
            menuItem_1.setVisible(false);
        }

        return true;
    }

    @Override

    public boolean onOptionsItemSelected(MenuItem item) {

        Intent intent;
        // User clicked on a menu option in the app bar overflow menu
        switch (item.getItemId()) {

            // Respond to a click in order to add a new card
            case R.id.add_new_card:

                intent = new Intent(ListActivity.this, EditorActivity.class);


                startActivity(intent);

                return true;



            case R.id.about:
                AlertDialog.Builder builder;
                builder = new AlertDialog.Builder(this);
                builder.setTitle("About").setMessage(getString(R.string.About_)).setPositiveButton(android.R.string.yes,
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                            }
                        });

                AlertDialog alertDialog = builder.create();
                alertDialog.show();
                return true;

            case R.id.replace:

                 intent = new Intent(ListActivity.this, EditorActivity.class);


                startActivity(intent);

                return true;
        }



        return super.onOptionsItemSelected(item);

    }

    @Override
    public void onListItemClicked(int clickditemindex) {

        Intent intent = new Intent(ListActivity.this, ConversionActivity.class);

        intent.putExtra("image",image);
        intent.putExtra("btcRate", btcRate);
        intent.putExtra("currencySymbol", currencySymbol);
        intent.putExtra("ethRate", ethRate);
        intent.putExtra("currencyAbr", currencyAbr);
        intent.putExtra("currencyName", currencyName);

        startActivity(intent);


    }



}



