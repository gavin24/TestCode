package com.ackerman.j.gavin.ispy.Factory;

import com.ackerman.j.gavin.ispy.Domain.Fame;
import com.ackerman.j.gavin.ispy.Domain.Story;

/**
 * Created by gavin.ackerman on 2016-04-28.
 */
public interface FameFactory {
    Fame createFame(Long id,long storyId, long userId, int shares,int likes,int dislikes,int views);

}
