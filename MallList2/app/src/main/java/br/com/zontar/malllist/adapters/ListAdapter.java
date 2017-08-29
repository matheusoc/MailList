package br.com.zontar.malllist.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.lang.reflect.Array;
import java.util.ArrayList;

import br.com.zontar.malllist.R;
import br.com.zontar.malllist.model.List;

/**
 * Created by MatheusdeOliveiraCam on 29/08/2017.
 */

public class ListAdapter extends BaseAdapter {

    private ArrayList<List> mList;
    private Context mContext;

    public ListAdapter (Context context, ArrayList<List> list) {
        this.mContext = context;
        this.mList = list;
    }


    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public Object getItem(int position) {
        return mList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        List item = mList.get(position);

        LayoutInflater inflater = (LayoutInflater)
                mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View layout = inflater.inflate(R.layout.create_list, null);

        TextView textView = (TextView) layout.findViewById(R.id.list_item_id);
        textView.setText(item.getNameList());

        return null;
    }
}
