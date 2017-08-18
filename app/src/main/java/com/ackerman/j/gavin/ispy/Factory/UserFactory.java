package com.ackerman.j.gavin.ispy.Factory;

import com.ackerman.j.gavin.ispy.Domain.User;

/**
 * Created by gavin.ackerman on 2016-06-21.
 */
public interface UserFactory {
    User createUser(Long id, String name, String surname, String email, String password);
}
