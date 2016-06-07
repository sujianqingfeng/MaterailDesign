package com.sujian.materaildesign.delegate;

import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.widget.Toolbar;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.sujian.materaildesign.R;
import com.sujian.materaildesign.frame.view.AppDelegate;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by sujian on 2016/6/1.
 * Mail:121116111@qq.com
 */
public class WedDelegate extends AppDelegate {

    @BindView(R.id.wv_web)
    WebView wv_web;
    @BindView(R.id.tb_web)
    Toolbar tb_web;
    @BindView(R.id.rl_web)
    RelativeLayout rl_web;
    @BindView(R.id.iv_go)
    ImageView iv_go;
    @BindView(R.id.iv_back)
    ImageView iv_back;
    @BindView(R.id.iv_refresh)
    ImageView iv_refresh;

    public int getRootLayoutId() {
        return R.layout.activity_web;
    }

    @Override
    public Toolbar getToolbar() {
        return get(R.id.tb_web);
    }

    @Override
    public void initWidget() {
        super.initWidget();
        initWeb();
    }

    private void initWeb() {
        //启用支持javascript
//        WebSettings settings = wv_web.getSettings();
//        settings.setJavaScriptEnabled(true);

        Bundle extras = getActivity().getIntent().getExtras();
        String url = extras.getString("url");
        wv_web.loadUrl(url);
        // 覆盖WebView默认使用第三方或系统默认浏览器打开网页的行为，使网页用WebView打开
        wv_web.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                // 返回值是true的时候控制去WebView打开，为false调用系统浏览器或第三方浏览器
                view.loadUrl(url);
                return true;
            }
        });

        wv_web.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onReceivedTitle(WebView view, String title) {
                super.onReceivedTitle(view, title);
                tb_web.setTitle(title);
            }
        });
    }


    @OnClick(R.id.rl_web)
    void clickRL() {//空实现 主要拦截事件
    }

    @OnClick(R.id.iv_back)
    void clickBack() {
        if (wv_web.canGoBack()) {
            wv_web.goBack();
        } else {
            getActivity().finish();
        }
    }

    @OnClick(R.id.iv_go)
    void clickGo() {
        if (wv_web.canGoForward()) {
            wv_web.goForward();
        }
    }

    @OnClick(R.id.iv_refresh)
    void refresh() {
        wv_web.reload();
    }
}
