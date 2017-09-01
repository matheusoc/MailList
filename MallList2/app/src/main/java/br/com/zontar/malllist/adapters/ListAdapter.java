package br.com.zontar.malllist.adapters;

import android.app.DialogFragment;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import br.com.zontar.malllist.Constants;
import br.com.zontar.malllist.view.ListItemsActivity;
import br.com.zontar.malllist.view.MainActivity;
import br.com.zontar.malllist.R;
import br.com.zontar.malllist.controller.SQLQuery;
import br.com.zontar.malllist.model.List;
import br.com.zontar.malllist.view.dialogs.CreateListDialog;
import br.com.zontar.malllist.view.dialogs.EditListDialog;

/**
 * Created by MatheusdeOliveiraCam on 29/08/2017.
 */

public class ListAdapter extends RecyclerView.Adapter {

    private ArrayList<List> mList;
    private Context mContext;
    private MainActivity mActivity;

    public ListAdapter (MainActivity context, ArrayList<List> list) {
        this.mContext = context;
        this.mList = list;
        this.mActivity = context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(mContext)
                .inflate(R.layout.create_list, parent, false);

        ListHolder holder = new ListHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        ListHolder listHolder = (ListHolder) holder;
        String name = mList.get(position).getNameList();

        listHolder.textView.setText(name);
        listHolder.deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                SQLQuery query = SQLQuery.getInstance(mContext);
                query.deleteList(mList.get(position));

                mList.remove(position);

                mActivity.refreshData();
            }
        });

        listHolder.editButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment dialogFragment = new EditListDialog();
                Bundle bundle = new Bundle();
                bundle.putInt(Constants.LIST_ID, mList.get(position).getIdList());
                bundle.putString(Constants.LIST_NAME, mList.get(position).getNameList());
                bundle.putInt(Constants.POSITION, position);
                dialogFragment.setArguments(bundle);
                dialogFragment.show(mActivity.getFragmentManager(), "List");
            }
        });

        listHolder.textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putInt(Constants.LIST_ID, mList.get(position).getIdList());

                Intent intent = new Intent(mContext, ListItemsActivity.class);
                intent.putExtras(bundle);
                mContext.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

}
