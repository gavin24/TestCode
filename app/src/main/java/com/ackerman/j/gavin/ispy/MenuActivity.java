package com.ackerman.j.gavin.ispy;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by gavin.ackerman on 2017-04-14.
 */
public class MenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        TextView text = new TextView(this);
        text.setText("Press the menu button to get list of menus.");
        addContentView(text, new ActionBar.LayoutParams(ActionBar.LayoutParams.FILL_PARENT, ActionBar.LayoutParams.FILL_PARENT));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Toast.makeText(getApplicationContext(),	item.getTitle() + " selected", Toast.LENGTH_SHORT).show();

     /*   switch (item.getItemId()) {
            case R.id.createStory:
                setContentView(R.layout.create_story);
                // do something
                break;
            case R.id.favMenu:
                // do something
                break;
            case R.id.listMenu:
                // do something
                break;
            case R.id.settingsMenu:
                // do something
                break;
        }*/
        return true;
    }



}
