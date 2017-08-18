package com.ackerman.j.gavin.ispy.Factory.Impl;

import com.ackerman.j.gavin.ispy.Domain.ProfileInfo;
import com.ackerman.j.gavin.ispy.Domain.UserImage;
import com.ackerman.j.gavin.ispy.Factory.ProfileInfoFactory;

/**
 * Created by gavin.ackerman on 2017-05-24.
 */

public class ProfileInfoFactoryImpl implements ProfileInfoFactory {
    private static ProfileInfoFactoryImpl factory = null;

    private ProfileInfoFactoryImpl(){

    }

    public static ProfileInfoFactoryImpl getInstance(){
        if (factory == null)
            factory = new ProfileInfoFactoryImpl();

        return factory;
    }


    public ProfileInfo createProfileInfo(Long id, int age, long userid, String about)
    {
        ProfileInfo ticket =  new ProfileInfo.Builder().id(id).age(age).userid(userid).about(about)
                .build();

        return ticket;
    }
}
