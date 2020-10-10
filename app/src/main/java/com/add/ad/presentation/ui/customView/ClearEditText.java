package com.add.ad.presentation.ui.customView;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.core.content.ContextCompat;

import com.add.ad.R;

public class ClearEditText extends AppCompatEditText implements TextWatcher, View.OnTouchListener, View.OnFocusChangeListener {

    private Drawable drawable;

    public ClearEditText(@NonNull Context context) {
        super(context);
        init();
    }

    public ClearEditText(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public ClearEditText(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        drawable = ContextCompat.getDrawable(getContext(), R.drawable.ic_clear);
        
        if (drawable != null) {
            drawable.setBounds(0, 0, drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());
        }

        setIcVisibility(false);

        setOnTouchListener(this);
        setOnFocusChangeListener(this);
        addTextChangedListener(this);
    }

    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        if (isFocused()) {
            setIcVisibility(charSequence.length() > 0);
        }
    }

    @Override
    public void afterTextChanged(Editable editable) {

    }

    @Override
    public void onFocusChange(View view, boolean bool) {
        if (bool) setIcVisibility(getText().length() > 0);
        else setIcVisibility(false);
    }

    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        final boolean isRight = motionEvent.getX() >= getWidth() - drawable.getIntrinsicWidth();

        if (motionEvent.getAction() == MotionEvent.ACTION_UP && isRight) {
            setText(null);
            setError(null);
        }

        return false;
    }

    private void setIcVisibility(boolean bool) {
        drawable.setVisible(bool, false);
        setCompoundDrawables(null, null, bool ? drawable : null, null);
    }
}