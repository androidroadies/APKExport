package com.example.apkexport;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings.Secure;
import android.util.Log;
import android.widget.FrameLayout.LayoutParams;
import android.widget.LinearLayout;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;

public class BaseActivity extends Activity {


    AdRequest request;
    AdView adView;
    LinearLayout llAd;
    private InterstitialAd interstitial;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    public void LoadAdd() {
        llAd = (LinearLayout) findViewById(R.id.llAd);
        // adView = new AdView(this, AdSize.SMART_BANNER, "ca-app-pub-7940680036537699/3690981363");
        AdView adView = new AdView(this);
        adView.setAdUnitId(getString(R.string.banner_ad_unit_id));
        adView.setAdSize(AdSize.SMART_BANNER);
//            adView.setId(0x05607);
        LayoutParams param = null;
        param = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);

        adView.setLayoutParams(param);
        adView.setBackgroundColor(getResources().getColor(android.R.color.black));
        llAd.addView(adView, param);
//        String deviceid = Secure.getString(getContentResolver(), Secure.ANDROID_ID);
        request = new AdRequest.Builder().build();
        // request.addTestDevice(deviceid);
        adView.loadAd(request);
    }


    void loadInterstitial(final Intent intent) {
        String deviceid = Secure.getString(getContentResolver(), Secure.ANDROID_ID);
        // request = new AdRequest.Builder().addTestDevice(deviceid).build();

        // Create ad request.
        AdRequest adRequest = new AdRequest.Builder().build();
        interstitial = new InterstitialAd(this);
        interstitial.setAdUnitId(getString(R.string.interstitial_ad_unit_id));
        // interstitial.loadAd(request);
        interstitial.setAdListener(new AdListener() {
            @Override
            public void onAdClosed() {
                Log.e("Ad", "Ad closed");
                super.onAdClosed();
                if (intent != null)
                    startActivity(intent);
//                finish();
            }

            @Override
            public void onAdFailedToLoad(int errorCode) {
                Log.e("Ad", "Ad Failed" + errorCode);
                super.onAdFailedToLoad(errorCode);
                if (intent != null)
                    startActivity(intent);
//                finish();
            }

            @Override
            public void onAdLoaded() {
                super.onAdLoaded();
                Log.e("Ad", "Ad loaded");
                interstitial.show();
            }
        });
        // Begin loading your interstitial.
        interstitial.loadAd(adRequest);
    }
}
