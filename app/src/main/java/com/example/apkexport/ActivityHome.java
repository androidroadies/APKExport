package com.example.apkexport;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;
import android.widget.TextView;

public class ActivityHome extends BaseActivity {
    LinearLayout llShareApp, llStartApp, llAboutusApp;
    TextView tvShareApp, tvStartApp, tvAboutusApp;
    String socialWidgetApp = "https://play.google.com/store/apps/details?id=com.multidots.OOs";
    Typeface fontBold, fontRegular;
    Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        LoadAdd();
        mContext = ActivityHome.this;
        init();

    }

    private void init() {
        // TODO Auto-generated method stub
        fontBold = Typeface.createFromAsset(mContext.getAssets(),
                "Roboto-Bold.ttf");
        fontRegular = Typeface.createFromAsset(mContext.getAssets(),
                "Roboto-Light.ttf");

        tvShareApp = (TextView) findViewById(R.id.tvShareApp);
        tvStartApp = (TextView) findViewById(R.id.tvStartApp);
        tvAboutusApp = (TextView) findViewById(R.id.tvAboutusApp);

        tvShareApp.setTypeface(fontRegular);
        tvStartApp.setTypeface(fontRegular);
        tvAboutusApp.setTypeface(fontRegular);

        llShareApp = (LinearLayout) findViewById(R.id.llShareApp);
        llStartApp = (LinearLayout) findViewById(R.id.llStartApp);
        llAboutusApp = (LinearLayout) findViewById(R.id.llAboutusApp);

        llShareApp.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Intent sharingIntent = new Intent(
                        Intent.ACTION_SEND);
                sharingIntent.setType("text/plain");
                sharingIntent.putExtra(Intent.EXTRA_SUBJECT,
                        "ApplicationShare");
                sharingIntent.putExtra(Intent.EXTRA_TEXT,
                        socialWidgetApp);
                startActivity(Intent.createChooser(sharingIntent, "Share via"));
            }
        });
        llStartApp.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Intent i = new Intent(getApplicationContext(),
                        MainActivity.class);
                startActivity(i);
            }
        });
        llAboutusApp.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Intent i = new Intent(getApplicationContext(), AbutUs.class);
                startActivity(i);
            }
        });

    }

    @Override
    public void onBackPressed() {
        loadInterstitial(null);
        super.onBackPressed();
    }
}
