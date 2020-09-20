package com.example.android.cryptoapp.fragments

import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.viewModels
import com.example.android.cryptoapp.R
import com.example.android.cryptoapp.di.AppModule
import com.example.android.cryptoapp.viewmodel.ConversionViewmodel
import com.example.android.cryptoapp.viewmodel.EditorViewModel
import com.squareup.picasso.Picasso
import jp.wasabeef.picasso.transformations.CropCircleTransformation
import kotlinx.android.synthetic.main.activity_conversion.*
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
import kotlin.properties.Delegates

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [ConversionFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ConversionFragment : Fragment() {
    // TODO: Rename and change types of parameters
//    private var param1: String? = null
//    private var param2: String? = null

//    var btc_amount: EditText? = null
//    var eth_amount: EditText? = null
//    var currencyImage: ImageView? = null
//    var btcImage: ImageView? = null
//    var ethImage: ImageView? = null
//    var currency_amount: TextView? = null
//    var btcExchangeRate: TextView? = null
//    var ethExchangeRate: TextView? = null
//    var currencySymbol_1: TextView? = null
//    var currencySymbol_2: TextView? = null
//    var currency_Name: TextView? = null
//    var currencyAbbreviation: TextView? = null
       var image :Int by Delegates.notNull<Int>()
      private var check by Delegates.notNull<Boolean>()

    private var currencyAbr: String  by Delegates.notNull<String>()
    private var currencySymbol: String by  Delegates.notNull<String>()
    private var currencyName: String  by  Delegates.notNull<String>()
    private var btcRate: Double  by  Delegates.notNull<Double>()
    private var ethRate: Double by Delegates.notNull<Double>()
    private lateinit  var bundle: Bundle
    private lateinit var format: DecimalFormat


    private val viewmodel: ConversionViewmodel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_conversion, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewmodel.bundle = requireArguments()
        viewmodel.check =   viewmodel.bundle == null
        viewmodel.format = DecimalFormat()

        image = viewmodel.image
        btcRate = viewmodel.btcRate
        currencyAbr = viewmodel.currencyAbr
        currencySymbol = viewmodel.currencySymbol
        currencyName = viewmodel.currencyName
        ethRate= viewmodel.ethRate
        check = viewmodel.check
        bundle = viewmodel.bundle

          format.isGroupingUsed = true
          format.maximumIntegerDigits = 20
          format.maximumFractionDigits = 3
//        currency_amount = currency_amount
//        btc_amount = btc_amount
//        eth_amount =eth_amount
        btc_amount!!.addTextChangedListener(generalWatcher)
        eth_amount!!.addTextChangedListener(generalWatcher)
        if (check) {

            viewmodel.image = bundle.getInt("image") ?:0
            viewmodel.btcRate = bundle.getDouble("btcRate")
            viewmodel.currencyAbr =bundle.getString("currencyAbr")
            viewmodel.currencySymbol =bundle.getString("currencySymbol")
            viewmodel.currencyName = bundle.getString("currencyName")
            viewmodel.ethRate =  bundle.getDouble("ethRate")
        }

        //Set the currency Image
        AppModule.providePicassoInstance().load(image)
                .transform(CropCircleTransformation())
                .into(currency_image)

        //Set the btc Image
        AppModule.providePicassoInstance().load(R.drawable.btc)
                .transform(CropCircleTransformation())
                .into(btc_logo)

        //Set the eth Image
        AppModule.providePicassoInstance().load(R.drawable.eth)
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