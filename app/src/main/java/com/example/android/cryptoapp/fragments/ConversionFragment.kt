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
import com.example.android.cryptoapp.R
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
    var image = 0
    var check = false
    var currencyAbr: String? = null
    var currencySymbol: String? = null
    var currencyName: String? = null
    var btcRate: Double? = null
    var ethRate: Double? = null
    var format: DecimalFormat? = null
    var bundle: Bundle? = null



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        arguments?.let {
//            param1 = it.getString(ARG_PARAM1)
//            param2 = it.getString(ARG_PARAM2)
//        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_conversion, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        bundle = arguments

        check = bundle == null
//        btcExchangeRate = btc_exchangerate
//        ethExchangeRate = eth_exchangerate
//        currencySymbol_1 = currency_symbol
//        currencySymbol_2 = currency_symbol_2
//        currencyAbbreviation = abrivation
//        currency_Name = currency_name
        format = DecimalFormat()
        format!!.isGroupingUsed = true
        format!!.maximumIntegerDigits = 20
        format!!.maximumFractionDigits = 3
//        currency_amount = currency_amount
//        btc_amount = btc_amount
//        eth_amount =eth_amount
        btc_amount!!.addTextChangedListener(generalWatcher)
        eth_amount!!.addTextChangedListener(generalWatcher)
        if (!check) {
            image = bundle?.getInt("image") ?:0
            btcRate = bundle?.getDouble("btcRate")
            currencyAbr = bundle?.getString("currencyAbr")
            currencySymbol = bundle?.getString("currencySymbol")
            currencyName = bundle?.getString("currencyName")
            ethRate =  bundle?.getDouble("ethRate")
        }

        //Set the currency Image
        Picasso.get().load(image)
                .transform(CropCircleTransformation())
                .into(currency_image)

        //Set the btc Image
        Picasso.get().load(R.drawable.btc)
                .transform(CropCircleTransformation())
                .into(btc_logo)

        //Set the eth Image
        Picasso.get().load(R.drawable.eth)
                .transform(CropCircleTransformation())
                .into(eth_logo)
        btc_exchangerate!!.text = format!!.format(btcRate!!)
        eth_exchangerate!!.text = format!!.format(ethRate!!)
        currency_symbol!!.text = currencySymbol
        currency_symbol_2!!.text = currencySymbol
        abrivation!!.text = currencyAbr
        currency_name!!.text = currencyName
    }

//    companion object {
//        /**
//         * Use this factory method to create a new instance of
//         * this fragment using the provided parameters.
//         *
//         * @param param1 Parameter 1.
//         * @param param2 Parameter 2.
//         * @return A new instance of fragment ConversionFragment.
//         */
//        // TODO: Rename and change types and number of parameters
//        @JvmStatic
//        fun newInstance(param1: String, param2: String) =
//                ConversionFragment().apply {
//                    arguments = Bundle().apply {
//                        putString(ARG_PARAM1, param1)
//                        putString(ARG_PARAM2, param2)
//                    }
//                }
//    }

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
                            sBtcValue = format!!.format(btcRate!! * fBtcValue)
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
                            sEthValue = format!!.format(ethRate!! * fEthValue)
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