package br.com.cast.turmaformacao.controledeestoque.util;

import android.widget.EditText;

/**
 * Created by Administrador on 25/09/2015.
 */
public final class FormerHelper {

    private FormerHelper(){
        super();
    }

    public static void clearFields(EditText... editTexts){

        for(EditText editText : editTexts){
            editText.setText("");
        }
    }

    public static boolean validateRequired(String requiredMessage, EditText... editTexts) {

        boolean hasRequired = false;

        for (EditText editText : editTexts) {

            String textValue = editText.getText().toString();
            if (textValue.trim().isEmpty()) {
                editText.setError(requiredMessage);
                hasRequired = true;
            }
        }

        return hasRequired;
    }


}
