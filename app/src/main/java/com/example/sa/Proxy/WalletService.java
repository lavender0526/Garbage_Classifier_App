package com.example.sa.Proxy;

import org.json.JSONException;
import org.json.JSONObject;

public interface WalletService {
    public JSONObject getWalletInfo();
    public Boolean createBankAcct(String accountCode,String bankType) throws JSONException;
    public JSONObject updateWalletInfo(String username);
}
