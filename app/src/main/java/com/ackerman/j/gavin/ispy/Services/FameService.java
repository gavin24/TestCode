package com.ackerman.j.gavin.ispy.Services;

import com.ackerman.j.gavin.ispy.Domain.Fame;

import java.util.ArrayList;

/**
 * Created by gavin.ackerman on 2016-06-22.
 */
public interface FameService {
    Fame addFame(Fame animal);
    Fame updateFame( Fame animal);
    Fame deleteFame( Fame animal);
    Fame getFame(Long d);
    ArrayList<Fame> getAllFames( );
    ArrayList<Fame> getAllFames(Long id);
    void removeAllFames();
    Fame getFameByStoryId(Long Id);
}
