package com.ackerman.j.gavin.ispy;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Environment;
import android.os.StrictMode;
import android.provider.MediaStore;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.PopupMenu;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.ackerman.j.gavin.ispy.Config.Util.Utility;
import com.ackerman.j.gavin.ispy.Domain.Fame;
import com.ackerman.j.gavin.ispy.Domain.Image;
import com.ackerman.j.gavin.ispy.Domain.Story;
import com.ackerman.j.gavin.ispy.Domain.User;
import com.ackerman.j.gavin.ispy.Domain.UserImage;
import com.ackerman.j.gavin.ispy.Services.Impl.FameServiceImpl;
import com.ackerman.j.gavin.ispy.Services.Impl.ImageServiceImpl;
import com.ackerman.j.gavin.ispy.Services.Impl.StoryServiceImpl;
import com.ackerman.j.gavin.ispy.Services.Impl.UserImageServiceImpl;
import com.ackerman.j.gavin.ispy.Services.Impl.UserServiceImpl;
import com.ackerman.j.gavin.ispy.Services.UserService;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

import static com.ackerman.j.gavin.ispy.Config.DbConstants.GlobalContext.context;

/**
 * Created by gavin.ackerman on 2016-08-15.
 */
public class ProfileActivity  extends AppCompatActivity implements PopupMenu.OnMenuItemClickListener {
  //  private StoryServiceImpl storyService;
   // private ImageServiceImpl imageService;
   // private FameServiceImpl fameService;
    private boolean isBound = false;
   // private UserService activateAccountService;
    TextView headline,story,tags,likeTextView,shareTextView,dislikeTextview,nameText;
    private ImageView ivImage;
    private long id;
    ListView mDrawerList;
    RelativeLayout mDrawerPane;
    private ActionBarDrawerToggle mDrawerToggle;
    private DrawerLayout mDrawerLayout;
    ArrayList<NavItem> mNavItems = new ArrayList<NavItem>();
    private int REQUEST_CAMERA = 0, SELECT_FILE = 1;
    byte [] picture;
    private String userChoosenTask;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        StrictMode.ThreadPolicy policy = new StrictMode.
                ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile_activity);

      ////  Bundle extras = getIntent().getExtras();
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
        ArrayList<Fame> fameList = fameService.getAllFames(user.getid());
        int likes =0,shares =0,dislikes =0;
        for (Fame fame : fameList) {
            likes = likes + fame.getLikes();
            shares = shares + fame.getShares();
            dislikes = dislikes + fame.getDisLikes();
        }
        Image image = imageService.getImage((long)1);
        Bitmap bmp = BitmapFactory.decodeByteArray(image.getImage(),0, image.getImage().length);
        nameText.setText(user.getName() + " " + user.getsurname());
        shareTextView.setText(String.valueOf(shares));
        likeTextView.setText(String.valueOf(likes));
        dislikeTextview.setText(String.valueOf(dislikes));
        findViewById(R.id.ivImage).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PopupMenu popupMenu = new PopupMenu(ProfileActivity.this, view);
                popupMenu.setOnMenuItemClickListener(ProfileActivity.this);
                popupMenu.inflate(R.menu.imageoptions_list);
                popupMenu.show();
            }
        });
    }
    public void storiesClick(View v) {
        Intent i = new Intent(ProfileActivity.this, ProfileStoriesActivity.class);
        i.putExtra("id", Long.valueOf(id));
        startActivity(i);


    }

    public void imageViewClick(View v) {



    }

    public void selectImage(View v) {
        final CharSequence[] items = { "Take Photo", "Choose from Library",
                "Cancel" };
        AlertDialog.Builder builder = new AlertDialog.Builder(ProfileActivity.this);
        builder.setTitle("Add Photo!");
        builder.setItems(items, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int item) {
                boolean result= Utility.checkPermission(ProfileActivity.this);
                if (items[item].equals("Take Photo")) {
                    userChoosenTask="Take Photo";
                    if(result)
                        cameraIntent();
                } else if (items[item].equals("Choose from Library")) {
                    userChoosenTask="Choose from Library";
                    if(result)
                        galleryIntent();
                } else if (items[item].equals("Cancel")) {
                    dialog.dismiss();
                }
            }
        });
        builder.show();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        switch (requestCode) {
            case Utility.MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    if(userChoosenTask.equals("Take Photo"))
                        cameraIntent();
                    else if(userChoosenTask.equals("Choose from Library"))
                        galleryIntent();
                } else {
                    //code for deny
                }
                break;
        }
    }

    private void galleryIntent()
    {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);//
        startActivityForResult(Intent.createChooser(intent, "Select File"),SELECT_FILE);
    }

    private void cameraIntent()
    {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent, REQUEST_CAMERA);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == Activity.RESULT_OK) {
            if (requestCode == SELECT_FILE)
                onSelectFromGalleryResult(data);
            else if (requestCode == REQUEST_CAMERA)
                onCaptureImageResult(data);
        }
    }

    private void onCaptureImageResult(Intent data) {
        Bitmap thumbnail = (Bitmap) data.getExtras().get("data");
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        thumbnail.compress(Bitmap.CompressFormat.JPEG, 90, bytes);

        File destination = new File(Environment.getExternalStorageDirectory(),
                System.currentTimeMillis() + ".jpg");
        picture = bytes.toByteArray();
        Log.v("Log", picture.toString());
        FileOutputStream fo;
        try {
            destination.createNewFile();
            fo = new FileOutputStream(destination);
            fo.write(bytes.toByteArray());
            fo.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        ivImage.setImageBitmap(thumbnail);
        UserImageServiceImpl userImageService = new UserImageServiceImpl();
        UserImage oldImage = userImageService.getImageByUserId(id);
        UserImage userImage = new UserImage.Builder()
                .copy(oldImage)
                .image(picture)
                .build();
        userImageService.updateImage(userImage);

    }

    @SuppressWarnings("deprecation")
    private void onSelectFromGalleryResult(Intent data) {

        Bitmap bm=null;
        if (data != null) {
            try {
                bm = MediaStore.Images.Media.getBitmap(getApplicationContext().getContentResolver(), data.getData());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        ivImage.setImageBitmap(bm);
        picture = getBytesFromBitmap(bm);
        UserImageServiceImpl userImageService = new UserImageServiceImpl();
        UserImage oldImage = userImageService.getImageByUserId(id);
        UserImage userImage = new UserImage.Builder()
                .copy(oldImage)
                .image(picture)
                .build();
                userImageService.updateImage(userImage);

    }
    public byte[] getBytesFromBitmap(Bitmap bitmap) {
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 90, stream);
        return stream.toByteArray();
    }

    public boolean onMenuItemClick(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.editPhot:
                Toast.makeText(this, "Edit", Toast.LENGTH_SHORT).show();
                final CharSequence[] items = { "Take Photo", "Choose from Library",
                        "Cancel" };
                AlertDialog.Builder builder = new AlertDialog.Builder(ProfileActivity.this);
                builder.setTitle("Add Photo!");
                builder.setItems(items, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int item) {
                        boolean result= Utility.checkPermission(ProfileActivity.this);
                        if (items[item].equals("Take Photo")) {
                            userChoosenTask="Take Photo";
                            if(result)
                                cameraIntent();
                        } else if (items[item].equals("Choose from Library")) {
                            userChoosenTask="Choose from Library";
                            if(result)
                                galleryIntent();
                        } else if (items[item].equals("Cancel")) {
                            dialog.dismiss();
                        }
                    }
                });
                builder.show();
                return true;
            case R.id.removePhoto:
                Toast.makeText(this, "Remove", Toast.LENGTH_SHORT).show();
                Drawable drawable = getResources().getDrawable(R.drawable.ic_person_24dp);
                Bitmap bitmap = ((BitmapDrawable) drawable).getBitmap();

                byte[] defaultImageBytes = getBytesFromBitmap(bitmap);
                UserImageServiceImpl userImageService = new UserImageServiceImpl();
                UserImage oldImage = userImageService.getImageByUserId(id);
                UserImage userImage = new UserImage.Builder()
                        .copy(oldImage)
                        .image(defaultImageBytes)
                        .build();
                userImageService.updateImage(userImage);


                return true;
        default:return true;
        }

    }

}
