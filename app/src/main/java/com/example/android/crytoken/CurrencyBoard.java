package com.example.android.crytoken;

public class CurrencyBoard {

    // Base currency
    private String mBaseCurrency;

    // Ethereum equivalent
    private double mEthEquivalent;

    // Bitcoin equivalent
    private double mBtcEquivalent;


    public CurrencyBoard(String baseCurrency, double ethEquivalent, double btcEquivalent){

        mBaseCurrency = baseCurrency;
        mEthEquivalent = ethEquivalent;
        mBtcEquivalent = btcEquivalent;

    }


    // method to get the base currency
    public String getBaseCurrency(){

        return mBaseCurrency;
    }

    // method to get the Ethereum equivalent
    public double getEthEquivalent(){

        return mEthEquivalent;
    }


    // method to get the Bitcoin equivalent
    public  double getBtcEquivalent(){

        return mBtcEquivalent;
    }



}
