package br.com.zontar.malllist;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import br.com.zontar.malllist.controller.SQLInsert;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SQLInsert sql = new SQLInsert(this);
        sql.insertList();
    }
}
