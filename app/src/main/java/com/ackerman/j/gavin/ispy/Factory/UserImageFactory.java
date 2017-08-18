package com.ackerman.j.gavin.ispy.Factory;

import com.ackerman.j.gavin.ispy.Domain.Image;
import com.ackerman.j.gavin.ispy.Domain.UserImage;

/**
 * Created by gavin.ackerman on 2017-05-24.
 */

public interface UserImageFactory {
    UserImage createImage(Long id, long userId, String name, byte[] image);
}
