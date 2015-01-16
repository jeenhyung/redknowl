package kr.co.ibreeze.redknowl.models;

/**
 * Created by cozmo-air1 on 2015-01-05.
 */
public class categoryItem {
    public String category;
    public int id;

    public categoryItem(){}

    public categoryItem(int id, String category)
    {
        this.id = id;
        this.category = category;
    }

}