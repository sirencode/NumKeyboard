package com.example.diablo.numkeyboard.edittext.xml;

import android.app.Activity;
import android.content.Context;
import android.inputmethodservice.Keyboard;
import android.inputmethodservice.KeyboardView;
import android.text.Editable;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import com.example.diablo.numkeyboard.R;

import java.util.List;

/**
 * Created by zhuanghongji on 2015/12/10.
 */
public class KeyboardUtil {
    private Context mContext;
    private Activity mActivity;
    private KeyboardView mKeyboardView;
    private EditText mEditText;


    public KeyboardUtil(Context context, Activity activity) {
        mContext = context;
        mActivity = activity;
        mKeyboardView = mActivity.findViewById(R.id.keyboard_view);
        mKeyboardView.setKeyboard(new Keyboard(mContext, R.xml.price_input_keyboard));
        mKeyboardView.setEnabled(true);
        mKeyboardView.setPreviewEnabled(false);
        mKeyboardView.setOnKeyboardActionListener(listener);
    }

    public void setmEditText(EditText editText) {
        this.mEditText = editText;
    }


    private KeyboardView.OnKeyboardActionListener listener = new KeyboardView.OnKeyboardActionListener() {
        @Override
        public void onPress(int primaryCode) {

        }

        @Override
        public void onRelease(int primaryCode) {

        }

        @Override
        public void onKey(int primaryCode, int[] keyCodes) {
            Editable editable = mEditText.getText();
            int start = mEditText.getSelectionStart();
            if (primaryCode == Keyboard.KEYCODE_CANCEL) { // cancel
                hideKeyboard();
            } else if (primaryCode == Keyboard.KEYCODE_DELETE) { // 回退
                if (editable != null && editable.length() > 0) {
                    if (start > 0) {
                        editable.delete(start - 1, start);
                    }
                }
            } else if (primaryCode == -112) {
                editable.clear();
            } else if (primaryCode == -111) {

            } else if (primaryCode == Keyboard.KEYCODE_DONE) {

            } else { // 输入键盘值
                editable.insert(start, Character.toString((char) primaryCode));
                mEditText.setSelection(start + 1);
            }
        }

        @Override
        public void onText(CharSequence text) {

        }

        @Override
        public void swipeLeft() {

        }

        @Override
        public void swipeRight() {

        }

        @Override
        public void swipeDown() {

        }

        @Override
        public void swipeUp() {

        }
    };


    public void hideKeyboard() {
        int visibility = mKeyboardView.getVisibility();
        if (visibility == View.VISIBLE) {
            mKeyboardView.setVisibility(View.GONE);
        }
    }

    public void showKeyboard() {
        int visibility = mKeyboardView.getVisibility();
        if (visibility == View.GONE || visibility == View.INVISIBLE) {
            mKeyboardView.setVisibility(View.VISIBLE);
        }
    }

    public boolean isShowing() {
        return mKeyboardView.getVisibility() == View.VISIBLE;
    }
}
