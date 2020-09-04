//package com.example.android.cryptoapp.activities
//
//import android.annotation.TargetApi
//import android.graphics.Color
//import android.os.Build
//import android.os.Bundle
//import android.text.Editable
//import android.text.TextWatcher
//import android.view.View
//import android.widget.EditText
//import android.widget.ImageView
//import android.widget.TextView
//import android.widget.Toast
//import androidx.appcompat.app.AppCompatActivity
//import com.example.android.cryptoapp.R
//import com.squareup.picasso.Picasso
//import jp.wasabeef.picasso.transformations.CropCircleTransformation
//import java.text.DecimalFormat
//
////import com.squareup.picasso.Picasso;
//class ConversionActivity :
//        AppCompatActivity() {
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
//    var image = 0
//    var check = false
//    var currencyAbr: String? = null
//    var currencySymbol: String? = null
//    var currencyName: String? = null
//    var btcRate: Double? = null
//    var ethRate: Double? = null
//    var format: DecimalFormat? = null
//
//    @TargetApi(Build.VERSION_CODES.N)
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_conversion)
//        check = intent.extras == null
//        btcExchangeRate = findViewById<View>(R.id.btc_exchangerate) as TextView
//        ethExchangeRate = findViewById<View>(R.id.eth_exchangerate) as TextView
//        currencySymbol_1 = findViewById<View>(R.id.currency_symbol) as TextView
//        currencySymbol_2 = findViewById<View>(R.id.currency_symbol_2) as TextView
//        currencyAbbreviation = findViewById<View>(R.id.abrivation) as TextView
//        currency_Name = findViewById<View>(R.id.currency_name) as TextView
//        format = DecimalFormat()
//        format!!.isGroupingUsed = true
//        format!!.maximumIntegerDigits = 20
//        format!!.maximumFractionDigits = 3
//        currency_amount = findViewById<View>(R.id.currency_amount) as TextView
//        btc_amount = findViewById<View>(R.id.btc_amount) as EditText
//        eth_amount = findViewById<View>(R.id.eth_amount) as EditText
//        btc_amount!!.addTextChangedListener(generalWatcher)
//        eth_amount!!.addTextChangedListener(generalWatcher)
//        if (!check) {
//            image = intent.extras.getInt("image")
//            btcRate = intent.extras.getDouble("btcRate")
//            currencyAbr = intent.getStringExtra("currencyAbr")
//            currencySymbol = intent.getStringExtra("currencySymbol")
//            currencyName = intent.getStringExtra("currencyName")
//            ethRate = intent.extras.getDouble("ethRate")
//        }
//
//        //Set the currency Image
//        currencyImage = findViewById<View>(R.id.currency_image) as ImageView
//        Picasso.get().load(image)
//                .transform(CropCircleTransformation())
//                .into(currencyImage)
//
//        //Set the btc Image
//        btcImage = findViewById<View>(R.id.btc_logo) as ImageView
//        Picasso.get().load(R.drawable.btc)
//                .transform(CropCircleTransformation())
//                .into(btcImage)
//
//        //Set the eth Image
//        ethImage = findViewById<View>(R.id.eth_logo) as ImageView
//        Picasso.get().load(R.drawable.eth)
//                .transform(CropCircleTransformation())
//                .into(ethImage)
//        btcExchangeRate!!.text = format!!.format(btcRate!!)
//        ethExchangeRate!!.text = format!!.format(ethRate!!)
//        currencySymbol_1!!.text = currencySymbol
//        currencySymbol_2!!.text = currencySymbol
//        currencyAbbreviation!!.text = currencyAbr
//        currency_Name!!.text = currencyName
//    }
//
//    //    @Override
//    //
//    //    public boolean onCreateOptionsMenu(Menu menu) {
//    //
//    //        getMenuInflater().inflate(R.menu.menu_conversion_screen, menu);
//    //
//    //        return true;
//    //    }
//    //
//    //    @Override
//    //    public boolean onOptionsItemSelected(MenuItem item) {
//    //        // User clicked on a menu option in the app bar overflow menu
//    //        switch (item.getItemId()) {
//    //            // Respond to a click on the "Up" arrow button in the app bar
//    //            case android.R.id.home:
//    //                // Navigate back to parent activity (CatalogActivity)
//    //                NavUtils.navigateUpFromSameTask(this);
//    //                return true;
//    //        }
//    //        return super.onOptionsItemSelected(item);
//    //    }
//    private val generalWatcher: TextWatcher = object : TextWatcher {
//        var ignore = true //This works fine with java but with kotlin
//        override fun beforeTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {}
//        override fun onTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {}
//        override fun afterTextChanged(editable: Editable) {
//            ignore = true //You have to reasign with kotlin you should try this
//            try {
//                val fBtcValue: Float
//                val sBtcValue: String
//                if (btc_amount!!.text.hashCode() == editable.hashCode()) {
//                    if (editable.length > 0) {
//                        if (ignore) {
//                            eth_amount!!.setText("")
//                            fBtcValue = btc_amount!!.text.toString().trim { it <= ' ' }.toFloat()
//                            sBtcValue = format!!.format(btcRate!! * fBtcValue)
//                            currency_amount!!.setTextColor(Color.parseColor("#ab7d0b"))
//                            currency_amount!!.text = currencySymbol + sBtcValue
//                            return
//                        } else {
//                            currency_amount!!.text = ""
//                        }
//                        ignore = false
//                    }
//                } else if (eth_amount!!.text.hashCode() == editable.hashCode()) {
//                    val sEthValue: String
//                    if (editable.length > 0) {
//                        if (ignore) {
//                            btc_amount!!.setText("")
//                            val fEthValue = eth_amount!!.text.toString().trim { it <= ' ' }.toFloat()
//                            sEthValue = format!!.format(ethRate!! * fEthValue)
//                            currency_amount!!.setTextColor(Color.parseColor("#5b6abd"))
//                            currency_amount!!.text = currencySymbol + sEthValue
//                            return
//                        } else {
//                            currency_amount!!.text = ""
//                        }
//                        ignore = false
//                    }
//                }
//            } catch (e: NumberFormatException) {
//                btc_amount!!.setText("")
//                eth_amount!!.setText("")
//                currency_amount!!.text = ""
//                Toast.makeText(baseContext, "You can only Enter Numbers!", Toast.LENGTH_LONG).show()
//            }
//        }
//    } //    //addCurrency button
//    //    public void convert(View view) {
//    //       try{
//    //        if (currency_amount.getText() != null) {
//    //
//    //            Float currencyFigure = Float.parseFloat(currency_amount.getText().toString().trim());
//    //
//    //
//    //            String fBtcRate = format.format(currencyFigure / btcRate);
//    //
//    //            btc_amount.setText(fBtcRate);
//    //
//    //
//    //            String fEthRate = format.format(currencyFigure / ethRate);
//    //
//    //            eth_amount.setText(fEthRate);
//    //
//    //
//    //        } }
//    //
//    //        catch (NumberFormatException e){
//    //
//    //            currency_amount.setText("");
//    //            eth_amount.setText("");
//    //            btc_amount.setText("");
//    //
//    //            Toast.makeText(getBaseContext(), "Enter an amount in " + currencyAbr  ,Toast.LENGTH_LONG).show();
//    //
//    //        }
//    //    }
//}