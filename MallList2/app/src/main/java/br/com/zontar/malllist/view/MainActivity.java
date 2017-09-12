package br.com.zontar.malllist.view;

import android.app.DialogFragment;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import java.util.ArrayList;

import br.com.zontar.malllist.R;
import br.com.zontar.malllist.adapters.ListAdapter;
import br.com.zontar.malllist.controller.SQLQuery;
import br.com.zontar.malllist.model.List;
import br.com.zontar.malllist.view.dialogs.CreateListDialog;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private FloatingActionButton mFab;
    private RecyclerView mListItems;

    private SQLQuery mSQlQuery;

    private ArrayList<List> mList;
    private ListAdapter mAdapter;

    private LinearLayout mLayoutEmpty;
    private ImageButton mAddListButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mSQlQuery = SQLQuery.getInstance(this);

        mFab = (FloatingActionButton) findViewById(R.id.fab);
        mFab.setOnClickListener(this);

        mListItems = (RecyclerView) findViewById(R.id.list_names_id);
        mListItems.setHasFixedSize(true);
        mListItems.setLayoutManager(new LinearLayoutManager(getApplicationContext()));

        mList = mSQlQuery.getList();

        mAdapter = new ListAdapter(this, mList);

        mListItems.setAdapter(mAdapter);

        mLayoutEmpty = (LinearLayout) findViewById(R.id.list_empty_layout);
        mAddListButton = (ImageButton) findViewById(R.id.add_list_image_button);
        mAddListButton.setOnClickListener(this);

        checkListSize();

    }

    private void checkListSize () {
        if (mList.size() == 0) {
            mListItems.setVisibility(View.GONE);
            mFab.setVisibility(View.GONE);
            mLayoutEmpty.setVisibility(View.VISIBLE);
        } else {
            mListItems.setVisibility(View.VISIBLE);
            mFab.setVisibility(View.VISIBLE);
            mLayoutEmpty.setVisibility(View.GONE);
        }
    }

    public void refreshDataAdd (List list) {
        mList.add(list);
        refreshData();
    }

    public void refreshDataUpdate (List list, int position) {
        mList.remove(position);
        mList.add(list);
        refreshData();
    }

    public void refreshData () {
        mAdapter.notifyDataSetChanged();
        if (!mFab.isShown()) {
            mFab.show();
        }
        checkListSize();
    }

    @Override
    public void onClick (View v) {

        switch (v.getId()) {
            case R.id.add_list_image_button:
            case R.id.fab:
                DialogFragment dialogFragment = new CreateListDialog();
                dialogFragment.show(getFragmentManager(), "List");
                break;
        }

    }

}
