package com.ackerman.j.gavin.ispy.Factory.Impl;

import com.ackerman.j.gavin.ispy.Domain.Fame;
import com.ackerman.j.gavin.ispy.Factory.FameFactory;

/**
 * Created by gavin.ackerman on 2016-04-28.
 */
public class FameFactoryImpl implements FameFactory{
    private static FameFactoryImpl factory = null;

    private FameFactoryImpl(){

    }

    public static FameFactoryImpl getInstance(){
        if (factory == null)
            factory = new FameFactoryImpl();

        return factory;
    }


    public Fame createFame(Long id,long storyId, long userId, int shares,int likes,int dislikes,int views)
    {
        Fame ticket =  new Fame.Builder().id(id).storyId(storyId).userId(userId).shares(shares).likes(likes).dislikes(dislikes).views(views)
                .build();

        return ticket;
    }
}
