package com.ackerman.j.gavin.ispy.Services;

import com.ackerman.j.gavin.ispy.Domain.Image;
import com.ackerman.j.gavin.ispy.Domain.UserImage;

import java.util.ArrayList;

/**
 * Created by gavin.ackerman on 2017-05-24.
 */

public interface UserImageService {
    UserImage addImage(UserImage animal);
    UserImage updateImage( UserImage animal);
    UserImage deleteImage(UserImage animal);
    UserImage getImage(Long d);
    ArrayList<UserImage> getAllImages( );
    UserImage getImageByUserId(Long d);
    void removeAllImages();
}
