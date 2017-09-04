package br.com.zontar.malllist.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import br.com.zontar.malllist.R;

/**
 * Created by matheusoliveira on 04/09/2017.
 */

public class ProductListHolder extends RecyclerView.ViewHolder {

    public TextView mQnt;
    public TextView mProductName;
    public TextView mPrice;
    public ImageButton deleteButton;
    public ImageButton editButton;

    public ProductListHolder(View itemView) {
        super(itemView);

        mQnt = (TextView) itemView.findViewById(R.id.qnt_items);
        mProductName = (TextView) itemView.findViewById(R.id.list_item_name);
        mPrice = (TextView) itemView.findViewById(R.id.list_item_value);
        editButton = (ImageButton) itemView.findViewById(R.id.edit_list_button);
        deleteButton = (ImageButton) itemView.findViewById(R.id.delete_list_button);

    }
}
