package kr.co.ibreeze.redknowl.Activities;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTabHost;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TabHost;
import android.widget.Toast;

import com.facebook.AppEventsLogger;
import com.squareup.otto.Subscribe;

import kr.co.ibreeze.redknowl.R;
import kr.co.ibreeze.redknowl.fragments.CategoryTabsFragment;
import kr.co.ibreeze.redknowl.fragments.MainFragment;
import kr.co.ibreeze.redknowl.fragments.MypageFragment;
import kr.co.ibreeze.redknowl.utils.BusProviderApp;

/**
 * Created by Seungwon on 2015-01-05.
 */
public class MainFragmentActivity extends FragmentActivity implements View.OnClickListener {


    private ImageButton headerbtn_left;
    private ImageButton headerbtn_right;
    private FragmentTabHost mTabHost;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        String userid = prefs.getString("userid", "None");

        if(userid==null||userid.equals("None")) {

            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
            overridePendingTransition(0, 0);
            finish();
        }else{
            initTab();
        }
    }


    private void initTab(){
        setContentView(R.layout.tabs_fragment);
        // mTabHost = new FragmentTabHost(this);
        // mTabHost.setup(this, getSupportFragmentManager(),
        // R.id.menu_settings);
        BusProviderApp.getInstance().register(this);

        Application app = getApplication();

        initHeader();
        mTabHost = (FragmentTabHost) findViewById(android.R.id.tabhost);
        mTabHost.setup(this, getSupportFragmentManager(), R.id.realtabcontent);

        Bundle b = new Bundle();
        b.putString("key", "Main");


        mTabHost.addTab(setIndicator(this, mTabHost.newTabSpec("Main"), R.drawable.tab_indicator_gen, "Main", R.drawable.main_btn1),
                MainFragment.class, b);
        //
        b = new Bundle();
        System.out.print("hello git");
        b.putString("key", "Category");
        b.putInt("type", 0);
        mTabHost.addTab(setIndicator(this, mTabHost.newTabSpec("Category"), R.drawable.tab_indicator_gen, "Category", R.drawable.main_btn2),
                CategoryTabsFragment.class, b);

        b = new Bundle();
        b.putString("key", "Channel");
        b.putInt("type", 1);
        mTabHost.addTab(setIndicator(this, mTabHost.newTabSpec("Channel"), R.drawable.tab_indicator_gen,"Channel",R.drawable.main_btn3),
                CategoryTabsFragment.class, b);

        b = new Bundle();
        b.putString("key", "My Page");
        mTabHost.addTab(setIndicator(this, mTabHost.newTabSpec("My Page"), R.drawable.tab_indicator_gen, "My Page", R.drawable.main_btn4),
                MypageFragment.class, b);
        // setContentView(mTabHost);

        mTabHost.getTabWidget().setDividerDrawable(null);
    }


    private void initHeader()
    {
        headerbtn_left = (ImageButton)findViewById(R.id.headerleftbtn);
        headerbtn_left.setOnClickListener(this);
        headerbtn_right= (ImageButton)findViewById(R.id.headerrightbtn);
        headerbtn_right.setOnClickListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.

        getParent().getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    private TabHost.TabSpec setIndicator(Context ctx, TabHost.TabSpec spec,
                                 int resid, String string, int genresIcon) {
        View v = LayoutInflater.from(ctx).inflate(R.layout.tab_item, null);
        v.setBackgroundResource(resid);
        //TextView tv = (TextView)v.findViewById(R.id.txt_tabtxt);
        ImageView img = (ImageView)v.findViewById(R.id.img_tab);
        //tv.setText(string);
        img.setBackgroundResource(genresIcon);
        return spec.setIndicator(v);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.headerleftbtn:
                Toast.makeText(this, "검색", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(this, SearchActivity.class);

                startActivity(intent);
                overridePendingTransition(0, 0);

                break;
            case R.id.headerrightbtn:
                //Toast.makeText(this, "새로고침", Toast.LENGTH_SHORT).show();
                BusProviderApp.getInstance().post("ListView Refresh");
                break;
        }
    }


    @Subscribe
    public void getInt(int i)
    {
        Toast.makeText(getApplication(), String.valueOf(i), Toast.LENGTH_LONG).show();
    }


    @Override
    protected void onResume() {
        super.onResume();
        BusProviderApp.getInstance().register(this);

        AppEventsLogger.activateApp(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        BusProviderApp.getInstance().unregister(this);
        AppEventsLogger.deactivateApp(this);
    }
}