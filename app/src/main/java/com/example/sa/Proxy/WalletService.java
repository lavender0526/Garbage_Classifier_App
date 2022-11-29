package com.example.sa.Proxy;

import org.json.JSONException;

public interface WalletService {
    public void getWalletInfo();
    public Boolean createBankAcct(String accountCode,String bankType) throws JSONException;
}
