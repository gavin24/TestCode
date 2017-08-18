package com.ackerman.j.gavin.ispy.Services;

import com.ackerman.j.gavin.ispy.Domain.Tag;

import java.util.ArrayList;

/**
 * Created by gavin.ackerman on 2016-06-22.
 */
public interface TagService {
    Tag addTag(Tag animal);
    Tag updateTag( Tag animal);
    Tag deleteTag( Tag animal);
    Tag getTag(Long d);
    ArrayList<Tag> getAllTags( );
    void removeAllTags();
}
