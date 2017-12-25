package com.zodolabs.zzf.zf630;

import android.os.Bundle;

import com.bill.ultimatefram.ui.UltimateActivity;
import com.zodolabs.zzf.zf630.Fragment.LoginFrag;

public class LoginActivity extends UltimateActivity {


    @Override
    protected int setContentView() {
        return 0;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initEvent(Bundle savedInstanceState) {
        startFragment(new LoginFrag());
    }

    @Override
    protected boolean canSetSystemBarOnFragment() {
        return false;
    }
}
