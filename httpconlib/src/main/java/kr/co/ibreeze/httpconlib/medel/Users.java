package kr.co.ibreeze.httpconlib.medel;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by jeenhyung on 2015-01-08.
 */
public class Users {

    @SerializedName(value="users")
    public List<usersclass> users;

    public void setUsers(List<usersclass> users) {
        this.users = users;
    }

    public static class usersclass {
        String id;
        String uid;
        String name;
        String nickname;
        String ischannel;
        String date;

        @Override
        public String toString() {
            return ( id + " " + uid + " " + name + " " + nickname + " " + ischannel + " " + date);
        }
    }
}
