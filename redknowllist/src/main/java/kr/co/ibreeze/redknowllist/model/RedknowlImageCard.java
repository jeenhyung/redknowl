package kr.co.ibreeze.redknowllist.model;

import kr.co.ibreeze.redknowllist.R;
import kr.co.ibreeze.redknowllist.controller.OnButtonPressListener;
import kr.co.ibreeze.redknowllist.controller.OnTextViewButtonPressListener;

/**
 * Created by Seungwon on 2014-12-27.
 */
public class RedknowlImageCard extends RedCard {

    private OnTextViewButtonPressListener onProfileImagePressedListener;
    private OnTextViewButtonPressListener onUserNamePressedListener;
    private OnTextViewButtonPressListener onImagePressedListener;
    private OnTextViewButtonPressListener onLikePressedListener;
    private OnButtonPressListener onCommentPressedListener;
    private OnTextViewButtonPressListener onScrapePressedListener;
    private boolean showDivider = false;
    private boolean fullDividerLength = false;



    public boolean getShowDivider() {
        return showDivider;
    }
    public boolean getFullDividerLength() {
        return fullDividerLength;
    }

    public void setFullDividerLength(boolean fullDividerLength) {
        this.fullDividerLength = fullDividerLength;
    }

    public void setShowDivider(boolean showDivider) {
        this.showDivider = showDivider;
    }

    public OnTextViewButtonPressListener getOnProfileImagePressedListener(){
        return onProfileImagePressedListener;
    }

    public OnTextViewButtonPressListener getOnUserNamePressedListener(){
        return onUserNamePressedListener;
    }

    public OnTextViewButtonPressListener getOnImagePressedListener(){
        return onImagePressedListener;
    }

    public OnTextViewButtonPressListener getOnLikePressedListener(){
        return onLikePressedListener;
    }

    public OnButtonPressListener getOnCommentPressedListener(){
        return onCommentPressedListener;
    }

    public OnTextViewButtonPressListener getOnScrapePressedListener(){
        return onScrapePressedListener;
    }

    public void setOnProfileImagePressedListener(OnTextViewButtonPressListener onProfileImagePressedListener) {
        this.onProfileImagePressedListener = onProfileImagePressedListener;
    }


    public void setOnUserNamePressedListener(OnTextViewButtonPressListener onUserNamePressedListener) {
        this.onUserNamePressedListener = onUserNamePressedListener;
    }

    public void setOnImagePressedListener(OnTextViewButtonPressListener onImagePressedListener) {
        this.onImagePressedListener = onImagePressedListener;
    }

    public void setOnLikePressedListener(OnTextViewButtonPressListener onLikePressedListener) {
        this.onLikePressedListener = onLikePressedListener;
    }

    public void setOnCommentPressedListener(OnButtonPressListener onCommentPressedListener) {
        this.onCommentPressedListener = onCommentPressedListener;
    }

    public void setOnScrapePressedListener(OnTextViewButtonPressListener onScrapePressedListener) {
        this.onScrapePressedListener = onScrapePressedListener;
    }


    @Override
    public int getLayout() {
        return R.layout.redknowl_basic_image_card;
    }
}
