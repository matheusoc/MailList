package br.com.zontar.malllist.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import br.com.zontar.malllist.R;
import br.com.zontar.malllist.model.Product;

/**
 * Created by matheusoliveira on 04/09/2017.
 */

public class ProductListAdapter extends RecyclerView.Adapter{

    private Context mContext;
    private ArrayList<Product> mList;

    public ProductListAdapter(Context mContext, ArrayList<Product> mList) {
        this.mContext = mContext;
        this.mList = mList;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(mContext)
                .inflate(R.layout.list_show_items, parent, false);

        ProductListHolder holder = new ProductListHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        ProductListHolder productHolder = (ProductListHolder) holder;
        Product product = mList.get(position);

        productHolder.mProductName.setText(product.getProductName());
        productHolder.mQnt.setText(String.valueOf(product.getProductQnt()));
        productHolder.mPrice.setText(String.valueOf(product.getProductPrice()));

    }

    @Override
    public int getItemCount() {
        return mList.size();
    }
}
