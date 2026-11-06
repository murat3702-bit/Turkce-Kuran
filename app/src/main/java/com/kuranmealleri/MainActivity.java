package com.kuranmealleri;

import android.app.Activity;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.webkit.JavascriptInterface;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class MainActivity extends Activity {
    private WebView web;

    @Override
    protected void onCreate(Bundle state) {
        super.onCreate(state);
        web = new WebView(this);
        setContentView(web);
        WebSettings s = web.getSettings();
        s.setJavaScriptEnabled(true);
        s.setDomStorageEnabled(true);   // yer imleri ve ayarlar (localStorage)
        s.setAllowFileAccess(true);
        s.setAllowContentAccess(false);
        s.setTextZoom(100);
        s.setSupportZoom(false);              // pinch-zoom kaynaklı yatay kaymayı önler
        s.setBuiltInZoomControls(false);
        s.setDisplayZoomControls(false);
        web.setOverScrollMode(View.OVER_SCROLL_NEVER);
        web.setHorizontalScrollBarEnabled(false);
        web.setBackgroundColor(Color.TRANSPARENT);
        web.addJavascriptInterface(new Object() {
            @JavascriptInterface
            public void setBars(final String bg, final boolean light) {
                runOnUiThread(new Runnable() {
                    @Override public void run() {
                        try {
                            int c = Color.parseColor(bg);
                            View d = getWindow().getDecorView();
                            getWindow().setStatusBarColor(c);
                            getWindow().setNavigationBarColor(c);
                            d.setBackgroundColor(c);
                            int f = d.getSystemUiVisibility();
                            if (light) f |= View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR;
                            else f &= ~View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR;
                            if (Build.VERSION.SDK_INT >= 26) {
                                if (light) f |= View.SYSTEM_UI_FLAG_LIGHT_NAVIGATION_BAR;
                                else f &= ~View.SYSTEM_UI_FLAG_LIGHT_NAVIGATION_BAR;
                            }
                            d.setSystemUiVisibility(f);
                        } catch (Exception ignored) { }
                    }
                });
            }
        }, "AndroidTheme");
        web.setWebViewClient(new WebViewClient());
        if (state == null) web.loadUrl("file:///android_asset/index.html");
        else web.restoreState(state);
    }

    @Override
    protected void onSaveInstanceState(Bundle out) {
        super.onSaveInstanceState(out);
        web.saveState(out);
    }

    @Override
    public void onBackPressed() {
        if (web.canGoBack()) web.goBack();
        else super.onBackPressed();
    }
}
