package com.example.sa.Proxy;

import org.json.JSONException;

public class WalletProxy implements WalletService {
    private WalletService walletService = new WalletServiceImpl();

    @Override
    public void getWalletInfo() {
        if(this.userValidate()){
            this.walletService.getWalletInfo();
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

    public boolean userValidate(){
        //TODO: get User login statement from singleton Pattern
        return true;
    }
}
