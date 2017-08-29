package br.com.zontar.malllist;

import android.app.DialogFragment;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.ArrayList;

import br.com.zontar.malllist.adapters.ListAdapter;
import br.com.zontar.malllist.controller.SQLQuery;
import br.com.zontar.malllist.model.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private FloatingActionButton mFab;
    private RecyclerView mListItems;

    private SQLQuery mSQlQuery;

    private ArrayList<List> mList;
    private ListAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mSQlQuery = SQLQuery.getInstance(this);

        mFab = (FloatingActionButton) findViewById(R.id.fab);
        mFab.setOnClickListener(this);

        mListItems = (RecyclerView) findViewById(R.id.list_names_id);

        mList = mSQlQuery.getList();

        mAdapter = new ListAdapter(this, mList);

        mListItems.setAdapter(mAdapter);

    }

    public void refreshData (List list) {
        mList.add(list);
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.fab:
                DialogFragment dialogFragment = new CreateListDialog();
                dialogFragment.show(getFragmentManager(), "List");
                break;
        }

    }

}
