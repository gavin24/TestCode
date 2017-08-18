package com.ackerman.j.gavin.ispy.Factory;

import com.ackerman.j.gavin.ispy.Domain.UserTags;

/**
 * Created by gavin.ackerman on 2016-08-14.
 */
public interface UserTagsFactory {
    UserTags createTag(Long id, String name, long userId);
}
