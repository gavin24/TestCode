package com.ackerman.j.gavin.ispy.Repositories;

import com.ackerman.j.gavin.ispy.Domain.Fame;
import com.ackerman.j.gavin.ispy.Domain.User;
import com.ackerman.j.gavin.ispy.Factory.Repository;

/**
 * Created by gavin.ackerman on 2016-06-22.
 */
public interface FameRepository extends Repository<Fame,Long> {
    Fame findByStoryId(Long id);
}
