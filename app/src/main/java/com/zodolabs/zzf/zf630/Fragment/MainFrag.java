package com.zodolabs.zzf.zf630.Fragment;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bill.ultimatefram.ui.UltimateFragment;
import com.zodolabs.zzf.zf630.R;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by zzf on 17-12-6.
 */

public class MainFrag extends UltimateFragment implements Runnable, View.OnClickListener {
    LinearLayout jiance,renwu,chaxun,zidian,zhishiku,guoji,shipin,xiangmu,shezhi;
    SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
    Handler handler = new Handler();
    @Override
    protected int setContentView() {
        return R.layout.frag_main;
    }

    @Override
    protected void initView() {
        getFlexibleBar().setVisibility(View.GONE);
        jiance = findViewById(R.id.lin_jiance);
        renwu = findViewById(R.id.lin_renwu);
        chaxun = findViewById(R.id.lin_chaxun);
        zidian = findViewById(R.id.lin_zidian);
        zhishiku = findViewById(R.id.lin_zhishiku);
        guoji = findViewById(R.id.lin_guoji);
        shipin = findViewById(R.id.lin_shipin);
        xiangmu = findViewById(R.id.lin_xiangmu);
        shezhi = findViewById(R.id.lin_shezhi);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.lin_jiance:
                replaceFragment(new JianceFrag(),true);
                break;
            case R.id.lin_renwu:
                replaceFragment(new RenwuFrag(),true);
                break;
            case R.id.lin_chaxun:
                replaceFragment(new ChaxunFrag(),true);
                break;
            case R.id.lin_zidian:
                replaceFragment(new ZidianFrag(),true);
                break;
            case R.id.lin_zhishiku:
                replaceFragment(new ZhishikuFrag(),true);
                break;
            case R.id.lin_guoji:
                replaceFragment(new GuojiFrag(),true);
                break;
            case R.id.lin_shipin:
                replaceFragment(new ShipinFrag(),true);
                break;
            case R.id.lin_xiangmu:
                replaceFragment(new XiangmuFrag(),true);
                break;
            case R.id.lin_shezhi:
                replaceFragment(new ShezhiFrag(),true);
                break;
        }
    }

    @Override
    protected void initEvent(Bundle savedInstanceState) {
        ((TextView)findViewById(R.id.tv_date)).setText(format.format(new Date()));
        handler.postDelayed(this, 1000);
        jiance.setOnClickListener(this);
        renwu.setOnClickListener(this);
        chaxun.setOnClickListener(this);
        zidian.setOnClickListener(this);
        zhishiku.setOnClickListener(this);
        guoji.setOnClickListener(this);
        shipin.setOnClickListener(this);
        xiangmu.setOnClickListener(this);
        shezhi.setOnClickListener(this);
    }

    @Override
    public void run() {
        ((TextView)findViewById(R.id.tv_date)).setText(format.format(new Date()));
        handler.postDelayed(this, 1000);
    }

}
