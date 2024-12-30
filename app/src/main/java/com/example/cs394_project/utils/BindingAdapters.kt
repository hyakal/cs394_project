package com.example.cs394_project.utils

import android.widget.ImageView
import androidx.databinding.BindingAdapter

@BindingAdapter("app:srcCompat")
fun bindImageViewResource(imageView: ImageView, resource: Int?) {
    resource?.let {
        imageView.setImageResource(it)
    }
}