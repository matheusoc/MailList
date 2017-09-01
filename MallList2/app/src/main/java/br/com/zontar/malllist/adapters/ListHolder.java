package br.com.zontar.malllist.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import br.com.zontar.malllist.R;

/**
 * Created by matheusoliveira on 29/08/2017.
 */

public class ListHolder extends RecyclerView.ViewHolder {

    public TextView textView;
    public ImageButton deleteButton;
    public ImageButton editButton;

    public ListHolder(View itemView) {
        super(itemView);

        textView = (TextView) itemView.findViewById(R.id.list_item_id);
        editButton = (ImageButton) itemView.findViewById(R.id.edit_list_button);
        deleteButton = (ImageButton) itemView.findViewById(R.id.delete_list_button);
    }
}
