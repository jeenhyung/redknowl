package kr.co.ibreeze.redknowl.fragments;


import android.app.Activity;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.Toast;

import kr.co.ibreeze.redknowl.R;
/**
 * Created by cozmo-air1 on 2015-01-05.
 */
public class CategoryTabsFragment extends Fragment implements View.OnClickListener {


    private View mRoot;

    private Button btn1;
    private Button categorybtn;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        mRoot = inflater.inflate(R.layout.tab_category, null);

//        ChannelFragment channelFragment = new ChannelFragment();
//        FragmentTransaction transaction = getFragmentManager().beginTransaction();
//        transaction.replace(R.id.c_container, channelFragment);
//        transaction.addToBackStack(null);
//
//        transaction.commit();
        categorybtn = (Button)mRoot.findViewById(R.id.categorybtn);
        btn1 = (Button)mRoot.findViewById(R.id.channelbtn);

        categorybtn.setOnClickListener(this);
        btn1.setOnClickListener(this);

        return mRoot;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        setRetainInstance(true);
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        Fragment fragment=null;
        if(getArguments()!=null){
            try{
                int type_value = getArguments().getInt("type");

                if(type_value==0) {
                    fragment = new CategoryListFragment();

                    transaction.replace(R.id.c_container, fragment , "SUB_CATEGORY");


                    transaction.commit();
                }else{
                    fragment = new ChannelFragment();

                    transaction.replace(R.id.c_container, fragment , "SUB_CHANNEL");


                    transaction.commit();
                }


            }catch (Exception e){
                e.printStackTrace();
            }
        }




    }

    @Override
    public void onClick(View v) {
        Log.d("Click Event","enter");
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        Fragment fragment=null;
        switch (v.getId())
        {
            case R.id.categorybtn:
                Toast.makeText(v.getContext(),"1", Toast.LENGTH_LONG).show();
                fragment = new CategoryListFragment();

                transaction.replace(R.id.c_container, fragment , "SUB_CATEGORY");


                transaction.commit();
                break;
            case R.id.channelbtn:
                Toast.makeText(v.getContext(),"2", Toast.LENGTH_LONG).show();
                fragment = new ChannelFragment();
                transaction.replace(R.id.c_container, fragment, "SUB_CHANNEL");


                transaction.commit();

                break;
            default:
                Toast.makeText(v.getContext(),"-1", Toast.LENGTH_LONG).show();
                break;

        }

    }
}
