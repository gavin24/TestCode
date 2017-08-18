package com.ackerman.j.gavin.ispy.Factory;

import com.ackerman.j.gavin.ispy.Domain.Fame;
import com.ackerman.j.gavin.ispy.Domain.UserFame;

/**
 * Created by gavin.ackerman on 2017-05-24.
 */

public interface UserFameFactory {
    UserFame createUserFame(Long id, long storyId, long userId, int shares, int likes, int dislikes, int views);
}
