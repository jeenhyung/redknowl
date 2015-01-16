package kr.co.ibreeze.redknowl.Activities;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

import com.facebook.AppEventsLogger;
import com.squareup.otto.Subscribe;

import kr.co.ibreeze.redknowl.App;
import kr.co.ibreeze.redknowl.events.LoadEvent;
import kr.co.ibreeze.redknowl.fragments.LoginFragment;
import kr.co.ibreeze.redknowl.utils.BusProviderApp;


public class MainActivity extends FragmentActivity {
    private static final String TAG = "MainActivity";
    private LoginFragment loginFragment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if(savedInstanceState == null) {
            loginFragment = new LoginFragment();
                    getSupportFragmentManager().beginTransaction()
                            .add(android.R.id.content, loginFragment).commit();


        }else{
            loginFragment = (LoginFragment)getSupportFragmentManager()
                            .findFragmentById(android.R.id.content);
        }

    }


    @Override
    protected void onResume() {
        super.onResume();

        AppEventsLogger.activateApp(this);
        BusProviderApp.getInstance().register(this);
    }

    @Override
    protected void onPause() {
        super.onPause();

        AppEventsLogger.deactivateApp(this);
        BusProviderApp.getInstance().unregister(this);
    }


    @Subscribe
    public void ReciveMsgBroker(LoadEvent e){
        switch (e.code)
        {
            case App.LOAD_MAIN:
                Intent intent = new Intent(this, MainFragmentActivity.class);
                startActivity(intent);
                overridePendingTransition(0, 0);
                finish();
                break;
        }

    }
}
