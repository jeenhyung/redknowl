package kr.co.ibreeze.httpconlib;

import java.util.ArrayList;

enum RestCallType {
    getUsers, getPosts, listPictures, addPost, uploadImage
}

/**
 * Created by jeenhyung on 2015-01-12.
 */
public class RestCallObject {
    private RestCallType type;
    private ArrayList<String> param = null;

    public RestCallObject(RestCallType type) {
        this.type = type;
    }

    public RestCallObject(RestCallType type, ArrayList<String> values ) {
        this.type = type;
        param = new ArrayList<String>(values);
    }

    public void setParam(ArrayList<String> values) {
        if(param != null) {
            param.clear();
            param = values;
            return;
        }
        param = new ArrayList<String>(values);
    }
    public ArrayList<String> getParam() {
        return this.param;
    }
    public String getParam(int i) {
        return this.param.get(i);
    }

    public RestCallType getType() {
        return this.type;
    }
}

