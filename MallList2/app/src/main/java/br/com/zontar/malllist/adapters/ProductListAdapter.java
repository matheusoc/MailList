package br.com.zontar.malllist.adapters;

import android.app.DialogFragment;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.text.NumberFormat;
import java.util.ArrayList;

import br.com.zontar.malllist.Constants;
import br.com.zontar.malllist.R;
import br.com.zontar.malllist.controller.SQLQuery;
import br.com.zontar.malllist.model.Product;
import br.com.zontar.malllist.view.ListItemsActivity;
import br.com.zontar.malllist.view.ShowItemActivity;
import br.com.zontar.malllist.view.dialogs.EditProductDialog;

/**
 * Created by matheusoliveira on 04/09/2017.
 */

public class ProductListAdapter extends RecyclerView.Adapter{

    private Context mContext;
    private ArrayList<Product> mList;
    private ListItemsActivity mActivity;

    public ProductListAdapter(ListItemsActivity mContext, ArrayList<Product> mList) {
        this.mContext = mContext;
        this.mList = mList;
        this.mActivity = mContext;
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

        final ProductListHolder productHolder = (ProductListHolder) holder;
        final Product product = mList.get(position);

        productHolder.mProductName.setText(product.getProductName());
        String formattedValue = NumberFormat.getCurrencyInstance().
                format(product.getProductPrice());
        productHolder.mQnt.setText(String.valueOf(product.getProductQnt()));
        productHolder.mPrice.setText(formattedValue);

        productHolder.deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SQLQuery query = SQLQuery.getInstance(mContext);
                query.deleteProduct(product.getIdProduct());

                mList.remove(productHolder.getAdapterPosition());
                mActivity.refreshList();
            }
        });

        productHolder.editButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment editProduct = new EditProductDialog();
                Bundle bundle = new Bundle();
                bundle.putInt(Constants.PRODUCT_ID, product.getIdProduct());
                bundle.putString(Constants.PRODUCT_NAME, product.getProductName());
                bundle.putInt(Constants.PRODUCT_QNT, product.getProductQnt());
                bundle.putFloat(Constants.PRODUCT_VALUE, product.getProductPrice());
                bundle.putInt(Constants.POSITION, productHolder.getAdapterPosition());
                editProduct.setArguments(bundle);
                editProduct.show(mActivity.getFragmentManager(), "edit");
            }
        });

        productHolder.mProductName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putString(Constants.PRODUCT_NAME, product.getProductName());
                bundle.putString(Constants.PRODUCT_QNT, String.valueOf(product.getProductQnt()));
                bundle.putFloat(Constants.PRODUCT_VALUE, product.getProductPrice());

                Intent intent = new Intent(mContext, ShowItemActivity.class);
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
