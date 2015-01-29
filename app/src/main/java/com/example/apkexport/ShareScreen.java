package com.example.apkexport;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

public class ShareScreen extends BaseActivity {
    TextView tvapp_nameData, tvShareAppH, tvStartAppH, tvAboutusAppH,
            app_pNameData, app_nameTittle, app_pNameTittle, appVersionTittle, appVersionData;
    Typeface fontRegular, fontBold;
    Context mContext;
    LinearLayout llShareAppH, llBackUpAppH, llUnistallAppH;
    int pos;
    ImageView ivImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.share_screen);
        LoadAdd();
        loadInterstitial(null);
        mContext = ShareScreen.this;
        init();
        Intent i = getIntent();
        pos = i.getIntExtra("position", 1);
        ivImage.setImageDrawable(AppConfig.iconArray.get(pos));
        tvapp_nameData.setText(AppConfig.appName.get(pos));
        app_pNameData.setText(AppConfig.apkPackageNameArray.get(pos));
        appVersionData.setText(AppConfig.apkVersionNameArray.get(pos));
        llShareAppH.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                ShareApplication();
            }
        });
        llBackUpAppH.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                BackUpApplication();
            }
        });

        llUnistallAppH.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                UninstallApp();
            }
        });
    }

    protected void ShareApplication() {
        // TODO Auto-generated method stub

        // TODO Auto-generated method stub
        System.out.println("AppData 53 file:///mnt/sdcard/rr.apk " + "------------"
                + AppConfig.apkPathArray.get(pos));

        Intent sharingIntent = new Intent(Intent.ACTION_SEND);
        sharingIntent.setType("application/vnd.android.package-archive");
//		sharingIntent.putExtra(Intent.EXTRA_EMAIL,new String[] { "yasin@multidots.in" });
//		sharingIntent.putExtra(Intent.EXTRA_SUBJECT,"Test Subject");
//		sharingIntent.putExtra(Intent.EXTRA_TEXT,"go on read the emails");
        sharingIntent.putExtra(Intent.EXTRA_STREAM, Uri.parse("file://" + AppConfig.apkPathArray.get(pos)));

//		Intent emailIntent = new Intent(android.content.Intent.ACTION_SEND);
//		emailIntent.setType("*/*");
//		emailIntent.putExtra(android.content.Intent.EXTRA_EMAIL, new String[]{"mayank.dudhatra@multidots.in"});
//		emailIntent.putExtra(android.content.Intent.EXTRA_SUBJECT,"Test Subject");
//		emailIntent.putExtra(android.content.Intent.EXTRA_TEXT, "From My App new 1");
//		emailIntent.putExtra(Intent.EXTRA_STREAM, Uri.parse("file:///mnt/sdcard/rr.apk"));
//		startActivity(Intent.createChooser(emailIntent, "Send mail..."));

        // sharingIntent.putExtra(android.content.Intent.ACTION_PICK,targetLocation);
        // sharingIntent.putExtra(android.content.Intent.EXTRA_STREAM,
        // Uri.fromFile(sourceLocation));
        startActivity(Intent.createChooser(sharingIntent, "Share Application"));

    }

    protected void BackUpApplication() {
        // TODO Auto-generated method stub

        // TODO Auto-generated method stub
        final String sdCard = Environment.getExternalStorageDirectory()
                .toString();
        final File file = new File(sdCard, "/"
                + getResources().getString(R.string.app_Name) + "/");
        if (!file.isDirectory())
            file.mkdir();
        // the file to be moved or copied
        final File sourceLocation = new File(AppConfig.apkPathArray.get(pos));// sdCard
        // +
        // "/yasin.txt");
        // make sure your target location folder exists!
        final File targetLocation = new File(sdCard, "/"
                + getResources().getString(R.string.app_Name) + "/"
                + AppConfig.appName.get(pos) + ".apk");

        // TODO Auto-generated method stub

        // just to take note of the location sources
        // Log.v(TAG, "sourceLocation: " + sourceLocation);
        // Log.v(TAG, "targetLocation: " + targetLocation);

        try {

            // 1 = move the file, 2 = copy the file
            int actionChoice = 2;

            // moving the file to another directory
            if (actionChoice == 1) {

                if (sourceLocation.renameTo(targetLocation)) {
                    // Log.v(TAG, "Move file successful.");
                } else {
                    // Log.v(TAG, "Move file failed.");
                }

            }

            // we will copy the file
            else {

                // make sure the target file exists

                if (sourceLocation.exists()) {

                    InputStream in = new FileInputStream(sourceLocation);
                    OutputStream out = new FileOutputStream(targetLocation);

                    // Copy the bits from instream to outstream
                    byte[] buf = new byte[1024];
                    int len;

                    while ((len = in.read(buf)) > 0) {
                        out.write(buf, 0, len);
                    }

                    in.close();
                    out.close();
                    Toast.makeText(mContext, "Saved to Apkexport directory.",
                            Toast.LENGTH_LONG).show();
                    // Log.v(TAG, "Copy file successful.");

                } else {
                    // Log.v(TAG,
                    // "Copy file failed. Source file missing.");
                }

            }

        } catch (NullPointerException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    protected void UninstallApp() {
        // TODO Auto-generated method stub
        Intent intent = new Intent(Intent.ACTION_DELETE);
        intent.setData(Uri.parse("package:" + AppConfig.apkPackageNameArray.get(pos)));
        startActivity(intent);

    }

    private void init() {
        // TODO Auto-generated method stub
        fontBold = Typeface.createFromAsset(mContext.getAssets(),
                "Roboto-Bold.ttf");
        fontRegular = Typeface.createFromAsset(mContext.getAssets(),
                "Roboto-Light.ttf");

        llShareAppH = (LinearLayout) findViewById(R.id.llShareAppH);
        llBackUpAppH = (LinearLayout) findViewById(R.id.llBackUpAppH);
        llUnistallAppH = (LinearLayout) findViewById(R.id.llUnistallAppH);

        tvShareAppH = (TextView) findViewById(R.id.tvShareAppH);
        tvStartAppH = (TextView) findViewById(R.id.tvStartAppH);
        tvAboutusAppH = (TextView) findViewById(R.id.tvAboutusAppH);
        app_pNameData = (TextView) findViewById(R.id.app_pNameData);
        app_nameTittle = (TextView) findViewById(R.id.app_nameTittle);
        tvapp_nameData = (TextView) findViewById(R.id.app_nameData);
        app_pNameTittle = (TextView) findViewById(R.id.app_pNameTittle);
        appVersionTittle = (TextView) findViewById(R.id.appVersionTittle);
        appVersionData = (TextView) findViewById(R.id.appVersionData);
        ivImage = (ImageView) findViewById(R.id.ivImage);

        tvShareAppH.setTypeface(fontRegular);
        tvStartAppH.setTypeface(fontRegular);
        tvAboutusAppH.setTypeface(fontRegular);
        app_nameTittle.setTypeface(fontBold);
        app_pNameTittle.setTypeface(fontBold);
        app_pNameData.setTypeface(fontRegular);
        tvapp_nameData.setTypeface(fontRegular);
        appVersionTittle.setTypeface(fontBold);
        appVersionData.setTypeface(fontRegular);
    }
}
