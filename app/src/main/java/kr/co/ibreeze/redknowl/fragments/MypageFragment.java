package kr.co.ibreeze.redknowl.fragments;


import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import in.srain.cube.views.ptr.PtrClassicFrameLayout;
import in.srain.cube.views.ptr.PtrDefaultHandler;
import in.srain.cube.views.ptr.PtrFrameLayout;
import in.srain.cube.views.ptr.PtrHandler;
import kr.co.ibreeze.redknowl.R;
import kr.co.ibreeze.redknowllist.RedknowlListviewAdapter;
import kr.co.ibreeze.redknowllist.controller.OnTextViewButtonPressListener;
import kr.co.ibreeze.redknowllist.model.CardList;
import kr.co.ibreeze.redknowllist.model.RedCard;
import kr.co.ibreeze.redknowllist.model.RedknowlImageCard;
import kr.co.ibreeze.redknowllist.view.RedknowlListView;

/**
 * Created by cozmo-air1 on 2015-01-05.
 */
public class MypageFragment extends Fragment {
    private RedknowlListView mListView;
    private CardList cardsList;
    private Context mContext;
    private RedknowlListviewAdapter adapter;
    private PtrClassicFrameLayout mPtrFrame;
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = LayoutInflater.from(getActivity()).inflate(R.layout.mypage_fragment, null);

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

            }
        }, 100);


        return v;
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

        Drawable icon;

        switch (type){

            default:
//                card = new SmallImageCard();
//                card.setDescription(description);
//                card.setTitle(title);
//                icon = getResources().getDrawable(R.drawable.ic_launcher);
//                card.setBitmap(icon);
                rcard = new RedknowlImageCard();
                rcard.setUserName("양승원");
                rcard.setDate("2014-12-28 16:51");
                rcard.setContent("배고프다");
                rcard.setState("좋아요 5개 댓글 10개");

                icon = getResources().getDrawable(R.drawable.photo2);
                rcard.setBitmap(icon);
                rcard.setCanDismiss(false);

                ((RedknowlImageCard)rcard).setOnUserNamePressedListener(new OnTextViewButtonPressListener() {

                    @Override
                    public void onPressButtonListener(TextView textview) {
                        Toast.makeText(mContext, "프로필", Toast.LENGTH_LONG).show();
                    }

                });
                return rcard;

        }

    }
}
