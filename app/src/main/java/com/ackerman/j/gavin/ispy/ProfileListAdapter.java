package com.ackerman.j.gavin.ispy;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.ackerman.j.gavin.ispy.Domain.Image;
import com.ackerman.j.gavin.ispy.Domain.UserImage;

import java.util.ArrayList;

/**
 * Created by gavin.ackerman on 2017-05-24.
 */

public class ProfileListAdapter extends ArrayAdapter<String> {
    private final Activity context;
    private final ArrayList<String> itemname;
    private final ArrayList<UserImage> imgid;

    public ProfileListAdapter(Activity context, ArrayList<String> itemname,ArrayList<UserImage> imgid) {
        super(context, R.layout.mylist, itemname);
        // TODO Auto-generated constructor stub

        this.context=context;
        this.itemname=itemname;
        this.imgid=imgid;
    }

    public View getView(int position, View view, ViewGroup parent) {
        LayoutInflater inflater=context.getLayoutInflater();
        View rowView=inflater.inflate(R.layout.mylist, null,true);

        TextView txtTitle = (TextView) rowView.findViewById(R.id.item);
        ImageView imageView = (ImageView) rowView.findViewById(R.id.icon);

        txtTitle.setText(itemname.get(position));
        UserImage image = imgid.get(position);

        Bitmap bmp = BitmapFactory.decodeByteArray(image.getImage(),0, image.getImage().length);
        imageView.setImageBitmap(bmp);

        return rowView;

    }
}
