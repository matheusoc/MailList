package br.com.zontar.malllist.view.dialogs;

import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import br.com.zontar.malllist.Constants;
import br.com.zontar.malllist.R;
import br.com.zontar.malllist.controller.SQLQuery;
import br.com.zontar.malllist.view.ListItemsActivity;

/**
 * Created by matheusoliveira on 04/09/2017.
 */

public class CreateItemDialog extends DialogFragment {

    private ListItemsActivity mActivity;

    private Dialog mDialog;

    private Bundle mBundle;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Context context = getActivity().getApplicationContext();
        mActivity = (ListItemsActivity) getActivity();
        mBundle = getArguments();
        return createDialog(context);
    }

    private Dialog createDialog (final Context context) {

        mDialog = new Dialog(getActivity());

        LayoutInflater inflater = (LayoutInflater)
                context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        View layout = inflater.inflate(R.layout.create_list_item_dialog, null);

        final EditText nameItem = (EditText) layout.findViewById(R.id.name_item);
        final EditText qntItem = (EditText) layout.findViewById(R.id.qnt_item);
        final EditText valueItem = (EditText) layout.findViewById(R.id.price_item);

        Button confirmButton = (Button) layout.findViewById(R.id.okButton);
        confirmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SQLQuery sqlQuery = SQLQuery.getInstance(context);
                long id = sqlQuery.addItemToList(
                        mBundle.getInt(Constants.LIST_ID),
                        nameItem.getText().toString(),
                        qntItem.getText().toString(),
                        valueItem.getText().toString());

                Toast.makeText(context, String.valueOf(id), Toast.LENGTH_SHORT).show();
                mDialog.dismiss();
            }
        });

        Button cancelButton = (Button) layout.findViewById(R.id.cancelButton);
        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDialog.dismiss();
            }
        });


        mDialog.setContentView(layout);

        WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams();
        layoutParams.copyFrom(mDialog.getWindow().getAttributes());
        layoutParams.width = WindowManager.LayoutParams.MATCH_PARENT;

        mDialog.getWindow().setAttributes(layoutParams);

        return mDialog;
    }

}
