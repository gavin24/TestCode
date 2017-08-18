package com.ackerman.j.gavin.ispy.Services;

import com.ackerman.j.gavin.ispy.Domain.Tag;
import com.ackerman.j.gavin.ispy.Domain.UserTags;

import java.util.ArrayList;

/**
 * Created by gavin.ackerman on 2016-08-14.
 */
public interface UserTagsService {
    UserTags addUserTags(UserTags animal);
    UserTags updateUserTags( UserTags animal);
    UserTags deleteUserTags(UserTags animal);
    UserTags getUserTags(Long d);
    ArrayList<UserTags> getAllUserTags( );
    void removeAllUserTags();
}
