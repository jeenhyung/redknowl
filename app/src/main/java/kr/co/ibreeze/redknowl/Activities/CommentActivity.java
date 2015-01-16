package kr.co.ibreeze.redknowl.Activities;

import android.app.TaskStackBuilder;
import android.content.Intent;
import android.support.v4.app.NavUtils;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import java.util.ArrayList;

import kr.co.ibreeze.redknowl.R;
import kr.co.ibreeze.redknowl.adapters.CommentAdapter;
import kr.co.ibreeze.redknowl.models.commentItem;

public class CommentActivity extends ActionBarActivity {
    private ListView mCommentListView;
    private CommentAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comment);

        ActionBar myActionbar = getSupportActionBar();
        myActionbar.setDisplayHomeAsUpEnabled(true);

        mCommentListView = (ListView)findViewById(R.id.commentlistview);




        adapter = new CommentAdapter(this, R.layout.item_comment ,TempItems());



        mCommentListView.setAdapter(adapter);

    }

    private ArrayList<commentItem> TempItems(){
        ArrayList<commentItem> items = new ArrayList<commentItem>();

        commentItem item = new commentItem("123", "img1", "Yang", "우왕 댓글이다.");
        items.add(item);
        commentItem item2 = new commentItem("43", "img2", "sdfasd", "우왕 댓글이다.우왕 댓글이다.우왕 댓글이다.우왕 댓글이다.우왕 댓글이다.우왕 댓글이다.우왕 댓글이다.우왕 댓글이다.우왕 댓글이다.우왕 댓글이다.우왕 댓글이다.우왕 댓글이다.우왕 댓글이다.우왕 댓글이다.우왕 댓글이다.우왕 댓글이다.우왕 댓글이다.우왕 댓글이다.우왕 댓글이다.우왕 댓글이다.우왕 댓글이다.우왕 댓글이다.우왕 댓글이다.우왕 댓글이다.우왕 댓글이다.우왕 댓글이다.");
        items.add(item2);
        commentItem item3 = new commentItem("65", "img3", "허경영", "What's up man?");
        items.add(item3);
        commentItem item4 = new commentItem("34", "img4", "Hot", "Hummmmmmmmmmmmm");
        items.add(item4);

        return  items;
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_comment, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId())
        {
            case android.R.id.home:
                Intent upIntent = NavUtils.getParentActivityIntent(this);
                if(NavUtils.shouldUpRecreateTask(this, upIntent)){
                    TaskStackBuilder.create(this).addNextIntentWithParentStack(upIntent).startActivities();
                }else{
                    NavUtils.navigateUpTo(this, upIntent);
                }
                overridePendingTransition(0, 0);
                return  true;

        }
        return super.onOptionsItemSelected(item);
    }
}
