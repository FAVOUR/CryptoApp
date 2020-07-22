package com.example.android.cryptoapp.activities

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.android.cryptoapp.R
import com.example.android.cryptoapp.Results
import com.example.android.cryptoapp.adapter.RatesAdapter
import com.example.android.cryptoapp.adapter.RatesAdapter.ListItemClickListiner
import com.example.android.cryptoapp.currency_data.Btc
import com.example.android.cryptoapp.currency_data.Eth
import com.example.android.cryptoapp.rest.ApiClient
import com.example.android.cryptoapp.rest.CryptoCurrencyService
import java.util.*
import kotlin.properties.Delegates

class ListActivity : AppCompatActivity(), ListItemClickListiner {
    var resultRv: RecyclerView? = null
    var resultAdapter: RatesAdapter? = null
    var layoutManager: LinearLayoutManager? = null
    lateinit var results: MutableList<Results>

    var soFar: List<Results>? = null
    lateinit var output: Results
    var image = 0
    lateinit var currencyAbr: String
    lateinit var currencySymbol: String
    lateinit var currencyName: String
    var check by Delegates.notNull<Boolean>()
    var btcRate by Delegates.notNull<Double>()
     var ethRate by  Delegates.notNull<Double>()
    var cryptoClient: CryptoCurrencyService? = null
    var btcConversionRates: Btc? = null
    var ethConversionRates: Eth? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list)
        check = intent.extras == null
        layoutManager = LinearLayoutManager(baseContext)
        results = ArrayList()
        soFar = ArrayList()
//        resultAdapter = RatesAdapter(applicationContext, results, this@ListActivity)
        resultAdapter = RatesAdapter(this@ListActivity, results)
        resultRv = findViewById<View>(R.id.rv_members) as RecyclerView
        resultRv!!.layoutManager = layoutManager
        resultRv!!.adapter = resultAdapter
        cryptoClient = ApiClient.client?.create(CryptoCurrencyService::class.java)
        if (!check) {
            image = intent.extras.getInt("image")
            btcRate = intent.extras.getDouble("btcRate")
            currencyAbr = intent.getStringExtra("currencyAbr")
            currencySymbol = intent.getStringExtra("currencySymbol")
            currencyName = intent.getStringExtra("currencyName")
            ethRate = intent.extras.getDouble("ethRate")
            resultRv!!.setHasFixedSize(true)
            output = Results(image, btcRate, ethRate, currencyName, currencyAbr, currencySymbol)
            resultAdapter!!.add(output)
        }
    }

    override fun onBackPressed() {
        val builder: AlertDialog.Builder
        builder = AlertDialog.Builder(this)
        builder.setTitle("Delete Entry")
                .setMessage("Are sure you wat to exit?")
                .setPositiveButton(android.R.string.yes
                ) { dialog, which -> finish() }
                .setNegativeButton(android.R.string.no
                ) { dialog, which -> dialog.dismiss() }
        val alertDialog = builder.create()
        alertDialog.show()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_list, menu)
        return true
    }

    override fun onPrepareOptionsMenu(menu: Menu): Boolean {
        //Let ApCompactActivity call the onPrepareOptionsMenu(menu) method
        super.onPrepareOptionsMenu(menu)
        val menuItem_1: MenuItem
        if (resultAdapter!!.itemCount > 0) {
            val menuItem = menu.findItem(R.id.add_new_card)
            menuItem.isVisible = false
            menuItem_1 = menu.findItem(R.id.replace)
            menuItem_1.isVisible = true
        } else {
            menuItem_1 = menu.findItem(R.id.replace)
            menuItem_1.isVisible = false
        }
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val intent: Intent
        when (item.itemId) {
            R.id.add_new_card -> {
                intent = Intent(this@ListActivity, EditorActivity::class.java)
                startActivity(intent)
                return true
            }
            R.id.about -> {
                val builder: AlertDialog.Builder
                builder = AlertDialog.Builder(this)
                builder.setTitle("About").setMessage(getString(R.string.About_)).setPositiveButton(android.R.string.yes
                ) { dialog, which -> }
                val alertDialog = builder.create()
                alertDialog.show()
                return true
            }
            R.id.replace -> {
                intent = Intent(this@ListActivity, EditorActivity::class.java)
                startActivity(intent)
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onListItemClicked(clickditemindex: Int) {
        val intent = Intent(this@ListActivity, ConversionActivity::class.java)
        intent.putExtra("image", image)
        intent.putExtra("btcRate", btcRate)
        intent.putExtra("currencySymbol", currencySymbol)
        intent.putExtra("ethRate", ethRate)
        intent.putExtra("currencyAbr", currencyAbr)
        intent.putExtra("currencyName", currencyName)
        startActivity(intent)
    }
}