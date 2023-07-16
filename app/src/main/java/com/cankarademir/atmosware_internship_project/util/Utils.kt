package com.cankarademir.atmosware_internship_project.util

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

class Utils {
    companion object {
        fun ImageView.downloadFromUrl(img_src: String?) {

            img_src?.let {
                Glide.with(context)
                    .load(img_src)
                    .centerCrop()
                    .into(this)
            }
        }
        @JvmStatic
        @BindingAdapter("android:downloadImageUrl")
        fun downloadImage(view: ImageView, img_src: String?) {
            view.downloadFromUrl(img_src)
        }
    }
}