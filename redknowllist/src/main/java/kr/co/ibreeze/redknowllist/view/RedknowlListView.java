package kr.co.ibreeze.redknowllist.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ListView;

import kr.co.ibreeze.redknowllist.RedknowlListviewAdapter;
import kr.co.ibreeze.redknowllist.model.CardList;

/**
 * Created by cozmo-air1 on 2015-01-12.
 */
public class RedknowlListView extends ListView {


    private RedknowlListviewAdapter mAdapter;


    public RedknowlListView (Context context) {
        super (context);
    }

    public RedknowlListView (Context context, AttributeSet attrs, int defStyle) {
        super (context, attrs, defStyle);
    }

    public RedknowlListView (Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public void setListViewAdapter(RedknowlListviewAdapter adapter) {
         mAdapter = adapter;
        setAdapter (mAdapter);
        setDivider (null);
        setDividerHeight (8);
    }

    public void addCardsToExistingAdapter(CardList newCards){
        mAdapter.addCardsToExistingSet(newCards);
    }

    public RedknowlListviewAdapter getMaterialListViewAdapter(){
        return mAdapter;
    }

}