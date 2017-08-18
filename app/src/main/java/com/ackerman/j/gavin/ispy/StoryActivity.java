package com.ackerman.j.gavin.ispy;

import android.app.AlertDialog;
import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.IBinder;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Calendar;
import java.util.HashMap;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;

import com.ackerman.j.gavin.ispy.Config.Util.App;
import com.ackerman.j.gavin.ispy.Config.Util.SessionManagerHelper;
import com.ackerman.j.gavin.ispy.Config.Util.Utility;
import com.ackerman.j.gavin.ispy.Domain.Fame;
import com.ackerman.j.gavin.ispy.Domain.Image;
import com.ackerman.j.gavin.ispy.Domain.Story;
import com.ackerman.j.gavin.ispy.Domain.User;
import com.ackerman.j.gavin.ispy.Services.Impl.FameServiceImpl;
import com.ackerman.j.gavin.ispy.Services.Impl.ImageServiceImpl;
import com.ackerman.j.gavin.ispy.Services.Impl.StoryServiceImpl;
import com.ackerman.j.gavin.ispy.Services.Impl.UserServiceImpl;
import com.ackerman.j.gavin.ispy.Services.UserService;

/**
 * Created by gavin.ackerman on 2016-08-15.
 */
public class StoryActivity extends AppCompatActivity {
   // private StoryServiceImpl storyService;
   // private ImageServiceImpl imageService;
   // private UserServiceImpl userService;
    private boolean isBound = false;
    private UserService activateAccountService;
    EditText headline,story,tags;

    private int REQUEST_CAMERA = 0, SELECT_FILE = 1;
    private Button btnSelect;
    byte [] picture;
    private ImageView ivImage2;
    private String userChoosenTask;
    SessionManagerHelper sess;
    User user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        StrictMode.ThreadPolicy policy = new StrictMode.
                ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.createstory_activity);
        sess = new SessionManagerHelper(getApplicationContext());
        headline = (EditText)findViewById(R.id.headlineText);
        tags = (EditText)findViewById(R.id.tagsText);
        story = (EditText)findViewById(R.id.storyText);
        HashMap<String, String> userDetails = sess.getUserDetails();
        ivImage2 = (ImageView) findViewById(R.id.ivImage);
        UserServiceImpl userService = new UserServiceImpl();
        user = userService.getUserByEmail(userDetails.get("email"));

    }

    public void submitClick(View v) {
        String headlineText = headline.getText().toString();
        String storyText = story.getText().toString();
        String tagsText = tags.getText().toString();
        StoryServiceImpl  storyService = new StoryServiceImpl();
        ImageServiceImpl imageService = new ImageServiceImpl();
        FameServiceImpl fameService = new FameServiceImpl();

        Calendar calendar = Calendar.getInstance();
        int mseconds = calendar.get(Calendar.MILLISECOND);
        String imgName = "Ispy" + mseconds;
        Image image = new Image.Builder()
                .image(picture)
                .name(imgName)
                .userId(user.getid())
                .build();
       Image imageId = imageService.addImage(image);

        Story story = new Story.Builder()
                .image(imageId.getId())
                .name(headlineText)
                .tag(tagsText)
                .text(storyText)
                .userId(user.getid())
                .build();


     Story storyId = storyService.addStory(story);

        Fame fameNew = new Fame.Builder()
                .likes(0)
                .dislikes(0)
                .shares(0)
                .storyId(storyId.getId())
                .userId(user.getid())
                .build();
       Fame fameCopy = fameService.addFame(fameNew);

        Intent intentLogin = new Intent(this, NewsfeedActivity.class);
        startActivity(intentLogin);
        finish();

    }
    public void selectImage(View v) {
        final CharSequence[] items = { "Take Photo", "Choose from Library",
                "Cancel" };
        AlertDialog.Builder builder = new AlertDialog.Builder(StoryActivity.this);
        builder.setTitle("Add Photo!");
        builder.setItems(items, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int item) {
                boolean result= Utility.checkPermission(StoryActivity.this);
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

        ivImage2.setImageBitmap(thumbnail);

    }


    private void onSelectFromGalleryResult(Intent data) {

        Bitmap bm=null;
        if (data != null) {
            try {
                bm = MediaStore.Images.Media.getBitmap(getApplicationContext().getContentResolver(), data.getData());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        ivImage2.setImageBitmap(bm);
        picture = getBytesFromBitmap(bm);
    }
    public byte[] getBytesFromBitmap(Bitmap bitmap) {
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 90, stream);
        return stream.toByteArray();
    }
}
