package kr.co.ibreeze.redknowl.models;

/**
 * Created by cozmo-air1 on 2015-01-06.
 */
public class commentItem {
    public String proimg;
    public String id;
    public String name;
    public String comment;

    public commentItem(){}
    public commentItem(String id, String proimg, String name, String comment){
        this.id = id;
        this.proimg = proimg;
        this.name= name;
        this.comment = comment;
    }



}
