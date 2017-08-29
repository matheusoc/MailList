package br.com.zontar.malllist;

import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

/**
 * Created by matheusoliveira on 29/08/2017.
 */

public class CreateListDialog extends DialogFragment {

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        return getDialog();
    }

    @Override
    public Dialog getDialog() {
        Context context = getActivity().getApplicationContext();
        Dialog dialog = new Dialog(getActivity().getApplicationContext());

        LayoutInflater inflater = (LayoutInflater)
                context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        View layout = inflater.inflate(R.layout.create_list_dialog, null);

        dialog.setContentView(layout);

        return dialog;
    }
}
