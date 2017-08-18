package com.ackerman.j.gavin.ispy.Factory;

import com.ackerman.j.gavin.ispy.Domain.Story;
import com.ackerman.j.gavin.ispy.Domain.User;

/**
 * Created by gavin.ackerman on 2016-04-28.
 */
public interface StoryFactory {
    Story createStory(Long id,String name, String tag, String text,long userId,long image);

}
