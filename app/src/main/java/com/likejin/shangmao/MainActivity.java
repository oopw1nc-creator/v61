package com.likejin.shangmao;

import android.app.Activity;
import android.os.Bundle;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.graphics.Color;
import android.view.View;
import android.view.Window;
import android.view.WindowInsets;
import android.view.WindowManager;

public class MainActivity extends Activity {
    private WebView web;
    @Override public void onCreate(Bundle b){
        super.onCreate(b);
        Window w=getWindow();
        w.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
        w.setStatusBarColor(Color.rgb(7,11,22));
        w.setNavigationBarColor(Color.rgb(7,11,22));
        if (android.os.Build.VERSION.SDK_INT >= 29) {
            w.setNavigationBarContrastEnforced(false);
            w.setStatusBarContrastEnforced(false);
        }
        web=new WebView(this);
        web.setBackgroundColor(Color.rgb(7,11,22));
        web.setWebViewClient(new WebViewClient());
        web.setWebChromeClient(new WebChromeClient());
        WebSettings s=web.getSettings();
        s.setJavaScriptEnabled(true);
        s.setDomStorageEnabled(true);
        s.setDatabaseEnabled(true);
        s.setAllowFileAccess(true);
        s.setAllowContentAccess(true);
        s.setBuiltInZoomControls(false);
        s.setDisplayZoomControls(false);
        s.setTextZoom(100);
        web.setOnApplyWindowInsetsListener((v, insets) -> {
            if (android.os.Build.VERSION.SDK_INT >= 30) {
                android.graphics.Insets sys=insets.getInsets(WindowInsets.Type.systemBars());
                v.setPadding(0, 0, 0, sys.bottom);
            } else {
                v.setPadding(0, 0, 0, insets.getSystemWindowInsetBottom());
            }
            return insets;
        });
        setContentView(web);
        web.loadUrl("file:///android_asset/index.html");
    }
    @Override public void onBackPressed(){
        web.evaluateJavascript("(function(){var m=document.getElementById('productModal'),p=document.getElementById('picker'),c=document.getElementById('customerView');if(m&&!m.classList.contains('hidden')){m.classList.add('hidden');return 'closed';}if(p&&!p.classList.contains('hidden')){p.classList.add('hidden');return 'closed';}if(c&&!c.classList.contains('hidden')){if(typeof closeCustomerView==='function'){closeCustomerView();}else{c.classList.add('hidden');}return 'closed';}return 'none';})()", value -> { if (!"\"closed\"".equals(value)) super.onBackPressed(); });
    }
}
