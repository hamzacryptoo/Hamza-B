package com.hamza.browser;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.webkit.WebResourceRequest;
import android.webkit.WebResourceResponse;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;
import java.io.ByteArrayInputStream;

public class MainActivity extends AppCompatActivity {

    private static final String HOME = "https://duckduckgo.com";
    private WebView webView;
    private EditText urlBar;

    @SuppressLint("SetJavaScriptEnabled")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        webView = findViewById(R.id.webView);
        urlBar = findViewById(R.id.urlBar);

        WebSettings s = webView.getSettings();
        s.setJavaScriptEnabled(true);
        s.setDomStorageEnabled(true);
        s.setAllowFileAccess(false);
        s.setAllowContentAccess(false);
        s.setGeolocationEnabled(false);
        s.setMediaPlaybackRequiresUserGesture(true);

        webView.setWebViewClient(new HamzaWebClient());
        webView.loadUrl(HOME);

        findViewById(R.id.btnGo).setOnClickListener(v -> navigate());
        urlBar.setOnEditorActionListener((v, actionId, event) -> { navigate(); return true; });
        findViewById(R.id.btnBack).setOnClickListener(v -> { if (webView.canGoBack()) webView.goBack(); });
        findViewById(R.id.btnForward).setOnClickListener(v -> { if (webView.canGoForward()) webView.goForward(); });
        findViewById(R.id.btnReload).setOnClickListener(v -> webView.reload());
        findViewById(R.id.btnHome).setOnClickListener(v -> webView.loadUrl(HOME));
    }

    private void navigate() {
        String input = urlBar.getText().toString().trim();
        if (input.isEmpty()) return;
        if (!input.contains("://")) {
            if (input.contains(".") && !input.contains(" ")) {
                input = "https://" + input;
            } else {
                input = "https://duckduckgo.com/?q=" + input.replace(" ", "+");
            }
        }
        webView.loadUrl(input);
    }

    private class HamzaWebClient extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
            return false;
        }

        @Override
        public WebResourceResponse shouldInterceptRequest(WebView view, WebResourceRequest request) {
            String url = request.getUrl().toString();
            if (AdBlocker.isBlockedUrl(url)) {
                return new WebResourceResponse("text/plain", "utf-8",
                        new ByteArrayInputStream("".getBytes()));
            }
            return super.shouldInterceptRequest(view, request);
        }

        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            urlBar.setText(url);
        }
    }

    @Override
    public void onBackPressed() {
        if (webView.canGoBack()) webView.goBack();
        else super.onBackPressed();
    }
}
