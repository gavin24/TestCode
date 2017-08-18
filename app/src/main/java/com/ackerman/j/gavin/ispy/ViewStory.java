package com.ackerman.j.gavin.ispy;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.ackerman.j.gavin.ispy.Config.Util.SessionManagerHelper;
import com.ackerman.j.gavin.ispy.Domain.Fame;
import com.ackerman.j.gavin.ispy.Domain.Image;
import com.ackerman.j.gavin.ispy.Domain.Story;
import com.ackerman.j.gavin.ispy.Domain.User;
import com.ackerman.j.gavin.ispy.Fragments.PreferencesFragment;
import com.ackerman.j.gavin.ispy.Services.Impl.FameServiceImpl;
import com.ackerman.j.gavin.ispy.Services.Impl.ImageServiceImpl;
import com.ackerman.j.gavin.ispy.Services.Impl.StoryServiceImpl;
import com.ackerman.j.gavin.ispy.Services.Impl.UserServiceImpl;
import com.ackerman.j.gavin.ispy.Services.StoryService;
import com.ackerman.j.gavin.ispy.Services.UserService;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by gavin.ackerman on 2016-08-15.
 */
public class ViewStory extends AppCompatActivity {
    private StoryServiceImpl storyService;
    private ImageServiceImpl imageService;
    private FameServiceImpl fameService;
    private boolean isBound = false;
    private UserService activateAccountService;
    TextView headline,story,tags,likeTextView,shareTextView,dislikeTextview,userNameTextView;
    private ImageView ivImage;
    private long id;
    long fameid;
    private long fameId;
    long userProfileId;
    ListView mDrawerList;
    RelativeLayout mDrawerPane;
    private ActionBarDrawerToggle mDrawerToggle;
    private DrawerLayout mDrawerLayout;
    ArrayList<NavItem> mNavItems = new ArrayList<NavItem>();
    int likes,dislikes,shares;
    SessionManagerHelper sess;
        @Override
        protected void onCreate(Bundle savedInstanceState) {

            StrictMode.ThreadPolicy policy = new StrictMode.
                    ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
            super.onCreate(savedInstanceState);
            setContentView(R.layout.viewstory_activity);
            mNavItems.add(new NavItem("Home", "newsfeed", R.drawable.ic_newsfeed_24dp));
            mNavItems.add(new NavItem("Story", "Create new story", R.drawable.ic_brush_dp));
            mNavItems.add(new NavItem("Profile", "Check your profile", R.drawable.ic_arrow_24dp));
            mNavItems.add(new NavItem("Logout", "Sign out of the app", R.drawable.ic_arrow_24dp));
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            // DrawerLayout
            mDrawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);
            sess = new SessionManagerHelper(getApplicationContext());
            // Populate the Navigtion Drawer with options
            mDrawerPane = (RelativeLayout) findViewById(R.id.drawerPane);
            mDrawerList = (ListView) findViewById(R.id.navList);
            DrawerListAdapter adapter = new DrawerListAdapter(this, mNavItems);
            mDrawerList.setAdapter(adapter);

