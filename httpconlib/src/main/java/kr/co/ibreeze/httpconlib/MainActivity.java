package kr.co.ibreeze.httpconlib;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.FragmentTabHost;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import kr.co.ibreeze.httpconlib.medel.Posts;
import kr.co.ibreeze.httpconlib.medel.Users;
import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;
import retrofit.mime.TypedFile;


public class MainActivity extends ActionBarActivity {

    private static final String PICTURE_SERVER = "http://ec2-54-64-196-49.ap-northeast-1.compute.amazonaws.com";
    private final int REQ_CODE_GALLERY = 100;


    private ImageButton headerbtn_left;
    private ImageButton headerbtn_right;
    private FragmentTabHost mTabHost;

    private PictureListLoader pictureListLoader = new PictureListLoader();
    private LinearLayout progress;
    private Button getUsersBtn;
    private Button getPostsBtn;
    private Button getGalleryBtn;
    private Button uploadImageBtn;
    private Button addPostBtn;
    private TextView responView;
    private TextView error;
    private ListView picturesList;
    private PictureListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

       //유저목록 다불러옴
        getUsersBtn = (Button) findViewById(R.id.getUsers);

        //게시글
        getPostsBtn = (Button) findViewById(R.id.getPosts);

        //갤러리 로컬
        getGalleryBtn = (Button) findViewById(R.id.getGallery);

        //이미지 업로드
        uploadImageBtn = (Button) findViewById(R.id.uploadImage);

        //게시글 추가
        addPostBtn = (Button) findViewById(R.id.addPost);

        //
        responView = (TextView) findViewById(R.id.responView);

        getUsersBtn.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                restGetUsers();
            }
        });
        getPostsBtn.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                restGetPosts();
            }
        });
        getGalleryBtn.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                //갤러리사진주소얻기
                getGallery();
            }
        });
        uploadImageBtn.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                //이미지파일 업로드
                imageUploader();
            }
        });
        addPostBtn.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                //게시글등록(정보추가)
                ArrayList<String> param = new ArrayList<String>();
                param.add("master");
                param.add("게시글제목");
                param.add("사진URL주소");
                param.add("게시글내용");
                RestCallObject restCallObj = new RestCallObject(RestCallType.addPost, param);
                addPost(restCallObj);
            }
        });

//        //유저목록받기
//        RestCommuService restCommuService = ((RestCommuApp)getApplication()).getRestCommuService();
//        restCommuService.getUsers( new Callback<Users>() {
//            @Override
//            public void success(Users users, Response response) {
//
//                for (int i = 0; i < users.users.size(); i++) {
//                    Log.d("users", users.users.get(i).toString());
//                }
//            }
//
//            @Override
//            public void failure(RetrofitError retrofitError) {
//                retrofitError.printStackTrace();
//            }
//        });

