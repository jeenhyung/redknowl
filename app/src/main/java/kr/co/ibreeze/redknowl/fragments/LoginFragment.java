package kr.co.ibreeze.redknowl.fragments;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.facebook.Session;
import com.facebook.SessionState;
import com.facebook.UiLifecycleHelper;
import com.facebook.model.GraphUser;
import com.facebook.widget.LoginButton;

import java.util.Arrays;

import kr.co.ibreeze.redknowl.App;
import kr.co.ibreeze.redknowl.R;
import kr.co.ibreeze.redknowl.events.LoadEvent;
import kr.co.ibreeze.redknowl.utils.BusProviderApp;

/**
 * Created by cozmo-air1 on 2015-01-15.
 */
public class LoginFragment extends Fragment {
    private static final String TAG = "LoginFragment";
    private UiLifecycleHelper uiHelper;
    private Session.StatusCallback callback;


    public LoginFragment(){

        callback = new Session.StatusCallback() {
            @Override
            public void call(Session session, SessionState sessionState, Exception e) {
                onSessionStateChange(session, sessionState, e);
            }
        };
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        uiHelper = new UiLifecycleHelper(getActivity(), callback);
        uiHelper.onCreate(savedInstanceState);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.activity_main, container, false);
        LoginButton authButton = (LoginButton)view.findViewById(R.id.authButton);



        authButton.setUserInfoChangedCallback(new LoginButton.UserInfoChangedCallback() {
            @Override
            public void onUserInfoFetched(GraphUser graphUser) {
                if(graphUser!=null) {
                    Toast.makeText(getActivity(), graphUser.getId() + " " + graphUser.getName(), Toast.LENGTH_SHORT).show();

                    //Save user information
                    SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getActivity());
                    prefs.edit().putString("userid", graphUser.getId()).commit();

                    BusProviderApp.getInstance().post(new LoadEvent(App.LOAD_MAIN));

                }else{
                    Toast.makeText(getActivity(), "You are not log-in", Toast.LENGTH_SHORT).show();
                }
            }
        });
        authButton.setFragment(this);
        authButton.setReadPermissions(Arrays.asList("user_likes", "user_status"));

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();

        BusProviderApp.getInstance().register(this);

        Session session = Session.getActiveSession();
        if(session!=null && (session.isOpened() || session.isClosed())){
            onSessionStateChange(session, session.getState(), null);
        }

        uiHelper.onResume();

    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        uiHelper.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void onPause() {
        super.onPause();
        uiHelper.onPause();

        BusProviderApp.getInstance().unregister(this);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        uiHelper.onDestroy();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        uiHelper.onSaveInstanceState(outState);
    }

    private void onSessionStateChange(Session session, SessionState state, Exception exception) {
        if (state.isOpened()) {
            Log.i(TAG, "Logged in...");
        } else if (state.isClosed()) {
            Log.i(TAG, "Logged out...");
        }
    }





}
