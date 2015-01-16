package kr.co.ibreeze.redknowllist;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.ArrayList;

import kr.co.ibreeze.redknowllist.model.CardList;
import kr.co.ibreeze.redknowllist.model.GridItemView;
import kr.co.ibreeze.redknowllist.model.RedCard;

/**
 * Created by cozmo-air1 on 2015-01-12.
 */
public class RedknowlListviewAdapter extends BaseAdapter {

    private Context mContext;
    private CardList mCardList;
    private ArrayList<Class> mClassList = new ArrayList<Class>();
    private ArrayList<Class> mDeletedList = new ArrayList<Class>();

    public void addCardsToExistingSet(CardList newCards){
        for (RedCard c : newCards) {
            mCardList.add(c);
        }
        fillClassList();
        notifyDataSetChanged();
    }

    public RedknowlListviewAdapter(Context context, CardList cardList){
        mContext = context;
        mCardList = cardList;

        fillClassList();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup viewGroup){

        if(convertView == null){
            boolean found = false;
            for(int i = 0;i<mClassList.size() && !found;i++){
                if(mClassList.get(i).isInstance(getItem(position))){
                    convertView = View.inflate(mContext, getItem(position).getLayout(), null);
                    found = true;
                }
            }
        } else {

            // Dirty hack to prevent imageview violations
            if(mDeletedList.contains(getItem(position).getClass())){
                convertView = View.inflate(mContext, getItem(position).getLayout(), null);
            }
        }

        ((GridItemView)convertView).configureView(getItem(position));


        return convertView;
    }

    private void fillClassList(){
        mClassList.clear();
        for(RedCard card : mCardList){
            Class cl = card.getClass();
            if(!mClassList.contains(cl)){
                mClassList.add(cl);
            }
        }
    }

    @Override
    public int getCount() {
        return mCardList.size();
    }

    @Override
    public RedCard getItem(int i){
        return mCardList.get(i);
    }

    @Override
    public long getItemId(int id){
        return 0;
    }

    @Override
    public int getItemViewType (int position){

        for(int i=0;i<mClassList.size();i++){
            Class cl = mClassList.get(i);
            if(cl.isInstance(getItem(position))) return i;
        }

        return -1;
    }

    @Override
    public int getViewTypeCount (){
        return mClassList.size();
    }

    public void remove(RedCard card) {
        if (mCardList != null){
            if(!mDeletedList.contains(card.getClass())){
                mDeletedList.add(card.getClass());
            }
            mCardList.remove(card);
        }


        notifyDataSetChanged();

    }

    public CardList getCardList(){
        return mCardList;
    }

}
