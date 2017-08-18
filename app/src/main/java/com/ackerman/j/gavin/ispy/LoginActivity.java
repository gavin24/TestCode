package com.ackerman.j.gavin.ispy;

import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.ackerman.j.gavin.ispy.Config.Util.SessionManagerHelper;
import com.ackerman.j.gavin.ispy.Services.Impl.UserServiceImpl;
import com.facebook.FacebookSdk;

/**
 * Created by gavin.ackerman on 2016-08-15.
 */
public class LoginActivity extends AppCompatActivity {
    private EditText userName;
    private EditText passWord;
    private UserServiceImpl userService;
    private boolean isValid = false;
    SessionManagerHelper sess;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        StrictMode.ThreadPolicy policy = new StrictMode.
                ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        super.onCreate(savedInstanceState);
        FacebookSdk.sdkInitialize(getApplicationContext());
        setContentView(R.layout.login_main);

        userName = (EditText)findViewById(R.id.editTextEmail);
        passWord = (EditText) findViewById(R.id.editTextPassword);
    }


    public void signInClick(View v) {
        String user = userName.getText().toString();
        String pass = passWord.getText().toString();
        userService = new UserServiceImpl();
        isValid = userService.isAuthentic(user,pass);
        if(isValid == true)
        {
            Intent i = new Intent(this,NewsfeedActivity.class);
            sess.createLoginSession(user, pass);
            startActivity(i);
        }
        else
        {
            Toast.makeText(getApplicationContext(),
                    "Please enter correct Username and Password", Toast.LENGTH_LONG)
                    .show();
        }

    }
    public void signInFacebookClick(View v) {

            Intent i = new Intent(this,FacebookLoginActivity.class);

            startActivity(i);
        }





}
