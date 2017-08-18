package com.ackerman.j.gavin.ispy.Factory;

import com.ackerman.j.gavin.ispy.Domain.Tag;
import com.ackerman.j.gavin.ispy.Domain.User;

/**
 * Created by gavin.ackerman on 2016-04-28.
 */
public interface TagFactory {
    Tag createTag(Long id,String name, String location);

}
