package com.example.android.cryptoapp.fragments

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.fragment.app.Fragment
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.android.cryptoapp.R
import com.example.android.cryptoapp.Results
import com.example.android.cryptoapp.adapter.RatesAdapter
import com.example.android.cryptoapp.currency_data.Btc
import com.example.android.cryptoapp.currency_data.Eth
import com.example.android.cryptoapp.rest.ApiClient
import com.example.android.cryptoapp.rest.CryptoCurrencyService
import kotlinx.android.synthetic.main.fragment_list.*
import java.util.ArrayList
import kotlin.properties.Delegates

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [ListFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ListFragment : Fragment(), RatesAdapter.ListItemClickListiner {
    // TODO: Rename and change types of parameters
   /* private var param1: String? = null
    private var param2: String? = null
*/
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
    var bundle: Bundle? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
     /*   arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }*/



    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_list, container, false)
    }

     val TAG :String = "ListFragment"
//    override fun onAttach(context: Context) {
//        super.onAttach(context)
//
//
//        Log.e(TAG,"${(context as RatesAdapter.ListItemClickListiner).toString()} ${context.resources.getString(R.string.exception_message)}")
//    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setHasOptionsMenu(true)

        bundle = arguments


        check = bundle == null
        layoutManager = LinearLayoutManager(requireContext())
        results = ArrayList()
        soFar = ArrayList()
//        resultAdapter = RatesAdapter(applicationContext, results, this@ListActivity)
        resultAdapter = RatesAdapter(requireContext(), results)
        resultRv = rv_members
        resultRv!!.layoutManager = layoutManager
        resultRv!!.adapter = resultAdapter
        cryptoClient = ApiClient.client?.create(CryptoCurrencyService::class.java)
        if (!check) {
            image = bundle!!.getInt("image")
            btcRate = bundle!!.getDouble("btcRate")
            currencyAbr = bundle?.getString("currencyAbr") ?:""
            currencySymbol = bundle?.getString("currencySymbol") ?:""
            currencyName = bundle?.getString("currencyName") ?:""
            ethRate = bundle?.getDouble("ethRate") ?:0.00
            resultRv!!.setHasFixedSize(true)
            output = Results(image, btcRate, ethRate, currencyName, currencyAbr, currencySymbol)
            resultAdapter!!.add(output)
        }
    }

  /*  override fun onBackPressed() {
        val builder: AlertDialog.Builder
        builder = AlertDialog.Builder(requireContext())
        builder.setTitle("Delete Entry")
                .setMessage("Are sure you wat to exit?")
                .setPositiveButton(android.R.string.yes
                ) { dialog, which -> activity?.finish() }
                .setNegativeButton(android.R.string.no
                ) { dialog, which -> dialog.dismiss() }
        val alertDialog = builder.create()
        alertDialog.show()
    }*/

    override fun onPrepareOptionsMenu(menu: Menu) {
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
//        return true
    }

    override fun onCreateOptionsMenu(menu: Menu,inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_list, menu)

        super.onCreateOptionsMenu(menu, inflater);


    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val intent: Intent
        when (item.itemId) {
            R.id.add_new_card -> {
//                intent = Intent(requireContext(), EditorActivity::class.java)
//                startActivity(intent)

                var editorFragment = EditorFragment()

                activity?.supportFragmentManager?.beginTransaction()
                        ?.replace(R.id.viewContainer,  editorFragment,null)
                        ?.addToBackStack(null)
                        ?.commit()
                return true
            }
            R.id.about -> {
                val builder: AlertDialog.Builder
                builder = AlertDialog.Builder(requireContext())
                builder.setTitle("About").setMessage(getString(R.string.About_)).setPositiveButton(android.R.string.yes
                ) { dialog, which -> }
                val alertDialog = builder.create()
                alertDialog.show()
                return true
            }
            R.id.replace -> {
                      val editorFragment = EditorFragment()
                val fragmentManager = activity?.let {   activity ->
                                activity.supportFragmentManager.beginTransaction()
                                        .replace(R.id.viewContainer,editorFragment,null)
                                        .addToBackStack(null)
                                        .commit()
                    }
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onListItemClicked(clickditemindex: Int) {
          val conversionFragment = ConversionFragment()

         val bundle = Bundle()
        bundle.putInt("image", image)
        bundle.putDouble("btcRate", btcRate)
        bundle.putString("currencySymbol", currencySymbol)
        bundle.putDouble("ethRate", ethRate)
        bundle.putString("currencyAbr", currencyAbr)
        bundle.putString("currencyName", currencyName)

        conversionFragment.arguments = bundle

        val fragmentManager = activity?.let{activity ->
            activity.supportFragmentManager.beginTransaction()
                    .replace(R.id.viewContainer,conversionFragment,null)
                    .addToBackStack(null)
                    .commit()

        }
    }


    /* companion object {
         *//**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment ListFragment.
         *//*
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
                ListFragment().apply {
                    arguments = Bundle().apply {
                        putString(ARG_PARAM1, param1)
                        putString(ARG_PARAM2, param2)
                    }
                }
    }*/
}