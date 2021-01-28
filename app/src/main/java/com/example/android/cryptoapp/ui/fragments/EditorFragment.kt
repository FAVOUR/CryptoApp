package com.example.android.cryptoapp.ui.fragments

import android.content.Context
import android.os.Bundle
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.example.android.cryptoapp.App
import com.example.android.cryptoapp.R
import com.example.android.cryptoapp.currency_data.*
import com.example.android.cryptoapp.databinding.FragmentEditorBinding
import com.example.android.cryptoapp.di.component.AppComponent
import com.example.android.cryptoapp.util.*
import com.example.android.cryptoapp.viewmodel.EditorViewModel
import com.example.android.cryptoapp.viewmodel.factory.ViewModelFactory
import kotlinx.android.synthetic.main.fragment_editor.*
import timber.log.Timber
import javax.inject.Inject


/**
 * A simple [Fragment] subclass.
 * Use the [EditorFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class EditorFragment : DialogFragment() {




    private lateinit  var appComponent: AppComponent


    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    // activity-ktx artifact
    private val viewmodel: EditorViewModel by viewModels{
//        val remoteDataSource  =  RemoteCryptoRateDataSource(apiClient = ApiClient,moshi = Moshi.Builder().build())
//        val localDataSource  = LocalCryptoRatesDataSource(CurrencyRoomDatabase.getDataBase(requireContext()).currencyDao())
//
//
        viewModelFactory
    }
//

    override fun onAttach(context: Context) {
        super.onAttach(context)
        appComponent = ( requireActivity().application as App).appComponent
        val conversionFragment = appComponent.create(this)
    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {




        val view =  FragmentEditorBinding.inflate(inflater, container, false)
        return view.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


//        viewmodel.cryptoClient= ApiClient.client?.create(CryptoCurrencyService::class.java)
//        currencySpinner = findViewById<View>(R.id.currencyName) as Spinner
//        viewmodel.currencySpinner = currencyName //Todo You may want to reconsider
//        loading = findViewById<View>(R.id.loading) as RelativeLayout
//        loading =progressBarRL
        activity?.actionBar?.title =resources.getString(R.string.editor_activity_title)

        indicateRemoteRequest()


        spinnerForCurrency()
        ExchangeRateBTN.setOnClickListener {
            addCurrency()
        }
    }

    private fun indicateRemoteRequest() {
        viewmodel.isLoading.observe(viewLifecycleOwner, Observer { isMakingNetworkRequest ->
            Timber.e(isMakingNetworkRequest.toString())
            if (isMakingNetworkRequest) {
                pbloading.visibility = View.VISIBLE
                textView.visibility = View.VISIBLE
            } else {
                pbloading.visibility = View.GONE
                textView.visibility = View.GONE
                dismiss()
            }
        })
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
//        viewmodel.currencySpinner!!.adapter = currencySpinnerAdapter
        currencyName.adapter = currencySpinnerAdapter

        // Set the currency Selected to the constant values
        //TODO Try to lazily instantiate the adapter.onItemSelected interface
        currencyName.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
                val selection = parent.getItemAtPosition(position) as String
                if (!TextUtils.isEmpty(selection)) {
                    //Use the position of item chosen to get the currencyAbr and currencyName
                    viewmodel.currencyAbr = getCurrencyAbbrFromSpinner(position)
                    viewmodel._currencyName = selection
                    viewmodel.currencySymbol = getSymbol( viewmodel.currencyAbr)
                    viewmodel.image = getCurrencyImage( viewmodel.currencyAbr)
                } else {
                    viewmodel.currencyAbr =  CurrencyAbbreviation.NONE
                    viewmodel. _currencyName = ""
                }
            }

            override fun onNothingSelected(adapterView: AdapterView<*>?) {}
        }
    }

  //Used to transfer data between the fragments
//    interface OnDataGotten{
//       fun data (bundle: Bundle)
//    }

/*
    fun addCurrency() {
//        loading!!.visibility = View.VISIBLE
        pbloading.visibility = View.VISIBLE
        textView.visibility = View.VISIBLE

//        GlobalScope.launch (Dispatchers.IO){
            val ok = viewmodel.cryptoClient!!.getJsonResponse(viewmodel.currencyAbr.abbr)

            ok?.enqueue(object : Callback<JsonResponse?> {
                override fun onResponse(call: Call<JsonResponse?>, response: Response<JsonResponse?>) {

                    if (response.body() != null) {
                        viewmodel.jsonResponse = response.body()!!
                        viewmodel.btcConversionRates = viewmodel.jsonResponse.bTC ?: Btc()
                        viewmodel.ethConversionRates = viewmodel.jsonResponse.eTH ?: Eth()

                        btcConversionRates = viewmodel.btcConversionRates
                        ethConversionRates = viewmodel.ethConversionRates

                        //check for the BTC rates
                        viewmodel.conversionFromBtc = getBtcRate(viewmodel.currencyAbr, btcConversionRates)

                        //check for the Eth rates
                        viewmodel.conversionFromEth = getEthRate(viewmodel.currencyAbr, ethConversionRates)


                        val bundle = Bundle()
                        bundle.putInt("image", viewmodel.image)
                        bundle.putDouble("btcRate", viewmodel.conversionFromBtc ?: 0.00)
                        bundle.putString("currencySymbol", viewmodel.currencySymbol.name)
                        bundle.putDouble("ethRate", viewmodel.conversionFromEth ?: 0.00)
                        bundle.putString("currencyAbr", viewmodel.currencyAbr.name)
                        bundle.putString("currencyName", viewmodel._currencyName)

                        viewmodel.saveData()
//                startActivity(intent)
                        mOnDataGotten.data(bundle)

//                loading!!.visibility = View.GONE
                        pbloading.visibility = View.GONE
                        textView.visibility = View.GONE
                        dismiss()

                    }


                }

                override fun onFailure(call: Call<JsonResponse?>, t: Throwable) {
//                loading!!.visibility = View.GONE

                    pbloading.visibility = View.GONE
                    textView.visibility = View.GONE
                    Toast.makeText(requireContext(), "Check your internet connection ", Toast.LENGTH_LONG).show()
                }
            }
            )
//        }
    }
*/




    fun addCurrency() {
        viewmodel.getCryptoRate()
    }

