package com.example.android.cryptoapp.activities

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.*
import android.widget.AdapterView.OnItemSelectedListener
import androidx.appcompat.app.AppCompatActivity
import com.example.android.cryptoapp.R
import com.example.android.cryptoapp.currency_data.Btc
import com.example.android.cryptoapp.currency_data.Eth
import com.example.android.cryptoapp.currency_data.JsonResponse
import com.example.android.cryptoapp.rest.ApiClient
import com.example.android.cryptoapp.rest.CryptoCurrencyService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class EditorActivity : AppCompatActivity() {
    private var currencySpinner: Spinner? = null
    var cryptoClient: CryptoCurrencyService? = null
    var jsonResponse: JsonResponse? = null
    var btcConversionRates: Btc? = null
    var ethConversionRates: Eth? = null
    var conversionFromBtc: Double? = null
    var conversionFromEth: Double? = null
    var currencyAbr: String? = null
    var currencySymbol: String? = null
    var currencyName: String? = null
    var image = 0
    var loading: RelativeLayout? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_editor)
        cryptoClient = ApiClient.client?.create(CryptoCurrencyService::class.java)
        currencySpinner = findViewById<View>(R.id.currencyName) as Spinner
        loading = findViewById<View>(R.id.progressBarRL) as RelativeLayout
        setTitle(R.string.editor_activity_title)
        spinnerForCurrency()
    }

    /**
     * Setup the dropdown spinner that allows the user to select the currency of choice.
     */
    private fun spinnerForCurrency(): String? {
        // Create Adapter for spinner. The list options are from the String array
        val currencySpinnerAdapter: ArrayAdapter<*> = ArrayAdapter.createFromResource(this,
                R.array.currency_options, android.R.layout.simple_spinner_item)

        // Specify dropdown layout style - simple list view with 1 item per line
        currencySpinnerAdapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line)

        // Apply the Adapter to the spinner
        currencySpinner!!.adapter = currencySpinnerAdapter

        // Set the currency Selected to the constant values
        currencySpinner!!.onItemSelectedListener = object : OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
                val selection = parent.getItemAtPosition(position) as String
                if (!TextUtils.isEmpty(selection)) {

                    //Use the position of item chosen to get the currencyAbr and currencyName
                    currencyAbr = checkSpinner(position)
                    currencyName = selection
                } else {
                    currencyAbr = getString(R.string.none)
                    currencyName = getString(R.string.none)
                }
            }

            override fun onNothingSelected(adapterView: AdapterView<*>?) {}
        }
        return currencyAbr
    }

    fun addCurrency(view: View) {
        loading!!.visibility = View.VISIBLE
        val ok = cryptoClient!!.getJsonResponse(currencyAbr)
        ok?.enqueue(object : Callback<JsonResponse?> {
            override fun onResponse(call: Call<JsonResponse?>, response: Response<JsonResponse?>) {
                jsonResponse = response.body()
                if (jsonResponse != null) {
                    btcConversionRates = jsonResponse!!.bTC
                    ethConversionRates = jsonResponse!!.eTH

                    //check for the BTC rates
                    conversionFromBtc = confirmBtcRates(currencyAbr)

                    //check for the Eth rates
                    conversionFromEth = confirmEthRates(currencyAbr)
                }
                val intent = Intent(this@EditorActivity, ListActivity::class.java)
                intent.putExtra("image", image)
                intent.putExtra("btcRate", conversionFromBtc)
                intent.putExtra("currencySymbol", currencySymbol)
                intent.putExtra("ethRate", conversionFromEth)
                intent.putExtra("currencyAbr", currencyAbr)
                intent.putExtra("currencyName", currencyName)
                startActivity(intent)
                loading!!.visibility = View.GONE
            }

            override fun onFailure(call: Call<JsonResponse?>, t: Throwable) {
                loading!!.visibility = View.GONE
                Toast.makeText(baseContext, "Check your internet connection ", Toast.LENGTH_LONG).show()
            }
        }
        )
    }

    //    Checks the spinner and returns the abbreviation and image resource of the currency selected
    private fun checkSpinner(position: Int): String {
        val CurrencyAbbreviation: String
        when (position) {
            1 -> {
                CurrencyAbbreviation = getString(R.string.Australia_Dollar)
                image = R.drawable.aud
            }
            2 -> {
                CurrencyAbbreviation = getString(R.string.Egypt_Pound)
                image = R.drawable.egp
            }
            3 -> {
                CurrencyAbbreviation = getString(R.string.Great_Britain_Pound)
                image = R.drawable.gbp
            }
            4 -> {
                CurrencyAbbreviation = getString(R.string.German_Euro)
                image = R.drawable.eur
            }
            5 -> {
                CurrencyAbbreviation = getString(R.string.Georgia_Lari)
                image = R.drawable.gel
            }
            6 -> {
                CurrencyAbbreviation = getString(R.string.Ghana_New_Cedi)
                image = R.drawable.ghs
            }
            7 -> {
                CurrencyAbbreviation = getString(R.string.Hong_Kong_Dollar)
                image = R.drawable.hdk
            }
            8 -> {
                CurrencyAbbreviation = getString(R.string.Israel_New_Shekel)
                image = R.drawable.ils
            }
            9 -> {
                CurrencyAbbreviation = getString(R.string.Jamaica_Dollar)
                image = R.drawable.jmd
            }
            10 -> {
                CurrencyAbbreviation = getString(R.string.Japan_Yen)
                image = R.drawable.jpy
            }
            11 -> {
                CurrencyAbbreviation = getString(R.string.Malaysia_Ringgit)
                image = R.drawable.myr
            }
            12 -> {
                CurrencyAbbreviation = getString(R.string.Nigeria_Naira)
                image = R.drawable.ngn
            }
            13 -> {
                CurrencyAbbreviation = getString(R.string.Philippines_Peso)
                image = R.drawable.php
            }
            14 -> {
                CurrencyAbbreviation = getString(R.string.Qatar_Rial)
                image = R.drawable.qar
            }
            15 -> {
                CurrencyAbbreviation = getString(R.string.Russia_Rouble)
                image = R.drawable.rub
            }
            16 -> {
                CurrencyAbbreviation = getString(R.string.South_Africa_Rand)
                image = R.drawable.zar
            }
            17 -> {
                CurrencyAbbreviation = getString(R.string.Switzerland_Franc)
                image = R.drawable.chf
            }
            18 -> {
                CurrencyAbbreviation = getString(R.string.Taiwan_Dollar)
                image = R.drawable.twd
            }
            19 -> {
                CurrencyAbbreviation = getString(R.string.Thailand_Baht)
                image = R.drawable.thb
            }
            20 -> {
                CurrencyAbbreviation = getString(R.string.usa_Dollar)
                image = R.drawable.usd
            }
            else -> CurrencyAbbreviation = getString(R.string.none)
        }
        return CurrencyAbbreviation
    }

    //confirm BTC rates after putting  the currency type as parameter of this method
    fun confirmBtcRates(currency: String?): Double {
        val BtcRates: Double
        BtcRates = when (currency) {
            "AUD" -> btcConversionRates?.AUD ?: 0.00
            "EGP" -> btcConversionRates?.EGP ?: 0.00
            "GBP" -> btcConversionRates?.GBP ?: 0.00
            "EUR" -> btcConversionRates?.EUR ?: 0.00
            "GEL" -> btcConversionRates?.GEL ?: 0.00
            "GHS" -> btcConversionRates?.GHS ?: 0.00
            "HKD" -> btcConversionRates?.HKD ?: 0.00
            "ILS" -> btcConversionRates?.ILS ?: 0.00
            "JMD" -> btcConversionRates?.JMD ?: 0.00
            "JPY" -> btcConversionRates?.JPY ?: 0.00
            "MYR" -> btcConversionRates?.MYR ?: 0.00
            "NGN" -> btcConversionRates?.NGN ?: 0.00
            "PHP" -> btcConversionRates?.PHP ?: 0.00
            "QAR" -> btcConversionRates?.QAR ?: 0.00
            "RUB" -> btcConversionRates?.RUB ?: 0.00
            "ZAR" -> btcConversionRates?.ZAR ?: 0.00
            "CHF" -> btcConversionRates?.CHF ?: 0.00
            "TWD" -> btcConversionRates?.TWD ?: 0.00
            "THB" -> btcConversionRates?.THB ?: 0.00
            "USD" -> btcConversionRates?.USD ?: 0.00
            else -> 0.0
        } as Double
        return BtcRates
    }

    //confirm ETH rates after putting the currency type as parameter of this method
    protected fun confirmEthRates(currency: String?): Double {
        val ethRates: Double
        when (currency) {
            "AUD" -> {
                ethRates = ethConversionRates?.AUD ?: 0.00
                currencySymbol = ethConversionRates?.aUDSymbol
            }
            "EGP" -> {
                ethRates = ethConversionRates?.EGP ?: 0.00
                currencySymbol = ethConversionRates?.eGPSymbol
            }
            "EUR" -> {
                ethRates = ethConversionRates?.EUR ?: 0.00
                currencySymbol = ethConversionRates?.eURSymbol
            }
            "GBP" -> {
                ethRates = ethConversionRates?.GBP ?: 0.00
                currencySymbol = ethConversionRates?.gBPSymbol
            }
            "GEL" -> {
                ethRates = ethConversionRates?.GEL ?: 0.00
                currencySymbol = ethConversionRates?.gELSymbol
            }
            "GHS" -> {
                ethRates = ethConversionRates?.GHS ?: 0.00
                currencySymbol = ethConversionRates?.gHSSymbol
            }
            "HKD ?: 0.00" -> {
                ethRates = ethConversionRates?.HKD ?: 0.00
                currencySymbol = ethConversionRates?.hKDSymbol
            }
            "ILS" -> {
                ethRates = ethConversionRates?.ILS ?: 0.00
                currencySymbol = ethConversionRates?.iLSSymbol
            }
            "JMD" -> {
                ethRates = ethConversionRates?.JMD ?: 0.00
                currencySymbol = ethConversionRates?.jMDSymbol
            }
            "JPY" -> {
                ethRates = ethConversionRates?.JPY ?: 0.00
                currencySymbol = ethConversionRates?.jPYSymbol
            }
            "MYR" -> {
                ethRates = ethConversionRates?.MYR ?: 0.00
                currencySymbol = ethConversionRates?.mYRSymbol
            }
            "NGN" -> {
                ethRates = ethConversionRates?.NGN ?: 0.00
                currencySymbol = ethConversionRates?.nGNSymbol
            }
            "PHP" -> {
                ethRates = ethConversionRates?.PHP ?: 0.00
                currencySymbol = ethConversionRates?.pHPSymbol
            }
            "QAR" -> {
                ethRates = ethConversionRates?.QAR ?: 0.00
                currencySymbol = ethConversionRates?.qARSymbol
            }
            "RUB" -> {
                ethRates = ethConversionRates?.RUB ?: 0.00
                currencySymbol = ethConversionRates?.rUBSymbol
            }
            "ZAR" -> {
                ethRates = ethConversionRates?.ZAR ?: 0.00
                currencySymbol = ethConversionRates?.zARSymbol
            }
            "CHF" -> {
                ethRates = ethConversionRates?.CHF ?: 0.00
                currencySymbol = ethConversionRates?.cHFSymbol
            }
            "TWD" -> {
                ethRates = ethConversionRates?.TWD ?: 0.00
                currencySymbol = ethConversionRates?.tWDSymbol
            }
            "THB" -> {
                ethRates = ethConversionRates?.THB ?: 0.00
                currencySymbol = ethConversionRates?.tHBSymbol
            }
            "USD" -> {
                ethRates = ethConversionRates?.USD ?: 0.00
                currencySymbol = ethConversionRates?.uSDSymbol
            }
            else -> ethRates = 0.0
        }
        return ethRates
    }
}