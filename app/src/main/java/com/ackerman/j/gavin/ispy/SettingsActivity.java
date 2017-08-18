package com.ackerman.j.gavin.ispy;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

/**
 * Created by gavin.ackerman on 2016-08-15.
 */
public class SettingsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }
    public void TagsClick(View v) {

        Intent i = new Intent(this,TagActivity.class);

        startActivity(i);
    }
    public void UserClick(View v) {

        Intent i = new Intent(this,UserInfoActivity.class);

        startActivity(i);
    }
    public void LogoutClick(View v) {

        Intent i = new Intent(this,MainActivity.class);

        startActivity(i);
    }
}
