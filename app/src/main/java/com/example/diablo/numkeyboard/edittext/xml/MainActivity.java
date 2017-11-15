package com.example.diablo.numkeyboard.edittext.xml;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.InputType;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import com.example.diablo.numkeyboard.R;

public class MainActivity extends AppCompatActivity {

    private Context mContext;
    private Activity mActivity;
    private EditText mUsernameEt;
    private EditText mPasswordEt;
    private KeyboardUtil keyboardUtil;

    @SuppressLint("NewApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mContext = this;
        mActivity = this;

        mUsernameEt = findViewById(R.id.account);
        mPasswordEt = findViewById(R.id.pwd);

        mUsernameEt.setShowSoftInputOnFocus(false);
        mUsernameEt.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
//                mUsernameEt.setInputType(InputType.TYPE_NULL);
                if (keyboardUtil == null) {
                    keyboardUtil = new KeyboardUtil(mContext, mActivity);
                }
                keyboardUtil.setmEditText(mUsernameEt);
                keyboardUtil.showKeyboard();
                return false;
            }
        });

        mPasswordEt.setShowSoftInputOnFocus(false);
        mPasswordEt.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (keyboardUtil == null) {
                    keyboardUtil = new KeyboardUtil(mContext, mActivity);
                }
                keyboardUtil.setmEditText(mPasswordEt);
                keyboardUtil.showKeyboard();
                return false;
            }
        });
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_BACK)) {
            if (keyboardUtil != null && keyboardUtil.isShowing()) {
                keyboardUtil.hideKeyboard();
                return false;
            }
        }
        return super.onKeyDown(keyCode, event);
    }
}
