package com.ackerman.j.gavin.ispy.Factory.Impl;

import com.ackerman.j.gavin.ispy.Domain.ProfileInfo;
import com.ackerman.j.gavin.ispy.Domain.Subscriber;
import com.ackerman.j.gavin.ispy.Factory.SubscriberFactory;

/**
 * Created by gavin.ackerman on 2017-05-24.
 */

public class SubscriberFactoryImpl implements SubscriberFactory {
    private static SubscriberFactoryImpl factory = null;

    private SubscriberFactoryImpl(){

    }

    public static SubscriberFactoryImpl getInstance(){
        if (factory == null)
            factory = new SubscriberFactoryImpl();

        return factory;
    }


    public Subscriber createSubscriber(Long id, int status, long userId, long subscriberuserid)
    {
        Subscriber ticket =  new Subscriber.Builder().id(id).status(status).userid(userId).subscriberuserid(subscriberuserid)
                .build();

        return ticket;
    }
}
