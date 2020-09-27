package com.example.android.cryptoapp.fragments

import android.app.DialogFragment.STYLE_NORMAL
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.DialogFragment.STYLE_NORMAL
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.android.cryptoapp.R
import com.example.android.cryptoapp.Results
import com.example.android.cryptoapp.adapter.RatesAdapter
import com.example.android.cryptoapp.currency_data.Btc
import com.example.android.cryptoapp.currency_data.Eth
import com.example.android.cryptoapp.rest.ApiClient
import com.example.android.cryptoapp.rest.CryptoCurrencyService
import com.example.android.cryptoapp.viewmodel.ListViewModel
import com.google.gson.Gson
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
class ListFragment : Fragment(), RatesAdapter.ListItemClickListiner, EditorFragment.OnDataGotten {

    var resultAdapter: RatesAdapter? = null
    var layoutManager: LinearLayoutManager? = null
    lateinit var results: MutableList<Results>

    var soFar: List<Results>? = null
//    lateinit var output: Results
//    var image = 0
//    lateinit var currencyAbr: String
//    lateinit var currencySymbol: String
//    lateinit var currencyName: String
    var check by Delegates.notNull<Boolean>()
    var btcRate by Delegates.notNull<Double>()
//    var ethRate by  Delegates.notNull<Double>()
//    var cryptoClient: CryptoCurrencyService? = null
//    var btcConversionRates: Btc? = null
//    var ethConversionRates: Eth? = null
        var bundle: Bundle? =null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


    }


     val viewmodel  by activityViewModels<ListViewModel>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment

        val view = inflater.inflate(R.layout.fragment_list, container, false)
        viewmodel.bundle = arguments
        check = viewmodel.check
        bundle = viewmodel.bundle

        return   view
    }

     val TAG :String = "ListFragment"

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setHasOptionsMenu(true)
        setupAdapter()

        layoutManager = LinearLayoutManager(requireContext())
        results = ArrayList()
        soFar = ArrayList()
//        resultAdapter = RatesAdapter(applicationContext, results, this@ListActivity)
        resultAdapter = RatesAdapter(results,this)
        rv_members!!.layoutManager = layoutManager
        rv_members!!.adapter = resultAdapter
        viewmodel.cryptoClient = ApiClient.client?.create(CryptoCurrencyService::class.java)
    }

    private fun setupAdapter() {


        check = bundle == null

        Log.e("bundle",Gson().toJson(bundle))
//

        if (!(check && this.bundle == null)) {
//            if (!(check || this.bundle == null)) {
                viewmodel.image = bundle!!.getInt("image")
                viewmodel.btcRate = bundle!!.getDouble("btcRate")
                viewmodel.currencyAbr = bundle?.getString("currencyAbr") ?: ""
                viewmodel.currencySymbol = bundle?.getString("currencySymbol") ?: ""
                viewmodel.currencyName = bundle?.getString("currencyName") ?: ""
                viewmodel.ethRate = bundle?.getDouble("ethRate") ?: 0.00
//            rv_members!!.setHasFixedSize(true)
                var output = Results(viewmodel.image, viewmodel.btcRate, viewmodel.ethRate, viewmodel.currencyName, viewmodel.currencyAbr, viewmodel.currencySymbol)
//            resultAdapter!!.add(output)

                Toast.makeText(requireContext(), Gson().toJson(output), Toast.LENGTH_SHORT).show()
                results.add(output)

                resultAdapter!!.notifyItemChanged(results.size)
//            }
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
                  editorFragment.setStyle(DialogFragment.STYLE_NORMAL, R.style.EditorTheme);
//                  editorFragment.setStyle(DialogFragment.STYLE_NORMAL, R.style.EditorTheme);
                  editorFragment.setTargetFragment(this@ListFragment,1)
                  editorFragment.show(activity?.supportFragmentManager!!,null)
//
//                var editorFragment = EditorFragment()
//                activity?.supportFragmentManager?.beginTransaction()
//                        ?.replace(R.id.viewContainer,  editorFragment,null)
//                        ?.addToBackStack(null)
//                        ?.commit()
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
//          val conversionFragment = ConversionFragment()
//
//         val bundle = Bundle()
//        bundle.putInt("image", image)
//        bundle.putDouble("btcRate", btcRate)
//        bundle.putString("currencySymbol", currencySymbol)
//        bundle.putDouble("ethRate", ethRate)
//        bundle.putString("currencyAbr", currencyAbr)
//        bundle.putString("currencyName", currencyName)
//
//        conversionFragment.arguments = bundle
//
//        val fragmentManager = activity?.let{activity ->
//            activity.supportFragmentManager.beginTransaction()
//                    .replace(R.id.viewContainer,conversionFragment,null)
//                    .addToBackStack(null)
//                    .commit()
//
//        }
    }

    override fun data(bundle: Bundle) {
        this.bundle = bundle
        setupAdapter()

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