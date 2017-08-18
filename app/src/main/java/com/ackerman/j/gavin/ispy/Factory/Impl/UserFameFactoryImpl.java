package com.ackerman.j.gavin.ispy.Factory.Impl;

import com.ackerman.j.gavin.ispy.Domain.Fame;
import com.ackerman.j.gavin.ispy.Domain.UserFame;
import com.ackerman.j.gavin.ispy.Factory.UserFactory;
import com.ackerman.j.gavin.ispy.Factory.UserFameFactory;

/**
 * Created by gavin.ackerman on 2017-05-24.
 */

public class UserFameFactoryImpl implements UserFameFactory {
    private static UserFameFactoryImpl factory = null;

    private UserFameFactoryImpl(){

    }

    public static UserFameFactoryImpl getInstance(){
        if (factory == null)
            factory = new UserFameFactoryImpl();

        return factory;
    }


    public UserFame createUserFame(Long id, long storyId, long userId, int shares, int likes, int dislikes, int views)
    {
        UserFame ticket =  new UserFame.Builder().id(id).storyId(storyId).userId(userId).shares(shares).likes(likes).dislikes(dislikes).views(views)
                .build();

        return ticket;
    }
}
