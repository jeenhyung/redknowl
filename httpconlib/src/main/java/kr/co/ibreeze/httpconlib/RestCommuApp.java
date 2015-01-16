package kr.co.ibreeze.httpconlib;

import android.app.Application;
import android.util.Log;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.internal.bind.DateTypeAdapter;
import com.squareup.okhttp.Cache;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.picasso.OkHttpDownloader;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.io.IOException;
import java.util.Date;

import retrofit.RestAdapter;
import retrofit.converter.GsonConverter;

/**
 * Created by jeenhyung on 2015-01-07.
 */
public class RestCommuApp extends Application {
    private static final String SERVER_URL = "http://ec2-54-64-196-49.ap-northeast-1.compute.amazonaws.com";
    private static final String PICDIR = "/pictures/post/";
    public static final int CACHE_SIZE = 10 * 1024 * 1024;

    private RestCommuService restCommuService;
    private Picasso picasso;
    private OkHttpClient okHttpClient;

    @Override public void onCreate() {
        super.onCreate();

        Gson gson = new GsonBuilder()
                .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                .registerTypeAdapter(Date.class, new DateTypeAdapter())
                .create();

        RestAdapter restAdapter = new RestAdapter.Builder()
                .setEndpoint(SERVER_URL)
                .setConverter(new GsonConverter(gson))
                .build();
        restCommuService = restAdapter.create(RestCommuService.class);

        okHttpClient = new OkHttpClient();
        try {
            okHttpClient.setCache(
                    new Cache(new File(getCacheDir(), "http-cache"), CACHE_SIZE));
        } catch (IOException e) {
            Log.w("PicturesApp", "Failed to create a response cache: ", e);
        }

        picasso = new Picasso.Builder(this)
                .downloader(new OkHttpDownloader(okHttpClient))
                .build();
    }

    public RestCommuService getRestCommuService() {
        return restCommuService;
    }

    public String fileToPictureUrl(String fileName) {
        return SERVER_URL + PICDIR + fileName;
    }

    public Picasso getPicasso() {
        return picasso;
    }
}
