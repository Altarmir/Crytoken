package com.example.android.crytoken;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {


    // TextView displayed when list is empty
    private TextView mEmptyStateTextView;

    public static final String LOG_TAG = MainActivity.class.getName();

    //Adapter for the list of currency comparison
    private static CurrencyBoardAdapter mAdapter;

    // API from cryptocompare site
    private static final String CRYPTOCOMPARE_REQUEST_URL =" https://min-api.cryptocompare.com/data/pricemulti?fsyms=ETH,BTC&tsyms=NGN,CAD,CNY,BND,EUR,USD,AUD,CHF,DKK,GHS,HKD,INR,JPY,KZT,NAD,NZD,OMR,RUB,SAR,SGD";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ListView currencyListView = (ListView) findViewById(R.id.list);

        mEmptyStateTextView = (TextView) findViewById(R.id.empty_view);
        currencyListView.setEmptyView(mEmptyStateTextView);

        mAdapter = new CurrencyBoardAdapter(this, new ArrayList<CurrencyBoard>());

        currencyListView.setAdapter(mAdapter);

        currencyListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {

                CurrencyBoard currencyboardItem = mAdapter.getItem(position);

                Intent intent = new Intent(getApplicationContext(), CurrencyConverter.class);

                Bundle b = new Bundle();

                b.putString("currencySymbol", currencyboardItem.getBaseCurrency());
                b.putDouble("ethEquivalent", currencyboardItem.getEthEquivalent());
                b.putDouble("btcEquivalent", currencyboardItem.getBtcEquivalent());

                intent.putExtra("dashBundle", b);

                // Send the intent to launch a new activity
                startActivity(intent);
            }
        });

        ConnectivityManager connMgr = (ConnectivityManager)
                getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();



        if (networkInfo != null && networkInfo.isConnected()) {

            // Execute the AsyncTask to fetch the API data
            CryptoCurrencyAsyncTask task = new CryptoCurrencyAsyncTask();
            task.execute(CRYPTOCOMPARE_REQUEST_URL);

        } else {
            
            View loadingIndicator = findViewById(R.id.loading_indicator);
            loadingIndicator.setVisibility(View.GONE);

            // Display "No Internet Connection when there is no internet connection"
            mEmptyStateTextView.setText(R.string.no_internet_connection);

        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    private class CryptoCurrencyAsyncTask extends AsyncTask<String, Void, List<CurrencyBoard>> {
        

        @Override
        protected List<CurrencyBoard> doInBackground(String... urls) {
            
            if (urls.length < 1 || urls[0] == null) {
                return null;
            }

            List<CurrencyBoard> result = CurrencyQuery.fetchDashboardData(urls[0]);
            return result;

        }

        @Override
        protected void onPostExecute(List<CurrencyBoard> data) {

            // Hide loading indicator because the data has been loaded
            View loadingIndicator = findViewById(R.id.loading_indicator);
            loadingIndicator.setVisibility(View.GONE);

            // Display "No data found for this currency)
            mEmptyStateTextView.setText(R.string.no_currency);

            mAdapter.clear();
            
            if (data != null && !data.isEmpty()) {
                mAdapter.addAll(data);
            }

        }
    }

}
