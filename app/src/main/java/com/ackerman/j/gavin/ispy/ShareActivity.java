package com.ackerman.j.gavin.ispy;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.ackerman.j.gavin.ispy.Config.Util.SessionManagerHelper;
import com.ackerman.j.gavin.ispy.Domain.Image;
import com.ackerman.j.gavin.ispy.Domain.Story;
import com.ackerman.j.gavin.ispy.Domain.User;
import com.ackerman.j.gavin.ispy.Domain.UserImage;
import com.ackerman.j.gavin.ispy.Services.Impl.ImageServiceImpl;
import com.ackerman.j.gavin.ispy.Services.Impl.StoryServiceImpl;
import com.ackerman.j.gavin.ispy.Services.Impl.UserImageServiceImpl;
import com.ackerman.j.gavin.ispy.Services.Impl.UserServiceImpl;
import com.ackerman.j.gavin.ispy.Services.StoryService;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by gavin.ackerman on 2017-05-24.
 */

public class ShareActivity extends AppCompatActivity {
    private StoryServiceImpl activateService;
    private boolean isBound = false;
    private StoryService stoService;
    private ImageServiceImpl imageService;
    ListView listView;
    Story newsItem;
    List<User> newsItems;
    FloatingActionButton fab;
    ArrayList<String> values = new ArrayList<String>();
    ArrayList<UserImage> images = new ArrayList<UserImage>();
    ListView mDrawerList;
    RelativeLayout mDrawerPane;
    private ActionBarDrawerToggle mDrawerToggle;
    private DrawerLayout mDrawerLayout;
    ArrayList<NavItem> mNavItems = new ArrayList<NavItem>();
    SessionManagerHelper sess;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
       /* StrictMode.ThreadPolicy policy = new StrictMode.
                ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.newsfeed_activity);
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
        System.out.println(">>>>>>>>>>>>>>>>>>>>>> "+mDrawerList);
        DrawerListAdapter adapter = new DrawerListAdapter(this, mNavItems);
        System.out.println(">>>>>>>>>>>>>>>>>>>>>> "+adapter);
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

        mDrawerLayout.setDrawerListener(mDrawerToggle);

        Drawable drawable = getResources().getDrawable(R.drawable.untitled);
        Bitmap bitmap = ((BitmapDrawable) drawable).getBitmap();
        System.out.println(">>>>>>>>>>>>>>>>>>>>>> "+adapter);
        byte[] defaultImageBytes = getBytesFromBitmap(bitmap);
        UserImage fakeImage = new UserImage.Builder()
                .name("fake image")
                .image(defaultImageBytes)
                .build();
        System.out.println(">>>>>>>>>>>>>>>>>>>>>> "+defaultImageBytes);
        UserServiceImpl service = new UserServiceImpl();
        newsItems = service.getAllStorys();
        UserImageServiceImpl imageService = new UserImageServiceImpl();

        ArrayList<UserImage> img = imageService.getAllImages();
        System.out.println("ImAGE SIZE  "+img.size());
        if(img.size() == 0)
        {
            images.add(fakeImage);
        }

        if (newsItems == null) {
            values.add("First News Story");
        } else {
            for (int x = 0; x < newsItems.size(); x++) {
                System.out.println("ImAGE iD  "+  newsItems.get(x).getImage());

                values.add(newsItems.get(x).getName());
                UserImage image = imageService.getImage(newsItems.get(x).getImage());
                images.add(image);
            }
        }





        CustomListAdapter adapter2 = new CustomListAdapter(this, values, images);
        listView = (ListView) findViewById(R.id.list);
        listView.setAdapter(adapter2);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                // TODO Auto-generated method stub
                String Slecteditem = values.get(+position);
                Toast.makeText(getApplicationContext(), Slecteditem, Toast.LENGTH_SHORT).show();

            }
        });

*/

    }


    public byte[] getBytesFromBitmap(Bitmap bitmap) {
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 90, stream);
        return stream.toByteArray();
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
                startActivity(new Intent(ShareActivity.this, NewsfeedActivity.class));
                mDrawerLayout.closeDrawers();
                break;
            case 1:
                startActivity(new Intent(ShareActivity.this, StoryActivity.class));
                break;
            case 2:
                startActivity(new Intent(ShareActivity.this, ProfileActivity.class));
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
}
