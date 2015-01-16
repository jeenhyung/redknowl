package kr.co.ibreeze.redknowl.Activities;

import android.app.TaskStackBuilder;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import kr.co.ibreeze.redknowl.R;
/**
 * Created by cozmo-air1 on 2015-01-06.
 */
public class SearchActivity extends ActionBarActivity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.search_activity);

        ActionBar myActionbar =  getSupportActionBar();
        myActionbar.setDisplayHomeAsUpEnabled(true);


        View customActionbar = LayoutInflater.from(this).inflate(R.layout.actionbar_search, null);
        myActionbar.setCustomView(customActionbar);
        myActionbar.setDisplayShowCustomEnabled(true);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
//        getMenuInflater().inflate(R.menu.menu_main, menu);
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
