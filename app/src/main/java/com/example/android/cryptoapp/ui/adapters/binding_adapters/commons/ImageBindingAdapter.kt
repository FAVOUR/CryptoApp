package com.example.android.cryptoapp.ui.adapters.binding_adapters.commons


import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.example.android.cryptoapp.R
import com.squareup.picasso.Picasso
import jp.wasabeef.picasso.transformations.CropCircleTransformation
import javax.inject.Inject


 class ImageBindingAdapter @Inject constructor(val picasso: Picasso) {

         @BindingAdapter("image")
         fun ImageView.setImage(imageResId: Int) {
             picasso.load(imageResId)
                     .transform(CropCircleTransformation())
                     .into(this)
         }



  }