            // Drawer Item click listeners
            mDrawerList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    selectItemFromDrawer(position);
                }
            });


            mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, R.string.drawer_open, R.string.drawer_close) {
                @Override
                public void onDrawerOpened(View drawerView) {
                    super.onDrawerOpened(drawerView);
                    invalidateOptionsMenu();
                }

                @Override
                public void onDrawerClosed(View drawerView) {
                    super.onDrawerClosed(drawerView);
                    invalidateOptionsMenu();
                }
            };

          //  mDrawerLayout.setDrawerListener(mDrawerToggle);
            Bundle extras = getIntent().getExtras();
            id = getIntent().getLongExtra("id", 0);
            StoryServiceImpl storyService = new StoryServiceImpl();
            ImageServiceImpl imageService = new ImageServiceImpl();
            UserServiceImpl userService = new UserServiceImpl();
            headline = (TextView)findViewById(R.id.headlineText);
            tags = (TextView)findViewById(R.id.tagsText);
            story = (TextView)findViewById(R.id.storyText);
            likeTextView = (TextView)findViewById(R.id.likeText);
            shareTextView = (TextView)findViewById(R.id.shareText);
            dislikeTextview = (TextView)findViewById(R.id.dislikeText);
            userNameTextView = (TextView)findViewById(R.id.userNameText);

            Story story2 = storyService.getStory(id);
            Image image = imageService.getImage(story2.getImage());
            ivImage = (ImageView) findViewById(R.id.ivImage);
            Bitmap bmp = BitmapFactory.decodeByteArray(image.getImage(),0, image.getImage().length);
            ivImage.setImageBitmap(bmp);
            headline.setText(story2.getName());
            tags.setText(story2.getTag());
            story.setText(story2.getText());
            userProfileId = story2.getuserId();
            FameServiceImpl  fameService = new FameServiceImpl();
            User user = userService.getUser(story2.getuserId());
            Fame    fame  = fameService.getFameByStoryId(id);
            Fame    fame2  = fameService.getFameByStoryId(story2.getId());
            likeTextView.setText(String.valueOf(fame.getLikes()));
            shareTextView.setText(String.valueOf(fame.getShares()));
            dislikeTextview.setText(String.valueOf(fame.getDisLikes()));
            userNameTextView.setText(user.getName() + " " + user.getsurname());
            fameid = fame.getId();


        }
    private void selectItemFromDrawer(int position) {
        android.app.Fragment fragment = new android.app.Fragment();

        android.app.FragmentManager fragmentManager = getFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.mainContent, fragment)
                .commit();

        mDrawerList.setItemChecked(position, true);
        setTitle(mNavItems.get(position).mTitle);
        switch (position){
            case 0:
                startActivity(new Intent(ViewStory.this, NewsfeedActivity.class));
                mDrawerLayout.closeDrawers();
                break;
            case 1:
                startActivity(new Intent(ViewStory.this, StoryActivity.class));
                break;
            case 2:
                startActivity(new Intent(ViewStory.this, ProfileActivity.class));
                //  mDrawerLayout.closeDrawers();
                break;
            case 3:
                sess.logoutUser();

                finish();

        }
        // Close the drawer
        mDrawerLayout.closeDrawer(mDrawerPane);
    }
    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        mDrawerToggle.syncState();
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Pass the event to ActionBarDrawerToggle
        // If it returns true, then it has handled
        // the nav drawer indicator touch event
        if (mDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }

        // Handle your other action bar items...

        return super.onOptionsItemSelected(item);
    }
    public void submitClick(View v) {



    }
    public void likeClick(View v) {
        likes = 1;
        likeTextView.setText(likeTextView.getText() + String.valueOf(likes));
        FameServiceImpl fameService1 = new FameServiceImpl();

            Fame fameCopy = fameService1.getFame(fameid);
            Fame fame = new Fame.Builder()
                    .copy(fameCopy)
                    .likes(fameCopy.getLikes() + 1)
                    .build();
        fameService1.updateFame(fame);

    }
    public void nameClick(View v) {

        Intent i = new Intent(ViewStory.this, ViewProfile.class);
        i.putExtra("id", Long.valueOf(userProfileId));
        startActivity(i);

    }

    public void dislikeClick(View v) {
        dislikes = 1;
        dislikeTextview.setText(dislikeTextview.getText() + String.valueOf(dislikes));
        FameServiceImpl fameService1 = new FameServiceImpl();
            Fame fameCopy = fameService1.getFame(fameid);
            Fame fame = new Fame.Builder()
                    .copy(fameCopy)
                    .likes(fameCopy.getDisLikes() + 1)
                    .build();
        fameService1.updateFame(fame);

    }
    public void shareClick(View v) {
        shares = 1;
        shareTextView.setText(shareTextView.getText() + String.valueOf(shares));
        FameServiceImpl fameService1 = new FameServiceImpl();
            Fame fameCopy = fameService1.getFame(fameid);
            Fame fame = new Fame.Builder()
                    .copy(fameCopy)
                    .likes(fameCopy.getShares() + 1)
                    .build();
        fameService1.updateFame(fame);

    }
}
