package kr.co.ibreeze.redknowl.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

import kr.co.ibreeze.redknowl.R;
import kr.co.ibreeze.redknowl.adapters.CategoryAdapter;
import kr.co.ibreeze.redknowl.models.categoryItem;

/**
 * Created by cozmo-air1 on 2015-01-05.
 */
public class CategoryListFragment extends ListFragment {
    private ArrayList<categoryItem> items = new ArrayList<categoryItem>();
    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        for(int i = 0 ; i<100; i++)
        {
            items.add(new categoryItem(i, "value"+i));
        }


        try{
            CategoryAdapter adapter = new CategoryAdapter(inflater.getContext(), R.layout.list_item, items);

            setListAdapter(adapter);
        }catch (Exception e)
        {
            e.printStackTrace();
        }


        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {


        super.onActivityCreated(savedInstanceState);
    }
}
