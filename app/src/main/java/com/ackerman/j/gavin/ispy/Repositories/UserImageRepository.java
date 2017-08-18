package com.ackerman.j.gavin.ispy.Repositories;

import com.ackerman.j.gavin.ispy.Domain.Fame;
import com.ackerman.j.gavin.ispy.Domain.Image;
import com.ackerman.j.gavin.ispy.Domain.UserImage;
import com.ackerman.j.gavin.ispy.Factory.Repository;

/**
 * Created by gavin.ackerman on 2017-05-24.
 */

public interface UserImageRepository extends Repository<UserImage,Long> {
    UserImage findByUserId(Long id);
}
