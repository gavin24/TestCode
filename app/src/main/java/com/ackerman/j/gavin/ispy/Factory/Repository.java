package com.ackerman.j.gavin.ispy.Factory;

import com.ackerman.j.gavin.ispy.Domain.User;

import java.util.ArrayList;
import java.util.Set;

/**
 * Created by gavin.ackerman on 2016-06-22.
 */
public interface Repository<E, ID> {
    E findById(ID id);

    E save(E entity);

    E update(E entity);

    E delete(E entity);

    ArrayList<E> findAll();

    int deleteAll();


}
