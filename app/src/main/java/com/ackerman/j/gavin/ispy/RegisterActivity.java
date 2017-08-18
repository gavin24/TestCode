package com.ackerman.j.gavin.ispy;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.IBinder;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.ackerman.j.gavin.ispy.Config.Util.App;
import com.ackerman.j.gavin.ispy.Config.Util.SessionManagerHelper;
import com.ackerman.j.gavin.ispy.Domain.ProfileInfo;
import com.ackerman.j.gavin.ispy.Domain.User;
import com.ackerman.j.gavin.ispy.Domain.UserImage;
import com.ackerman.j.gavin.ispy.Services.Impl.ProfileInfoServiceImpl;
import com.ackerman.j.gavin.ispy.Services.Impl.UserImageServiceImpl;
import com.ackerman.j.gavin.ispy.Services.Impl.UserServiceImpl;
import com.ackerman.j.gavin.ispy.Services.UserService;
import com.facebook.FacebookSdk;

import java.io.ByteArrayOutputStream;
import java.util.Calendar;

/**
 * Created by gavin.ackerman on 2016-08-15.
 */
public class RegisterActivity extends AppCompatActivity {
    private UserServiceImpl activateService;
    private boolean isBound = false;
    private UserService activateAccountService;
    EditText Email,passWord,Name,surName;
    Intent loginIntent;
    SessionManagerHelper sess;

    private boolean isValid = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        StrictMode.ThreadPolicy policy = new StrictMode.
                ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        super.onCreate(savedInstanceState);
        FacebookSdk.sdkInitialize(getApplicationContext());
        setContentView(R.layout.register_main);
        activateService = UserServiceImpl.getInstance();
        Email = (EditText)findViewById(R.id.email);
        passWord = (EditText)findViewById(R.id.password);
        Name = (EditText)findViewById(R.id.name);
        surName = (EditText)findViewById(R.id.surname);
        sess = new SessionManagerHelper(getApplicationContext());
    }

    public void registerClick(View v) {
        String name = Name.getText().toString();
        String surname = surName.getText().toString();
        String email = Email.getText().toString();
        String password = passWord.getText().toString();
        if(name == "" || surname == "" || email == "" || password == "") {
            Toast.makeText(getApplicationContext(),
                    "Please Fill in all fields", Toast.LENGTH_LONG)
                    .show();

        }
        else
        {
            UserImageServiceImpl userImageService = new UserImageServiceImpl();

            UserServiceImpl userService = new UserServiceImpl();
            isValid = userService.isAuthentic(email,password);

            if(isValid == false) {
                User user = new User.Builder()

                        .name(name)
                        .surname(surname)
                        .email(email)
                        .password(password)
                        .build();
               User userId = userService.addUser(user);
                Intent i = new Intent(this,StoryActivity.class);
                Calendar calendar = Calendar.getInstance();
                int mseconds = calendar.get(Calendar.MILLISECOND);
                String imgName = "Ispy_profile_"+ name + mseconds;
                Drawable drawable = getResources().getDrawable(R.drawable.ic_person_24dp);
                Bitmap bitmap = ((BitmapDrawable) drawable).getBitmap();

                byte[] defaultImageBytes = getBytesFromBitmap(bitmap);
                UserImage userImage = new UserImage.Builder()
                        .image(defaultImageBytes)
                        .name(imgName)
                        .userId(userId.getid())
                        .build();
                userImageService.addImage(userImage);

                sess.createLoginSession(email, password);
                startActivity(i);
            }
            else
            {
                Toast.makeText(getApplicationContext(),
                        "User is already registered", Toast.LENGTH_LONG)
                        .show();
            }

        }

    }
    public void registerFacebookClick(View v) {

        Intent i = new Intent(this,FacebookRegisterActivity.class);

        startActivity(i);

    }
    public byte[] getBytesFromBitmap(Bitmap bitmap) {
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 90, stream);
        return stream.toByteArray();
    }
}
