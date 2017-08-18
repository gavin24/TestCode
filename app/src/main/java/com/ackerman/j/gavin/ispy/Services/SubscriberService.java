package com.ackerman.j.gavin.ispy.Services;

import com.ackerman.j.gavin.ispy.Domain.Image;
import com.ackerman.j.gavin.ispy.Domain.Subscriber;

import java.util.ArrayList;

/**
 * Created by gavin.ackerman on 2017-05-24.
 */

public interface SubscriberService {
    Subscriber addSubscriber(Subscriber animal);
    Subscriber updateSubscriber( Subscriber animal);
    Subscriber deleteSubscriber(Subscriber animal);
    Subscriber getSubscriber(Long d);
    ArrayList<Subscriber> getAllSubs( );
    void removeAllSubs();
}
