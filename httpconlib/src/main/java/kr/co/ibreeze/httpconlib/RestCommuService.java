package kr.co.ibreeze.httpconlib;

import java.util.List;

import kr.co.ibreeze.httpconlib.medel.Posts;
import kr.co.ibreeze.httpconlib.medel.Users;
import retrofit.Callback;
import retrofit.client.Response;
import retrofit.http.GET;
import retrofit.http.Multipart;
import retrofit.http.POST;
import retrofit.http.Part;
import retrofit.mime.TypedFile;

/**
 * Created by jeenhyung on 2015-01-07.
 */
public interface RestCommuService {
    //모든사용자정보받기
    @GET("/api/users")
    void getUsers(Callback<Users> usersCallback);

    //모든게시글정보받기
    @GET("/api/posts")
    void getPosts(Callback<Posts> postsCallback);

    //사진이름목록받기
    @GET("/api/pictures/post")
    void listPictures( Callback<List<String>> picturesCallback);

    @Multipart
    @POST("/api/posts")
    Response addPost( @Part("userid")String userid, @Part("name")String name, @Part("picpath")String picpath, @Part("content")String content);

    @Multipart
    @POST("/api/upload")
    Response uploadImage( @Part("upload_file") TypedFile file);



}
