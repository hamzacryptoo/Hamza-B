package com.hamza.browser;

import android.util.Log;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/** AdBlocker: يحجب الإعلانات والمتتبعات عبر كشف النطاقات المدرجة في القائمة. */
public class AdBlocker {

    private static final String TAG = "HAMZA-AdBlock";
    private static final Set<String> BLOCKED = new HashSet<>(Arrays.asList(
        "doubleclick.net", "googlesyndication.com", "googleadservices.com",
        "adservice.google.com", "ads.yahoo.com", "ads.twitter.com",
        "facebook.com/tr", "analytics.facebook.com", "static.ads-twitter.com",
        "ads.linkedin.com", "analytics.twitter.com", "scorecardresearch.com",
        "quantserve.com", "adbrite.com", "adnxs.com", "rubiconproject.com",
        "pubmatic.com", "criteo.com", "taboola.com", "outbrain.com",
        "moatads.com", "telemetry.microsoft.com", "telemetry.mozilla.org"
    ));

    public static boolean isBlockedUrl(String url) {
        if (url == null) return false;
        String lower = url.toLowerCase();
        for (String domain : BLOCKED) {
            if (lower.contains(domain)) {
                Log.d(TAG, "محجوب: " + url);
                return true;
            }
        }
        return false;
    }
}
