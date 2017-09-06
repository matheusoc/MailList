package br.com.zontar.malllist.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import java.text.NumberFormat;

import br.com.zontar.malllist.Constants;
import br.com.zontar.malllist.R;

/**
 * Created by matheusoliveira on 06/09/2017.
 */

public class ShowItemActivity extends AppCompatActivity{

    private Bundle mBundle;

    private TextView mProdutoName;
    private TextView mProdutoQnt;
    private TextView mProdutoPrice;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_item);

        mBundle = getIntent().getExtras();

        mProdutoName = (TextView) findViewById(R.id.nome);
        mProdutoQnt = (TextView) findViewById(R.id.qnt_produto);
        mProdutoPrice = (TextView) findViewById(R.id.price_produto);

        String nome = mBundle.getString(Constants.PRODUCT_NAME);
        String qnt = mBundle.getString(Constants.PRODUCT_QNT);
        float price = mBundle.getFloat(Constants.PRODUCT_VALUE);

        String formattedValue = NumberFormat.getCurrencyInstance().
                format(price);


        mProdutoName.setText(nome);
        mProdutoQnt.setText(qnt);
        mProdutoPrice.setText(formattedValue);
    }
}
