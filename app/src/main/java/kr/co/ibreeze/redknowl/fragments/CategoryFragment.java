package kr.co.ibreeze.redknowl.fragments;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import kr.co.ibreeze.redknowl.R;

/**
 * Created by cozmo-air1 on 2015-01-05.
 */
public class CategoryFragment extends Fragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View v = LayoutInflater.from(getActivity()).inflate(R.layout.category_fragment, null);
        TextView text = (TextView)v.findViewById(R.id.textView);

        if(getArguments()!=null){
            try{
                String value = getArguments().getString("key");
                text.setText("현재값 : " + value);

            }catch (Exception e){
                e.printStackTrace();
            }
        }


        return v;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }
}
