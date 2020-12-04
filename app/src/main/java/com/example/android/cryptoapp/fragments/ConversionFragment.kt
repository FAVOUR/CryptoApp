package com.example.android.cryptoapp.fragments

import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import com.example.android.cryptoapp.App
import com.example.android.cryptoapp.R
import com.example.android.cryptoapp.di.component.AppComponent
import com.example.android.cryptoapp.viewmodel.ConversionViewmodel
import com.example.android.cryptoapp.viewmodel.factory.ViewModelFactory
import com.google.gson.Gson
import com.squareup.picasso.Picasso
import jp.wasabeef.picasso.transformations.CropCircleTransformation
import kotlinx.android.synthetic.main.fragment_conversion.*
import kotlinx.android.synthetic.main.fragment_conversion.btc_amount
import kotlinx.android.synthetic.main.fragment_conversion.btc_exchangerate
import kotlinx.android.synthetic.main.fragment_conversion.btc_logo
import kotlinx.android.synthetic.main.fragment_conversion.currency_amount
import kotlinx.android.synthetic.main.fragment_conversion.currency_image
import kotlinx.android.synthetic.main.fragment_conversion.eth_amount
import kotlinx.android.synthetic.main.fragment_conversion.eth_exchangerate
import kotlinx.android.synthetic.main.fragment_conversion.eth_logo
import java.text.DecimalFormat
import javax.inject.Inject
import kotlin.properties.Delegates



/**
 * A simple [Fragment] subclass.
 * Use the [ConversionFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ConversionFragment : Fragment() {

       var image :Int by Delegates.notNull<Int>()
      private var check by Delegates.notNull<Boolean>()

    private var currencyAbr: String  by Delegates.notNull<String>()
    private var currencySymbol: String by  Delegates.notNull<String>()
    private var currencyName: String  by  Delegates.notNull<String>()
    private var btcRate: Double  by  Delegates.notNull<Double>()
    private var ethRate: Double by Delegates.notNull<Double>()
    private   var bundle: Bundle? = null
    private lateinit var format: DecimalFormat
    private lateinit  var appComponent: AppComponent

    @Inject
    lateinit  var picasso:Picasso

    @Inject
    lateinit  var viewModelFactory: ViewModelFactory

    private val viewmodel: ConversionViewmodel by viewModels    {
//
//        val remoteDataSource  =  RemoteCryptoRateDataSource(apiClient = ApiClient,moshi = Moshi.Builder().build())
//        val localDataSource  = LocalCryptoRatesDataSource(CurrencyRoomDatabase.getDataBase(requireContext()).currencyDao())
//
//        ViewModelFactory(CurrencyRoomDatabase.getDataBase(requireContext()).currencyDao(), DefaultCryptoRepository(localCryptoRatesDataSource = localDataSource,remoteCryptoRateDataSource = remoteDataSource))
        viewModelFactory
    }


    override fun onAttach(context: Context) {
        super.onAttach(context)
         appComponent = ( requireActivity().application as App).appComponent
        val conversionFragment = appComponent.create(this)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_conversion, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewmodel.bundle = requireArguments()

        check =viewmodel.check
        format = viewmodel.format
        bundle = viewmodel.bundle
        Log.e("  conversion frag bundle", Gson().toJson(bundle))

          format.isGroupingUsed = true
          format.maximumIntegerDigits = 20
          format.maximumFractionDigits = 3

//        currency_amount = currency_amount
//        btc_amount = btc_amount
//        eth_amount =eth_amount
        btc_amount!!.addTextChangedListener(generalWatcher)
        eth_amount!!.addTextChangedListener(generalWatcher)

        //TODO this check may be irrelevant
        if (check) {

        //  TODO Consider using a switch statement instead of the boring initialization of classes
            viewmodel.image = bundle!!.getInt("image")
            viewmodel.btcRate = bundle!!.getDouble("btcRate")
            viewmodel.currencyAbr = bundle?.getString("currencyAbr") ?: ""
            viewmodel.currencySymbol = bundle?.getString("currencySymbol") ?: ""
            viewmodel.currencyName = bundle?.getString("currencyName") ?: ""
            viewmodel.ethRate = bundle?.getDouble("ethRate") ?: 0.00


            image = viewmodel.image
            btcRate = viewmodel.btcRate
            currencyAbr = viewmodel.currencyAbr
            currencySymbol = viewmodel.currencySymbol
            currencyName = viewmodel.currencyName
            ethRate= viewmodel.ethRate
            check = viewmodel.check
        }

        //Set the currency Image
        picasso.load(image)
                .transform(CropCircleTransformation())
                .into(currency_image)

        //Set the btc Image
        picasso.load(R.drawable.btc)
                .transform(CropCircleTransformation())
                .into(btc_logo)

        //Set the eth Image
        picasso.load(R.drawable.eth)
                .transform(CropCircleTransformation())
                .into(eth_logo)

        btc_exchangerate!!.text  =    format.format(btcRate)
        eth_exchangerate!!.text  =    format.format(ethRate)

        currency_symbol!!.text   =     currencySymbol
        currency_symbol_2!!.text =     currencySymbol
        abrivation!!.text        =     currencyAbr
        currency_name!!.text     =     currencyName
    }


    private val generalWatcher: TextWatcher = object : TextWatcher {
        var ignore = true //This works fine with java but with kotlin
        override fun beforeTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {}
        override fun onTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {}
        override fun afterTextChanged(editable: Editable) {
            ignore = true //You have to reassign with kotlin you should try this
            try {
                val fBtcValue: Float
                val sBtcValue: String
                if (btc_amount!!.text.hashCode() == editable.hashCode()) {
                    if (editable.length > 0) {
                        if (ignore) {
                            eth_amount!!.setText("")
                            fBtcValue = btc_amount!!.text.toString().trim { it <= ' ' }.toFloat()
                            sBtcValue =   format.format(btcRate * fBtcValue)
                            currency_amount!!.setTextColor(Color.parseColor("#ab7d0b"))
                            currency_amount!!.text = currencySymbol + sBtcValue
                            return
                        } else {
                            currency_amount!!.text = ""
                        }
                        ignore = false
                    }
                } else if (eth_amount!!.text.hashCode() == editable.hashCode()) {
                    val sEthValue: String
                    if (editable.length > 0) {
                        if (ignore) {
                            btc_amount!!.setText("")
                            val fEthValue = eth_amount!!.text.toString().trim { it <= ' ' }.toFloat()
                            sEthValue =   format!!.format(ethRate!! * fEthValue)
                            currency_amount!!.setTextColor(Color.parseColor("#5b6abd"))
                            currency_amount!!.text = currencySymbol + sEthValue
                            return
                        } else {
                            currency_amount!!.text = ""
                        }
                        ignore = false
                    }
                }
            } catch (e: NumberFormatException) {
                btc_amount!!.setText("")
                eth_amount!!.setText("")
                currency_amount!!.text = ""
                Toast.makeText(requireContext(), "You can only Enter Numbers!", Toast.LENGTH_LONG).show()
            }
        }
    } //    //addCurrency button

}