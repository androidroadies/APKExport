package com.example.apkexport;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;

public class AbutUs extends Activity {
    int imageArra[] = {R.drawable.md_kavan, R.drawable.md_ofice_izico,
            R.drawable.md_ofice_main_hall, R.drawable.md_ofice_neha,
            R.drawable.md_ofice_wall_clock};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aboutus);
        init();

        ViewPager myPager = (ViewPager) findViewById(R.id.myfivepanelpager);
        Md_ViewPagerAdapter adapter = new Md_ViewPagerAdapter(this, imageArra);

        myPager.setAdapter(adapter);
        myPager.setCurrentItem(0);
    }

    private void init() {
        // TODO Auto-generated method stub
        ImageView twitter = (ImageView) findViewById(R.id.twitter);
        ImageView facebook = (ImageView) findViewById(R.id.facebook);
        // ImageView skyp = (ImageView) findViewById(R.id.skyp);
        ImageView google_plus = (ImageView) findViewById(R.id.google_plus);
        // ImageView linked_in = (ImageView) findViewById(R.id.linked_in);
        // ImageView flickr = (ImageView) findViewById(R.id.flickr);
        ImageView you_tube = (ImageView) findViewById(R.id.you_tube);
        ImageView pinterest = (ImageView) findViewById(R.id.pinterest);

        TextView call_md_lable = (TextView) findViewById(R.id.call_md);
        TextView inqry_md_mail_lable = (TextView) findViewById(R.id.inqry_md_mail_lable);
        TextView multidots = (TextView) findViewById(R.id.multidots);

        multidots.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                String url = "http://www.multidots.com/";
                Intent browserIntent = new Intent("android.intent.action.VIEW",
                        Uri.parse(url));
                startActivity(browserIntent);
            }
        });
        twitter.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                String url = "https://twitter.com/multidots";
                Intent browserIntent = new Intent("android.intent.action.VIEW",
                        Uri.parse(url));
                startActivity(browserIntent);
            }
        });

        facebook.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                String url = "https://www.facebook.com/multidots";
                Intent browserIntent = new Intent("android.intent.action.VIEW",
                        Uri.parse(url));
                startActivity(browserIntent);
            }
        });

        // skyp.setOnClickListener(new OnClickListener() {
        // @Override
        // public void onClick(View v) {
        // // TODO Auto-generated method stub
        // String url = "skype:multidotsindia";
        // Intent browserIntent = new Intent("android.intent.action.VIEW",
        // Uri.parse(url));
        // startActivity(browserIntent);
        // }
        // });

        // linked_in.setOnClickListener(new OnClickListener() {
        //
        // @Override
        // public void onClick(View arg0) {
        // // TODO Auto-generated method stub
        // String url = "www.linkedin.com/company/multidots";
        // Intent browserIntent = new Intent("android.intent.action.VIEW",
        // Uri.parse(url));
        // startActivity(browserIntent);
        //
        // }
        // });

        google_plus.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                String url = "https://plus.google.com/u/0/+Multidots/posts";
                Intent browserIntent = new Intent("android.intent.action.VIEW",
                        Uri.parse(url));
                startActivity(browserIntent);
            }
        });

        // flickr.setOnClickListener(new OnClickListener() {
        //
        // @Override
        // public void onClick(View v) {
        // // TODO Auto-generated method stub
        // String url = "www.flickr.com/photos/multidots/";
        // Intent b = new Intent("android.intent.action.VIEW", Uri
        // .parse(url));
        // startActivity(b);
        // }
        // });

        you_tube.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub
                String url = "http://www.youtube.com/user/multidotstv";
                Intent b = new Intent("android.intent.action.VIEW", Uri
                        .parse(url));
                startActivity(b);
            }
        });

        pinterest.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub
                String url = "https://www.pinterest.com/multidots/";
                Intent b = new Intent("android.intent.action.VIEW", Uri
                        .parse(url));
                startActivity(b);
            }
        });

        call_md_lable.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub

                Intent myIntent = new Intent(Intent.ACTION_CALL);
                // YASIN
                String phNum = "tel:" + "+918141834322";
                // MD RAJVI
                // String phNum = "tel:" + "+917940322440";
                // HIRAL
                // String phNum = "tel:" + "+919033801862";
                myIntent.setData(Uri.parse(phNum));
                startActivity(myIntent);
            }
        });

        inqry_md_mail_lable.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub

                String url = "http://www.multidots.com/get-in-touch/";
                Intent b = new Intent("android.intent.action.VIEW", Uri
                        .parse(url));
                startActivity(b);

                // Intent company = new Intent(Md_main.this,
                // Md_email_send.class);
                // company.setFlags(Intent.FLAG_ACTIVITY_BROUGHT_TO_FRONT);
                // company.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                // startActivity(company);
                // overridePendingTransition(android.R.anim.fade_in,
                // android.R.anim.fade_out);
            }
        });

    }
}
