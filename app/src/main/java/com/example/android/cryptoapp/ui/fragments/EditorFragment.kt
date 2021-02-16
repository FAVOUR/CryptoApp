package com.example.android.cryptoapp.ui.fragments

import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.example.android.cryptoapp.App
import com.example.android.cryptoapp.R
import com.example.android.cryptoapp.currency_data.*
import com.example.android.cryptoapp.databinding.FragmentEditorBinding
import com.example.android.cryptoapp.di.component.AppComponent
import com.example.android.cryptoapp.util.*
import com.example.android.cryptoapp.viewmodel.EditorViewModel
import com.example.android.cryptoapp.viewmodel.factory.ViewModelFactory
import timber.log.Timber
import javax.inject.Inject


/**
 * A simple [Fragment] subclass.
 * Use the [EditorFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class EditorFragment : DialogFragment() {

    private lateinit  var appComponent: AppComponent


    @Inject
    lateinit var viewModelFactory: ViewModelFactory


    lateinit var binding: FragmentEditorBinding
    // activity-ktx artifact
    private val viewmodel: EditorViewModel by viewModels{

        viewModelFactory
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        appComponent = ( requireActivity().application as App).appComponent
        val conversionFragment = appComponent.create(this)
    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {




         binding =  FragmentEditorBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        activity?.actionBar?.title =resources.getString(R.string.editor_activity_title)

        indicateRemoteRequest()


        spinnerForCurrency()
        binding.ExchangeRateBTN.setOnClickListener {
            addCurrency()
        }
    }

    private fun indicateRemoteRequest() {
        viewmodel.isLoading.observe(viewLifecycleOwner, Observer { isMakingNetworkRequest ->
            Timber.e(isMakingNetworkRequest.toString())
            if (isMakingNetworkRequest) {
                binding.pbloading.visibility = View.VISIBLE
                binding.textView.visibility = View.VISIBLE
                binding.group.visibility = View.GONE
//                binding.root.background=ColorDrawable (Color.parseColor("#99000000"))
                binding.root.background=ColorDrawable (Color.TRANSPARENT)

            } else {
                binding.pbloading.visibility = View.GONE
                binding. textView.visibility = View.GONE
                dismiss()
            }
        })
    }

    /**
     * Setup the dropdown spinner that allows the user to select the currency of choice.
     */
    private fun spinnerForCurrency(){
        // Create Adapter for spinner. The list options are from the String array
        val currencySpinnerAdapter: ArrayAdapter<*> = ArrayAdapter.createFromResource(requireContext(),
                R.array.currency_options, android.R.layout.simple_spinner_item)

        // Specify dropdown layout style - simple list view with 1 item per line
        currencySpinnerAdapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line)

        // Apply the Adapter to the spinner
        binding.currencyName.adapter = currencySpinnerAdapter

        // Set the currency Selected to the constant values
        //TODO Try to lazily instantiate the adapter.onItemSelected interface
        binding. currencyName.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
                val selection = parent.getItemAtPosition(position) as String
                if (!TextUtils.isEmpty(selection)) {
                    //Use the position of item chosen to get the currencyAbr and currencyName
                    viewmodel.currencyAbr = getCurrencyAbbrFromSpinner(position)
                    viewmodel._currencyName = selection
                    viewmodel.currencySymbol = getSymbol( viewmodel.currencyAbr)
                    viewmodel.image = getCurrencyImage( viewmodel.currencyAbr)
                } else {
                    viewmodel.currencyAbr =  CurrencyAbbreviation.NONE
                    viewmodel. _currencyName = ""
                }
            }

            override fun onNothingSelected(adapterView: AdapterView<*>?) {}
        }
    }


    fun addCurrency() {
        viewmodel.getCryptoRate()
    }







}