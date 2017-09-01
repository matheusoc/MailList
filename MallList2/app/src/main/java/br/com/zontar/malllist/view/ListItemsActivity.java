package br.com.zontar.malllist.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import br.com.zontar.malllist.Constants;
import br.com.zontar.malllist.R;

/**
 * Created by matheusoliveira on 01/09/2017.
 */

public class ListItemsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_list_item_name);

        Bundle bundle = getIntent().getExtras();
        Toast.makeText(this, String.valueOf(bundle.getInt(Constants.LIST_ID)), Toast.LENGTH_SHORT).show();

    }
}
