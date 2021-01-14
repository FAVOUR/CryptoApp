package com.example.android.cryptoapp.ui.fragments

import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import com.example.android.cryptoapp.App
import com.example.android.cryptoapp.R
import com.example.android.cryptoapp.databinding.FragmentConversionBinding
import com.example.android.cryptoapp.di.component.AppComponent
import com.example.android.cryptoapp.viewmodel.ConversionViewmodel
import com.example.android.cryptoapp.viewmodel.factory.ViewModelFactory
import com.squareup.picasso.Picasso
import jp.wasabeef.picasso.transformations.CropCircleTransformation
//import kotlinx.android.synthetic.main.fragment_conversion.*
//import kotlinx.android.synthetic.main.fragment_conversion.btc_amount
//import kotlinx.android.synthetic.main.fragment_conversion.btc_exchangerate
//import kotlinx.android.synthetic.main.fragment_conversion.btc_logo
//import kotlinx.android.synthetic.main.fragment_conversion.currency_amount
//import kotlinx.android.synthetic.main.fragment_conversion.currency_image
//import kotlinx.android.synthetic.main.fragment_conversion.eth_amount
//import kotlinx.android.synthetic.main.fragment_conversion.eth_exchangerate
//import kotlinx.android.synthetic.main.fragment_conversion.eth_logo
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
    private lateinit  var _binding : FragmentConversionBinding
    private val  binding : FragmentConversionBinding
    get() = _binding

    @Inject
    lateinit  var picasso:Picasso

    @Inject
    lateinit  var viewModelFactory: ViewModelFactory

    private val viewmodel: ConversionViewmodel by viewModels    {
        viewModelFactory
    }


    override fun onAttach(context: Context) {
        super.onAttach(context)
         appComponent = ( requireActivity().application as App).appComponent
        val conversionFragment = appComponent.create(this)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        _binding= FragmentConversionBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewmodel.bundle = requireArguments()

        check =viewmodel.check
        format = viewmodel.format
        bundle = viewmodel.bundle

          format.isGroupingUsed = true
          format.maximumIntegerDigits = 20
          format.maximumFractionDigits = 3

        binding.btcAmount.addTextChangedListener(generalWatcher)
        binding.ethAmount.addTextChangedListener(generalWatcher)

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
                .into(binding.currencyImage)

        //Set the btc Image
        picasso.load(R.drawable.btc)
                .transform(CropCircleTransformation())
                .into(binding.btcLogo)

        //Set the eth Image
        picasso.load(R.drawable.eth)
                .transform(CropCircleTransformation())
                .into(binding.ethLogo)

        binding.btcExchangerate.text  =    format.format(btcRate)
        binding.ethExchangerate.text  =    format.format(ethRate)

        binding.currencySymbol.text   =     currencySymbol
        binding.currencySymbol2.text =     currencySymbol
        binding.abrivation.text        =     currencyAbr
        binding.currencyName!!.text     =     currencyName
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
                if (binding.btcAmount.text.hashCode() == editable.hashCode()) {
                    if (editable.length > 0) {
                        if (ignore) {
                            binding.ethAmount.setText("")
                            fBtcValue = binding.btcAmount.text.toString().trim { it <= ' ' }.toFloat()
                            sBtcValue =   format.format(btcRate * fBtcValue)
                            binding.currencySymbol.setTextColor(Color.parseColor("#ab7d0b"))
                            binding.currencyAmount.text = currencySymbol + sBtcValue
                            return
                        } else {
                            binding.currencyAmount.text = ""
                        }
                        ignore = false
                    }
                } else if (binding.ethAmount.text.hashCode() == editable.hashCode()) {
                    val sEthValue: String
                    if (editable.length > 0) {
                        if (ignore) {
                            binding.btcAmount.setText("")
                            val fEthValue = binding.ethAmount.text.toString().trim { it <= ' ' }.toFloat()
                            sEthValue =   format.format(ethRate * fEthValue)
                            binding.currencyAmount.setTextColor(Color.parseColor("#5b6abd"))
                            binding.currencyAmount.text = currencySymbol + sEthValue
                            return
                        } else {
                            binding.currencyAmount.text = ""
                        }
                        ignore = false
                    }
                }
            } catch (e: NumberFormatException) {
                binding.btcAmount.setText("")
               binding.ethAmount.setText("")
               binding.currencyAmount.text = ""
                Toast.makeText(requireContext(), "You can only Enter Numbers!", Toast.LENGTH_LONG).show()
            }
        }
    } //    //addCurrency button

}