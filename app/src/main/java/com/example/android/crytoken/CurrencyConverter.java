package com.example.android.crytoken;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class CurrencyConverter extends AppCompatActivity {

    // Ethereum equivalent variable
    private double ethereum;

    // Bitcoin equivalent variable
    private double bitcoin;

    private static final String LOG_TAG = "ConverterActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.currency_converter);

        Intent intent = getIntent();

        // then we extract the bundle containing our data
        Bundle b = intent.getBundleExtra("dashBundle");

        //we extract the symbols for the currency
        String symbol = b.getString("currencySymbol");

        //we extract the Ethereum equivalent
        ethereum = b.getDouble("ethEquivalent");

        //we extract the Bitcoin equivalent
        bitcoin = b.getDouble("btcEquivalent");

        TextView mBaseSymbol = (TextView) findViewById(R.id.base_symbol);
        mBaseSymbol.setText(symbol);


    }

    /**
     * Converts inputted amount from the user to its ETH and BTC equivalent
     */
    public void convertAmount (View view){

        // variable for edit text view
        EditText mInputCurrency = (EditText) findViewById(R.id.retrieve_amount);


        // input amount by user
        double convertingAmount = ParseDouble(String.valueOf(mInputCurrency.getText()));

        double amountToEth = convertingAmount / ethereum;

        double amountToBtc = convertingAmount / bitcoin;



        // variable for eth text view
        TextView mBasetoEth = (TextView) findViewById(R.id.base_to_eth);
        mBasetoEth.setText("ETH " + Double.toString(amountToEth));


        // variable for btc text view
        TextView mBasetoBtc = (TextView) findViewById(R.id.base_to_btc);
        mBasetoBtc.setText("BTC " + Double.toString(amountToBtc) );



    }


    private  double ParseDouble(String strNumber) {
        if (strNumber != null && strNumber.length() > 0) {
            try {
                return Double.parseDouble(strNumber);
            } catch(Exception e) {
                return -1;
            }
        }
        else return 0;
    }


}





