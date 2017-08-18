package com.ackerman.j.gavin.ispy.Repositories;

import com.ackerman.j.gavin.ispy.Domain.User;
import com.ackerman.j.gavin.ispy.Factory.Repository;

/**
 * Created by gavin.ackerman on 2016-06-21.
 */
public interface UserRepository extends Repository<User,Long> {
    User findByEmail(String email);
}
