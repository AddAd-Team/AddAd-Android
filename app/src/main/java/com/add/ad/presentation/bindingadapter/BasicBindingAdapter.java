package com.add.ad.presentation.bindingadapter;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

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

    @BindingAdapter("setFeedImages")
    public static void setFeedImage(ImageView image, String url){
        try{
            RequestOptions requestOptions = new RequestOptions();
            requestOptions.placeholder(R.color.colorDarkGray);

            Glide.with(image.getContext())
                    .setDefaultRequestOptions(requestOptions)
                    .load(url)
                    .into(image);
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    @BindingAdapter("setItemVisibility")
    public static void setItemVisibility(TextView textView, Boolean bool){
        if(bool){
            textView.setVisibility(View.GONE);
        }else {
            textView.setVisibility(View.VISIBLE);
        }
    }
}
