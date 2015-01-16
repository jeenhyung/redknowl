package kr.co.ibreeze.redknowllist.model;


import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;

/**
 * Created by cozmo-air1 on 2014-12-28.
 */
public abstract class RedCard {
    private String id;
    private Bitmap profileimg;
    private String userNameText;
    private String postedDateText;
    private String contentText;
    private String stateText;
    private Bitmap photo;
    private boolean canDismiss = true;

    public RedCard(){}

    public RedCard(String username, String postedDate, String content,  String state, Bitmap bitmap)
    {
        this.userNameText=username;
        this.postedDateText = postedDate;
        this.contentText = content;
        this.stateText = state;
        this.photo = bitmap;
    }

    public RedCard(Context context, String username, String postedDate, String content,  String state, int res)
    {
        this.userNameText=username;
        this.postedDateText = postedDate;
        this.contentText = content;
        this.stateText = state;
        this.photo = resourceToBitmap(context, res);
    }

    public RedCard(String username, String postedDate, String content,  String state, Drawable drawable)
    {
        this.userNameText=username;
        this.postedDateText = postedDate;
        this.contentText = content;
        this.stateText = state;
        this.photo = drawableToBitmap(drawable);
    }

    public Bitmap getProfileBitmap() {
        return profileimg;
    }
    public void setProfileBitmap(Bitmap bitmap) {
        this.profileimg = bitmap;
    }
    public void setProfileBitmap(Drawable drawable){
        profileimg = drawableToBitmap(drawable);
    }

    public String getID(){ return id;}
    public void setID(String id){this.id = id;}

    public String getContent(){ return contentText;}
    public void setContent(String content ){ this.contentText = content;}

    public String getDate(){ return postedDateText;}
    public void setDate(String date ){ this.postedDateText = date;}

    public String getUserName(){ return userNameText;}
    public void setUserName(String name ){ this.userNameText = name;}

    public String getState(){ return stateText;}
    public void setState(String state ){ this.stateText = state;}

    public Bitmap getBitmap() {
        return photo;
    }

    public void setBitmap(Bitmap bitmap) {
        this.photo = bitmap;
    }

    public void setBitmap(Drawable drawable){
        photo = drawableToBitmap(drawable);
    }

    private Bitmap resourceToBitmap (Context context, int resourceId){
        Resources res = context.getResources();
        Drawable d = res.getDrawable(resourceId);
        return drawableToBitmap(d);
    }

    private Bitmap drawableToBitmap (Drawable drawable) {
        if (drawable instanceof BitmapDrawable) {
            return ((BitmapDrawable)drawable).getBitmap();
        }

        Bitmap bitmap = Bitmap.createBitmap(drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        drawable.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
        drawable.draw(canvas);

        return bitmap;
    }


    public boolean canDismiss() {
        return canDismiss;
    }

    public void setCanDismiss(boolean canDismiss) {
        this.canDismiss = canDismiss;
    }

    public abstract int getLayout();




}
