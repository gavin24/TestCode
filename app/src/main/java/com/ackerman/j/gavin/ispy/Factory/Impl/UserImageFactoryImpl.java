package com.ackerman.j.gavin.ispy.Factory.Impl;

import com.ackerman.j.gavin.ispy.Domain.Image;
import com.ackerman.j.gavin.ispy.Domain.UserImage;
import com.ackerman.j.gavin.ispy.Factory.UserImageFactory;

/**
 * Created by gavin.ackerman on 2017-05-24.
 */

public class UserImageFactoryImpl implements UserImageFactory {
    private static UserImageFactoryImpl factory = null;

    private UserImageFactoryImpl(){

    }

    public static UserImageFactoryImpl getInstance(){
        if (factory == null)
            factory = new UserImageFactoryImpl();

        return factory;
    }


    public UserImage createImage(Long id, long userId, String name, byte[] image)
    {
        UserImage ticket =  new UserImage.Builder().id(id).name(name).userId(userId).image(image)
                .build();

        return ticket;
    }
}
