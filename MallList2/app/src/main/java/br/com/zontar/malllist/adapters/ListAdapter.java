package br.com.zontar.malllist.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import br.com.zontar.malllist.MainActivity;
import br.com.zontar.malllist.R;
import br.com.zontar.malllist.controller.SQLQuery;
import br.com.zontar.malllist.model.List;

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
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

}
