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

import br.com.zontar.malllist.R;
import br.com.zontar.malllist.controller.SQLQuery;
import br.com.zontar.malllist.model.List;
import br.com.zontar.malllist.view.MainActivity;

/**
 * Created by matheusoliveira on 29/08/2017.
 */

public class CreateListDialog extends DialogFragment implements View.OnClickListener {

    private Button mConfirmButton;
    private Button mCancelButton;

    private EditText mNameList;

    private Dialog mDialog;

    private MainActivity mActivity;

    public CreateListDialog () {

    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Context context = getActivity().getApplicationContext();
        mActivity = (MainActivity) getActivity();
        return createDialog(context);
    }

    public Dialog createDialog(final Context context) {
        mDialog = new Dialog(getActivity());

        LayoutInflater inflater = (LayoutInflater)
                context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        View layout = inflater.inflate(R.layout.create_list_dialog, null);

        mNameList = (EditText) layout.findViewById(R.id.nameList);
        mNameList.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    mDialog.getWindow().setSoftInputMode
                            (WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
                }
            }
        });

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
                List list = insertList();
                if(list != null) {
                    mActivity.refreshDataAdd(list);
                    dismiss();
                } else {
                    Toast.makeText(getActivity().getApplicationContext(),
                            "Erro ao criar lista", Toast.LENGTH_SHORT).show();
                }
                break;

            case R.id.cancelButton:
                mDialog.cancel();
                break;
        }
    }

    private List insertList () {

        String nameList = mNameList.getText().toString();
        if (!nameList.equals("")) {
            SQLQuery sql = new SQLQuery(getActivity().getApplicationContext());

            int id = (int) sql.insertList(nameList);
            if (id != -1) {
                List list = new List();
                list.setIdList(id);
                list.setNameList(nameList);
                return list;
            }
        } else {
            Toast.makeText(getActivity().getApplicationContext(),
                    "Campo de nome não preenchido", Toast.LENGTH_SHORT).show();
        }

        return null;
    }
}
