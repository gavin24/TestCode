package com.ackerman.j.gavin.ispy.Factory;

import com.ackerman.j.gavin.ispy.Domain.ProfileInfo;
import com.ackerman.j.gavin.ispy.Domain.Story;

/**
 * Created by gavin.ackerman on 2017-05-24.
 */

public interface ProfileInfoFactory {
    ProfileInfo createProfileInfo(Long id, int age, long userid, String about);
}