//

        //이미지파일 다운
        progress = (LinearLayout) findViewById(R.id.progress);
        picturesList = (ListView) findViewById(R.id.picturesList);
        error = (TextView) findViewById(R.id.error);

        adapter = new PictureListAdapter();
        picturesList.setAdapter(adapter);

        error.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                pictureListLoader.load(getApp());
            }
        });

    }


    /*
    구현
     */

    //모든유저정보받기
    public void restGetUsers() {
        responView.setText("");
        RestCommuService restCommuService = getApp().getRestCommuService();
        restCommuService.getUsers( new Callback<Users>() {
            @Override
            public void success(Users users, Response response) {
                for (int i = 0; i < users.users.size(); i++) {
                    responView.append(users.users.get(i).toString() + "\n");
                    Log.d("users", users.users.get(i).toString());
                }
            }
            @Override
            public void failure(RetrofitError retrofitError) {
                responView.append(retrofitError.toString());
                retrofitError.printStackTrace();
            }
        });
    }

    //모든게시글정보받기
    public void restGetPosts() {
        responView.setText("");
        RestCommuService restCommuService = getApp().getRestCommuService();
        restCommuService.getPosts( new Callback<Posts>() {
            @Override
            public void success(Posts posts, Response response) {
                for (int i = 0; i < posts.posts.size(); i++) {
                    responView.append(posts.posts.get(i).toString() + "\n");
                    Log.d("users", posts.posts.get(i).toString());
                }
            }
            @Override
            public void failure(RetrofitError retrofitError) {
                responView.append(retrofitError.toString());
                retrofitError.printStackTrace();
            }
        });
    }

    //게시글추가하기(아직 이미지1개)
    public void addPost(RestCallObject obj) {
        new ProcessAddPostTask().execute(obj);
    }
    private class ProcessAddPostTask extends AsyncTask<RestCallObject, Void, Void> {
        @Override
        protected Void doInBackground(RestCallObject... obj) {
            try {
                RestAdapter restAdapter = new RestAdapter.Builder()
                        .setEndpoint(PICTURE_SERVER)
                        .build();
                switch(obj[0].getType().name()) {
                    case "addPost" :
                        restAdapter.create(RestCommuService.class)
                            .addPost(
                                    obj[0].getParam(0),
                                    obj[0].getParam(1),
                                    obj[0].getParam(2),
                                    obj[0].getParam(3)
                            );
                        break;
                    default :
                        break;
                }
            } catch(Exception e) {
                e.printStackTrace();
            }
            return null;
        }
    }

    private RestCommuApp getApp() {
        return (RestCommuApp) getApplication();
    }

    public static class PictureListLoader {
        MainActivity target;

        public void setTarget(MainActivity target) {
            this.target = target;
        }

        public void load(RestCommuApp app) {
            target.progress.setVisibility(View.VISIBLE);
            target.picturesList.setVisibility(View.GONE);
            target.error.setVisibility(View.GONE);

            RestCommuService restCommuService = app.getRestCommuService();

            restCommuService.listPictures( new Callback<List<String>>() {
                @Override public void success(List<String> pictures, Response response) {
                    if (target == null) return;

                    target.progress.setVisibility(View.GONE);
                    target.picturesList.setVisibility(View.VISIBLE);
                    target.error.setVisibility(View.GONE);

                    target.adapter.setPictureFileNames(pictures);
                }

                @Override public void failure(RetrofitError retrofitError) {
                    if (target == null) return;

                    target.error.setVisibility(View.VISIBLE);
                    target.progress.setVisibility(View.GONE);
                    target.picturesList.setVisibility(View.GONE);

                    target.error.setText("Error: " + retrofitError.getMessage());
                }
            });
        }
    }

    public class PictureListAdapter extends BaseAdapter {
        private RestCommuApp app = getApp();
        private List<String> pictureFileNames = Collections.emptyList();

        public void setPictureFileNames(List<String> pictureFileNames) {
            this.pictureFileNames = pictureFileNames;
            notifyDataSetChanged();
        }

        @Override public int getCount() {
            return pictureFileNames.size();
        }

        @Override public Object getItem(int position) {
            return pictureFileNames.get(position);
        }

        @Override public long getItemId(int position) {
            return position;
        }

        @Override public View getView(int position, View convertView, ViewGroup parent) {
            LinearLayout view = convertView != null
                    ? (LinearLayout) convertView
                    : (LinearLayout) getLayoutInflater().inflate(R.layout.pictureitem, parent, false);
            TextView text = (TextView) view.findViewById(R.id.text);
            ImageView picture = (ImageView) view.findViewById(R.id.picture);

            String pictureFileName = pictureFileNames.get(position);
            text.setText(pictureFileName);

            if (isImage(pictureFileName)) {
                app.getPicasso()
                        .load(app.fileToPictureUrl(pictureFileName))
                        .placeholder(R.drawable.loading)
                        .resize(300, 260)
                        .centerCrop()
                        .into(picture);
            } else {
                app.getPicasso().cancelRequest(picture);
                picture.setImageResource(R.drawable.doc);
            }

            return view;
        }
    }

    private boolean isImage(String pictureFileName) {
        return pictureFileName.endsWith(".png")
                || pictureFileName.endsWith(".gif")
                || pictureFileName.endsWith(".jpg")
                || pictureFileName.endsWith(".jpeg");
    }

    private void getGallery()
    {
        //버튼 클릭시 처리로직
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType(android.provider.MediaStore.Images.Media.CONTENT_TYPE);
        intent.setData(android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent, REQ_CODE_GALLERY);
    }
    public String getImageNameToUri(Uri data)
    {
        String[] proj = { MediaStore.Images.Media.DATA };
        Cursor cursor = managedQuery(data, proj, null, null, null);
        int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);

        cursor.moveToFirst();

        String imgPath = cursor.getString(column_index);
//        String imgName = imgPath.substring(imgPath.lastIndexOf("/")+1);

        return imgPath;
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        Toast.makeText(getBaseContext(), "resultCode : "+resultCode,Toast.LENGTH_SHORT).show();

        if(requestCode == REQ_CODE_GALLERY)
        {
            if(resultCode== Activity.RESULT_OK)
            {
                try {
                    //Uri에서 이미지 이름을 얻어온다.
                    String psth_Str = getImageNameToUri(data.getData());
                    responView.setText("");
                    responView.append(psth_Str);
//                    //이미지 데이터를 비트맵으로 받아온다.
//                    Bitmap image_bitmap 	= Images.Media.getBitmap(getContentResolver(), data.getData());
//                    ImageView image = (ImageView)findViewById(R.id.imageView1);
//
//                    //배치해놓은 ImageView에 set
//                    image.setImageBitmap(image_bitmap);


                    //Toast.makeText(getBaseContext(), "name_Str : "+name_Str , Toast.LENGTH_SHORT).show();


                }
//                catch (FileNotFoundException e) {
//                    // TODO Auto-generated catch block
//                    e.printStackTrace();
//                } catch (IOException e) {
//                    // TODO Auto-generated catch block
//                    e.printStackTrace();
//                }
                 catch (Exception e)
                {
                    e.printStackTrace();
                }
            }
        }
    }

    //이미지업로드(POST)
    private void imageUploader() {

        String fname = responView.getText().toString();
        File file = new File(fname);
        new ProcessImageUploaderTask().execute(file);
    }
    private class ProcessImageUploaderTask extends AsyncTask<File, Void, Void> {

        @Override
        protected Void doInBackground(File... file) {
            try {
                RestAdapter restAdapter = new RestAdapter.Builder()
                        .setEndpoint(PICTURE_SERVER)
                        .build();
                restAdapter.create(RestCommuService.class)
                        .uploadImage(new TypedFile("image/*", file[0]));
            } catch(Exception e) {
                e.printStackTrace();
            }
            return null;
        }
    }




    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
    @Override protected void onResume() {
        super.onResume();
        pictureListLoader.setTarget(this);
        pictureListLoader.load(getApp());
    }
    @Override protected void onPause() {
        pictureListLoader.setTarget(null);
        super.onPause();
    }

}