/*     val ok = viewmodel.cryptoClient!!.getJsonResponse_(viewmodel.currencyAbr.name)
        ok?.enqueue(object : Callback<JsonResponse?> {
            override fun onResponse(call: Call<JsonResponse?>, response: Response<JsonResponse?>) {

                if (response.body() != null) {
                    viewmodel.jsonResponse = response.body()!!
                    viewmodel.btcConversionRates = viewmodel.jsonResponse.bTC ?: Btc()
                    viewmodel.ethConversionRates = viewmodel.jsonResponse.eTH ?:Eth()

                    btcConversionRates=viewmodel.btcConversionRates
                    ethConversionRates=viewmodel.ethConversionRates

                    //check for the BTC rates
                    viewmodel.conversionFromBtc = getBtcRate(viewmodel.currencyAbr,btcConversionRates)

                    //check for the Eth rates
                    viewmodel.conversionFromEth = getEthRate(viewmodel.currencyAbr,ethConversionRates)


                    val bundle = Bundle()
                    bundle.putInt("image", viewmodel.image)
                    bundle.putDouble("btcRate", viewmodel.conversionFromBtc?:0.00)
                    bundle.putString("currencySymbol", viewmodel.currencySymbol.name)
                    bundle.putDouble("ethRate", viewmodel.conversionFromEth ?:0.00)
                    bundle.putString("currencyAbr", viewmodel.currencyAbr.name)
                    bundle.putString("currencyName", viewmodel._currencyName)

                    viewmodel.saveData()
//                startActivity(intent)
                    mOnDataGotten.data(bundle)

//                loading!!.visibility = View.GONE


                }





            }

            override fun onFailure(call: Call<JsonResponse?>, t: Throwable) {
//                loading!!.visibility = View.GONE

                pbloading.visibility = View.GONE
                textView.visibility = View.GONE
                Toast.makeText(requireContext(), "Check your internet connection ", Toast.LENGTH_LONG).show()
            }
        }
        )*//*

//    }
*/


 /*   override fun onAttach(context: Context) {
        super.onAttach(context)
//          Log.e("targetFragment", "Name of fragment $targetFragment")
//          Log.e("targetFragment as targetFragment", "Name of fragment Cast  ${targetFragment as OnDataGotten} ")
//
//        TODO Try to set the interface implementation to the activity Scope
         mOnDataGotten = targetFragment as OnDataGotten

    }*/







}