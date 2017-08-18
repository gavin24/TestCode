package com.ackerman.j.gavin.ispy.Factory.Impl;

import com.ackerman.j.gavin.ispy.Domain.Tag;
import com.ackerman.j.gavin.ispy.Domain.User;
import com.ackerman.j.gavin.ispy.Domain.UserTags;

/**
 * Created by gavin.ackerman on 2016-08-14.
 */
public class UserTagsFactoryImpl {
    private static UserTagsFactoryImpl factory = null;

    private UserTagsFactoryImpl(){

    }

    public static UserTagsFactoryImpl getInstance(){
        if (factory == null)
            factory = new UserTagsFactoryImpl();

        return factory;
    }


    public UserTags createUserTag(Long id, String name, long userId)
    {
        UserTags ticket =  new UserTags.Builder().id(id).name(name).userid(userId)
                .build();

        return ticket;
    }
}
