package br.com.zontar.malllist;

import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import br.com.zontar.malllist.controller.SQLInsert;

/**
 * Created by matheusoliveira on 29/08/2017.
 */

public class CreateListDialog extends DialogFragment implements View.OnClickListener {

    private Button mConfirmButton;
    private Button mCancelButton;

    private EditText mNameList;

    private Dialog mDialog;

    public CreateListDialog () {

    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Context context = getActivity().getApplicationContext();
        return createDialog(context);
    }

    public Dialog createDialog(final Context context) {
        mDialog = new Dialog(getActivity());

        LayoutInflater inflater = (LayoutInflater)
                context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        View layout = inflater.inflate(R.layout.create_list_dialog, null);

        mNameList = (EditText) layout.findViewById(R.id.nameList);

        mConfirmButton = (Button) layout.findViewById(R.id.okButton);
        mCancelButton = (Button) layout.findViewById(R.id.cancelButton);

        mConfirmButton.setOnClickListener(this);
        mCancelButton.setOnClickListener(this);


        mDialog.setContentView(layout);

        return mDialog;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.okButton:
                insertList();
                break;

            case R.id.cancelButton:
                mDialog.cancel();
                break;
        }
    }

    private void insertList () {

        String nameList = mNameList.getText().toString();
        SQLInsert sql = new SQLInsert(getActivity().getApplicationContext());

        sql.insertList(nameList);

    }
}
