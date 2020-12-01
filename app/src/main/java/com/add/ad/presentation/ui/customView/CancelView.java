//package com.add.ad.presentation.ui.customView;
//
//import android.content.Context;
//import android.text.Editable;
//import android.text.TextWatcher;
//import android.util.AttributeSet;
//import android.util.Log;
//import android.view.KeyEvent;
//import android.view.LayoutInflater;
//import android.view.MotionEvent;
//import android.view.View;
//import android.view.inputmethod.EditorInfo;
//import android.widget.EditText;
//import android.widget.TextView;
//
//import androidx.annotation.NonNull;
//import androidx.annotation.Nullable;
//import androidx.constraintlayout.widget.ConstraintLayout;
//
//import com.add.ad.R;
//import com.add.ad.presentation.viewModel.feed.FeedViewModel;
//import com.add.ad.presentation.viewModel.search.SearchViewModel;
//
//public class CancelView extends ConstraintLayout implements TextWatcher, View.OnTouchListener, EditText.OnFocusChangeListener {
//
//    private TextView textView;
//    private EditText editText;
//
//    public CancelView(@NonNull Context context) {
//        super(context);
//        init();
//    }
//
//    public CancelView(@NonNull Context context, @Nullable AttributeSet attrs) {
//        super(context, attrs);
//        init();
//    }
//
//    public CancelView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
//        super(context, attrs, defStyleAttr);
//        init();
//    }
//
//    private void init() {
//        final LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//        addView(inflater.inflate(R.layout.custom_search_edit, this, false));
//
//        textView = (TextView) findViewById(R.id.search_cancel_tv);
//        editText = (EditText) findViewById(R.id.search_creator_et);
//
//        editText.setImeOptions(EditorInfo.IME_ACTION_SEARCH);
//        editText.setOnTouchListener(this);
//        editText.setOnFocusChangeListener(this);
//        editText.addTextChangedListener(this);
//
//        editText.setOnEditorActionListener((textView, actionId, keyEvent) -> {
//            switch (actionId) {
//                case EditorInfo.IME_ACTION_SEARCH:
//
//                    break;
//
//            }
//            return true;
//        });
//    }
//
//    @Override
//    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
//    }
//
//    @Override
//    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
//        if (hasFocus()) setCancelVisibility(charSequence.length() > 0);
//    }
//
//    @Override
//    public void afterTextChanged(Editable editable) {
//    }
//
//    @Override
//    public void onFocusChange(View view, boolean bool) {
//        if (bool) setCancelVisibility(editText.getText().length() > 0);
//        else setCancelVisibility(false);
//    }
//
//    @Override
//    public boolean onTouch(View view, MotionEvent motionEvent) {
//        final boolean isRight = motionEvent.getX() >= editText.getWidth() - textView.getWidth();
//
//        if (motionEvent.getAction() == MotionEvent.ACTION_UP && isRight) {
//            editText.setText(null);
//        }
//
//        return false;
//    }
//
//    private void setCancelVisibility(Boolean bool) {
//        textView.setVisibility(bool ? View.VISIBLE : View.INVISIBLE);
//    }
//
//}