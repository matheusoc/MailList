package br.com.zontar.malllist.view;

import android.app.DialogFragment;
import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;

import br.com.zontar.malllist.R;
import br.com.zontar.malllist.adapters.ListAdapter;
import br.com.zontar.malllist.controller.SQLQuery;
import br.com.zontar.malllist.model.List;
import br.com.zontar.malllist.utils.CalendarHelper;
import br.com.zontar.malllist.view.dialogs.CreateListDialog;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private FloatingActionButton mFab;
    private RecyclerView mListItems;

    private SQLQuery mSQlQuery;

    private ArrayList<List> mList;
    private ListAdapter mAdapter;

    private LinearLayout mLayoutEmpty;
    private ImageButton mAddListButton;

    private Spinner mMonthSpinner;

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

        mMonthSpinner = (Spinner) findViewById(R.id.month_selector);
        mMonthSpinner.setAdapter(getMonthAdapter());

        checkListSize();

        handleIntent(getIntent());

        Toast.makeText(this, String.valueOf(CalendarHelper.getMonthName(this, null)),
                Toast.LENGTH_SHORT).show();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_search, menu);

        SearchManager searchManager =
                (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        SearchView searchView =
                (SearchView) menu.findItem(R.id.search).getActionView();
        searchView.setSearchableInfo(
                searchManager.getSearchableInfo(getComponentName()));

        return true;
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        handleIntent(getIntent());
    }

    private ArrayAdapter<String> getMonthAdapter () {

        ArrayList<String> arrayList = new ArrayList<>();
        for (int i = 0; i <12; i++) {
            arrayList.add(CalendarHelper.getMonthName(this, i));
        }

        ArrayAdapter adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_dropdown_item,arrayList);

        return adapter;
    }

    private void handleIntent(Intent intent) {

        if (Intent.ACTION_SEARCH.equals(intent.getAction())) {
            String query = intent.getStringExtra(SearchManager.QUERY);
        }
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
