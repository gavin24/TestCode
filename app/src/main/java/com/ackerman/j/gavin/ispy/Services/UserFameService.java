package com.ackerman.j.gavin.ispy.Services;

import com.ackerman.j.gavin.ispy.Domain.Fame;
import com.ackerman.j.gavin.ispy.Domain.UserFame;

import java.util.ArrayList;

/**
 * Created by gavin.ackerman on 2017-05-24.
 */

public interface UserFameService {
    UserFame addFame(UserFame animal);
    UserFame updateFame( UserFame animal);
    UserFame deleteFame( UserFame animal);
    UserFame getFame(Long d);
    ArrayList<UserFame> getAllFames( );
    ArrayList<UserFame> getAllFames(Long id);
    void removeAllFames();
    UserFame getFameByStoryId(Long Id);
}
