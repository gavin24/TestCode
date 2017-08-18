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

import com.ackerman.j.gavin.ispy.Domain.Image;
import com.ackerman.j.gavin.ispy.Domain.Story;
import com.ackerman.j.gavin.ispy.Services.Impl.ImageServiceImpl;
import com.ackerman.j.gavin.ispy.Services.Impl.StoryServiceImpl;
import com.ackerman.j.gavin.ispy.Services.StoryService;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by gavin.ackerman on 2017-05-19.
 */

public class UserStoriesList extends AppCompatActivity {
    private StoryServiceImpl activateService;
    private boolean isBound = false;
    private StoryService stoService;
    private ImageServiceImpl imageService;
    ListView listView;
    Story newsItem;
    List<Story> newsItems;
    FloatingActionButton fab;
    ArrayList<String> values = new ArrayList<String>();
    ArrayList<Image> images = new ArrayList<Image>();
    ListView mDrawerList;
    RelativeLayout mDrawerPane;
    private ActionBarDrawerToggle mDrawerToggle;
    private DrawerLayout mDrawerLayout;
    ArrayList<NavItem> mNavItems = new ArrayList<NavItem>();
    private long id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        StrictMode.ThreadPolicy policy = new StrictMode.
                ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.userstories_activity);
        mNavItems.add(new NavItem("Home", "newsfeed", R.drawable.ic_newsfeed_24dp));
        mNavItems.add(new NavItem("Story", "Create new story", R.drawable.ic_brush_dp));
        mNavItems.add(new NavItem("Profile", "Check your profile", R.drawable.ic_arrow_24dp));
        mNavItems.add(new NavItem("Logout", "Sign out of the app", R.drawable.ic_arrow_24dp));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        // DrawerLayout
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);

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

        mDrawerLayout.setDrawerListener(mDrawerToggle);
        id = getIntent().getLongExtra("id", 0);
        Drawable drawable = getResources().getDrawable(R.drawable.untitled);
        Bitmap bitmap = ((BitmapDrawable) drawable).getBitmap();
        byte[] defaultImageBytes = getBytesFromBitmap(bitmap);

        Image fakeImage = new Image.Builder()
                .name("fake image")
                .image(defaultImageBytes)
                .build();
        StoryServiceImpl service = new StoryServiceImpl();

        newsItems = service.getAllStorys(id);
        ImageServiceImpl imageService = new ImageServiceImpl();
        ArrayList<Image> img = imageService.getAllImages();
        if(img.size() == 0)
        {
            images.add(fakeImage);
        }

        if (newsItems == null) {
            values.add("First News Story");
        } else {
            for (int x = 0; x < newsItems.size(); x++) {
                values.add(newsItems.get(x).getName());
                Image image = imageService.getImage(newsItems.get(x).getImage());
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
                Story clickStory = newsItems.get(+position);
                System.out.println("story id "+ clickStory.getId());
                Intent i = new Intent(UserStoriesList.this, ViewStory.class);
                i.putExtra("id", clickStory.getId());
                startActivity(i);
            }
        });

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
