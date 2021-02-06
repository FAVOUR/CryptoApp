package com.example.android.cryptoapp.ui.fragments

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.fragment.app.Fragment
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.android.cryptoapp.App
import com.example.android.cryptoapp.R
import com.example.android.cryptoapp.domain.model.CryptoCurrencyRates
import com.example.android.cryptoapp.adapter.RatesAdapter
import com.example.android.cryptoapp.databinding.FragmentListBinding
import com.example.android.cryptoapp.di.component.AppComponent
import com.example.android.cryptoapp.domain.model.asDomainModel
import com.example.android.cryptoapp.viewmodel.ListViewModel
import com.example.android.cryptoapp.viewmodel.factory.ViewModelFactory
import com.google.gson.Gson
import kotlinx.android.synthetic.main.fragment_list.*
import timber.log.Timber
import java.util.ArrayList
import javax.inject.Inject

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [ListFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
//class ListFragment : Fragment(), RatesAdapter.ListItemClickListiner, EditorFragment.OnDataGotten {
class ListFragment : Fragment(), RatesAdapter.ListItemClickListiner {

    lateinit var resultAdapter: RatesAdapter
    var layoutManager: LinearLayoutManager? = null
    lateinit var results: MutableList<CryptoCurrencyRates>
    private lateinit  var appComponent: AppComponent


    var soFar: List<CryptoCurrencyRates>? = null
//    var check by Delegates.notNull<Boolean>()

        var bundle: Bundle? =null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    val viewmodel : ListViewModel by viewModels {
        viewModelFactory
     }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        appComponent = ( requireActivity().application as App).appComponent
        val conversionFragment = appComponent.create(this)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        // Inflate the layout for this fragment
        val view = FragmentListBinding.inflate(inflater, container, false)


        return   view.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setHasOptionsMenu(true)

        layoutManager = LinearLayoutManager(requireContext())
        results = ArrayList()
        soFar = ArrayList()
//        resultAdapter = RatesAdapter(applicationContext, results, this@ListActivity)
        resultAdapter = RatesAdapter(results,this)
        rv_members!!.layoutManager = layoutManager
        rv_members!!.adapter = resultAdapter

        viewmodel.cryptoCurrencyData.observe(viewLifecycleOwner, Observer { cryptoCurrencyData ->


            Timber.e(Gson().toJson(cryptoCurrencyData))
            if(cryptoCurrencyData !=null) {
                val data = cryptoCurrencyData.asDomainModel()

                Log.e("data", Gson().toJson(data))
                resultAdapter.add(data)
            }

        })



    }




    override fun onCreateOptionsMenu(menu: Menu,inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_list, menu)

        super.onCreateOptionsMenu(menu, inflater);


    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val intent: Intent
        when (item.itemId) {
            R.id.add_new_card -> {

                var editorFragment = EditorFragment()
                  editorFragment.setStyle(DialogFragment.STYLE_NORMAL, R.style.EditorTheme);
//                  editorFragment.setStyle(DialogFragment.STYLE_NORMAL, R.style.EditorTheme);
                  editorFragment.setTargetFragment(this@ListFragment,1)
                  editorFragment.show(activity?.supportFragmentManager!!,null)

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
//            R.id.replace -> {
//                      val editorFragment = EditorFragment()
//                val fragmentManager = activity?.let {   activity ->
//                                activity.supportFragmentManager.beginTransaction()
//                                        .replace(R.id.viewContainer,editorFragment,null)
//                                        .addToBackStack(null)
//                                        .commit()
//                    }
//                return true
//            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onListItemClicked(result: CryptoCurrencyRates) {
          val conversionFragment = ConversionFragment()

         val bundle = Bundle()
        bundle.putInt("image", result.image)
        bundle.putDouble("btcRate", result.firstExRate)
        bundle.putString("currencySymbol", result.symbol)
        bundle.putDouble("ethRate", result.secondExRate)
        bundle.putString("currencyAbr", result.abbrivation)
        bundle.putString("currencyName", result.name)

        conversionFragment.arguments = bundle

        val fragmentManager = activity?.let{activity ->
            activity.supportFragmentManager.beginTransaction()
                    .replace(R.id.viewContainer,conversionFragment,null)
                    .addToBackStack(null)
                    .commit()

        }
    }

//    override fun data(bundle: Bundle) {
//        this.bundle = bundle
////        setupAdapter()
//
//    }


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