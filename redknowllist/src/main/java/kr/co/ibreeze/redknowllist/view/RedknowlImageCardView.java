package kr.co.ibreeze.redknowllist.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import kr.co.ibreeze.redknowllist.R;
import kr.co.ibreeze.redknowllist.model.GridItemView;
import kr.co.ibreeze.redknowllist.model.RedknowlImageCard;

/**
 * Created by cozmo-air1 on 2015-01-12.
 */
public class RedknowlImageCardView extends GridItemView<RedknowlImageCard> {

    private ImageView mUserImg;
    private TextView mUserNameText;
    private TextView mDateText;
    private TextView mContentText;
    private TextView mPost_state;
    private ImageView mImage;

    private Button mCommentBtn;


    public RedknowlImageCardView(Context context) {
        super(context);
    }

    public RedknowlImageCardView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public RedknowlImageCardView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }
    @Override
    public void configureView(RedknowlImageCard card) {
        setUserImg(card.getProfileBitmap());
        setUserName(card.getUserName());
        setDate(card.getDate());
        setContent(card.getContent());
        setPostState(card.getState());
        setPhoto(card.getBitmap());
        setUserNameText(card);
        setCommentBtn(card);
    }

    public void setUserImg(Bitmap bm) {
        if(bm == null) return;

        mImage = (ImageView)findViewById(R.id.profileimg);
        mImage.setImageBitmap(bm);
    }

    public void setUserName(String name){
        mUserNameText = (TextView)findViewById(R.id.userNameTextView);
        mUserNameText.setText(name);
    }



    public void setDate(String date){
        mDateText = (TextView)findViewById(R.id.datePostText);
        mDateText.setText(date);
    }
    public void setPhoto(Bitmap bm){
        mImage = (ImageView)findViewById(R.id.imageView);
        mImage.setImageBitmap(bm);
    }

    public void setContent(String date){
        mContentText = (TextView)findViewById(R.id.contentTextView);
        mContentText.setText(date);
    }

    public void setPostState(String state){
        mContentText = (TextView)findViewById(R.id.post_state);
        mContentText.setText(state);
    }


    public void setUserNameText(final RedknowlImageCard card){
        mUserNameText = (TextView) findViewById(R.id.userNameTextView);
        mUserNameText.setText(card.getUserName());
        mUserNameText.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                card.getOnUserNamePressedListener().onPressButtonListener(mUserNameText);
            }
        });
    }

    public void setCommentBtn(final RedknowlImageCard card){
        mCommentBtn = (Button)findViewById(R.id.commentBtn);
        mCommentBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                card.getOnCommentPressedListener().onPressButtonListener(card.getID(), mCommentBtn);

               // Toast.makeText(getContext(), "Comment", Toast.LENGTH_SHORT).show();
            }
        });
    }

}
