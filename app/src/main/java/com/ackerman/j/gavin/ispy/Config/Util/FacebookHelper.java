package com.ackerman.j.gavin.ispy.Config.Util;

/**
 * Created by gavin.ackerman on 2017-04-20.
 */
import android.app.Activity;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;

import org.json.JSONException;
import org.json.JSONObject;
public class FacebookHelper extends Activity {

    // Facebook Application APP ID (Get it from faacebook app setting page)
   /* private static String APP_ID = "766325873534705";

    private AsyncFacebookRunner fbAsyncRunner;
    private SharedPreferences fbPrefs;

    // Create Object of Facebook Class
    private Facebook facebook = new Facebook(APP_ID);


    // Buttons
    Button fbLoginButton;
    Button fbMyProfileButton;
    Button fbFriendButton;
    Button fbPostToWallButton;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        fbLoginButton = (Button) findViewById(R.id.login_btn);
        fbMyProfileButton = (Button) findViewById(R.id.profile_btn);
        fbPostToWallButton = (Button) findViewById(R.id.wall_btn);
        fbFriendButton = (Button) findViewById(R.id.friend_btn);
        fbAsyncRunner = new AsyncFacebookRunner(facebook);

        //Login button Clicked
        fbLoginButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                facebookLogin();

            }
        });


        // My facebook Profile info

        // Post at Facebook Wall
        fbPostToWallButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                facebookWallPost();

            }
        });

        // Post at Facebook Wall

    }

    // Function for login
    public void facebookLogin() {

        // Create shared preference to save login or not

        fbPrefs = getPreferences(MODE_PRIVATE);
        String access_token = fbPrefs.getString("access_token", null);

        // Get Expire value from shared preference
        long expires = fbPrefs.getLong("access_expires", 0);

        if (access_token != null) {
            // Get facebook access token to call facebook Graph api
            facebook.setAccessToken(access_token);

            fbLoginButton.setVisibility(View.INVISIBLE);

            // Make button visible
            fbMyProfileButton.setVisibility(View.VISIBLE);
            fbPostToWallButton.setVisibility(View.VISIBLE);
            fbFriendButton.setVisibility(View.VISIBLE);
        }

        if (expires != 0) {
            facebook.setAccessExpires(expires);
        }

        if (!facebook.isSessionValid()) {
            facebook.authorize(this,
                    new String[] { "email", "publish_stream","user_friends" },
                    new DialogListener() {

                        @Override
                        public void onCancel() {
                            // Here work for facebook login page cancel event
                        }

                        @Override
                        public void onComplete(Bundle values) {

                            // update Shared Preferences values
                            SharedPreferences.Editor editor = fbPrefs.edit();
                            editor.putString("access_token",
                                    facebook.getAccessToken());
                            editor.putLong("access_expires",
                                    facebook.getAccessExpires());
                            editor.commit();

                            // Make button invisible
                            fbLoginButton.setVisibility(View.INVISIBLE);

                            // Make button visible
                            fbMyProfileButton.setVisibility(View.VISIBLE);
                            fbPostToWallButton.setVisibility(View.VISIBLE);
                            fbFriendButton.setVisibility(View.VISIBLE);

                        }

                        @Override
                        public void onError(DialogError error) {
                            // Here Work for handle error

                        }

                        @Override
                        public void onFacebookError(FacebookError fberror) {
                            // Here Work for handle Facebook errors

                        }

                    });
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        facebook.authorizeCallback(requestCode, resultCode, data);
    }


    // My Profile information

    // My Freinds information


    //Post to wall function
    public void facebookWallPost() {

        facebook.dialog(this, "feed", new DialogListener() {

            @Override
            public void onFacebookError() {
            }

            @Override
            public void onError(DialogError e) {
            }

            @Override
            public void onComplete(Bundle values) {
            }

            @Override
            public void onCancel() {
            }
        });

    } */
}
