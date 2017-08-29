package br.com.zontar.malllist;

import android.app.DialogFragment;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import br.com.zontar.malllist.controller.SQLInsert;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private FloatingActionButton mFab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mFab = (FloatingActionButton) findViewById(R.id.fab);

        mFab.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.fab:
                DialogFragment dialogFragment = new CreateListDialog();
                dialogFragment.show(getFragmentManager(), "list");
                break;
        }

    }
}
