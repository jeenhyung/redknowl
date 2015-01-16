package kr.co.ibreeze.httpconlib.medel;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by jeenhyung on 2015-01-09.
 */
public class Posts {
    @SerializedName(value="posts")
    public List<postsclass> posts;

    public void setPosts(List<postsclass> posts) {
        this.posts = posts; }

    public static class postsclass {
        String id;
        String userid;
        String name;
        String picpath;
        String content;
        String date;

        @Override
        public String toString() {
            return ( id + " " + userid + " " + name + " " + picpath + " " + content + " " + date);
        }
    }
}
