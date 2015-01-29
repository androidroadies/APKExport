package com.example.apkexport;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.ResolveInfo;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class MainActivity extends BaseActivity {

    private static final String TAG = "MainActivity.java";
    Dialog mDialog;
    ResolveInfo info;
    Context mContext;
    List pkgAppsList;
    TextView tvShare, tvBackUp;
    Typeface fontBold, fontRegular;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        LoadAdd();
        mContext = MainActivity.this;
        fontBold = Typeface.createFromAsset(mContext.getAssets(),
                "Roboto-Bold.ttf");
        fontRegular = Typeface.createFromAsset(mContext.getAssets(),
                "Roboto-Regular.ttf");

        getPackages();
        final Intent mainIntent = new Intent(Intent.ACTION_MAIN, null);
        mainIntent.addCategory(Intent.CATEGORY_LAUNCHER);
        pkgAppsList = getPackageManager().queryIntentActivities(mainIntent, 0);

        ListView listView = (ListView) findViewById(R.id.listView);
        Collections.sort(AppConfig.appName, StringAscComparator);
        CustomList adapter = new CustomList(MainActivity.this,
                AppConfig.appName, AppConfig.iconArray);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                Intent i = new Intent(getApplicationContext(),
                        ShareScreen.class);
                i.putExtra("position", position);
//                loadInterstitial(i);
                startActivity(i);

            }
        });


    }

    // Comparator for Ascending Order
    public static Comparator<String> StringAscComparator = new Comparator<String>() {

        public int compare(String app1, String app2) {

            String stringName1 = app1;
            String stringName2 = app2;

            return stringName1.compareToIgnoreCase(stringName2);
        }
    };




    private ArrayList<PInfo> getPackages() {
        ArrayList<PInfo> apps = getInstalledApps(false); /*
                                                         * false = no system
														 * packages
														 */
        final int max = apps.size();
        getInstalledApps(true);
        for (int i = 0; i < max; i++) {
            apps.get(i).prettyPrint();
        }
        return apps;
    }

    private ArrayList<PInfo> getInstalledApps(boolean getSysPackages) {
        ArrayList<PInfo> res = new ArrayList<PInfo>();
        List<PackageInfo> packs = getPackageManager().getInstalledPackages(0);
        AppConfig.iconArray.clear();
        AppConfig.appName.clear();
        AppConfig.apkPathArray.clear();
        for (int i = 0; i < packs.size(); i++) {
            PackageInfo p = packs.get(i);
            if ((!getSysPackages) && (p.versionName == null)) {
                continue;
            }
            PInfo newInfo = new PInfo();
            newInfo.appname = p.applicationInfo.loadLabel(getPackageManager())
                    .toString();
            newInfo.pname = p.packageName;
            newInfo.versionName = p.versionName;
            newInfo.versionCode = p.versionCode;
            newInfo.icon = p.applicationInfo.loadIcon(getPackageManager());
            newInfo.apkpath = p.applicationInfo.publicSourceDir;

            res.add(newInfo);
            AppConfig.iconArray.add(newInfo.icon);
            AppConfig.apkPackageNameArray.add(newInfo.pname);
            AppConfig.appName.add(newInfo.appname);
            AppConfig.apkPathArray.add(newInfo.apkpath);
            AppConfig.apkVersionNameArray.add(newInfo.versionName);

            System.out.println("107 >>>>>>>>> " + newInfo.versionCode);
        }
        return res;
    }

    public class CustomList extends ArrayAdapter<String> {
        private final Activity context;
        private final ArrayList<String> web;
        private final ArrayList<Drawable> imageId;

        public CustomList(Activity context, ArrayList<String> appname,
                          ArrayList<Drawable> imagearray) {
            super(context, R.layout.listview_item, appname);
            this.context = context;
            this.web = appname;
            this.imageId = imagearray;
        }

        @SuppressLint({"ViewHolder", "ResourceAsColor"})
        @Override
        public View getView(int position, View view, ViewGroup parent) {
            LayoutInflater inflater = context.getLayoutInflater();
            View rowView = inflater.inflate(R.layout.listview_item, null, true);

            TextView txtTitle = (TextView) rowView
                    .findViewById(R.id.tvPackageName);
            ImageView imageView = (ImageView) rowView
                    .findViewById(R.id.ivImage);

//            if (position % 2 == 1) {
//                rowView.setBackgroundColor(rowView.getResources().getColor(
//                        R.color.bg_color));
//                txtTitle.setTextColor(rowView.getResources().getColor(
//                        R.color.font_color));
//            } else {
//                rowView.setBackgroundColor(rowView.getResources().getColor(
//                        R.color.bg_two));

//            }

            txtTitle.setTypeface(fontRegular);
            txtTitle.setText(web.get(position));
            imageView.setImageDrawable(imageId.get(position));
            return rowView;
        }
    }

    class PInfo {
        Drawable icon;
        private String appname = "";
        private String pname = "";
        private String versionName = "";
        private String apkpath = "";
        private int versionCode = 0;

        private void prettyPrint() {
            // Log.v(appname + "\t" + pname + "\t" + versionName + "\t" +
            // versionCode);

            // imagearray.add(icon);
            // packagename.add(pname);

            System.out.println("78 data check " + appname);
            /*
			 * for (int i = 0; i < imagearray.size(); i++) { if (i == 0) {
			 * img.setImageDrawable(imagearray.get(i)); }
			 *
			 * }
			 */
        }
    }
}
