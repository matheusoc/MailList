package br.com.zontar.malllist.view.dialogs;

import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.text.NumberFormat;

import br.com.zontar.malllist.Constants;
import br.com.zontar.malllist.R;
import br.com.zontar.malllist.controller.SQLQuery;
import br.com.zontar.malllist.model.Product;
import br.com.zontar.malllist.view.ListItemsActivity;

/**
 * Created by matheusoliveira on 04/09/2017.
 */

public class CreateProductDialog extends DialogFragment {

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

    private Dialog createDialog(final Context context) {

        mDialog = new Dialog(getActivity());

        LayoutInflater inflater = (LayoutInflater)
                context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        View layout = inflater.inflate(R.layout.create_list_item_dialog, null);

        final EditText nameItem = (EditText) layout.findViewById(R.id.name_item);
        final EditText qntItem = (EditText) layout.findViewById(R.id.qnt_item);
        final EditText valueItem = (EditText) layout.findViewById(R.id.price_item);

        nameItem.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    mDialog.getWindow().setSoftInputMode
                            (WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
                }
            }
        });

        valueItem.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            private String current = "";
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!s.toString().equals(current)) {
                    valueItem.removeTextChangedListener(this);
                    String cleanString = s.toString().replaceAll("[R$,.]", "");

                    double parsed = Double.parseDouble(cleanString);
                    String formatted = NumberFormat.getCurrencyInstance().format((parsed / 100));

                    current = formatted;
                    valueItem.setText(formatted);
                    valueItem.setSelection(formatted.length());

                    valueItem.addTextChangedListener(this);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        Button confirmButton = (Button) layout.findViewById(R.id.okButton);
        confirmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!nameItem.getText().toString().equals("")) {
                    SQLQuery sqlQuery = SQLQuery.getInstance(context);
                    String value = valueItem.getText().toString().replaceAll("[R$]", "");
                    value = value.replace(",", ".");
                    String quant = qntItem.getText().toString();
                    if (quant.equals("")) {
                        quant = "1";
                    }
                    long id = sqlQuery.addItemToList(
                            mBundle.getInt(Constants.LIST_ID),
                            nameItem.getText().toString(),
                            quant,
                            value);

                    if (id != -1) {
                        String name = nameItem.getText().toString();
                        int qnt = Integer.parseInt(quant);
                        float price;
                        if (value.equals("")) {
                            price = 0;
                        } else {
                            price = Float.parseFloat(value);
                        }
                        Product product = new Product(((int) id), name, qnt, price);
                        mActivity.refreshAddList(product);
                    }

                    mDialog.dismiss();
                } else {
                    Toast.makeText(context, "Preencha o campo nome", Toast.LENGTH_SHORT).show();
                }



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
