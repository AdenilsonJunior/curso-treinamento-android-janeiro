package com.treinamento.adenilson.myretrofitapplication.util;

import android.app.Activity;
import android.content.Context;
import android.support.design.widget.TextInputLayout;
import android.text.TextUtils;
import android.widget.EditText;

import com.treinamento.adenilson.myretrofitapplication.R;

/**
 * Created by adenilson on 11/01/17.
 */

public class AppUtil {

    public static boolean validateRequiredField(Context context, TextInputLayout... fields) {

        boolean isValid = true;
        for (TextInputLayout field : fields) {
            EditText editText = field.getEditText();
            if (editText != null) {
                if(TextUtils.isEmpty(editText.getText())){
                    isValid = false;
                    field.setErrorEnabled(true);
                    field.setError(context.getString(R.string.message_empty_field));
                }else{
                    field.setErrorEnabled(false);
                    field.setError(null);
                }
            }else{
                throw new RuntimeException("InputTextLayout não tem um EditText.");
            }
        }

        return isValid;
    }
}
