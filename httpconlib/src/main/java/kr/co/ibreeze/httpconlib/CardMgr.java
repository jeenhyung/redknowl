package kr.co.ibreeze.httpconlib;

import android.app.Application;
import android.util.Log;
import android.widget.Toast;

import kr.co.ibreeze.httpconlib.medel.Posts;
import kr.co.ibreeze.redknowllist.model.CardList;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by cozmo-air1 on 2015-01-15.
 * Singleton
 *
 *
 * Manager of Cards
 * 글, 댓글 관련 매서드
 *
 */


public class CardMgr {

    private final int REQ_CODE_GALLERY = 100;

    private static CardMgr _instance = null;

    public static CardMgr instance(){

        if(_instance == null){
            _instance = new CardMgr();
        }
        return _instance;
    }

    //모든 게시글 정보 받기
    public void restGetPosts(final Application application, CardList cardlist){
        RestCommuApp app = (RestCommuApp)application;
        RestCommuService restCommuervice = app.getRestCommuService();

        restCommuervice.getPosts(new Callback<Posts>() {
            @Override
            public void success(Posts posts, Response response) {
                StringBuilder test= new StringBuilder();

                for (int i = 0; i < posts.posts.size(); i++) {
                    test.append(posts.posts.get(i).toString() + "\n");
                    Log.d("posts", posts.posts.get(i).toString());
//                    final RedCard rcard;
//
//                    rcard = new RedknowlImageCard();
//                    rcard.setProfileBitmap(profileimg);
//                    rcard.setID(String.valueOf(position));
//                    rcard.setUserName("양승원");
//                    rcard.setDate("2014-12-28 16:51");
//                    rcard.setContent("배고프다");
//                    rcard.setState("좋아요 5개 댓글 10개");
//
//                    icon = getResources().getDrawable(R.drawable.photo);
//                    rcard.setBitmap(icon);
//                    rcard.setCanDismiss(false);
//
//                    ((RedknowlImageCard) rcard).setOnUserNamePressedListener(new OnTextViewButtonPressListener() {
//
//                        @Override
//                        public void onPressButtonListener(TextView textview) {
//                            Toast.makeText(mContext, "프로필", Toast.LENGTH_LONG).show();
//                        }
//
//                    });
//
//                    ((RedknowlImageCard)rcard).setOnCommentPressedListener(new OnButtonPressListener() {
//                        @Override
//                        public void onPressButtonListener(String id, Button button) {
//                            Toast.makeText(mContext, "Card id = " + id, Toast.LENGTH_SHORT ).show();
//                            Intent intent = new Intent(mContext, CommentActivity.class);
//                            startActivity(intent);
//                            getActivity().overridePendingTransition(0, 0);
//                        }
//
//
//                    });
                }

                Toast.makeText(application, test, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void failure(RetrofitError retrofitError) {
                //responView.append(retrofitError.toString());
                retrofitError.printStackTrace();
            }
        });


    }


}
