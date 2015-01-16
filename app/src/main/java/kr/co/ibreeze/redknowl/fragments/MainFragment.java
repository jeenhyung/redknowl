package kr.co.ibreeze.redknowl.fragments;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.otto.Subscribe;

import in.srain.cube.views.ptr.PtrClassicFrameLayout;
import in.srain.cube.views.ptr.PtrDefaultHandler;
import in.srain.cube.views.ptr.PtrFrameLayout;
import in.srain.cube.views.ptr.PtrHandler;
import kr.co.ibreeze.httpconlib.CardMgr;
import kr.co.ibreeze.redknowl.Activities.CommentActivity;
import kr.co.ibreeze.redknowl.App;
import kr.co.ibreeze.redknowl.R;
import kr.co.ibreeze.redknowl.events.LoadEvent;
import kr.co.ibreeze.redknowl.utils.BusProviderApp;
import kr.co.ibreeze.redknowllist.RedknowlListviewAdapter;
import kr.co.ibreeze.redknowllist.controller.OnButtonPressListener;
import kr.co.ibreeze.redknowllist.controller.OnTextViewButtonPressListener;
import kr.co.ibreeze.redknowllist.model.CardList;
import kr.co.ibreeze.redknowllist.model.RedCard;
import kr.co.ibreeze.redknowllist.model.RedknowlImageCard;
import kr.co.ibreeze.redknowllist.view.RedknowlListView;

/**
 * Created by Seungwon on 2015-01-04.
 */

public class MainFragment extends Fragment{

    private RedknowlListView mListView;
    private CardList cardsList;
    private Context mContext;
    private RedknowlListviewAdapter adapter;
    private PtrClassicFrameLayout mPtrFrame;

    private CardMgr cardMgr;

    public MainFragment(){
    }

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {


        final View v = LayoutInflater.from(getActivity()).inflate(R.layout.main_cardlist, null);
        cardMgr = CardMgr.instance();
        mContext = getActivity();
        mListView = (RedknowlListView)v.findViewById(R.id.redknowl_listview);
        cardsList = new CardList();
        fillArray();
        adapter = new RedknowlListviewAdapter(mContext, cardsList);
        mListView.setListViewAdapter(adapter);



        mPtrFrame = (PtrClassicFrameLayout)v.findViewById(R.id.rotate_header_list_view_frame);
        mPtrFrame.setLastUpdateTimeRelateObject(this);
        mPtrFrame.setPtrHandler(new PtrHandler() {
            @Override
            public boolean checkCanDoRefresh(PtrFrameLayout frame, View content, View header) {
                return PtrDefaultHandler.checkContentCanBePulledDown(frame, content, header);
            }

            @Override
            public void onRefreshBegin(PtrFrameLayout frame) {
                frame.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mPtrFrame.refreshComplete();
                    }
                }, 1800);
            }
        });



        mPtrFrame.setResistance(1.7f);
        mPtrFrame.setRatioOfHeaderHeightToRefresh(1.2f);
        mPtrFrame.setDurationToClose(200);
        mPtrFrame.setDurationToCloseHeader(1000);

        mPtrFrame.setPullToRefresh(false);

        mPtrFrame.setKeepHeaderWhenRefresh(true);

        mPtrFrame.postDelayed(new Runnable() {
            @Override
            public void run() {
                cardMgr.restGetPosts(getApp(), cardsList);
                Log.d("position", "postDelayed");
            }
        }, 100);


        BusProviderApp.getInstance().register(this);
        return v;
    }



    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }


    public Application getApp()
    {
        return getActivity().getApplication();
    }

    private void updateData() {


    }


    private void fillArray(){
        for(int i=0;i<35;i++){
            RedCard card = getRandomCard(i);
            cardsList.add(card);
        }
    }

    private RedCard getRandomCard(final int position){

        int type = position % 5;


        final RedCard rcard;

        Drawable profileimg;
        Drawable icon;

        switch (type){

            default:
//                card = new SmallImageCard();
//                card.setDescription(description);
//                card.setTitle(title);
                profileimg = getResources().getDrawable(R.drawable.ki);
                //card.setBitmap(icon);
                rcard = new RedknowlImageCard();
                rcard.setProfileBitmap(profileimg);
                rcard.setID(String.valueOf(position));
                rcard.setUserName("양승원");
                rcard.setDate("2014-12-28 16:51");
                rcard.setContent("배고프다");
                rcard.setState("좋아요 5개 댓글 10개");

                icon = getResources().getDrawable(R.drawable.photo);
                rcard.setBitmap(icon);
                rcard.setCanDismiss(false);

                ((RedknowlImageCard) rcard).setOnUserNamePressedListener(new OnTextViewButtonPressListener() {

                    @Override
                    public void onPressButtonListener(TextView textview) {
                        Toast.makeText(mContext, "프로필", Toast.LENGTH_LONG).show();
                    }

                });

                ((RedknowlImageCard)rcard).setOnCommentPressedListener(new OnButtonPressListener() {
                    @Override
                    public void onPressButtonListener(String id, Button button) {
                        Toast.makeText(mContext, "Card id = " + id, Toast.LENGTH_SHORT ).show();
                        Intent intent = new Intent(mContext, CommentActivity.class);
                        startActivity(intent);
                        getActivity().overridePendingTransition(0, 0);
                    }


                });


                return rcard;

        }

    }


    @Subscribe public void ReciveMsgBroker(LoadEvent e){
        switch (e.code)
        {
            case App.REFRESH:
                Toast.makeText(getActivity(), "LoadEvent = "+ e.code, Toast.LENGTH_SHORT).show();
                break;
        }

    }

    @Subscribe public void onCustomStringEvent(String event) {
        Log.d("Bus event", event);
        mListView.setSelectionAfterHeaderView();
        mPtrFrame.autoRefresh();
    }



    @Override
    public void onPause() {
        super.onPause();
        BusProviderApp.getInstance().unregister(this);
    }

    @Override
    public void onResume() {
        super.onResume();
        BusProviderApp.getInstance().register(this);
    }
}
