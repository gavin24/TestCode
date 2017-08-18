package com.ackerman.j.gavin.ispy.Factory.Impl;

import com.ackerman.j.gavin.ispy.Domain.User;
import com.ackerman.j.gavin.ispy.Factory.UserFactory;

/**
 * Created by gavin.ackerman on 2016-06-21.
 */
public class UserFactoryImpl implements UserFactory {
    private static UserFactoryImpl factory = null;

    private UserFactoryImpl(){

    }

    public static UserFactoryImpl getInstance(){
        if (factory == null)
            factory = new UserFactoryImpl();

        return factory;
    }


    public User createUser(Long id, String name, String surname, String email, String password)
    {
        User ticket =  new User.Builder().id(id).name(name).surname(surname).email(email).password(password)
                .build();

        return ticket;
    }
}
