package br.com.zontar.malllist.view;

import android.app.DialogFragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import br.com.zontar.malllist.Constants;
import br.com.zontar.malllist.R;
import br.com.zontar.malllist.adapters.ProductListAdapter;
import br.com.zontar.malllist.controller.SQLQuery;
import br.com.zontar.malllist.model.Product;
import br.com.zontar.malllist.view.dialogs.CreateItemDialog;

/**
 * Created by matheusoliveira on 01/09/2017.
 */

public class ListItemsActivity extends AppCompatActivity implements View.OnClickListener{

    private TextView mListName;
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

        initComponents();

    }

    private void initComponents () {
        mListName = (TextView) findViewById(R.id.nome_da_lista);
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
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.fab_add_item:
                DialogFragment createItemDialog = new CreateItemDialog();
                createItemDialog.setArguments(mBundle);
                createItemDialog.show(getFragmentManager(), "create_item");
                break;
        }
    }
}
