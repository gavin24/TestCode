package com.ackerman.j.gavin.ispy;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ackerman.j.gavin.ispy.Domain.Fame;
import com.ackerman.j.gavin.ispy.Domain.Image;
import com.ackerman.j.gavin.ispy.Domain.User;
import com.ackerman.j.gavin.ispy.Services.Impl.FameServiceImpl;
import com.ackerman.j.gavin.ispy.Services.Impl.ImageServiceImpl;
import com.ackerman.j.gavin.ispy.Services.Impl.StoryServiceImpl;
import com.ackerman.j.gavin.ispy.Services.Impl.UserServiceImpl;
import com.ackerman.j.gavin.ispy.Services.UserService;

import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * Created by gavin.ackerman on 2017-05-23.
 */

public class ViewProfile extends AppCompatActivity {
    private StoryServiceImpl storyService;
    private ImageServiceImpl imageService;
    private FameServiceImpl fameService;
    private boolean isBound = false;
    private UserService activateAccountService;
    TextView headline,story,tags,likeTextView,shareTextView,dislikeTextview,nameText;
    private ImageView ivImage;
    private long id;
    ListView mDrawerList;
    RelativeLayout mDrawerPane;
    private ActionBarDrawerToggle mDrawerToggle;
    private DrawerLayout mDrawerLayout;
    ArrayList<NavItem> mNavItems = new ArrayList<NavItem>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        StrictMode.ThreadPolicy policy = new StrictMode.
                ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.viewuserprofile_activity);

        id = getIntent().getLongExtra("id", 0);
        StoryServiceImpl storyService = new StoryServiceImpl();
        ImageServiceImpl imageService = new ImageServiceImpl();
        nameText = (TextView)findViewById(R.id.NameText);
        UserServiceImpl userService = new UserServiceImpl();
        User user = userService.getUser(id);
        likeTextView = (TextView)findViewById(R.id.likeText);
        shareTextView = (TextView)findViewById(R.id.shareText);
        dislikeTextview = (TextView)findViewById(R.id.dislikeText);
        FameServiceImpl fameService = new FameServiceImpl();
        ivImage = (ImageView) findViewById(R.id.ivImage);
        System.out.println("user Id "+user.getid());
        ArrayList<Fame> fameList = fameService.getAllFames(user.getid());

        int likes =0,shares =0,dislikes =0;
        for (int i = 0; i < fameList.size(); i++) {
            likes = likes + fameList.get(i).getLikes();
            shares = shares + fameList.get(i).getShares();
            dislikes = dislikes + fameList.get(i).getDisLikes();
        }

        Image image = imageService.getImage((long)1);
        Bitmap bmp = BitmapFactory.decodeByteArray(image.getImage(),0, image.getImage().length);
        ivImage.setImageBitmap(bmp);
        nameText.setText(user.getName() + " " +  user.getsurname());
        shareTextView.setText(String.valueOf(shares));
        likeTextView.setText(String.valueOf(likes));
        dislikeTextview.setText(String.valueOf(dislikes));

    }
    public void storiesClick(View v) {
        Intent i = new Intent(ViewProfile.this, UserStoriesList.class);
        i.putExtra("id", Long.valueOf(id));
        startActivity(i);
    }

    public void mailClick(View v) {

    }
}
