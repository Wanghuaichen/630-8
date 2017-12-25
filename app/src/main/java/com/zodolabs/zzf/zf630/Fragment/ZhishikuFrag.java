package com.zodolabs.zzf.zf630.Fragment;

import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;

import com.bill.ultimatefram.ui.UltimateFragment;
import com.zodolabs.zzf.zf630.R;

/**
 * Created by zzf on 17-12-6.
 */

public class ZhishikuFrag extends UltimateFragment {
    WebView webView;
    @Override
    protected int setContentView() {
        return R.layout.frag_zhishiku;
    }

    @Override
    protected void initView() {
        getFlexibleBar().setVisibility(View.GONE);
        webView = findViewById(R.id.webview);
    }

    @Override
    protected void initEvent(Bundle savedInstanceState) {
        webView.loadUrl("http://www.sdtdata.com/fx/fmzd/tsLibIndex");
    }
}
