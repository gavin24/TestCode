package com.ackerman.j.gavin.ispy.Repositories;

import com.ackerman.j.gavin.ispy.Domain.Fame;
import com.ackerman.j.gavin.ispy.Domain.UserFame;
import com.ackerman.j.gavin.ispy.Factory.Repository;

/**
 * Created by gavin.ackerman on 2017-05-24.
 */

public interface UserFameRepository extends Repository<UserFame,Long> {
    UserFame findByStoryId(Long id);
}
