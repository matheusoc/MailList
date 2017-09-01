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
import br.com.zontar.malllist.model.List;
import br.com.zontar.malllist.view.MainActivity;

/**
 * Created by matheusoliveira on 01/09/2017.
 */

public class EditListDialog extends DialogFragment implements View.OnClickListener{

    private Button mConfirmButton;
    private Button mCancelButton;

    private EditText mNameList;

    private Dialog mDialog;

    private MainActivity mActivity;

    private List mList;
    private int mPosition;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Context context = getActivity().getApplicationContext();
        mActivity = (MainActivity) getActivity();

        String nameList = getArguments().getString(Constants.LIST_NAME);
        int idList = getArguments().getInt(Constants.LIST_ID);
        mPosition = getArguments().getInt(Constants.POSITION);

        mList = new List(idList, nameList);

        return createDialog(context);
    }

    public Dialog createDialog (Context context) {

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

    private List updateList () {
        String newNameList = mNameList.getText().toString();

        if (!newNameList.equals("")) {
            SQLQuery query = new SQLQuery(getActivity().getApplicationContext());
            query.updateList(mList, newNameList);
            mList.setNameList(newNameList);
            return mList;
        } else {
            Toast.makeText(getActivity().getApplicationContext(),
                    "Campo de nome não preenchido", Toast.LENGTH_SHORT).show();
        }

        return null;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.okButton:
                List list = updateList();
                if(list != null) {
                    mActivity.refreshDataUpdate(list, mPosition);
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
}
