package com.add.ad.presentation.bindinadapter;

import android.widget.ImageView;

import androidx.databinding.BindingAdapter;

import com.add.ad.R;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

public class BasicBindingAdapter {
    @BindingAdapter("setImages")
    public static void setImage(ImageView image, String url){
        try{
            RequestOptions requestOptions = new RequestOptions();
            requestOptions.placeholder(R.drawable.ic_profile);

            Glide.with(image.getContext())
                    .setDefaultRequestOptions(requestOptions)
                    .load(url)
                    .into(image);
        } catch (Exception e){
            e.printStackTrace();
        }

    }
}
