package com.add.ad.presentation.bindingadapter;

import android.graphics.Color;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.style.ForegroundColorSpan;
import android.util.Log;
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

    @BindingAdapter({"setTextColor1","setTextColor2"})
    public static void setTextColor(TextView textView, String s1, String s2){
        SpannableString span = new SpannableString(s1 + s2);
        span.setSpan(new ForegroundColorSpan(Color.parseColor("#FF6138")),0,s1.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

        textView.setText(span);
    }
}
