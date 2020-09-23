package com.example.android.cryptoapp.fragments

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModel
import com.example.android.cryptoapp.R
import com.example.android.cryptoapp.currency_data.Btc
import com.example.android.cryptoapp.currency_data.Currency
import com.example.android.cryptoapp.currency_data.Eth
import com.example.android.cryptoapp.currency_data.JsonResponse
import com.example.android.cryptoapp.rest.ApiClient
import com.example.android.cryptoapp.rest.CryptoCurrencyService
import com.example.android.cryptoapp.viewmodel.EditorViewModel
import kotlinx.android.synthetic.main.fragment_editor.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


/**
 * A simple [Fragment] subclass.
 * Use the [EditorFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class EditorFragment : DialogFragment() {


    private lateinit var mOnDataGotten:OnDataGotten
     var btcConversionRates: Btc?= null
     var ethConversionRates: Eth?=null
//    var loading: RelativeLayout? = nul

    // activity-ktx artifact
    private val viewmodel: EditorViewModel by viewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {




        val view =  inflater.inflate(R.layout.fragment_editor, container, false)
        return view
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        viewmodel.cryptoClient= ApiClient.client?.create(CryptoCurrencyService::class.java)
//        currencySpinner = findViewById<View>(R.id.currencyName) as Spinner
        viewmodel.currencySpinner = currencyName
//        loading = findViewById<View>(R.id.loading) as RelativeLayout
//        loading =progressBarRL
        activity?.actionBar?.title =resources.getString(R.string.editor_activity_title)
        spinnerForCurrency()
        ExchangeRateBTN.setOnClickListener {
            addCurrency()
        }
    }

    /**
     * Setup the dropdown spinner that allows the user to select the currency of choice.
     */
    private fun spinnerForCurrency(){
        // Create Adapter for spinner. The list options are from the String array
        val currencySpinnerAdapter: ArrayAdapter<*> = ArrayAdapter.createFromResource(requireContext(),
                R.array.currency_options, android.R.layout.simple_spinner_item)

        // Specify dropdown layout style - simple list view with 1 item per line
        currencySpinnerAdapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line)

        // Apply the Adapter to the spinner
        viewmodel.currencySpinner!!.adapter = currencySpinnerAdapter

        // Set the currency Selected to the constant values
        //TODO Try to lazily instantiate the adapter.onItemSelected interface
        viewmodel.currencySpinner!!.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
                val selection = parent.getItemAtPosition(position) as String
                if (!TextUtils.isEmpty(selection)) {

                    //Use the position of item chosen to get the currencyAbr and currencyName
                    viewmodel.currencyAbr = checkSpinner(position)
                    viewmodel._currencyName = selection
                } else {
                    viewmodel.currencyAbr = getString(R.string.none)
                    viewmodel. _currencyName = getString(R.string.none)
                }
            }

            override fun onNothingSelected(adapterView: AdapterView<*>?) {}
        }
    }

  //Used to transfer data between the fragments
    interface OnDataGotten{
       fun data (bundle: Bundle)
    }

    fun addCurrency() {
//        loading!!.visibility = View.VISIBLE
        pbloading.visibility = View.VISIBLE
        textView.visibility = View.VISIBLE
        val ok = viewmodel.cryptoClient!!.getJsonResponse(viewmodel.currencyAbr)
        ok?.enqueue(object : Callback<JsonResponse?> {
            override fun onResponse(call: Call<JsonResponse?>, response: Response<JsonResponse?>) {

                if (response.body() != null) {
                    viewmodel.jsonResponse = response.body()!!
                    viewmodel.btcConversionRates = viewmodel.jsonResponse.bTC ?: Btc()
                    viewmodel.ethConversionRates = viewmodel.jsonResponse.eTH ?:Eth()

                    btcConversionRates=viewmodel.btcConversionRates
                    ethConversionRates=viewmodel.ethConversionRates

                    //check for the BTC rates
                    viewmodel.conversionFromBtc = confirmBtcRates(viewmodel.currencyAbr)

                    //check for the Eth rates
                    viewmodel.conversionFromEth = confirmEthRates(viewmodel.currencyAbr)
                }




                val bundle = Bundle()
                bundle.putInt("image", viewmodel.image)
                bundle.putDouble("btcRate", viewmodel.conversionFromBtc?:0.00)
                bundle.putString("currencySymbol", viewmodel.currencySymbol)
                bundle.putDouble("ethRate", viewmodel.conversionFromEth ?:0.00)
                bundle.putString("currencyAbr", viewmodel.currencyAbr)
                bundle.putString("currencyName", viewmodel._currencyName)
//                startActivity(intent)
                mOnDataGotten.data(bundle)

//                val listFragment =ListFragment()
//                listFragment.arguments=bundle
//                val supportFragment    =   activity!!.supportFragmentManager.beginTransaction()
//                                                  .replace(R.id.viewContainer,listFragment,null)
//                                                  .commit()

//                loading!!.visibility = View.GONE
                pbloading.visibility = View.GONE
                textView.visibility = View.GONE
                dismiss()
            }

            override fun onFailure(call: Call<JsonResponse?>, t: Throwable) {
//                loading!!.visibility = View.GONE

                pbloading.visibility = View.GONE
                textView.visibility = View.GONE
                Toast.makeText(requireContext(), "Check your internet connection ", Toast.LENGTH_LONG).show()
            }
        }
        )
    }


    override fun onAttach(context: Context) {
        super.onAttach(context)
//          Log.e("targetFragment", "Name of fragment $targetFragment")
//          Log.e("targetFragment as targetFragment", "Name of fragment Cast  ${targetFragment as OnDataGotten} ")
//
//        TODO Try to set the interface implementation to the activity Scope
         mOnDataGotten = targetFragment as OnDataGotten

    }


    //    Checks the spinner and returns the abbreviation and image resource of the currency selected
    private fun checkSpinner(position: Int): String {
        val CurrencyAbbreviation: String
        when (position) {
            1 -> {
                CurrencyAbbreviation = getString(R.string.Australia_Dollar)
                viewmodel.image = R.drawable.aud
            }
            2 -> {
                CurrencyAbbreviation = getString(R.string.Egypt_Pound)
                viewmodel.image = R.drawable.egp
            }
            3 -> {
                CurrencyAbbreviation = getString(R.string.Great_Britain_Pound)
                viewmodel. image = R.drawable.gbp
            }
            4 -> {
                CurrencyAbbreviation = getString(R.string.German_Euro)
                viewmodel.image = R.drawable.eur
            }
            5 -> {
                CurrencyAbbreviation = getString(R.string.Georgia_Lari)
                viewmodel.image = R.drawable.gel
            }
            6 -> {
                CurrencyAbbreviation = getString(R.string.Ghana_New_Cedi)
                viewmodel.image = R.drawable.ghs
            }
            7 -> {
                CurrencyAbbreviation = getString(R.string.Hong_Kong_Dollar)
                viewmodel.image = R.drawable.hdk
            }
            8 -> {
                CurrencyAbbreviation = getString(R.string.Israel_New_Shekel)
                viewmodel.image = R.drawable.ils
            }
            9 -> {
                CurrencyAbbreviation = getString(R.string.Jamaica_Dollar)
                viewmodel. image = R.drawable.jmd
            }
            10 -> {
                CurrencyAbbreviation = getString(R.string.Japan_Yen)
                viewmodel.image = R.drawable.jpy
            }
            11 -> {
                CurrencyAbbreviation = getString(R.string.Malaysia_Ringgit)
                viewmodel.image = R.drawable.myr
            }
            12 -> {
                CurrencyAbbreviation = getString(R.string.Nigeria_Naira)
                viewmodel.image = R.drawable.ngn
            }
            13 -> {
                CurrencyAbbreviation = getString(R.string.Philippines_Peso)
                viewmodel.image = R.drawable.php
            }
            14 -> {
                CurrencyAbbreviation = getString(R.string.Qatar_Rial)
                viewmodel.image = R.drawable.qar
            }
            15 -> {
                CurrencyAbbreviation = getString(R.string.Russia_Rouble)
                viewmodel.image = R.drawable.rub
            }
            16 -> {
                CurrencyAbbreviation = getString(R.string.South_Africa_Rand)
                viewmodel. image = R.drawable.zar
            }
            17 -> {
                CurrencyAbbreviation = getString(R.string.Switzerland_Franc)
                viewmodel.image = R.drawable.chf
            }
            18 -> {
                CurrencyAbbreviation = getString(R.string.Taiwan_Dollar)
                viewmodel.image = R.drawable.twd
            }
            19 -> {
                CurrencyAbbreviation = getString(R.string.Thailand_Baht)
                viewmodel.image = R.drawable.thb
            }
            20 -> {
                CurrencyAbbreviation = getString(R.string.usa_Dollar)
                viewmodel.image = R.drawable.usd
            }
            else -> CurrencyAbbreviation = getString(R.string.none)
        }
        return CurrencyAbbreviation
    }

    //confirm BTC rates after putting  the currency type as parameter of this method
    fun confirmBtcRates(currency: String?): Double {
        val BtcRates: Double
        BtcRates = when (currency) {
            "AUD" -> viewmodel.btcConversionRates?.AUD ?: 0.00
            "EGP" -> viewmodel. btcConversionRates?.EGP ?: 0.00
            "GBP" -> viewmodel. btcConversionRates?.GBP ?: 0.00
            "EUR" -> viewmodel.btcConversionRates?.EUR ?: 0.00
            "GEL" -> viewmodel.btcConversionRates?.GEL ?: 0.00
            "GHS" -> viewmodel.btcConversionRates?.GHS ?: 0.00
            "HKD" -> viewmodel.btcConversionRates?.HKD ?: 0.00
            "ILS" -> viewmodel.btcConversionRates?.ILS ?: 0.00
            "JMD" -> viewmodel.btcConversionRates?.JMD ?: 0.00
            "JPY" -> viewmodel.btcConversionRates?.JPY ?: 0.00
            "MYR" -> viewmodel.btcConversionRates?.MYR ?: 0.00
            "NGN" -> viewmodel. btcConversionRates?.NGN ?: 0.00
            "PHP" -> viewmodel.btcConversionRates?.PHP ?: 0.00
            "QAR" -> viewmodel.btcConversionRates?.QAR ?: 0.00
            "RUB" -> viewmodel.btcConversionRates?.RUB ?: 0.00
            "ZAR" -> viewmodel.btcConversionRates?.ZAR ?: 0.00
            "CHF" -> viewmodel. btcConversionRates?.CHF ?: 0.00
            "TWD" -> viewmodel.btcConversionRates?.TWD ?: 0.00
            "THB" -> viewmodel.btcConversionRates?.THB ?: 0.00
            "USD" -> viewmodel.btcConversionRates?.USD ?: 0.00
            else -> 0.0
        } as Double
        return BtcRates
    }

    //confirm ETH rates after putting the currency type as parameter of this method
    protected fun confirmEthRates(currency: String?): Double {
        val ethRates: Double
        var currencySymbol: String =""
        when (currency) {
            "AUD" -> {
                ethRates = ethConversionRates?.AUD ?: 0.00
                currencySymbol = Currency.AUD.name
            }
            "EGP" -> {
                ethRates =ethConversionRates?.EGP ?: 0.00
                    currencySymbol =  Currency.EGP.name
            }
            "EUR" -> {
                ethRates =  ethConversionRates?.EUR ?: 0.00
                currencySymbol =   Currency.EUR.name
            }
            "GBP" -> {
                ethRates = ethConversionRates?.GBP ?: 0.00
                currencySymbol =   Currency.GBP.name
            }
            "GEL" -> {
                ethRates = ethConversionRates?.GEL ?: 0.00
                currencySymbol =   Currency.GEL.name
            }
            "GHS" -> {
                ethRates = ethConversionRates?.GHS ?: 0.00
                currencySymbol =   Currency.EUR.name
            }
            "HKD" -> {
                ethRates = ethConversionRates?.HKD ?: 0.00
                currencySymbol =   Currency.HKD.name
            }
            "ILS" -> {
                ethRates = ethConversionRates?.ILS ?: 0.00
                currencySymbol =   Currency.ILS.name
            }
            "JMD" -> {
                ethRates = ethConversionRates?.JMD ?: 0.00
                currencySymbol =   Currency.JMD.name
            }
            "JPY" -> {
                ethRates = ethConversionRates?.JPY ?: 0.00
                currencySymbol =   Currency.JPY.name
            }
            "MYR" -> {
                ethRates = ethConversionRates?.MYR ?: 0.00
                currencySymbol =   Currency.MYR.name
            }
            "NGN" -> {
                ethRates = ethConversionRates?.NGN ?: 0.00
                currencySymbol =   Currency.NGN.name
            }
            "PHP" -> {
                ethRates = ethConversionRates?.PHP ?: 0.00
                currencySymbol =   Currency.PHP.name
            }
            "QAR" -> {
                ethRates = ethConversionRates?.QAR ?: 0.00
                currencySymbol =   Currency.QAR.name
            }
            "RUB" -> {
                ethRates = ethConversionRates?.RUB ?: 0.00
                currencySymbol =   Currency.RUB.name
            }
            "ZAR" -> {
                ethRates = ethConversionRates?.ZAR ?: 0.00
                currencySymbol =   Currency.ZAR.name
            }
            "CHF" -> {
                ethRates = ethConversionRates?.CHF ?: 0.00
                currencySymbol =   Currency.CHF.name
            }
            "TWD" -> {
                ethRates = ethConversionRates?.TWD ?: 0.00
                currencySymbol =   Currency.TWD.name
            }
            "THB" -> {
                ethRates = ethConversionRates?.THB ?: 0.00
                currencySymbol =   Currency.THB.name
            }
            "USD" -> {
                ethRates = ethConversionRates?.USD ?: 0.00
                currencySymbol =   Currency.USD.name
            }
            else -> ethRates = 0.0
        }

        viewmodel.currencySymbol =currencySymbol
        return ethRates
    }


}