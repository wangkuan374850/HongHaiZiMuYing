package com.example.wangkuan.honghaizimuying.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.example.wangkuan.honghaizimuying.R;

/**
 * Created by wangkuan on 2016/11/16.
 */
public class XiangQing_GuiGeCanShu extends Fragment {
    private String url="http://product.suning.com/pds-web/product/graphicDetailsApp/0000000000/102295661/10051/R9000413/1.html";
    private WebView wb;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View inflate = inflater.inflate(R.layout.xiangqing_guigecanshu, container, false);
        wb = (WebView) inflate.findViewById(R.id.guigexiangqing_webView);
        return inflate;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        wb.setWebViewClient(new WebViewClient());
        WebSettings w = wb.getSettings();
        w.setJavaScriptEnabled(true);
        wb.loadUrl(url);
    }
}

