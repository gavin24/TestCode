package com.ackerman.j.gavin.ispy.Services;

import com.ackerman.j.gavin.ispy.Domain.Image;
import com.ackerman.j.gavin.ispy.Domain.ProfileInfo;

import java.util.ArrayList;

/**
 * Created by gavin.ackerman on 2017-05-24.
 */

public interface ProfileInfoService {
    ProfileInfo addProfileInfo(ProfileInfo animal);
    ProfileInfo updateProfileInfo( ProfileInfo animal);
    ProfileInfo deleteProfileInfo( ProfileInfo animal);
    ProfileInfo getProfileInfo(Long d);
    ArrayList<ProfileInfo> getAllProfileInfo( );
    void removeAllProfileInfo();
}
