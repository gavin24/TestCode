package com.ackerman.j.gavin.ispy.Factory.Impl;

import com.ackerman.j.gavin.ispy.Domain.Story;
import com.ackerman.j.gavin.ispy.Factory.StoryFactory;

/**
 * Created by gavin.ackerman on 2016-04-28.
 */
public class StoryFactoryImpl implements StoryFactory{
    private static StoryFactoryImpl factory = null;

    private StoryFactoryImpl(){

    }

    public static StoryFactoryImpl getInstance(){
        if (factory == null)
            factory = new StoryFactoryImpl();

        return factory;
    }


    public Story createStory(Long id,String name, String tag, String text,long userId,long image)
    {
        Story ticket =  new Story.Builder().id(id).name(name).tag(tag).text(text).userId(userId).image(image)
                .build();

        return ticket;
    }
}
