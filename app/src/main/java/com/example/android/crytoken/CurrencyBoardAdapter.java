package com.example.android.crytoken;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class CurrencyBoardAdapter extends ArrayAdapter<CurrencyBoard> {

    private static final String LOG_TAG = CurrencyBoardAdapter.class.getSimpleName();


    public CurrencyBoardAdapter(Activity context, ArrayList<CurrencyBoard> dashboard) {

        super(context, 0, dashboard);

    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Check if the existing view is being reused, otherwise inflate the view
        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.currency_board, parent, false);
        }

        CurrencyBoard currentDashboard = getItem(position);

        // Find the text view in the currency_board.xml layout with the ID base_currency
        TextView baseCurrency = (TextView) listItemView.findViewById(R.id.base_currency);

        // display the base currency of the particular item
        baseCurrency.setText(currentDashboard.getBaseCurrency());


        // Find the text view in the currency_board.xml layout with the ID eth_equivalent
        TextView ethereumEquivalent = (TextView) listItemView.findViewById(R.id.eth_equivalent);

        // display the base currency of the particular dashboard item
        // The double value had to be converted to a string instance before being used as parameter to setText
        ethereumEquivalent.setText(Double.toString(currentDashboard.getEthEquivalent()));


        // Find the text view in the currency_board.xml layout with the ID btc_equivalent
        TextView bitcoinEquivalent = (TextView) listItemView.findViewById(R.id.btc_equivalent);

        // display the base currency of the particular dashboard item
        // The double value had to be converted to a string instance before being used as parameter to setText
        bitcoinEquivalent.setText(Double.toString(currentDashboard.getBtcEquivalent()));

        return  listItemView;
    }

}
