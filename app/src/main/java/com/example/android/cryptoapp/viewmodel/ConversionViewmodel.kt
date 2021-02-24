package com.example.android.cryptoapp.viewmodel

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import androidx.databinding.adapters.Converters
import androidx.databinding.library.baseAdapters.BR
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.android.cryptoapp.R
import com.example.android.cryptoapp.data.source.repository.CryptoRepository
import com.example.android.cryptoapp.domain.model.CryptoCurrencyRates
import com.example.android.cryptoapp.ui.adapters.binding_adapters.commons.component.Converters.formatAndReturnString
import com.example.android.cryptoapp.util.Helpers
import javax.inject.Inject
import kotlin.properties.Delegates
@SuppressLint("TimberArgCount")
class ConversionViewmodel @Inject constructor(val repository: CryptoRepository):BaseObservableViewModel(){

    lateinit var currencyAbr: String
    lateinit var currencySymbol: String
    lateinit var currencyName: String

    private var cryptoRatesData = MutableLiveData<CryptoCurrencyRates>()


    var btcRate by Delegates.notNull<Double>()
    var ethRate by Delegates.notNull<Double>()
    var currencyImage: Int by Delegates.notNull()
    var firstCryptoIcon: Int by Delegates.notNull()
    var secondCryptoIcon: Int by Delegates.notNull()

    @Bindable
    var totalAmount: String =""

//    @Bindable Find a solution so that the values can be dynamic
    var isEth: String="1"

    @Bindable
    var sEthValue: String? =null
      set(value) {
//          if(!sBtcValue.isNullOrBlank()) {
//              sBtcValue=null
//              notifyPropertyChanged(BR.sBtcValue)
//
//          }
          if(sEthValue != value && !value.isNullOrEmpty()) {

              value?.let{
//                  isEth="0"
                  var dEthValue = it.replace(",", "").trim { it <= ' ' }.toDouble()
                          totalAmount = "$currencySymbol${formatAndReturnString(dEthValue * ethRate)}"
                  notifyPropertyChanged(BR.sEthValue)
                  notifyPropertyChanged(BR.totalAmount)
//                  notifyPropertyChanged(BR.isEth)
                  field = value

              }


          }

      }
    @Bindable
    var sBtcValue: String? =null
       set(value) {
//           if(!sEthValue.isNullOrEmpty()) {
//               sEthValue=null
//               notifyPropertyChanged(BR.sEthValue)
//
//           }

           if(sBtcValue != value && !value.isNullOrEmpty()) {
//               isEth="1"

               value?.let{
                   var dBtcValue =   it.replace(",", "").trim { it <= ' ' }.toDouble()


                   totalAmount = "$currencySymbol${formatAndReturnString(dBtcValue * btcRate)}"
                   notifyPropertyChanged(BR.sBtcValue)
                   notifyPropertyChanged(BR.totalAmount)
//                   notifyPropertyChanged(BR.isEth)
                   field = value  //you can use this to format before displaying

               }


           }

       }




//    fBtcValue = binding.btcAmount.text.toString().trim { it <= ' ' }.toFloat()
//    sBtcValue =   format.format(btcRate * fBtcValue)
//    binding.currencySymbol.setTextColor(Color.parseColor("#ab7d0b"))


    private var mbundle: MutableLiveData<Bundle?> = MutableLiveData()
    val lbundle:LiveData<Bundle?>
        get() = mbundle

      fun setBundleData(bundle:Bundle?){
    mbundle.value=bundle
}



    fun setCryptoData(){
        mbundle.value?.let {
        cryptoRatesData.value= mbundle.value!!.getParcelable(Helpers.DATA) as CryptoCurrencyRates
            populateFields()
        }
    }
      fun populateFields() {
          if(cryptoRatesData !=null){
          currencyImage = cryptoRatesData.value!!.image
          btcRate = cryptoRatesData.value!!.firstExRate
          currencyAbr = cryptoRatesData.value!!.abbrivation
          currencySymbol = cryptoRatesData.value!!.symbol
          currencyName = cryptoRatesData.value!!.name
          ethRate = cryptoRatesData.value!!.secondExRate
          firstCryptoIcon = R.drawable.btc
          secondCryptoIcon = R.drawable.eth
      }
    }
    

    

}