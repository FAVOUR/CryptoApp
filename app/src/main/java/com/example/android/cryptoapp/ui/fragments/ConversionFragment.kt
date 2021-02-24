package com.example.android.cryptoapp.ui.fragments

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.example.android.cryptoapp.App
import com.example.android.cryptoapp.databinding.FragmentConversionBinding
import com.example.android.cryptoapp.di.component.AppComponent
import com.example.android.cryptoapp.ui.adapters.binding_adapters.commons.component.ImageAdapterComponent
import com.example.android.cryptoapp.viewmodel.ConversionViewmodel
import com.example.android.cryptoapp.viewmodel.factory.ViewModelFactory
import com.squareup.picasso.Picasso
import java.text.DecimalFormat
import javax.inject.Inject
import kotlin.properties.Delegates




class ConversionFragment : Fragment() {

    var image :Int by Delegates.notNull<Int>()
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
        appComponent.create(this)
        viewmodel.setBundleData( requireArguments())

    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,savedInstanceState: Bundle?): View? {

        // Inflate the layout for this fragment
        _binding= FragmentConversionBinding.inflate(inflater,container,false, ImageAdapterComponent(picasso = picasso))

        return binding.root
    }

    @SuppressLint("TimberArgCount")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
            binding.viewmodel=viewmodel
        // Pass the data to viewmodel and do the population in the viewmodel fields
           viewmodel.setCryptoData()
    }



}