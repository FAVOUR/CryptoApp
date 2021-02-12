package com.example.android.cryptoapp.ui.adapters.binding_adapters.commons


import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.squareup.picasso.Picasso
import jp.wasabeef.picasso.transformations.CropCircleTransformation
import javax.inject.Inject


 class ImageAdapters @Inject constructor(val picasso: Picasso) {


         @BindingAdapter("icon")
         fun ImageView.setImage(imageResId: String) {
             picasso.load(imageResId)
                     .transform(CropCircleTransformation())
                     .into(this)
         }


  }