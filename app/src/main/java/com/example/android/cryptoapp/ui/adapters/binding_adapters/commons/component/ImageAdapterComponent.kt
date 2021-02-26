package com.example.android.cryptoapp.ui.adapters.binding_adapters.commons.component

import androidx.databinding.DataBindingComponent
import com.example.android.cryptoapp.ui.adapters.binding_adapters.commons.ImageBindingAdapter
import com.squareup.picasso.Picasso

/**
 * This classes was created simply for making the picasso dependency (generated by Dagger) readily available for
 * the ImageBindingAdapter to use as I do not want to manually instantiate the picasso class inside the ImageBindingAdapter.kt file
 * */

class ImageAdapterComponent(val picasso:Picasso) : DataBindingComponent {
    override fun getImageBindingAdapter(): ImageBindingAdapter = ImageBindingAdapter(picasso)
}