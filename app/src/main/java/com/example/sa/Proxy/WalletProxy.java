package com.example.sa.Proxy;

import android.util.Log;

import com.example.sa.store.UserStore;

import org.json.JSONException;
import org.json.JSONObject;

public class WalletProxy implements WalletService {
    private WalletService walletService = new WalletServiceImpl();

    @Override
    public JSONObject getWalletInfo() {
        if(this.userValidate()){
            return this.walletService.getWalletInfo();
        }else{
            return null;
        }
    }

    @Override
    public Boolean createBankAcct(String accountCode,String bankType) throws JSONException {
        if(this.userValidate()){
            return this.walletService.createBankAcct(accountCode,bankType);
        }else{
            return false;
        }
    }

    @Override
    public JSONObject updateWalletInfo(String username) {
        if(this.userValidate()){
            return this.walletService.updateWalletInfo(username);
        }else{
          return null;
        }
    }

    public boolean userValidate(){
        //TODO: get User login statement from singleton Pattern
        String username = UserStore.getInstance().getUsername();
        if(username != null){
            Log.d("INFO",username+" is valid");
            return true;
        }else{
            Log.d("INFO","User is invalid");
            return false;
        }

    }
}
