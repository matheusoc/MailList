package br.com.zontar.malllist.view;

import android.app.DialogFragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import java.text.NumberFormat;
import java.util.ArrayList;

import br.com.zontar.malllist.Constants;
import br.com.zontar.malllist.R;
import br.com.zontar.malllist.adapters.ProductListAdapter;
import br.com.zontar.malllist.controller.SQLQuery;
import br.com.zontar.malllist.model.Product;
import br.com.zontar.malllist.view.dialogs.CreateProductDialog;

/**
 * Created by matheusoliveira on 01/09/2017.
 */

public class ListItemsActivity extends AppCompatActivity implements View.OnClickListener{

    private TextView mListName;
    private TextView mTotalPriceText;
    private RecyclerView mItemsList;
    private FloatingActionButton mFabCreateItem;
    private Bundle mBundle;
    private ProductListAdapter mAdapter;
    private ArrayList<Product> mProductList;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_list_item_name);

        mBundle = getIntent().getExtras();

        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        initComponents();

    }

    private void initComponents () {
        mListName = (TextView) findViewById(R.id.nome_da_lista);
        mTotalPriceText = (TextView) findViewById(R.id.total_sum_products);
        mItemsList = (RecyclerView) findViewById(R.id.items_list);
        mFabCreateItem = (FloatingActionButton) findViewById(R.id.fab_add_item);

        mListName.setText(mBundle.getString(Constants.LIST_NAME));

        mFabCreateItem.setOnClickListener(this);

        mItemsList.setHasFixedSize(true);
        mItemsList.setLayoutManager(new LinearLayoutManager(getApplicationContext()));

        SQLQuery sqlQuery = SQLQuery.getInstance(this);
        mProductList = sqlQuery.getProducts(mBundle.getInt(Constants.LIST_ID));

        mAdapter = new ProductListAdapter(this, mProductList);
        mItemsList.setAdapter(mAdapter);

        setTotalPrice();
    }

    public void refreshUpdateList (Product product, int position) {
        mProductList.remove(position);
        mProductList.add(product);
        refreshList();
    }

    public void refreshAddList(Product product) {
        mProductList.add(product);
        refreshList();
    }

    public void refreshList () {
        mAdapter.notifyDataSetChanged();
        setTotalPrice();
    }

    public void setTotalPrice () {
        float price = 0;
        for (Product product : mProductList) {
            price = price + product.getProductQnt() * product.getProductPrice();
        }
        String formattedPrice = NumberFormat.getCurrencyInstance().
                format(price);
        mTotalPriceText.setText(formattedPrice);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.fab_add_item:
                DialogFragment createItemDialog = new CreateProductDialog();
                createItemDialog.setArguments(mBundle);
                createItemDialog.show(getFragmentManager(), "create_item");
                break;
        }
    }

}
