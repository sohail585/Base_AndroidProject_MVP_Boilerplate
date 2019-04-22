package com.thbs.baseboilerplateandroid.listeners;

import android.support.design.widget.TextInputLayout;
import android.text.Editable;
import android.text.TextWatcher;

/**
 * Created by muhammed_suhail on 3/12/2018.
 */

public class TextChangeListener implements TextWatcher {

    private TextInputLayout textInputLayout;

    public TextChangeListener(TextInputLayout userIdLayout) {
        this.textInputLayout = userIdLayout;
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        if (before <= 0) {
            if (count > 0) {
                textInputLayout.setErrorEnabled(false);
            }
        }
    }

    @Override
    public void afterTextChanged(Editable s) {

    }
}
